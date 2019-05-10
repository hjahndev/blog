package com.blog.mapper;

import java.util.List;

import com.blog.vo.PostVO;

public interface PostMapper {

	public List<PostVO> getList();

	public PostVO get(Long pno);

	public int insert(PostVO vo);

	public int update(PostVO vo);

	public int delete(Long pno);

}
