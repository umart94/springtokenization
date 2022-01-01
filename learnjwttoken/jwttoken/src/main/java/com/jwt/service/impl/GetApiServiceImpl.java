package com.jwt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jwt.request.PostApiRequest;
import com.jwt.response.GenericResponse;
import com.jwt.response.GetApiResponse;
import com.jwt.response.PostApiResponse;
import com.jwt.service.GetApiService;
import com.jwt.utils.Constants;

@Service
public class GetApiServiceImpl implements GetApiService{

	
	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	@Override
	public GenericResponse getResponse() {
		// TODO Auto-generated method stub
		ResponseEntity<List<GetApiResponse>> todosResponse =
		        restTemplate.exchange("https://jsonplaceholder.typicode.com/todos/",
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<GetApiResponse>>() {
		            });
		List<GetApiResponse> todos = todosResponse.getBody();
		GenericResponse todosfinalGetResponse = new GenericResponse<>(Constants.ResponseCodes.OK, Constants.ResponseDescription.OK,todos);
		return todosfinalGetResponse;
		
	}
	@Override
	public GenericResponse postResponse(PostApiRequest postApiRequest) {
		// TODO Auto-generated method stub
		HttpHeaders headers = new HttpHeaders();
	   // headers.set("X-COM-PERSIST", "true");    
		headers.setContentType(MediaType.APPLICATION_JSON);
	 
	    HttpEntity<PostApiRequest> request = new HttpEntity<>(postApiRequest, headers);
	     
	    ResponseEntity<PostApiResponse> result = restTemplate.postForEntity("https://jsonplaceholder.typicode.com/posts", request, PostApiResponse.class);
	    System.out.println(result);
	    GenericResponse genericResult = new GenericResponse<>(Constants.ResponseCodes.OK,Constants.ResponseDescription.OK,result.getBody());
	    
	    return genericResult;
	}

	

}
