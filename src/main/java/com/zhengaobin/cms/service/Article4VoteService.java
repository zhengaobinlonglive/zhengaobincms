package com.zhengaobin.cms.service;


import java.util.List;

import com.zhengaobin.cms.entity.Article4Vote;
import com.zhengaobin.cms.entity.VoteStatic;

/**
 * @author 郑奥斌
 *
 * 2019年10月28日
 */
public interface Article4VoteService {
	
	int publish(Article4Vote av);
	
	List<Article4Vote>  list();
	
	Article4Vote  findById(Integer id);
	
	int vote(Integer articleId,Character option);
	//int vote(Integer userId, Integer articleId,Character option);
	
	List<VoteStatic> getVoteStatics(Integer articleId);
	
}
