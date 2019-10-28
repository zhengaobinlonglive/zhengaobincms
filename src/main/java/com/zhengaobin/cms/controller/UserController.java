package com.zhengaobin.cms.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.zhengaobin.cms.comon.ConstClass;
import com.zhengaobin.cms.entity.Article;
import com.zhengaobin.cms.entity.Article4Vote;
import com.zhengaobin.cms.entity.User;
import com.zhengaobin.cms.service.Article4VoteService;
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
	
	@Autowired
	Article4VoteService avService;
	
	@GetMapping("push")
	public String push(HttpServletRequest request) {
		return "my/vote/add";
		
	}
	
	@PostMapping("push")
	@ResponseBody
	public boolean  push(HttpServletRequest request,Article4Vote av) {
		return avService.publish(av)>0;
		
	}
	
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
	public boolean delArticle(HttpServletRequest request,Integer id) {
		
		//判断文章是否存在
		Article article = articleService.findById(id);
		if(article==null)
			return false;
		
		//判断文章是否属于自己的
		User loginUser =(User) request.getSession().getAttribute(
				ConstClass.SESSION_USER_KEY);
		if(loginUser.getId()!= article.getUserId()) {
			return false;
		}
		//删除文章
		return articleService.remove(id)>0;
	}
	
	/**
	 * 进入个人中心 获取我的文章
	 * @param request
	 * @return
	 */
	@RequestMapping("myarticlelist")
	public String myarticles(HttpServletRequest request,
			@RequestParam(defaultValue="1") Integer page) {
		
		User loginUser =(User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		PageInfo<Article>  pageArticles = articleService.listArticleByUserId(loginUser.getId(),page);
		//PageUtils.page(request, "/user/myarticlelist", 10, pageArticles.getList(), (long)pageArticles.getTotal(), pageArticles.getPageNum());
		String pageStr = PageUtils.pageLoad(pageArticles.getPageNum(), pageArticles.getPages(), "/user/myarticlelist", 10);
		request.setAttribute("pageArticles", pageArticles);
		request.setAttribute("page", pageStr);
		return "/my/list";
	}
	
	/**
	 * 跳转到上传页面
	 */
	@GetMapping("toAddhead_picture")
	public String toAddhead_picture() {
		return "my/addhead_picture";
	}
	
	@PostMapping("addhead_picture")
	public String addHead_picture(HttpServletRequest request,MultipartFile file) throws IllegalStateException, IOException {
		User user = (User)request.getSession().getAttribute("SESSION_USER_KEY");
		System.out.println("112323423121233");
		System.out.println("user----------"+user);
		processFile(file,user);
		
		 userService.addHead_picture(user);
		return "redirect:home";
		
	}
	
	/**
	 * 处理接收到的文件
	 */
	
	private void processFile(MultipartFile file,User user) throws IllegalStateException, IOException {


		// 原来的文件名称
		System.out.println("file.isEmpty() :" + file.isEmpty()  );
		System.out.println("file.name :" + file.getOriginalFilename());
		
		if(file.isEmpty()||"".equals(file.getOriginalFilename()) || file.getOriginalFilename().lastIndexOf('.')<0 ) {
			user.setHead_picture("");
			return;
		}
			
		String originName = file.getOriginalFilename();
		String suffixName = originName.substring(originName.lastIndexOf('.'));
		SimpleDateFormat sdf=  new SimpleDateFormat("yyyyMMdd");
		String path = "d:/pic/" + sdf.format(new Date());
		File pathFile = new File(path);
		if(!pathFile.exists()) {
			pathFile.mkdir();
		}
		String destFileName = 		path + "/" +  UUID.randomUUID().toString() + suffixName;
		File distFile = new File( destFileName);
		file.transferTo(distFile);//文件另存到这个目录下边
		user.setHead_picture(destFileName.substring(7));
		
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
