package com.zhengaobin.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhengaobin.cms.dao.ArticleMapper;
import com.zhengaobin.cms.entity.Article;
import com.zhengaobin.cms.service.ArticleService;

/**
 * @author 郑奥斌
 *
 * 2019年10月17日
 */
@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	ArticleMapper articleMapper;
	
	@Override
	public PageInfo<Article> list(Integer chnId, Integer catId, Integer page) {
		//设置页码
		PageHelper.startPage(page,10);
		//查询指定页码数据 并返回页面信息
		return new PageInfo(articleMapper.list(chnId, catId));
	}

	@Override
	public PageInfo<Article> hostList(Integer page) {
		//设置页码
		PageHelper.startPage(page, 10);
		//查询指定页码数据 并返回页面信息
		return new PageInfo(articleMapper.listHot()) ;
	}

	@Override
	public List<Article> last(int sum) {
		
		return articleMapper.listLast(sum);
	}

	@Override
	public Article findById(Integer articleId) {
		
		return articleMapper.findById(articleId);
	}

	@Override
	public int add(Article article) {
		
		return articleMapper.add(article);
	}

	@Override
	public PageInfo<Article> listArticleByUserId(Integer userId, Integer page) {
	
		return new PageInfo<Article>(articleMapper.listByUserId(userId));
	}

	@Override
	public int remove(Integer id) {
		
		return articleMapper.deleteById(id);
	}

	@Override
	public int update(Article article) {
		
		return articleMapper.update(article);
	}

}
