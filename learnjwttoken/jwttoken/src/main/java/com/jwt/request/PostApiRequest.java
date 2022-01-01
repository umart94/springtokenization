package com.jwt.request;

import java.io.Serializable;

public class PostApiRequest implements Serializable{

	public String userId;
	public String body;
	public String title;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public PostApiRequest(String userId, String body, String title) {
		super();
		this.userId = userId;
		this.body = body;
		this.title = title;
	}
	public PostApiRequest() {
		
	}
	
	
	
}
