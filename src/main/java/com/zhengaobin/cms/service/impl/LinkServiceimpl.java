package com.zhengaobin.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhengaobin.cms.dao.LinkMapper;
import com.zhengaobin.cms.entity.Link;
import com.zhengaobin.cms.service.LinkService;

/**
 * @author 郑奥斌
 *
 * 2019年10月27日
 */
@Service
public class LinkServiceimpl implements LinkService{
	
	@Autowired
	private LinkMapper linkMapper;

	@Override
	public List<Link> linklist() {
		// TODO Auto-generated method stub
		return linkMapper.linklist();
	}

	@Override
	public int addlink(Link link) {
		// TODO Auto-generated method stub
		return linkMapper.addlink(link);
	}
	//友情链接的删除
	@Override
	public int deletelink(Integer id) {
		// TODO Auto-generated method stub
		return linkMapper.deletelink(id);
	}

	/*@Override
	public int linkupdate(Integer id) {
		// TODO Auto-generated method stub
		return linkMapper.linkupdate(id);
	}
	*/
	
}
