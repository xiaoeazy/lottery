package com.huan.lottery.common.utils;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.huan.lottery.pojo.LtUser;

public class XiaoFeiThread implements Runnable {
	BlockingQueue<LtUser> bl =null;
	public XiaoFeiThread(BlockingQueue<LtUser> bl) {
		super();
		this.bl = bl;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("================XiaoFei==============");
		 Map<String, String> cookie=null;
		try {
			
			while(bl.size()!=0) {
				System.out.println("XiaoFei:"+bl.size());
				LtUser ltuser = bl.take();
				System.out.println("ltuser:"+ltuser);
				System.out.println("XiaoFei take one");
				cookie = login(ltuser);
				System.out.println("cookie:"+cookie);
				afterLogin(cookie);
				Thread.sleep(100);
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	 public  static Map<String, String>  login(LtUser ltuser) throws IOException, InterruptedException {
		 	
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
	                .data("salespersonid", "1");
	        
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
	           Document document = Jsoup.connect(url).cookies(cookies).post();
//	           System.out.println(document.toString());
	    }

}
