package com.blog.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.mapper.CommentMapper;
import com.blog.vo.CommentVO;

@Service
public class CommentServiceImpl implements CommentService {
	private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
	private CommentMapper mapper;
	@Autowired
	private PasswordEncoder pwencoder;

	public CommentServiceImpl(CommentMapper mapper) {
		this.mapper = mapper;
	}
	@Override
	public List<CommentVO> getList(Long pno) {
		return mapper.getList(pno);
	}

	@Override
	public boolean register(CommentVO vo) {
		logger.info("댓글 입력: {}", vo);
		vo.encodePassword(pwencoder);
		return mapper.insert(vo) == 1;
	}

	@Override
	public boolean modify(CommentVO vo) {
		return mapper.update(vo) == 1;
	}

	@Override
	public boolean remove(Long cno) {
		return mapper.delete(cno) == 1;
	}

}
