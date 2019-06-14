package com.blog.service;

import java.util.List;

import com.blog.vo.LinkVO;
import com.blog.vo.PageSettingVO;
import com.blog.vo.PostVO;

public interface PostService {
	
	public List<PostVO> getList();
	public List<PostVO> getListWithPage(PageSettingVO vo);
	public List<PostVO> getListWithSearch(PageSettingVO vo);
	public PostVO get(Long pno);
	public LinkVO getLink(Long pno);
	public boolean register(PostVO vo);
	public boolean modify(PostVO vo);
	public boolean remove(Long pno);
	public int getTotal(PageSettingVO vo);
	
}
