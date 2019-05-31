package com.blog.mapper;

import com.blog.vo.AuthVO;
import com.blog.vo.MemberVO;

public interface MemberMapper {

	public MemberVO checkUser(String userid);

	public int join(MemberVO vo);

	public int addAuth(AuthVO authVO);

	public int checkNickname(String nickname);

	public int checkEmail(String email);
}
