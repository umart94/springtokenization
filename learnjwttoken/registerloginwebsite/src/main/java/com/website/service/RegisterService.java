package com.website.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.website.model.RegistrationUser;
import com.website.model.User;
import com.website.response.GenericResponse;

public interface RegisterService {

	public GenericResponse registerUser(RegistrationUser user) throws JsonProcessingException;;
	public GenericResponse loginUser(User user) throws JsonProcessingException;;
}
