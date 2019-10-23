package com.zhengaobin.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

	@Override
	public PageInfo<User> userList(Integer pageNum,String name) {
		PageHelper.startPage(pageNum, 10);
		List<User> userList = userMapper.userList(name);
		return new PageInfo<>(userList);
	}

	@Override
	public int updatelocked(Integer id, Integer locked) {
		int i = userMapper.updatelocked(id, locked);
		return i;
	}

	
	
	
	
	
}
