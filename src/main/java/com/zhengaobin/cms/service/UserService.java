package com.zhengaobin.cms.service;

import com.zhengaobin.cms.entity.User;

/**
 * @author 郑奥斌
 *
 * 2019年10月16日
 */
public interface UserService {
	
	int register(User user);
	User login(User user);
	
	boolean checkUserExist(String username);
	
}
