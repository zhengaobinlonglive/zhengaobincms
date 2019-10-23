package com.zhengaobin.cms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
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
	
	PageInfo<User> userList(Integer pageNum,String name);
	
	int updatelocked(Integer id,Integer locked);
}
