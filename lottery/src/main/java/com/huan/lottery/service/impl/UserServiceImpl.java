package com.huan.lottery.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.lottery.mapper.LtActivityMapper;
import com.huan.lottery.mapper.LtPrizeitemMapper;
import com.huan.lottery.mapper.LtUserMapper;
import com.huan.lottery.pojo.LtActivity;
import com.huan.lottery.pojo.LtPrizeitem;
import com.huan.lottery.pojo.LtPrizeitemExample;
import com.huan.lottery.pojo.LtUser;
import com.huan.lottery.pojo.LtUserExample;
import com.huan.lottery.pojo.LtUserExample.Criteria;
import com.huan.lottery.pojo.TaotaoResult;
import com.huan.lottery.service.UserService;


@Service("excelService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private LtUserMapper ltUserMapper;
	@Autowired
	private LtPrizeitemMapper ltPrizeitemMapper;
	@Autowired
	private LtActivityMapper ltActivityMapper;
	
	public static  Object obj = new Object();
	
//	@Value("${SSO_SESSION_EXPIRE}")
//	private Integer SSO_SESSION_EXPIRE;
	@Override
	public LtActivity loadActivity(Integer id ) {
		return ltActivityMapper.selectByPrimaryKey(id);
	}
	
	public List<LtUser> loadAllUser(){
		return ltUserMapper.selectByExample(null);
	}
	
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
			throw new Exception("用户名不匹配!");
		}
		
		if (user.getFlag()==true) {
			
			LtPrizeitem it = ltPrizeitemMapper.selectByPrimaryKey(user.getPid());
			String addString = "";
			if(it!=null)
				addString+="奖品为:"+it.getPrizename();
			throw new Exception("对不起，您已经参加过抽奖! "+addString);
		}
		
		//返回token
		return user;
	}

	@Override
	public  TaotaoResult choujiang(Integer userid,String salesPersonId,String phone) throws Exception{
		int resultNum = 0;
		try {
			synchronized(obj){
				LtUser user = ltUserMapper.selectByPrimaryKey(userid);
				if(user.getFlag()==true) {
					LtPrizeitem it = ltPrizeitemMapper.selectByPrimaryKey(user.getPid());
					String addString = "";
					if(it!=null)
						addString+="奖品为:"+it.getPrizename();
					throw new Exception("对不起，您已经参加过抽奖! "+addString);
				}
				
				Date nowDate = new Date();
				LtActivity ltActivity = ltActivityMapper.selectByPrimaryKey(1);
				judgeDate(nowDate, ltActivity);
				
				double random = randomNum();
				LtPrizeitemExample example = new LtPrizeitemExample();
				com.huan.lottery.pojo.LtPrizeitemExample.Criteria criteria = example.createCriteria();
				criteria.andAidEqualTo(1);
				List<LtPrizeitem> list = ltPrizeitemMapper.selectByExample(example);
				if(list!=null && list.size()==0)
					throw new Exception("对不起,尚未设置奖品!");
				
				LtPrizeitem lp = null;
				for (LtPrizeitem award : list) {
		            if(random < award.getProbability()){
		            	if(award.getRemainnum()>0){ //以免奖项已经为0
		            		lp= award;//获得的是哪个奖项
		                	break;
		            	}
		            }
		        }
				
				LtUser useritem = new LtUser();
				useritem.setId(userid);
				useritem.setFlag(true);
				useritem.setAwarddate(nowDate);
				useritem.setSalespersonid(salesPersonId);
				useritem.setPhone(phone);
				if(lp!=null){
					LtPrizeitem item = new LtPrizeitem();
					item.setId(lp.getId());
					item.setRemainnum(lp.getRemainnum()-1);
					ltPrizeitemMapper.updateByPrimaryKeySelective(item);
					useritem.setPid(item.getId());   //更新奖品表中的数据
					resultNum = item.getId();
				}
				ltUserMapper.updateByPrimaryKeySelective(useritem);//更新用户获奖状态;
			}
		} catch (Exception e) {
			 throw e;
		}
		return  TaotaoResult.ok(resultNum);
	}
	
	@Override
	public  TaotaoResult choujiangTest(Integer userid) throws Exception{
		int resultNum = 0;
		double random = randomNum();
		LtPrizeitemExample example = new LtPrizeitemExample();
		com.huan.lottery.pojo.LtPrizeitemExample.Criteria criteria = example.createCriteria();
		criteria.andAidEqualTo(1);
		List<LtPrizeitem> list = ltPrizeitemMapper.selectByExample(example);
		if(list!=null && list.size()==0)
			throw new Exception("对不起,尚未设置奖品!");
		
		LtPrizeitem lp = null;
		for (LtPrizeitem award : list) {
            if(random < award.getProbability()){
            	if(award.getRemainnum()>0){ //以免奖项已经为0
            		lp= award;//获得的是哪个奖项
                	break;
            	}
            }
        }
		if(lp!=null){
			resultNum=lp.getId();
		}
		return TaotaoResult.ok(resultNum);
	}
	
	private void judgeDate(Date nowDate,LtActivity ltActivity ) throws Exception{
	
		Date startDate = ltActivity.getStartdate();
		Date endDate = ltActivity.getEnddate();
		if(startDate!=null){
			if(nowDate.getTime()<startDate.getTime()){
				throw new Exception("活动尚未开启!");
			}
		}
		if(endDate!=null){
			if(endDate.getTime()<nowDate.getTime()){
				throw new Exception("活动已经结束!");
			}
		}
	}
	
	private double randomNum(){
		 	Random randomTool = new Random();
	        Double userSelect = randomTool.nextDouble()*100;
	        return userSelect;
	}

	
	

}
