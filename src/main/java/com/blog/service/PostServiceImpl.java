package com.blog.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.mapper.CommentMapper;
import com.blog.mapper.PostMapper;
import com.blog.vo.LinkVO;
import com.blog.vo.PageSettingVO;
import com.blog.vo.PostVO;

@Service
public class PostServiceImpl implements PostService {
	private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
	private PostMapper mapper;
	@Autowired
	private CommentMapper commentMapper;
	
	public PostServiceImpl(PostMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<PostVO> getList() {
		return mapper.getList();
	}

	@Override
	public List<PostVO> getListWithPage(PageSettingVO vo) {
		return mapper.getListWithPage(vo);
	}
	
	@Override
	public PostVO get(Long pno) {
		return mapper.get(pno);
	}

	@Override
	public boolean register(PostVO vo) {
		return mapper.insert(vo) == 1;
	}

	@Override
	public boolean modify(PostVO vo) {
		return mapper.update(vo) == 1;
	}

	@Transactional
	@Override
	public boolean remove(Long pno) {
		int commentNum = commentMapper.countComments(pno);
		logger.info("post service: {}, 댓글 개수: {}", pno, commentNum);
		if(commentNum > 0) {
			commentMapper.deleteAll(pno);
		}
		return mapper.delete(pno) == 1;
	}

/*	@Override
	public int getTotal() {
		return mapper.getTotal();
	}*/
	@Override
	public int getTotal(PageSettingVO vo) {
		return mapper.getTotal(vo);
	}

	@Override
	public LinkVO getLink(Long pno) {
		return mapper.getLink(pno);
	}

	@Override
	public List<PostVO> getListWithSearch(PageSettingVO vo) {
		logger.info("getListWithSearch: {}", vo);
		return mapper.getListWithSearch(vo);
	}

}
