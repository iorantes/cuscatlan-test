package cuscatlan.test.demo.config.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import static java.util.stream.Collectors.joining;

@Service
public class JwtUtil {

	private static final String AUTHORITIES_KEY = "roles";

	private int jwtExpirationInMs;
	private String secret;

	@Value("${jwt.secret}")
	public void setSecret(String secret) {
		this.secret = secret;
	}

	private SecretKey secretKey;

	@PostConstruct
	public void init() {
		var secret = Base64.getEncoder().encodeToString(this.secret.getBytes());
		this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}

	@Value("${jwt.expirationDateInMs}")
	public void setJwtExpirationInMs(int jwtExpirationInMs) {
		this.jwtExpirationInMs = jwtExpirationInMs;
	}

	public String generateToken(Authentication authentication) {

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String username = authentication.getName();

		var claimsBuilder = Jwts.claims().subject(username);

		if (!authorities.isEmpty()) {
			claimsBuilder.add(AUTHORITIES_KEY,
					authorities.stream().map(GrantedAuthority::getAuthority).collect(joining(",")));
		}

		var claims = claimsBuilder.build();
		return doGenerateToken(claims, username);
	}

	private String doGenerateToken(Claims claims, String subject) {
		return Jwts.builder().claims(claims).subject(subject).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + jwtExpirationInMs)).signWith(secretKey).compact();
	}

	public boolean validateToken(String token) throws Exception {
		try {
			Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new Exception("Invalid JWT token: {}", e);
		}
	}

	public Authentication getAuthentication(String token) {
		Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();

		Object authoritiesClaim = claims.get(AUTHORITIES_KEY);

		Collection<? extends GrantedAuthority> authorities = authoritiesClaim == null ? AuthorityUtils.NO_AUTHORITIES
				: AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesClaim.toString());

		User principal = new User(claims.getSubject(), "", authorities);

		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}

}
