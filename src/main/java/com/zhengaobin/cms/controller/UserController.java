package com.zhengaobin.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zhengaobin.cms.comon.ConstClass;
import com.zhengaobin.cms.entity.Article;
import com.zhengaobin.cms.entity.User;
import com.zhengaobin.cms.service.ArticleService;
import com.zhengaobin.cms.service.UserService;
import com.zhengaobin.cms.web.PageUtils;

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
	
	@Autowired
	ArticleService articleService;
	
	@GetMapping("register")
	public String register(){
		
		return "user/register";
	}
	
	@RequestMapping("index")
	public String index(){
		return "user/index";
	}
	
	/**
	 * 判断用户名是否已经被占用
	 * @param username
	 * @return
	 */
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
	
	@PostMapping("login")// 只接受POst的请求
	public String login(HttpServletRequest request,@Validated User user,
			BindingResult erroResult,Integer locked){
		if(erroResult.hasErrors()){
			return "login";
		}
		
		//登录
		
			User login = userService.login(user);
			
			if(login==null){
				request.setAttribute("errMsg","用户名密码错误");
				return "user/login";
			}else {
				if(login.getLocked()==1){
					request.setAttribute("errMsg","该用户已被封禁");
					return "user/login";
				}
				request.getSession().setAttribute(ConstClass.SESSION_USER_KEY,login);
				//普通注册用户
				if(login.getRole()==ConstClass.USER_ROLE_GENERAL){
					return "redirect:home";	
					//管理员用户	
				}else if(login.getRole()==ConstClass.USER_ROLE_ADMIN){
					return "redirect:../admin/index";
				}else {
					// 其他情况
					return "user/login";
				}
				
			}
		
	
	}
	
	/**
	 * 进入个人中心(普通注册用户)
	 * @param request
	 * @return
	 */
	@RequestMapping("home")
	public String home(HttpServletRequest request){
		return "my/home";
	}
	
	/**
	 * 删除用户自己的文章
	 * @param id 文章id
	 * @return
	 */
	@RequestMapping("delArticle")
	@ResponseBody
	public boolean delArticle(Integer id){
		return articleService.remove(id)>0;
	}
	
	/**
	 * 进入个人中心 获取我的文章
	 * @param request
	 * @return
	 */
	@RequestMapping("myarticlelist")
	public String myarticles(HttpServletRequest request,@RequestParam(defaultValue="1")Integer page){
		User loginUser = (User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		PageInfo<Article> pageArticles = articleService.listArticleByUserId(loginUser.getId(), page);
		PageUtils.page(request, "/user/myarticlelist", 10, pageArticles.getList(), (long)pageArticles.getSize(), pageArticles.getPageNum());
		request.setAttribute("pageArticles", pageArticles);
		return "/my/list";
	}
	
	/**
	 * 	管理员管理用户
	 * @param model
	 * @param pageNum     分页页码
	 * @param name        模糊查询名称
	 * @return
	 */
					
	@RequestMapping("managerUser")
	public String managerUser(Model model,@RequestParam(defaultValue="1")Integer page,
			@RequestParam(defaultValue="")String name){
		PageInfo<User> userList=userService.userList(page, name);
		String pageLoad = PageUtils.pageLoad(userList.getPageNum(),userList.getPages(),"/user/managerUser?name="+name, 10);
		model.addAttribute("name",name);
		model.addAttribute("userList",userList);
		model.addAttribute("pageUtil",pageLoad);
		return "admin/user/list";
		
	}
	
	/**
	 * 	修改用户状态
	 * @param id
	 * @param locked
	 * @return
	 */
	@RequestMapping("updatelocked")
	@ResponseBody
	public boolean updatelocked(Integer id,Integer locked){
		System.out.println("修改状态为"+locked);
		int i = userService.updatelocked(id, locked);
		return i>0;
		
	}
	
	
}
