package com.huan.lottery.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.huan.lottery.pojo.LtUser;
import com.huan.lottery.service.UserService;
import com.huan.lottery.system.controllers.BaseController;

/**
 * 通用的 Controller,用来获取公共数据.
 *
 */
@RestController
public class IndexController extends BaseController{
	public static final String VIEW_LOGIN = "/index";
    private Logger logger = LoggerFactory.getLogger(IndexController.class);
    
	@Autowired
	private UserService userService;
    
    @RequestMapping(value = {"/","/index"})
    public ModelAndView index( Model model) {
        return new ModelAndView(VIEW_LOGIN);
    }

    
}
