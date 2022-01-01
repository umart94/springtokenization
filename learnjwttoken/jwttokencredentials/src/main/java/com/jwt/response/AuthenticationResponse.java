package com.jwt.response;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 8225334744231345997L;
	private final String expiry;
	private final String token;
	

	public AuthenticationResponse(String expiry,String token) {
		this.expiry = expiry;
		this.token = token;
	}

	@Override
	public String toString() {
		return "AuthenticationResponse [expiry=" + expiry + ", token=" + token + "]";
	}

	public String getExpiry() {
		return expiry;
	}

	public String getToken() {
		return this.token;
	}
	
}