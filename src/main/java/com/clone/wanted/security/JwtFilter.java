package com.clone.wanted.security;

import com.clone.wanted.user.UserAuth;
import com.clone.wanted.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

	@Value("${jwt.secret-key}")
	private final String secretKey;
	private final UserService userService;

	// 실제 필터릴 로직
	// 토큰의 인증정보를 SecurityContext에 저장하는 역할 수행
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		// 인증과 관련된 Header를 받아온다.
		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		// header 에 유효한 값이 없을 때
		if(header == null || !header.startsWith("Bearer ")){
			// 토큰이 없는 경우 통과시킴
			filterChain.doFilter(request, response);
			return;
		}
		// header 가 정상적으로 있는 경우
		try{
			final String token = header.split(" ")[1].trim();

			// 토큰 유효성 검사
			if(TokenProvider.isExpired(token, secretKey)){
				log.error("key is expired");
				filterChain.doFilter(request, response);
				return;
			}
			// 토큰에서 userName 가져오기
			String userName = TokenProvider.getUserName(token, secretKey);

			// userName이 유효한지 다시 확인
			UserAuth user = userService.loadUserByUserName(userName);
			// 다시 request에 넣어서 controller로 보내주는 부분
			// 인증된 유저 정보를 넣어준다.
			// principal(누구), credentials(자격), authorities(권한)
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					// Todo
					user, null, user.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request ));
			// 넣어준 유저 정보를 다시 넣어준다.
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);  // request 정보 함께 전달

			// 로그인 정보를 전역 변수 thread local에 저장

		} catch (RuntimeException e){
			log.error("Error occurs while validating {}", e.toString());
			filterChain.doFilter(request, response);
			return;
		}

		filterChain.doFilter(request, response);
	}
}