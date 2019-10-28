package com.zhengaobin.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhengaobin.cms.dao.CommnentMapper;
import com.zhengaobin.cms.entity.Comment;
import com.zhengaobin.cms.service.CommentService;

/**
 * @author 郑奥斌
 *
 * 2019年10月28日
 */
@Service
public class CommnentServiceImpl implements CommentService{
	
	@Autowired
	CommnentMapper commnentMapper;
	

	

	@Override
	public List<Comment> commnentlist(Integer articleId) {
		// TODO Auto-generated method stub
		return commnentMapper.commnentlistByUser(articleId);
	}

	@Override
	public void comment(Integer userId, Integer articleId, String content) {
		// TODO Auto-generated method stub
		Comment commnent = new Comment(articleId,userId,content);
		commnentMapper.addCommnent(commnent);
		commnentMapper.increaseCommentCnt(articleId);
	}
	
	
	
}
