package com.zhengaobin.cms.service;

import java.util.List;

import com.zhengaobin.cms.entity.Comment;

/**
 * @author 郑奥斌
 *
 * 2019年10月28日
 */
public interface CommentService {
	
	List<Comment> commnentlist(Integer articleId);

	/**
	 * 发布评论
	 * @param id
	 * @param articleId
	 * @param content
	 */
	void comment(Integer id, Integer articleId, String content);
	
}
