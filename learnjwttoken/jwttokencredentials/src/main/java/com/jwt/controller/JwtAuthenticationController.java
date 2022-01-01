package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.config.JwtTokenUtil;
import com.jwt.model.UserDTO;
import com.jwt.request.AuthenticationRequest;
import com.jwt.response.AuthenticationResponse;
import com.jwt.response.GenericResponse;
import com.jwt.service.impl.ChannelServiceImpl;
import com.jwt.service.impl.JwtUserDetailsServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Authentication Controller")
@RestController
@CrossOrigin
public class JwtAuthenticationController {
	
	
	@Autowired
	private JwtUserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private ChannelServiceImpl channelServiceImpl;

	@ApiOperation(value = "Authenticate", notes = "Authenticate Function")
	@RequestMapping(value = ApiController.AUTHENTICATE_URL, method = RequestMethod.POST)
	public GenericResponse<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		//authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

//		final UserDetails userDetails = userDetailsService
//				.loadUserByUsername(authenticationRequest.getUsername());

	//	final String token = jwtTokenUtil.generateToken(userDetails);
	//	return ResponseEntity.ok(new AuthenticationResponse(token));
		
		
		return userDetailsService.authenticatedToken(authenticationRequest);
	}
	
	@ApiOperation(value = "Register", notes = "Register Function")
	@RequestMapping(value = ApiController.REGISTER_URL, method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody AuthenticationRequest user) throws Exception {
		
		userDetailsService.saveCredential(user);
		channelServiceImpl.saveCustomerChannel(user);
		return ResponseEntity.ok(new AuthenticationResponse("00", "SUCCESS"));
	}

//	private void authenticate(String username, String password) throws Exception {
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		} catch (DisabledException e) {
//			throw new Exception("USER_DISABLED", e);
//		} catch (BadCredentialsException e) {
//			throw new Exception("INVALID_CREDENTIALS", e);
//		}
//	}
}