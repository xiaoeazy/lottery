package com.huan.lottery.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huan.lottery.pojo.LtUser;
import com.huan.lottery.pojo.TaotaoResult;

public interface ExcelService {

	public  void getData(File file, int startrow) ;
}
