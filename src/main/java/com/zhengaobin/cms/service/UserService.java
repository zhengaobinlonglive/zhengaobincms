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
	//判断用户名是否已经被占用
	int register(User user);
	//注册用户
	User login(User user);
	//登录
	boolean checkUserExist(String username);
	//用户管理//用户管理 禁用和解封
	PageInfo<User> userList(Integer pageNum,String name);
	//修改禁止该用户
	int updatelocked(Integer id,Integer locked);
	
	//个人主要上传头像
	int addHead_picture(User user);
	
	
	
}
