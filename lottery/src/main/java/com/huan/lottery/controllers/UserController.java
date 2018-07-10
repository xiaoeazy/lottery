package com.huan.lottery.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.huan.lottery.pojo.LtActivity;
import com.huan.lottery.pojo.LtUser;
import com.huan.lottery.pojo.TaotaoResult;
import com.huan.lottery.service.UserService;
import com.huan.lottery.system.controllers.BaseController;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	public static final String STANDARD_FORMAT = "yyyy年MM月dd日 HH点mm分ss秒";
	protected static final String REDIRECT = "redirect:";
	public static final String VIEW_MAIN = "/cj";
	
	@Autowired
	private UserService userService;
	
	
  //用户登录
  	@RequestMapping(value="/login", method=RequestMethod.POST)
  	public ModelAndView userLogin(String username, String idcard,String salesPersonId,String phone,RedirectAttributes redirectAttributes,
  			HttpServletRequest request, HttpServletResponse response) {
  		ModelAndView view = new ModelAndView(UserController.VIEW_MAIN);
  		try {
  			
  			LtActivity la = userService.loadActivity(1);
  			String startdate = null;
  			String enddate = null;
  			if(la.getStartdate()!=null)
  				startdate=dateToStr(la.getStartdate(), STANDARD_FORMAT);
  			if(la.getEnddate()!=null)
  				enddate = dateToStr(la.getEnddate(), STANDARD_FORMAT);
			view.addObject("startdate",startdate);
			view.addObject("enddate",enddate);
  			HttpSession session = request.getSession(false);
  			// 有session 则不再登录
  			if (session != null && session.getAttribute(BaseController.FIELD_USER_ID) != null) {
  				 ModelAndView mv = new ModelAndView(UserController.VIEW_MAIN);
  				return mv;
  			}
  			
  			LtUser user = userService.userLogin(username, idcard, request, response);
  			if (user == null) {
  				throw new Exception("用户名或者密码错误");
  			} else {
  				session = request.getSession();
  				session.setAttribute(BaseController.FIELD_USER_ID, user.getId());
  				session.setAttribute(BaseController.SALES_PERSON_ID, salesPersonId);
  				session.setAttribute(BaseController.PHONE, phone);
  				session.setAttribute("userRealName", user.getUsername());
  			}
  		} catch (Exception e) {
  			view.setViewName(REDIRECT+ IndexController.VIEW_LOGIN);
  			//ExceptionUtil.getStackTrace(e)
  			redirectAttributes.addFlashAttribute("errorInfo",e.getMessage() ); 
//  			view.addObject("errorInfo", e.getMessage());
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
			String phone =(String)request.getSession().getAttribute(BaseController.PHONE) ;
//			TaotaoResult tr = userService.choujiang(userid, salesPersonId, phone);
			TaotaoResult tr = userService.choujiangTest(userid);
			tr.setData("5");
			return tr;
		} catch (Exception e) {
			return TaotaoResult.build(500, e.getMessage());
		}
	}
	
	 private static String dateToStr(Date date,String formatStr){
	        if(date == null){
	            return StringUtils.EMPTY;
	        }
	        DateTime dateTime = new DateTime(date);
	        return dateTime.toString(formatStr);
	    }

	
}