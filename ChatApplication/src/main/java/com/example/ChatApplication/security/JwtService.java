package com.example.ChatApplication.security;

import java.security.Key;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private String secretKey = "bXlTdXBlclNlY3JldEtleUZvckpXVG15U3VwZXJTZWNyZXRLZXk=";
	

//	private String getSercretKey() {
//		// TODO Auto-generated method stub
//		try {
//			KeyGenerator key = KeyGenerator.getInstance("HmacSHA256");
//			SecretKey secret = key.generateKey();
//			return Base64.getEncoder().encodeToString(secret.getEncoded());
//		}catch(Exception e) {
//			throw new RuntimeException("Error in generatning key");
//		}
//		
//	}

	public String generateToken(String name) {
		
		Map<String,Object> claims = new HashMap<>();
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(name)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ (1000 * 60 * 100)))
				.signWith(getSignKey(),SignatureAlgorithm.HS256)
				.compact()
				;
		
	}

	private Key getSignKey() {
		// TODO Auto-generated method stub
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String extractUsername(String token) {
		// TODO Auto-generated method stub
		
		return extractClaim(token,Claims::getSubject);
	}

	private <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
		// TODO Auto-generated method stub
		final Claims claims = extractAllClaims(token);
		
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		// TODO Auto-generated method stub
		String username = extractUsername(token);
		
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token,Claims::getExpiration);
	}
	
}

