package com.blog.service;

import com.blog.vo.MemberVO;
import com.blog.vo.TokenVO;

public interface MemberService {

	public boolean join(MemberVO vo);

	public boolean forgotPassword(String email);

	public boolean checkNickname(String nickname);

	public boolean checkEmail(String email);

	public boolean checkToken(TokenVO vo);

	public boolean resetPassword(MemberVO vo, TokenVO token);

	public boolean cancelMembership(MemberVO vo);
}
