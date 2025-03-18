package com.qualizeal.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromExcel {
    
    public Object[][] readData() {
    	String userDir = System.getProperty("user.dir");
		String filePath = userDir+ "\\src\\main\\resources\\Book 16.xlsx";
        File file = new File(filePath);
        
        Object[][] data = null;
        
        try (FileInputStream fis = new FileInputStream(file);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            
            data = new Object[rowCount - 1][colCount];
            DataFormatter formatter = new DataFormatter();
            
            for (int i = 1; i < rowCount; i++) {
                if (sheet.getRow(i) != null) {
                    for (int j = 0; j < colCount; j++) {
                        if (sheet.getRow(i).getCell(j) != null) {
                            data[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
                        } else {
                            data[i - 1][j] = "";
                        }
                    }
                } else {
                    for (int j = 0; j < colCount; j++) {
                        data[i - 1][j] = "";
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return data;
    }
}
