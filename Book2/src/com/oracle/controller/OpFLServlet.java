package com.oracle.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.oracle.domain.Fenlei;
import com.oracle.factory.FenleiServiceFactory;
import com.oracle.util.DownUtils;
 
public class OpFLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action =request.getParameter("action");
		List<Fenlei> list=null;
		String key="";
		if("all".equals(action)){
			list=FenleiServiceFactory.getFenleiServiceImpl().findfl();
			key="全部";
			
		}
		if("outids".equals(action)){
			String ids1=request.getParameter("ids");
			String[] ids=ids1.split(",");
			list=FenleiServiceFactory.getFenleiServiceImpl().showIdsfl(ids);
			key="勾选";
			
		}
		HSSFWorkbook  Workbook=new HSSFWorkbook();
		HSSFSheet sheet=Workbook.createSheet(key+"分类信息表");
		
		sheet.setColumnWidth(2, 20*256);
		HSSFCellStyle style=Workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font=Workbook.createFont();
		font.setBold(true);
          font.setColor(HSSFColor.DARK_RED.index);
         style.setFont(font);
         String[] title={"编号","分类名"};
         HSSFRow row=sheet.createRow(0);
         for(int i=0 ;i<title.length;i++){                                                                                                                                                                                                                    
			 HSSFCell cell=row.createCell(i);
			 cell.setCellStyle(style);
			 cell.setCellValue(title[i]);
		 }
         HSSFCellStyle style1=Workbook.createCellStyle();
		 style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
		//设置字体样式
        for(int i=0;i<list.size();i++){
			 
		HSSFRow row1=sheet.createRow(i+1);
		 Fenlei fenlei =list.get(i);
		 
		
		HSSFCell cell1=row1.createCell(0);
		cell1.setCellValue(fenlei.getId());
		
		HSSFCell cell2=row1.createCell(1);
		cell2.setCellValue(fenlei.getName());
 
		 
		cell1.setCellStyle(style1);
		cell2.setCellStyle(style1);
 
	 
		 }
        File f=new File("分类信息表.xls");
		 
		OutputStream outputStream=new FileOutputStream(f);
			//把工作蒲的内容写入文件
		Workbook.write(outputStream);
		 
		 //响应浏览器，
		 String file=f.getName();
		
		//System.out.println(filename);
		//文件类型
		String mime=request.getServletContext().getMimeType(file);
		
		 String filename=DownUtils.filenameEncoding(key+file, request);
		 
	//默认inline
		String disposition="attachment;filename="+filename;
		
		
		//两个头
		response.setHeader("Content-Type", mime);
		response.setHeader("Content-DisPosition",disposition);
		
		//两个流
		//文件加载到内存
		InputStream inputStream=new FileInputStream(file);
		//流输给浏览器
		ServletOutputStream output=response.getOutputStream();
		IOUtils.copy(inputStream, output);
		
 
	}

}
