package com.blog.mapper;

import com.blog.vo.AuthVO;
import com.blog.vo.MemberVO;
import com.blog.vo.TokenVO;

public interface MemberMapper {

	public MemberVO checkUser(String userid);

	public int join(MemberVO vo);

	public int cancelMembership(MemberVO vo);

	public int addAuth(AuthVO authVO);

	public int deleteAuth(MemberVO vo);

	public int checkNickname(String nickname);

	public int checkEmail(String email);

	public int checkToken(TokenVO vo);

	public int deleteToken(TokenVO token);

	public int addToken(TokenVO token);

	public int resetPassword(MemberVO vo);
}
