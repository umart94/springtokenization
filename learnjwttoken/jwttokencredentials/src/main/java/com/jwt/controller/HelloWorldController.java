package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.request.PostApiRequest;
import com.jwt.response.GenericResponse;
import com.jwt.response.PostApiResponse;
import com.jwt.service.GetApiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;


@Api(tags = "Hello World Controller")
@RestController
@RequestMapping(path = ApiController.HELLO_WORLD_URL)
public class HelloWorldController {

	@Autowired
	private GetApiService getApiService;
	@ApiOperation(value = "Hello World", notes = "Hello World", authorizations = {@Authorization(value="jwtToken")})
	@RequestMapping(path = "/get", method = RequestMethod.GET)
	public GenericResponse hitGetApi() {
		GenericResponse response = getApiService.getResponse();
		return response;
		
			}
	
	
	@ApiOperation(value = "Hello World", notes = "Hello World", authorizations = {@Authorization(value="jwtToken")})
	@RequestMapping(path = "/post", method = RequestMethod.POST)
	public GenericResponse hitPostApi(@RequestBody PostApiRequest postApiRequest) {
		GenericResponse response = getApiService.postResponse(postApiRequest);
		return response;
		
	}
	
	
	
	@ApiOperation(value = "Hello World", notes = "Hello World", authorizations = {@Authorization(value="jwtToken")})
	@RequestMapping(path = "/helloworld", method = RequestMethod.POST)
	public String firstPage() {
		return "Hello World";
	}
}
