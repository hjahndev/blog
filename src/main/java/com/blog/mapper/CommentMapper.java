package com.blog.mapper;

import java.util.List;

import com.blog.vo.CommentVO;

public interface CommentMapper {

	public List<CommentVO> getList(Long pno);

	public int insert(CommentVO vo);

	public int update(CommentVO vo);

	public int delete(Long cno);

	public int deleteAll(Long pno);
	
	public int countComments(Long pno);

	public String checkPassword(CommentVO vo);
}
