package com.securejwt.request;

import java.io.Serializable;

public class PostApiRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5323871017361923965L;
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
	@Override
	public String toString() {
		return "PostApiRequest [userId=" + userId + ", body=" + body + ", title=" + title + "]";
	}
	
	
}
