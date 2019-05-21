package com.blog.service;

import java.util.List;

import com.blog.vo.CommentVO;

public interface CommentService {
	
	public List<CommentVO> getList(Long pno);
	public boolean register(CommentVO vo);
	public boolean modify(CommentVO vo);
	public boolean remove(Long cno);
}
