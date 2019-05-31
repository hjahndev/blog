package com.blog.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberVO {

	private String email;
	private String password;
	private String nickname;
	private List<AuthVO> authList;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public List<AuthVO> getAuthList() {
		return authList;
	}
	public void setAuthList(List<AuthVO> authList) {
		this.authList = authList;
	}
	public void addAuth(String auth) {
		if(authList == null) {
			authList = new ArrayList<>();
		}
		authList.add(new AuthVO(email, auth));
	}
	public void encodePassword(PasswordEncoder pwencoder) {
		setPassword(pwencoder.encode(password));
	}
	
	@Override
	public String toString() {
		return "MemberVO [email=" + email + ", password=" + password + ", nickname=" + nickname + ", authList="
				+ authList + "]";
	}
	
}
