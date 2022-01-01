package com.consume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.consume.service.ConsumeService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class TestController {
	
	@Autowired
	private ConsumeService consumeService;
	
	@RequestMapping(value = "/getResponse", method = RequestMethod.GET)
	public String getResponse() throws JsonProcessingException {
		return consumeService.getResponse();
	}
	

}
