/**
 * 
 */
package automation.careSuiteClient.Package11;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author E004014
 * Retrieves test case data from excel sheet
 *
 */
public class CSSAutomationTestCaseData {
	
	 public static Hashtable<String,String> getTestCaseData(String path, String sheetName) throws IOException 
	{
		Hashtable<String, String> testCaseData = new Hashtable<String, String>();
		try {
			
			//Retrieve test case data from sheet 
			FileInputStream fis = new FileInputStream(path);
			Workbook workbook = new XSSFWorkbook(fis);
			int sheetNumber = 0;
			
			//Get sheet number from the workbook
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {

				if (workbook.getSheetName(i).trim().equalsIgnoreCase(sheetName.trim())) {
					sheetNumber = i;
					break;
				}
			}
            
            
			Sheet sheet = workbook.getSheetAt(sheetNumber);
			int lastRow = sheet.getLastRowNum();

			// Looping over entire row
			for (int i = 1; i <= lastRow; i++)
			{
				Row row = sheet.getRow(i);
				// 1sh Cell as Key
				Cell keyCell = row.getCell(1);
				// 2nd Cell as Value
				Cell valueCell = row.getCell(2);
				String key = keyCell.getStringCellValue().trim();
				String value = valueCell.getStringCellValue().trim();

				// Putting key & value in dataMap
				testCaseData.put(key, value);
			}

			// Returning excelFileMap
			return testCaseData;

		}

		catch (Exception e) {
			System.out.println("Error in getting test case data from excel sheet");
			e.printStackTrace();

		}
		return testCaseData;
	}

		 
}


