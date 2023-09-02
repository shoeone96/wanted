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
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String token;
		try {
			if (header == null || !header.startsWith("Bearer ")) {
				log.error("Authorization Header does not start with Bearer {}", request.getRequestURI());
				chain.doFilter(request, response);
				return;
			} else {
				token = header.split(" ")[1].trim();
			}

			String userName = TokenProvider.getUserName(token, secretKey);
			UserAuth userDetails = userService.loadUserByUserName(userName);

			if (!TokenProvider.validate(token, userDetails.getUsername(), secretKey)) {
				chain.doFilter(request, response);
				return;
			}
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					userDetails, null,
					userDetails.getAuthorities()
			);
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (RuntimeException e) {
			chain.doFilter(request, response);
			return;
		}

		chain.doFilter(request, response);
	}
}