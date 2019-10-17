package com.zhengaobin.cms.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.zhengaobin.cms.entity.User;


/**
 * @author 郑奥斌
 *
 * 2019年10月16日
 */
public interface UserMapper {
	@Insert("insert into cms_user(username,password,gender,create_time) values(#{username},#{password},#{gender},now())")
	int add(User user);
	
	@Select("select id,username,password from cms_user where username=#{value} limit 1")
	User findByName(String username);
	
	
}
