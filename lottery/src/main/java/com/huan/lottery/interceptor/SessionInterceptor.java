package com.huan.lottery.interceptor;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.huan.lottery.controllers.IndexController;
import com.huan.lottery.system.controllers.BaseController;

public class SessionInterceptor extends HandlerInterceptorAdapter{
	 @Override
	    public boolean preHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler) throws Exception { 
		 	HttpSession session = request.getSession();
		    if (session.getAttribute(BaseController.FIELD_USER_ID) != null) {
		        return true;
		    }
		    // 如果是ajax请求，请求头会有x-requested-with
		    String requestWith = request.getHeader("x-requested-with");
		    if (requestWith != null && requestWith.equalsIgnoreCase("XMLHttpRequest")){
		        ServletOutputStream out = response.getOutputStream();
		        response.addHeader("sessionstatus", "timeout");   
		        out.close();
		    } else {
		    	String contextName =  request.getContextPath();
	    		response.sendRedirect(contextName+IndexController.VIEW_LOGIN);
		    }
		    return false;
	    }
}
