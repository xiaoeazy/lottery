package com.huan.lottery.common.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huan.lottery.pojo.LtUser;
import com.huan.lottery.service.UserService;

public class Ceshi {
	 public static  UserService userService;
	 
	  @BeforeClass
	     public static  void init() {//junit之前init spring
	        try {
				ApplicationContext   context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");//这里路径之前没有配对于是一直出错
				System.out.println("加载完毕");
				userService = (UserService)context.getBean(UserService.class);
				
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     }
	  
	  @Test
    public  void addProject() throws Exception {
	  System.out.println(userService==null);
	  BlockingQueue<LtUser> bl = new  ArrayBlockingQueue<LtUser>(1);
	  List<LtUser> userList =userService.loadAllUser();
	  try {
		for(LtUser lu:userList) {
			  Map<String, String> cookies  = login(lu);
			  afterLogin(cookies);
		  }
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//	  login(userList.get(0));
//	  ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//	  cachedThreadPool.execute(new AddThread(userList,bl));
//	  
//	  for(int i=0;i<1;i++) {
//		  System.out.println(bl.size());
//		  cachedThreadPool.execute(new XiaoFeiThread(bl));
//	  }
//	  new Thread( new AddThread(userList,bl)).start() ;
//	  for(int i=0;i<1;i++) {
//		  new Thread(new XiaoFeiThread(bl)).start() ;
//	  }
	 
    }
	  
	  public   Map<String, String>  login(LtUser ltuser) throws IOException, InterruptedException {
		 	
	        String urlLogin = "http://localhost:8080/lottery/user/login";
	        Connection connect = Jsoup.connect(urlLogin);
	        // 伪造请求头
	        connect.header("Accept", "application/json, text/javascript, */*; q=0.01").header("Accept-Encoding",
	                "gzip, deflate");
	        connect.header("Accept-Language", "zh-CN,zh;q=0.9").header("Connection", "keep-alive");
	        connect.header("Content-Length", "72").header("Content-Type",
	                "application/x-www-form-urlencoded; charset=UTF-8");
//	        connect.header("Host", "qiaoliqiang.cn").header("Referer", "http://qiaoliqiang.cn/Exam/");
	        connect.header("User-Agent",
	                "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
	                .header("X-Requested-With", "XMLHttpRequest");
	        
	        // 携带登陆信息
	        connect.data("username", ltuser.getUsername()).data("idcard", ltuser.getIdcard()).data("phone", "1")
	                .data("salesPersonId", "1");
	        
	        //请求url获取响应信息
	        Response res = connect.ignoreContentType(true).method(Method.POST).execute();// 执行请求
	        // 获取返回的cookie
	        Map<String, String> cookies = res.cookies();
	        for (Entry<String, String> entry : cookies.entrySet()) {
	            System.out.println(entry.getKey() + "-" + entry.getValue());
	        }
	        System.out.println("---------华丽的分割线-----------");
//	        String body = res.body();// 获取响应体
//	        System.out.println(body);
	        return cookies;
	    }
	  
	  public static void afterLogin( Map<String, String> cookies) throws IOException {
   	   String url = "http://localhost:8080/lottery/user/choujiang";
          // 直接获取DOM树，带着cookies去获取
          Document document = Jsoup.connect(url).cookies(cookies).ignoreContentType(true).post();
//          System.out.println(document.toString());
   }
	  
   
}
