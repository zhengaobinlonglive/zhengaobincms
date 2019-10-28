package com.zhengaobin.cms.service;

import java.util.List;

import com.zhengaobin.cms.entity.Link;

/**
 * @author 郑奥斌
 *
 * 2019年10月27日
 */
public interface LinkService {
	
	//获取友情链接
		List<Link> linklist();
		//获取友情添加
		int addlink(Link link);
		//友情链接的删除
		int deletelink(Integer id);

	/*	int linkupdate(Integer id);*/
	
}
