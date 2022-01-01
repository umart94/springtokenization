package com.jwt.service;


import com.jwt.request.PostApiRequest;
import com.jwt.response.GenericResponse;

public interface GetApiService {
	public GenericResponse getResponse();
	public GenericResponse postResponse(PostApiRequest postApiRequest);

}
