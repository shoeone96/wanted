package com.clone.wanted.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class TokenProvider{

	public static Boolean validate(String token, String userName, String key) {
		String usernameByToken = getUserName(token, key);
		return usernameByToken.equals(userName) && !isExpired(token, key);
	}
	public static String getUserName(String token, String key){
		return extractClaims(token, key).get("email", String.class);
	}

	// 현재 시간보다 만료 시간이 긴지 아닌지 여부로 토큰 만료 시간 확인
	public static boolean isExpired(String token, String key){
		Date expiredDate = extractClaims(token, key).getExpiration();
		return expiredDate.before(new Date());
	}

	// token 에서 Claims 부분 추출하는 메소드
	private static Claims extractClaims(String token, String key){
		return Jwts.parserBuilder().setSigningKey(getKey(key))
				.build().parseClaimsJws(token).getBody();
	}

	// token username 넣고, key는 userName 넣은 값을 암호화, 유효 기간
	public static String generateToken(String email, String key, long expiredTimeMs){
		Claims claims = Jwts.claims();
		claims.put("email", email);

		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiredTimeMs))
				.signWith(getKey(key), SignatureAlgorithm.HS256)
				.compact();
	}

	private static Key getKey(String key){
		byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}