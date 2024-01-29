package cuscatlan.test.demo.services;

import cuscatlan.test.demo.model.dto.AuthRequest;
import cuscatlan.test.demo.model.dto.AuthResponse;

public interface SecurityService {

	public AuthResponse authClient(AuthRequest auth) throws Exception;

}
