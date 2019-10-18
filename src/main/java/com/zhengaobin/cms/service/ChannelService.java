package com.zhengaobin.cms.service;

import java.util.List;

import com.zhengaobin.cms.entity.Channel;

/**
 * @author 郑奥斌
 *
 * 2019年10月17日
 */
public interface ChannelService {
	
	/**
	 *  获取所有的频道（栏目）
	 * @return
	 */
	List<Channel> getAllChnls();
	
}
