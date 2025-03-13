package com.cio.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.cio.base.TestBase;

public class TestUtils extends TestBase {
	
	
	public static String screenshotName;
	
	public static void captureScreenshot() throws IOException {
		
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ","_")+".JPG";
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\"+screenshotName));
		System.out.println("Inside CaptureScreenshot");
	}
	
	@DataProvider(name="dp")	
	public static String[][] getData(Method m) throws EncryptedDocumentException, IOException{
		
		String getSheetName = m.getName();
		File f = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testdata.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheetName = wb.getSheet(getSheetName);
		System.out.println(getSheetName);
		int totalRows = sheetName.getLastRowNum();
		Row rowCells = sheetName.getRow(0);		
		int totalCols = rowCells.getLastCellNum();
		
		
		DataFormatter format = new DataFormatter();
		
		String[][] testData = new String[totalRows][totalCols];
		
		for(int i=1;i<=totalRows;i++) {
			for(int j=0;j<totalCols;j++) {
				testData[i-1][j] = format.formatCellValue(sheetName.getRow(i).getCell(j));
			}
		}
		
		return testData;
		
	}
}
