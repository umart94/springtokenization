package com.website.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RegistrationUser extends User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 36083739479599068L;
	
	@NotNull(message = "role may not be null")
	@NotEmpty(message = "role may not be empty")
	@NotBlank(message = "role may not be blank")
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}