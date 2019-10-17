package com.zhengaobin.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhengaobin.cms.dao.UserMapper;
import com.zhengaobin.cms.entity.User;
import com.zhengaobin.cms.service.UserService;
import com.zhengaobin.utils.Md5Utils;

/**
 * @author 郑奥斌
 *
 * 2019年10月16日
 */
@Service
public class UserServiceimpl implements UserService{
	@Autowired
	UserMapper userMapper;

	@Override
	public int register(User user) {
		User findByName = userMapper.findByName(user.getUsername());
		if(findByName!=null){
			return -1;
		}
		user.setPassword(Md5Utils.md5(user.getPassword(),user.getUsername()));
		return userMapper.add(user);
	}

	@Override
	public User login(User user) {
		String md5 = Md5Utils.md5(user.getPassword(),user.getUsername());
		User byName = userMapper.findByName(user.getUsername());
		if(byName!=null&&md5.equals(byName.getPassword())){
			return byName;
		}
		return null;
	}

	@Override
	public boolean checkUserExist(String username) {
		
		return null!=userMapper.findByName(username);
	}
	
	
	
}
