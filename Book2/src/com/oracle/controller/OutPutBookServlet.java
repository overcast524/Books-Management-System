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

import com.oracle.domain.Book;
import com.oracle.domain.User;
import com.oracle.factory.BookServiceFactory;
import com.oracle.util.DownUtils;



/**
 * Servlet implementation class OutPutBookServlet
 */
public class OutPutBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action =request.getParameter("action");
		List<Book> list=null;
		String key="";
		if("all".equals(action)){
			list=BookServiceFactory.getBookServiceImpl().showBook();
			key="全部";
			
		}
		if("outids".equals(action)){
			String ids1=request.getParameter("ids");
			String[] ids=ids1.split(",");
			list=BookServiceFactory.getBookServiceImpl().showIdsBook(ids);
			key="勾选";
			
		}
		HSSFWorkbook  Workbook=new HSSFWorkbook();
		HSSFSheet sheet=Workbook.createSheet(key+"图书信息表");
		
		sheet.setColumnWidth(7, 15*256);
		HSSFCellStyle style=Workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font=Workbook.createFont();
		font.setBold(true);
          font.setColor(HSSFColor.DARK_RED.index);
         style.setFont(font);
         String[] title={"编号","分类名","图书名","图书价格","出版社","状态","借书人"};
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
		Book book =list.get(i);
		 
		
		HSSFCell cell1=row1.createCell(0);
		cell1.setCellValue(book.getId());
		
		HSSFCell cell2=row1.createCell(1);
		cell2.setCellValue(book.getFlname());
		
		HSSFCell cell3=row1.createCell(2);
		cell3.setCellValue(book.getName());
		
		HSSFCell cell4=row1.createCell(3);
		cell4.setCellValue(book.getMoney());
		
		HSSFCell cell5=row1.createCell(4);
		cell5.setCellValue(book.getPress());
		
		HSSFCell cell6=row1.createCell(5);
		cell6.setCellValue(book.getState());
		
		HSSFCell cell7=row1.createCell(6);
		cell7.setCellValue(book.getReader());
		 
		cell1.setCellStyle(style1);
		cell2.setCellStyle(style1);
		cell3.setCellStyle(style1);
		cell4.setCellStyle(style1);
		cell5.setCellStyle(style1);
		cell6.setCellStyle(style1);
		cell7.setCellStyle(style1);
	 
		 }
        File f=new File("图书信息表.xls");
		 
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
