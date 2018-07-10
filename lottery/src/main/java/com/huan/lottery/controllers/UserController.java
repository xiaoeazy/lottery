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

import com.huan.lottery.common.utils.ExceptionUtil;
import com.huan.lottery.pojo.LtUser;
import com.huan.lottery.pojo.TaotaoResult;
import com.huan.lottery.service.UserService;
import com.huan.lottery.system.controllers.BaseController;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	protected static final String REDIRECT = "redirect:";
	public static final String VIEW_INDEX = "/cj";
	
	@Autowired
	private UserService userService;
	
	
    
  //用户登录
  	@RequestMapping(value="/login", method=RequestMethod.POST)
  	@ResponseBody
  	public ModelAndView userLogin(String username, String idcard,String salesPersonId,
  			HttpServletRequest request, HttpServletResponse response) {
  		ModelAndView view = new ModelAndView(UserController.VIEW_INDEX);
  		try {
  			
  			HttpSession session = request.getSession(false);
  			// 有session 则不再登录
  			if (session != null && session.getAttribute(BaseController.FIELD_USER_ID) != null) {
  				 ModelAndView mv = new ModelAndView(UserController.VIEW_INDEX);
  				return mv;
  			}
  			
  			LtUser user = userService.userLogin(username, idcard, request, response);
  			if (user == null) {
  				throw new Exception("用户名或者密码错误");
  			} else {
  				session = request.getSession();
  				session.setAttribute(BaseController.FIELD_USER_ID, user.getId());
  				session.setAttribute(BaseController.SALES_PERSON_ID, salesPersonId);
  				session.setAttribute("userRealName", user.getUsername());
  			}
  		} catch (Exception e) {
  			view.setViewName(getViewPath() + IndexController.VIEW_LOGIN);
  			view.addObject("errorInfo", e.getMessage());
  		}
  		return view;
  	}
   
	
	//用户登录
	@RequestMapping(value="/choujiang", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult choujiang(HttpServletRequest request, HttpServletResponse response) {
		try {
			Integer userid =(Integer)request.getSession().getAttribute(BaseController.FIELD_USER_ID) ;
			String salesPersonId =(String)request.getSession().getAttribute(BaseController.SALES_PERSON_ID) ;
//			TaotaoResult tr = userService.choujiang(userid, salesPersonId);
			TaotaoResult tr = userService.choujiangTest(userid, salesPersonId);
			return tr;
		} catch (Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
}