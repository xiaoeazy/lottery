package com.huan.lottery.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huan.lottery.pojo.LtUser;
import com.huan.lottery.pojo.TaotaoResult;

public interface UserService {

	public LtUser userLogin(String username, String idcard,HttpServletRequest request, HttpServletResponse response) throws Exception  ;
	public TaotaoResult choujiang(Integer userid,String salesPersonId)  throws Exception;
	public TaotaoResult choujiangTest(Integer userid, String salesPersonId) throws Exception;
}
