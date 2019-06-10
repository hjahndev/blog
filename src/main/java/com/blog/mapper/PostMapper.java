package com.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.blog.vo.LinkVO;
import com.blog.vo.PageSettingVO;
import com.blog.vo.PostVO;

public interface PostMapper {

	public List<PostVO> getList();

	public List<PostVO> getListWithPage(PageSettingVO vo);

	public PostVO get(Long pno);

	public LinkVO getLink(Long pno);

	public int insert(PostVO vo);

	public int update(PostVO vo);

	public int delete(Long pno);

	public int getTotal();

}
