package com.cio.rough;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class sample {
	
public String[][] getData(String getSheetName ) throws EncryptedDocumentException, IOException{
		
		//String getSheetName = m.getName();
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
				System.out.println(testData[i-1][j]);
			}
		}
		
		return testData;
		
	}
	

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
		
		
		/*
		 * sample sa = new sample(); sa.getData("openGoogle");
		 */
		
		System.out.println(System.getProperty("user.dir"));
		
		
//		Date d = new Date();
//		System.out.println(d.toString().replace(":", "_").replace(" ","_"));
		
	}

}
