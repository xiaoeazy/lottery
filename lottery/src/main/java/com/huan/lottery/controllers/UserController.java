package com.huan.lottery.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huan.lottery.pojo.LtUser;
import com.huan.lottery.service.UserService;
import com.huan.lottery.system.controllers.BaseController;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	protected static final String REDIRECT = "redirect:";
	
	@Autowired
	private UserService userService;
	
	
	//用户登录
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView userLogin(String username, String idcard,
			HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView view = new ModelAndView(REDIRECT + "/");
		try {
			
			HttpSession session = request.getSession(false);
			// 有session 则不再登录
			if (session != null && session.getAttribute(BaseController.FIELD_USER_ID) != null) {
				 ModelAndView mv = new ModelAndView(REDIRECT + "/");
				return mv;
			}
			
			LtUser user = userService.userLogin(username, idcard, request, response);
			if (user == null) {
				throw new Exception("用户名或者密码错误");
			} else {
				session = request.getSession();
				session.setAttribute(BaseController.FIELD_USER_ID, user.getId());
				session.setAttribute("userRealName", user.getUsername());
			}
		} catch (Exception e) {
			view.setViewName(getViewPath() + "/index/login");
			view.addObject("errorInfo", e.getMessage());
		}
		
		return view;
	}
	
}