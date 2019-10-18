package com.zhengaobin.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhengaobin.cms.dao.ChannelMapper;
import com.zhengaobin.cms.entity.Channel;
import com.zhengaobin.cms.service.ChannelService;

/**
 * @author 郑奥斌
 *
 * 2019年10月17日
 */
@Service
public class ChannelServiceImpl implements ChannelService{
	
	@Autowired
	ChannelMapper channelMapper;
	
	
	/**
	 *  获取所有的频道（栏目）
	 * @return
	 */
	@Override
	public List<Channel> getAllChnls() {
		
		return channelMapper.listAll();
	}

}
