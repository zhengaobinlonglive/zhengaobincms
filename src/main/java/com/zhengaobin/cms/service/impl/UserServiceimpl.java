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
	// 用户注册 
	@Override
	public int register(User user) {
		User findByName = userMapper.findByName(user.getUsername());
		if(findByName!=null){
			return -1;// 用户已经存在
		}
		user.setPassword(Md5Utils.md5(user.getPassword(),user.getUsername()));
		return userMapper.add(user);
	}
	// 用户登录
	@Override
	public User login(User user) {
		// 获取密码密文
		String md5 = Md5Utils.md5(user.getPassword(),user.getUsername());
		//根据用户名称查找用户
		User byName = userMapper.findByName(user.getUsername());
		//判断数据库中密码密文与与计算所得的密文是否相同
		if(byName!=null&&md5.equals(byName.getPassword())){
			//登录成功
			return byName;
		}
		//登录失败
		return null;
	}

	@Override
	public boolean checkUserExist(String username) {
		
		return null!=userMapper.findByName(username);
	}
	//獲取所以用戶
	@Override
	public PageInfo<User> userList(Integer pageNum,String name) {
		PageHelper.startPage(pageNum, 10);
		List<User> userList = userMapper.userList(name);
		return new PageInfo<>(userList);
	}
	//解封用戶
	@Override
	public int updatelocked(Integer id, Integer locked) {
		int i = userMapper.updatelocked(id, locked);
		return i;
	}

	@Override
	public int addHead_picture(User user) {
		// TODO Auto-generated method stub
		return userMapper.addHead_picture(user);
	}

	
	
	
	
	
}
