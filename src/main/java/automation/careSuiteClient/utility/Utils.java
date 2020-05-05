package automation.careSuiteClient.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gallop.Logger;
import com.relevantcodes.extentreports.ExtentTest;


public class Utils {

	private static String reportingPath = Utils.loadProperty("reportingPath");
	private static String reportPath = Utils.loadProperty("reportPath");
	
	
	//Below code is for excel reading logic for caresuite scenario *****************************************
	/******************************************************************************************************/

    
	
	/*public static List<Registry> getRegisteriesforCareSuite() {
		Long time=System.currentTimeMillis();

		List<Registry> registriesAcc = new ArrayList<Registry>();
		try {
			// Open the Excel file
			String excelFilePath1 = properties.getProperty("ScenarioExcelPathforCareSuite"); // //
			//String excelFilePath1 = "C:\\Users\\E002174\\Desktop\\Jenkins Run\\5AM Automation\\5AMScenarios.xlsx";
			System.out.println(excelFilePath1);
			FileInputStream inputStream1 = new FileInputStream(new File(excelFilePath1));
			
			// Access the required test data sheet
			XSSFWorkbook ExcelWBook = new XSSFWorkbook(inputStream1);
			// Access the required test data sheet
			XSSFSheet SheetName=ExcelWBook.getSheetAt(0);//first sheet of the excel 
			//XSSFSheet sheet = ExcelWBook.getSheet(SheetName); // /SheetName
			System.out.println(SheetName);
			int rowCount = SheetName.getLastRowNum();
			System.out.println("rowcount:"+rowCount);
//			for (int i = rowCount; i > 0; i--) {
			for (int i = 1; i <= rowCount; i++) {
				String registry1 = "";
				Registry registry = new Registry();
				Row row = SheetName.getRow(i);
				String registryName = null;
				if (row.getCell(0) != null && row.getCell(0).getCellType() == 0) {
					registryName = row.getCell(0).getNumericCellValue() + "";
				} else if (row.getCell(0) != null
						&& row.getCell(0).getCellType() == 1) {
					registryName = row.getCell(0).getStringCellValue();
				}
				if (registryName != null) {
					registry1=registryName;
				}
				
				registry.setRegistryName(registry1);
				//registry.setRegistryWelcomeLabel(registry1);
				registriesAcc.add(registry);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("registries of ncdr are "+registriesAcc);
		System.out.println("Time taken for registeries:"+(System.currentTimeMillis()-time)+" msecs");
		return registriesAcc;
	}
	
	// end of this logic 
	
	// this method is to fetch the scenarios of type string
*/	
	
	// ************************************//


	public static String createReportWithTimeStamp(String sourceFilePath,String browserName,String moduleName) {
		String destFilePath = reportPath + "\\" + moduleName + "_" + browserName + "_"
				+ getCurrentTimeStamp() + ".html";
		//String sourceFilePath = reportPath + "\\testReports.html";// "Q:\\QA
																	// Stuff\\Syam
																	// pithani\\NCDR\\NCDR\\WebContent\\WEB-INF\\views\\testReports.html";//"Q:\\QA
																	// Stuff\\Syam
																	// pithani\\accautomation\\accicd\\automationACC\\test-output\\ExtentReports.html";

		try {
	System.out.println("Source path is "+sourceFilePath);
			
			System.out.println("destination path is "+destFilePath);
			File source = new File(sourceFilePath);
			File dest = new File(destFilePath);
		
			InputStream input = null;
			OutputStream output = null;
			try {
				if (!dest.exists()) {
					dest.createNewFile();
				}
				input = new FileInputStream(source);
				output = new FileOutputStream(dest);
				byte[] buf = new byte[1024];
				int bytesRead;
				while ((bytesRead = input.read(buf)) > 0) {
					output.write(buf, 0, bytesRead);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				input.close();
				output.close();
			}
			return destFilePath;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		String timeStamp = new java.sql.Timestamp(today.getTime()).toString();
		timeStamp = timeStamp.replaceAll(" ", "_");
		timeStamp = timeStamp.replaceAll("\\.", "_");
		timeStamp = timeStamp.replaceAll("\\:", "_");
		return timeStamp;
	}

	

	public static String loadProperty(String name) {
		Properties prop = new Properties();
		InputStream input = null;
		String value = null;
		try {

			// input = new FileInputStream("constants.properties");

			// load a properties file
			// prop.load(input);
			prop.load(Utils.class.getClassLoader().getResourceAsStream(
					"constants.properties"));
			value = prop.getProperty(name);
			// get the property value and print it out

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	public static void page_wait() {
		try {
			Thread.sleep(10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void page_long_wait() {
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void page_long_wait1() {
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void page_wait(WebDriver driver) {
		String browserName = "";
		try {
			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			browserName = cap.getBrowserName().toLowerCase();
			System.out.println(browserName);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (browserName.equalsIgnoreCase("chrome")
				|| browserName.equalsIgnoreCase("firefox")) {
			System.out.println("Implicit wait for chrome/firefox 4 secs");
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		} else {
			System.out.println("Implicit wait for IE 10 secs");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			/*try {
				driver.wait(10*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			/*try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}



	

}
