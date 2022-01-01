package com.jwt.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.config.JwtTokenUtil;
import com.jwt.entities.Credential;
import com.jwt.repositories.CredentialDao;
import com.jwt.request.AuthenticationRequest;
import com.jwt.response.AuthenticationResponse;
import com.jwt.response.GenericResponse;
import com.jwt.service.CredentialDetailsService;
import com.jwt.utils.Constants;
import com.jwt.utils.Constants.ResponseDescription;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService,CredentialDetailsService{

	
	
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private CredentialDao credentialDao;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		UserEntity user = userRepository.findByUsername(username);
//		if (user == null) {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
//		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//				new ArrayList<>());
//	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Credential user = (Credential) credentialDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
//	public UserEntity save(UserDTO user) {
//		UserEntity newUser = new UserEntity();
//		newUser.setUsername(user.getUsername());
//		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
//		return userRepository.save(newUser);
//	}

	@Override
	public GenericResponse<AuthenticationResponse> authenticatedToken(AuthenticationRequest authenticationRequest) {
		// TODO Auto-generated method stub
		GenericResponse<AuthenticationResponse> response = new GenericResponse<>();
		String responseCode = "";
		try {
		try { 
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			
			responseCode = "400";
			response = new GenericResponse<>(responseCode, "User is disabled");
			return response;
		} catch (BadCredentialsException e) {
			
			responseCode = "401";
			response = new GenericResponse<>(responseCode, "Unauthorized – Invalid credentials");
			return response;

		}
		
		final UserDetails userDetails = loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails.getUsername(),authenticationRequest.getChannel());
		final String expiry = jwtTokenUtil.getTokenExpiry(token);
		responseCode = "00";
		response = new GenericResponse<>(Constants.ResponseCodes.OK, new AuthenticationResponse(expiry, token));
		} catch(Exception e) {
			response = new GenericResponse<>(Constants.ResponseCodes.UNABLE_TO_PROCESS,
					ResponseDescription.UNABLE_TO_PROCESS);
			responseCode = "99";
			return response;
		} finally {
			//do audit logging
			return response;
		}
	}

	@Override
	public Credential saveCredential(AuthenticationRequest user) {
		// TODO Auto-generated method stub
		Credential newUser = new Credential();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setChannelName(user.getChannel());
		return (Credential) credentialDao.save(newUser);
	}
	
	



}
