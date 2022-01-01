package com.jwt.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetApiResponse implements Serializable{
	
	@JsonProperty("userId")
	private String userId;
	@JsonProperty("id")
	private String id;
	@JsonProperty("title")
	private String title;
	@JsonProperty("completed")
	private String completed;
	public GetApiResponse(String userId, String id, String title, String completed) {
		super();
		this.userId = userId;
		this.id = id;
		this.title = title;
		this.completed = completed;
	}
	public GetApiResponse() {
		
	}
	@Override
	public String toString() {
		return "GetApiResponse [userId=" + userId + ", id=" + id + ", title=" + title + ", completed=" + completed
				+ "]";
	}
	
	
	
}
