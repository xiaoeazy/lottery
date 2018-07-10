package com.huan.lottery.common.utils;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huan.lottery.service.ExcelService;
import com.huan.lottery.service.UserService;

public class Excel {
	public static  ExcelService excelService;
	
	  @BeforeClass
     public static  void init() {//junit之前init spring
        try {
			ApplicationContext   context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");//这里路径之前没有配对于是一直出错
			System.out.println("加载完毕");
			excelService = (ExcelService)context.getBean(ExcelService.class);
			System.out.println("over");
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
	
	  @Test
	    public  void addProject() throws Exception {
		  System.out.println(excelService==null);
		  File file = new File("C:\\Users\\huanghuan\\Desktop\\苹果树名单.xlsx");
		  System.out.println("end");
		  excelService.getData(file, 1);
	    }

	  
//	 public  void main(String[] args) throws Exception {
//	        File file = new File("C:\\Users\\huanghuan\\Desktop\\苹果树名单.xlsx");
//	        List<LtUser> result = getData(file, 1);
////	        int rowLength = result.length;
////	        for (int i = 0; i < rowLength; i++) {
////	            System.out.println(result[i]);
////	            for (int j = 0; j < result[i].length; j++) {
////	                System.out.print(result[i][j] + "\t\t");
////	            }
////	            System.out.println();
////	        }
//	    }
	
	
}

