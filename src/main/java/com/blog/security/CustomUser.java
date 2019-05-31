package com.blog.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.blog.vo.MemberVO;


public class CustomUser extends User {
	private static final long serialVersionUID = 1L;
	private MemberVO member;
	
	public CustomUser(MemberVO vo) {
		super(vo.getEmail(), vo.getPassword(), vo.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth())).
				collect(Collectors.toList()));
		
		this.member = vo;
	}

	public MemberVO getMember() {
		return member;
	}
}
