package util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class RedaDataFromExcel {

	public static FileInputStream inputStream(String path) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return fis;
	}

	public static Workbook createWorkbook(String path) {
		FileInputStream fis = inputStream(path);
		Workbook workbook = null;

		try {
			workbook = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return workbook;
	}

	public static Sheet getSheet(String path, String sheetname) {
		Workbook workbook = createWorkbook(path);
		Sheet sheet = workbook.getSheet(sheetname);
		return sheet;
	}

	public static Object[][] readData(String path, String sheetname) {
		Sheet sheet = getSheet(path, sheetname);
		int lastrow = sheet.getPhysicalNumberOfRows();
		int lastcolno = sheet.getRow(1).getLastCellNum();
		Object[][] data = new Object[lastrow - 1][lastcolno];
		for (int i = 1; i < lastrow; i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < lastcolno; j++) {
				Cell cell = row.getCell(j);
				String value = cell.getStringCellValue();
				data[i - 1][j] = value;
				System.out.println(data[i - 1][j]);
			}
		}
		return data;
	}

}
