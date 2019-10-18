package com.zhengaobin.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhengaobin.cms.dao.CatMapper;
import com.zhengaobin.cms.entity.Cat;
import com.zhengaobin.cms.service.CatService;

/**
 * @author 郑奥斌
 *
 * 2019年10月17日
 */
@Service
public class CatServiceImpl implements CatService{
	
	@Autowired
	CatMapper catMapper;
	
	@Override
	public List<Cat> getListByChnlId(Integer id) {
		
		return catMapper.selectByChnlId(id);
	}

}
