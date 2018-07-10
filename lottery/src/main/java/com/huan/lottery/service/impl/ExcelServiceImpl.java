package com.huan.lottery.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huan.lottery.mapper.LtUserMapper;
import com.huan.lottery.pojo.LtUser;
import com.huan.lottery.service.ExcelService;
@Service
public class ExcelServiceImpl implements  ExcelService{
	@Autowired
	private LtUserMapper ltUserMapper;
	
	 public  void getData(File file, int startrow) {
	        try {
					List<LtUser> result = new ArrayList<LtUser>();
					
					FileInputStream fi = new FileInputStream(file);
					XSSFWorkbook wb = new XSSFWorkbook(fi);
					int sheetSize = wb.getNumberOfSheets();
					int sheetIndex= 1;
					System.out.println(wb.getNumberOfSheets());
					XSSFSheet sheet= wb.getSheetAt(sheetIndex);
			       // 第一行为标题，不取
			       for (int rowIndex = startrow; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
							LtUser lu = new LtUser();
							XSSFRow  row = sheet.getRow(rowIndex);
							 if (row == null) {
							     continue;
							 }
							 boolean hasValue = false;
							 for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
							     String value = "";
							     XSSFCell cell = row.getCell(columnIndex);
							     if(cell!=null)
							     	value = cell.getStringCellValue();
							     switch(columnIndex) {
							            case 0:{
							            	 lu.setUsername(value);
							            	 break;
							            }
							            case 1:{
							            	 lu.setIdcard(value);
							            	 break;
							            }
							     }
							 }
							 result.add(lu);
			       }
			       for(LtUser lur : result) {
			    	   ltUserMapper.insertSelective(lur);
			       }
				fi.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
	    }
}
