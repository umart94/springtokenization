package com.consume.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.consume.model.RegistrationUser;
import com.consume.model.ResponseToken;
import com.consume.model.User;
import com.consume.service.ConsumeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ConsumeServiceImpl implements ConsumeService{

	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String REGISTRATION_URL = "https://localhost:8443/api/v1/register";
	private static final String AUTHENTICATION_URL = "https://localhost:8443/api/v1/authenticate";
	private static final String HELLO_URL = "https://localhost:8443/api/v1/admin/helloadmin";
	private static final String REFRESH_TOKEN = "https://localhost:8443/refreshtoken";
	
	private String token;
	

	@Override
	public String getResponse() throws JsonProcessingException{
		String response = null;
		// create user registration object
		RegistrationUser registrationUser = getRegistrationUser();
		// convert the user registration object to JSON
		String registrationBody = getBody(registrationUser);
		// create headers specifying that it is JSON request
		HttpHeaders registrationHeaders = getHeaders();
		HttpEntity<String> registrationEntity = new HttpEntity<String>(registrationBody, registrationHeaders);

		try {
			// Register User
			ResponseEntity<String> registrationResponse = restTemplate.exchange(REGISTRATION_URL, HttpMethod.POST,
					registrationEntity, String.class);
			   // if the registration is successful		
			System.out.println(registrationResponse.getStatusCode().equals(HttpStatus.OK));
			
			if (registrationResponse.getStatusCode().equals(HttpStatus.OK)) {

				// create user authentication object
				User authenticationUser = getAuthenticationUser();
				// convert the user authentication object to JSON
				String authenticationBody = getBody(authenticationUser);
				// create headers specifying that it is JSON request
				HttpHeaders authenticationHeaders = getHeaders();
				HttpEntity<String> authenticationEntity = new HttpEntity<String>(authenticationBody,
						authenticationHeaders);

				// Authenticate User and get JWT
				ResponseEntity<ResponseToken> authenticationResponse = restTemplate.exchange(AUTHENTICATION_URL,
						HttpMethod.POST, authenticationEntity, ResponseToken.class);
					
				// if the authentication is successful		
				System.out.println(authenticationResponse.getStatusCode().equals(HttpStatus.OK));
				// if the authentication is successful		
				if (authenticationResponse.getStatusCode().equals(HttpStatus.OK)) {
					token = "Bearer " + authenticationResponse.getBody().getToken();
					response = getData();
				}
				
			}
			
				
		}catch(Exception ex)
		{
			// check if exception is due to ExpiredJwtException
						if (ex.getMessage().contains("io.jsonwebtoken.ExpiredJwtException")) {
							// Refresh Token
							refreshToken();
							// try again with refresh token
							response = getData();
						}else {
							System.out.println(ex);
						}
			
		}
		return response;
	}
	
	private void refreshToken() {
		HttpHeaders headers = getHeaders();
		headers.set("Authorization", token);
		headers.set("isRefreshToken", "true");
		HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);
		// Use Token to get Response
		ResponseEntity<ResponseToken> refreshTokenResponse = restTemplate.exchange(REFRESH_TOKEN, HttpMethod.GET, jwtEntity,
				ResponseToken.class);
		if (refreshTokenResponse.getStatusCode().equals(HttpStatus.OK)) {
			token = "Bearer " +refreshTokenResponse.getBody().getToken();
		}

	}
	
	private String getData() {
		String response = null;

		HttpHeaders headers = getHeaders();
		headers.set("Authorization", token);
		HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);
		// Use Token to get Response
		ResponseEntity<String> helloResponse = restTemplate.exchange(HELLO_URL, HttpMethod.GET, jwtEntity,
				String.class);
		if (helloResponse.getStatusCode().equals(HttpStatus.OK)) {
			response = helloResponse.getBody();
		}
		return response;

	}
	
	
	
	
	private RegistrationUser getRegistrationUser() {
		RegistrationUser user = new RegistrationUser();
		user.setUsername("tariq");
		user.setPassword("ABC@#$321");
		user.setRole("ROLE_ADMIN");
		return user;
	}

	private User getAuthenticationUser() {
		User user = new User();
		user.setUsername("tariq");
		user.setPassword("ABC@#$321");
		return user;
	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}

	private String getBody(final User user) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(user);
	}
}
