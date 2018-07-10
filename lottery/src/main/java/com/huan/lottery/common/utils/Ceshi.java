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
	  ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	  cachedThreadPool.execute(new AddThread(userList,bl));
	  
	  for(int i=0;i<1;i++) {
		  System.out.println(bl.size());
		  cachedThreadPool.execute(new XiaoFeiThread(bl));
	  }
	 
    }
	  
	  
   
}
