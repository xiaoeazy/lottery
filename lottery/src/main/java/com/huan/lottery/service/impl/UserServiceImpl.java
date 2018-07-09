package com.huan.lottery.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.huan.lottery.mapper.LtUserMapper;
import com.huan.lottery.pojo.LtUser;
import com.huan.lottery.pojo.LtUserExample;
import com.huan.lottery.pojo.LtUserExample.Criteria;
import com.huan.lottery.pojo.TaotaoResult;
import com.huan.lottery.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private LtUserMapper ltUserMapper;
	
//	@Value("${SSO_SESSION_EXPIRE}")
//	private Integer SSO_SESSION_EXPIRE;
	

	/**
	 * 用户登录
	 * <p>Title: userLogin</p>
	 * <p>Description: </p>
	 * @param username
	 * @param password
	 * @return
	 * @see com.taotao.sso.service.UserService#userLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public LtUser userLogin(String username, String idcard,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LtUserExample example = new LtUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdcardEqualTo(idcard);
		List<LtUser> list = ltUserMapper.selectByExample(example);
		//如果没有此idcard
		if (null == list || list.size() == 0) {
			throw new Exception("投保人证件号不存在！");
		}
		LtUser user = list.get(0);
		//比对用户名
		if (!user.getUsername().equals(username)) {
			throw new Exception("投保人证件号与用户名不匹配!");
		}
		
		if (user.getFlag()==true) {
			throw new Exception("对不起，您已经参加过抽奖!");
		}
		
		//返回token
		return user;
	}

	

}
