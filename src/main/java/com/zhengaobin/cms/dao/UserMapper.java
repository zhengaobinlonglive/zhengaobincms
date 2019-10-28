package com.zhengaobin.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zhengaobin.cms.entity.User;


/**
 * @author 郑奥斌
 *
 * 2019年10月16日
 */
public interface UserMapper {
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@Insert("insert into cms_user(username,password,gender,create_time) values(#{username},#{password},#{gender},now())")
	int add(User user);
	
	/**
	 * 根据用户名查找
	 * @param username
	 * @return
	 */
	@Select("select id,username,password,locked,role from cms_user where username=#{value} limit 1")
	User findByName(String username);
	
	List<User> userList(@Param("name")String name);
	
	// 修改用户的状态
	@Update("UPDATE cms_user SET locked = #{locked} WHERE id = #{id}")
	int updatelocked(@Param("id")Integer id, @Param("locked")Integer locked);
	
	/**
	 * 查询
	 * @param locked
	 * @return
	 */
	List<User> list(@Param("locked")int locked);
	
	//上傳圖片
	@Update("update cms_user set head_picture=#{head_picture} where id=#{id}")
	int addHead_picture(User user);
	
}
