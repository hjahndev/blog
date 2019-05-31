package com.blog.service;

import com.blog.vo.MemberVO;

public interface MemberService {

	public boolean join(MemberVO vo);

	public boolean forgotPassword(MemberVO vo);

	public int checkNickname(String nickname);

	public int checkEmail(String email);

}
