package com.blog.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.blog.mapper.MemberMapper;
import com.blog.vo.MemberVO;

public class CustomUserDetailsService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	private MemberMapper memberMapper;
	
	public CustomUserDetailsService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) 
		throws UsernameNotFoundException {

		logger.warn("Load User By UserName : {}", userName);
		// userName means userid
		MemberVO vo = memberMapper.checkUser(userName);
		
		logger.warn("queried by member mapper: {}", vo);
		return vo == null ? null : new CustomUser(vo);
	}

}
