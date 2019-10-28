package com.zhengaobin.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhengaobin.cms.comon.ConstClass;
import com.zhengaobin.cms.comon.ResultMsg;
import com.zhengaobin.cms.entity.Comment;
import com.zhengaobin.cms.entity.User;
import com.zhengaobin.cms.service.CommentService;

/**
 * @author 郑奥斌
 *
 * 2019年10月28日
 */
@Controller
@RequestMapping("commnent")
public class CommnentController {
	@Autowired
	CommentService commnentService;
	//添加评论
	@RequestMapping("commnentinsert")
	@ResponseBody
	public ResultMsg comment1(HttpServletRequest request,Integer articleId,String content) {
		User loginUser = (User)request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		if(loginUser==null) {
			return new ResultMsg(2, "用户尚未登录", "");		
		}
		commnentService.comment(loginUser.getId(),articleId,content);
		return new ResultMsg(1, "发布成功", "");
	}

	@RequestMapping("commnentlist")
	public String getComment(HttpServletRequest request ,@Param("userId")Integer articleId) {
		System.out.println("123");
		List<Comment> comments =commnentService.commnentlist(articleId);
		for (Comment commnent : comments) {
			System.out.println(commnent);
		}
		request.setAttribute("comments", comments);
		return "article/clist";
		
	}
}
