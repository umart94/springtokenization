package com.jwt.request;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8396436785949897705L;
	@Override
	public String toString() {
		return "AuthenticationRequest [username=" + username + ", password=" + password + ", channel=" + channel + "]";
	}

	private String username;
	private String password;
	private String channel;

	public AuthenticationRequest() {

	}

	public AuthenticationRequest(String username, String password,String channel) {
		this.setUsername(username);
		this.setPassword(password);
		this.setChannel(channel);
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}