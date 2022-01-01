package com.jwt.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.jwt.entities.Credential;
import com.jwt.request.AuthenticationRequest;
import com.jwt.response.AuthenticationResponse;
import com.jwt.response.GenericResponse;

public interface CredentialDetailsService {

	public GenericResponse<AuthenticationResponse> authenticatedToken(AuthenticationRequest authenticationRequest);
	public UserDetails loadUserByUsername(String username);
	public Credential saveCredential(AuthenticationRequest user);
}
