package gframework.common.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public abstract class CommonExcelView extends AbstractExcelView{
	
	protected HSSFWorkbook workbook;
	protected HSSFSheet sheet;
	protected HSSFRow row;
	protected HSSFCell cell;
	
	protected Map<String, Object> model;
	protected HttpServletRequest request;	
	protected HttpServletResponse response;
	
	protected HSSFCellStyle styleHeader;
	protected HSSFCellStyle styleCenter;
	protected HSSFCellStyle styleLeft;
	protected HSSFCellStyle styleMoney;
	protected HSSFCellStyle styleDate;
	protected HSSFCellStyle styleNumber;
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		init(model, workbook, request, response);
		setFileName();
		setStyle();
		buildHeader();
		buildBody();
	}
	
	protected void init(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response){
		
		this.model			= model;
		this.workbook 		= workbook;		
		this.sheet 			= workbook.createSheet();		
		this.response		= response;
		this.request		= request;
	}
	
	abstract protected void buildHeader();
	
	abstract protected void buildBody();

	abstract protected void setFileName();
	
	protected void setStyle(){
		styleHeader = workbook.createCellStyle();
		styleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleHeader.setBottomBorderColor(HSSFColor.BLACK.index);
		styleHeader.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		styleHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleHeader.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleHeader.setAlignment(CellStyle.ALIGN_CENTER);
		
		HSSFFont fontHeader = workbook.createFont();
		fontHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontHeader.setColor(HSSFColor.BLACK.index);		
		styleHeader.setFont(fontHeader);
		
		styleCenter = workbook.createCellStyle();
		styleCenter.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleCenter.setAlignment(CellStyle.ALIGN_CENTER);

		styleLeft 	= workbook.createCellStyle();
		styleLeft.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleLeft.setAlignment(CellStyle.ALIGN_LEFT);
		styleLeft.setWrapText(true);

		styleDate 	= workbook.createCellStyle();
		styleDate.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
		styleDate.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleDate.setAlignment(CellStyle.ALIGN_CENTER);

		styleNumber = workbook.createCellStyle();
		styleNumber.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleNumber.setAlignment(CellStyle.ALIGN_CENTER);

		styleMoney 	= workbook.createCellStyle();
		styleMoney.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
		styleMoney.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleMoney.setAlignment(CellStyle.ALIGN_RIGHT);
	}

}
