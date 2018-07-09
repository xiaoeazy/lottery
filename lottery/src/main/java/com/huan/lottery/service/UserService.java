package com.huan.lottery.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huan.lottery.pojo.LtUser;

public interface UserService {

	public LtUser userLogin(String username, String idcard,HttpServletRequest request, HttpServletResponse response) throws Exception  ;
	
}
