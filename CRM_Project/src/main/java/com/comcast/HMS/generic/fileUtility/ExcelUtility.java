package com.comcast.HMS.generic.fileUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility 
{
	public String getDataFromExcel(String sheetName, int rowNumber, int cellNumber) throws Throwable
	{
		FileInputStream fis = new FileInputStream("./data/vtigerTestScript.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).getStringCellValue();
		
		wb.close();
		
		return data;
	}
	
	public int getRowCount(String sheetName) throws Throwable
	{
		FileInputStream fis = new FileInputStream("./testdata/vtigerTestScript.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(sheetName).getLastRowNum();
		
		wb.close();
		
		return rowCount;
	}
	
	public void setDataIntoExcel(String sheetName, int rowNumber, int cellNumber , String data) throws Throwable
	{
		FileInputStream fis = new FileInputStream("./testdata/vtigerTestScript.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNumber).createCell(cellNumber);
		
		FileOutputStream fos = new FileOutputStream("./testdata/vtigerTestScript.xlsx");
		wb.write(fos);
		
		wb.close();
	}
}
