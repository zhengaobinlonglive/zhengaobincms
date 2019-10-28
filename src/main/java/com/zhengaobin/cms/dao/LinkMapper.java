package com.zhengaobin.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.zhengaobin.cms.entity.Link;

/**
 * @author 郑奥斌
 *
 * 2019年10月27日
 */
public interface LinkMapper {
	
	@Select("select * from cms_link")
	List<Link> linklist();
	
	@Insert("insert into cms_link (http,name) VALUES(#{http},#{name})" )
	int addlink(Link link);

	//友情链接的删除
	@Delete("delete from cms_link where id =#{id}")
	int deletelink(Integer id);

	
	
	/*int linkupdate(Integer id);*/
	
}
