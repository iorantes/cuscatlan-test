package cuscatlan.test.demo.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import cuscatlan.test.demo.config.security.JwtUtil;
import cuscatlan.test.demo.model.dto.AuthRequest;
import cuscatlan.test.demo.model.dto.AuthResponse;
import cuscatlan.test.demo.services.SecurityService;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public AuthResponse authClient(AuthRequest auth) throws Exception {
		try {

			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));

			String token = jwtUtil.generateToken(authentication);
			return new AuthResponse(token);
			
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
