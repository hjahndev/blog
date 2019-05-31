package com.blog.vo;

public class AuthVO {

	private String email;	
	private String auth;
	public AuthVO(String email, String auth) {
		this.email = email;
		this.auth = auth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}	
	
	@Override
	public String toString() {
		return "AuthVO [email=" + email + ", auth=" + auth + "]";
	}
}
