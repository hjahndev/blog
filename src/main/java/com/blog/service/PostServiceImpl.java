package com.blog.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.blog.mapper.PostMapper;
import com.blog.vo.PageSettingVO;
import com.blog.vo.PostVO;

@Service
public class PostServiceImpl implements PostService {
	private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
	private PostMapper mapper;
	
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

	@Override
	public boolean remove(Long pno) {
		return mapper.delete(pno) == 1;
	}

	@Override
	public int getTotal() {
		return mapper.getTotal();
	}
}
