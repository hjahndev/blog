package com.blog.vo;

import java.util.UUID;

public class TokenVO {
	private String token;
	private String email;
	
	public TokenVO() {
	}
	
	public TokenVO(String email) {
		this.email = email;
		makePassworkdToken();	
	}
	
	public TokenVO(String email, String token) {
		this.email = email;
		this.token = token;	
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private void makePassworkdToken() {
		token = UUID.randomUUID().toString();
	}
	@Override
	public String toString() {
		return "TokenVO [token=" + token + ", email=" + email + "]";
	}
}
