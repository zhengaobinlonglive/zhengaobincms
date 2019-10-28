package com.zhengaobin.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhengaobin.cms.entity.Article;
import com.zhengaobin.cms.entity.Comment;

/**
 * @author 郑奥斌
 *
 * 2019年10月17日
 */
public interface ArticleService {
	
	/**
	 * 
	 * @param chnId 频道id
	 * @param catId 分类id
	 * @param page  页码
	 * @return
	 */
	PageInfo<Article> list(Integer chnId, Integer catId, Integer page);

	/**
	 * 
	 * @param page
	 * @return
	 */
	PageInfo<Article> hostList( Integer page);

	/**
	 * 获取最新文章
	 * @param sum  获取的数目
	 * @return
	 */
	List<Article> last(int sum);

	/**
	 * 根据文章的主键获取文章的内容
	 * @param articleId
	 * @return
	 */
	Article findById(Integer articleId);

	int add(Article article);

	/**
	 *  根据用户id查找文章列表
	 * @param id 用户id
	 * @param page
	 * @return 
	 */
	PageInfo<Article> listArticleByUserId(Integer id, Integer page);

	/**
	 * 删除文章
	 * @param id  文章id
	 * @return
	 */
	int remove(Integer id);

	/**
	 *  修改文章
	 * @param article
	 * @return
	 */
	int update(Article article);

	/**
	 * 
	 * @param page 页码
	 * @param status 审核的状态
	 * @return
	 */
	PageInfo<Article> getAdminArticles(Integer page,Integer status);
	
/**
 *  审核文章
 * @param articleId
 * @param status 要审核的状态
 * @return
 */
	int updateStatus(Integer articleId, int status);

	/**
	 * 
	 *  修改热门
	 * @param articleId
	 * @param status
	 * @return
	 */
	int updateHot(Integer articleId, int status);
	
	/**
	 * 发表文章评论
	 * @param id
	 * @param articleId
	 * @param content
	 */
	void comment(Integer id, Integer articleId, String content);
	
	PageInfo<Comment> getCommentByArticleId(Integer articleId,Integer page);
	
	/**
	 * 增加文章点击次数
	 * @param id 文章id
	 * @return
	 */
	int addHits(Integer id);
	
	
}
