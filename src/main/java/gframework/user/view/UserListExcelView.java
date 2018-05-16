package gframework.user.view;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;

import gframework.common.view.CommonExcelView;

public class UserListExcelView extends CommonExcelView {
	
	protected void buildHeader(){
		row = sheet.createRow(0);
		int cm = 1300;
		row.setHeightInPoints(25);
		
		HSSFCell cell;
		cell = row.createCell(0);
		cell.setCellStyle(styleHeader);		
		cell.setCellValue("No");
		sheet.setColumnWidth(0, 1 * cm);
		
		cell = row.createCell(1);
		cell.setCellStyle(styleHeader);
		cell.setCellValue("Username");
		sheet.setColumnWidth(1, 5 * cm);

		cell = row.createCell(2);
		cell.setCellStyle(styleHeader);
		cell.setCellValue("First Name");
		sheet.setColumnWidth(2, 10 * cm);
		
		cell = row.createCell(3);
		cell.setCellStyle(styleHeader);
		cell.setCellValue("Last Name");
		sheet.setColumnWidth(3, 10 * cm);

		cell = row.createCell(4);
		cell.setCellStyle(styleHeader);
		cell.setCellValue("Register Date");
		sheet.setColumnWidth(4, 5 * cm);
	}
	
	protected void buildBody(){
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> data = (List<Map<String, Object>>) model.get("user");
		int rowIdx = 1;
		for(Map<String, Object> record : data){
			row = sheet.createRow(rowIdx);
			
			
			//No
			cell = row.createCell(0);
			cell.setCellStyle(styleCenter);
			cell.setCellValue(rowIdx);
			
			//Username
			final String username = String.valueOf(record.get("username")); 
			cell = row.createCell(1);
			cell.setCellStyle(styleLeft);
			cell.setCellValue(username);
			
			//First Name	
			final String firstName = String.valueOf(record.get("firstName")); 
			cell = row.createCell(2);
			cell.setCellStyle(styleLeft);
			cell.setCellValue(firstName);
			
			//Last Name	
			final String lastName = String.valueOf(record.get("lastName")); 
			cell = row.createCell(3);
			cell.setCellStyle(styleLeft);
			cell.setCellValue(lastName);

			//Register Date
			final String regDate = String.valueOf(record.get("regDate")); 
			cell = row.createCell(4);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date date = null;
			try {
				date = format.parse(regDate);
				cell.setCellValue(date);
			} catch (ParseException e) {
				logger.info("##### [Exception] Date cannot be parsed.");			
				cell.setCellValue("");
			}			
			cell.setCellStyle(styleDate);
			
			logger.info("##### [Excel] Row "+rowIdx+" : ["+rowIdx+"] ["+username+"] ["+firstName+"] ["+lastName+"] ["+date+"]");			

			rowIdx++;
		}
		
	}

	protected void setFileName(){
		response.setHeader("Content-Disposition", "attachment; filename=UserList.xls");	
	}

}
