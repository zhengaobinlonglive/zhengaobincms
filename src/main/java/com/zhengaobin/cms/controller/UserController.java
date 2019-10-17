package com.zhengaobin.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhengaobin.cms.comon.ConstClass;
import com.zhengaobin.cms.entity.User;
import com.zhengaobin.cms.service.UserService;

/**
 * @author 郑奥斌
 *
 * 2019年10月16日
 */
@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping("register")
	public String register(){
		
		return "user/register";
	}
	
	@RequestMapping("index")
	public String index(){
		return "user/index";
	}
	
	@RequestMapping("checkExist")
	@ResponseBody
	public boolean checkExist(String username){
		return !userService.checkUserExist(username);
		
	}
	
	@PostMapping("register")
	public String register(HttpServletRequest request,
			@Validated User user,
			BindingResult errorResult){
		if(errorResult.hasErrors()){
			return "user/register";
		}
		int result=userService.register(user);
		if(result>0){
			return "redirect:login";
		}else {
			request.setAttribute("errorMsg","系统错误，请稍后重试");
			return "user/register";
		}
		
	}
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login(){
		return "user/login";
	}
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute(ConstClass.SESSION_USER_KEY);
		return "user/login";
	}
	
	@PostMapping("login")
	public String login(HttpServletRequest request,@Validated User user,
			BindingResult erroResult){
		if(erroResult.hasErrors()){
			return "login";
		}
		User login = userService.login(user);
		if(login==null){
			request.setAttribute("errMsg","用户名密码错误");
			return "user/login";
		}else {
			request.getSession().setAttribute(ConstClass.SESSION_USER_KEY,login);
			if(login.getRole()==ConstClass.USER_ROLE_GENERAL){
				return "redirect:home";
			}else if(login.getRole()==ConstClass.USER_ROLE_ADMIN){
				return "redirect:../admin/index";
			}else {
				return "user/login";
			}
			
		}
		
	}
	
}
