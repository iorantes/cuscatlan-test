package cuscatlan.test.demo.config.security;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class JwtTokenAuthenticationFilter extends GenericFilterBean {

	public static final String HEADER_PREFIX = "Bearer ";

	private JwtUtil jwtTokenProvider;

	public JwtTokenAuthenticationFilter(JwtUtil jwtTokenProvider) {
		super();
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {

		String token = resolveToken((HttpServletRequest) req);

		try {
			if (token != null && jwtTokenProvider.validateToken(token)) {
				Authentication auth = jwtTokenProvider.getAuthentication(token);

				if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
					SecurityContext context = SecurityContextHolder.createEmptyContext();
					context.setAuthentication(auth);
					SecurityContextHolder.setContext(context);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		filterChain.doFilter(req, res);
	}

	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
			return bearerToken.substring(7);
		}
		return null;
	}

}
