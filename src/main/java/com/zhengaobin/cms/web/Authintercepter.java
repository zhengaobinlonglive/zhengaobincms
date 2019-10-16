package com.zhengaobin.cms.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;
import com.zhengaobin.cms.comon.ConstClass;

/**
 * @author 郑奥斌
 *
 * 2019年10月16日
 */
public class Authintercepter implements HandlerInterceptor{
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response
			,Object handler) throws ServletException, IOException{
			
			User loginUser = (User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
			if(loginUser==null){
				request.getRequestDispatcher("/user/login").forward(request, response);
				return false;
			}
		return true;
	}
	
	
}
