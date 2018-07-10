package com.huan.lottery.common.utils;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.huan.lottery.pojo.LtUser;

public class AddThread implements Runnable {
	List<LtUser> userList =null;
	BlockingQueue<LtUser> bl =null;
	public AddThread(List<LtUser> userList,BlockingQueue<LtUser> b) {
		super();
		this.userList = userList;
		this.bl = b;
	}

	@Override
	public void run() {
		System.out.println("================addStart==============");
		// TODO Auto-generated method stub
		for(LtUser ltUser:userList) {
			try {
				bl.put(ltUser);
				System.out.println("toAdd:"+bl.size());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
