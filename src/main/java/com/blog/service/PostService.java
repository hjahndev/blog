package com.blog.service;

import java.util.List;
import com.blog.vo.PostVO;

public interface PostService {
	
	public List<PostVO> getList();
	public PostVO get(Long pno);
	public boolean register(PostVO vo);
	public boolean modify(PostVO vo);
	public boolean remove(Long pno);
	
}