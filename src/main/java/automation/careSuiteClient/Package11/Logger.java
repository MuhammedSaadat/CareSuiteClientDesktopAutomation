package automation.careSuiteClient.Package11;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.windows.WindowsDriver;


public class Logger {

	static Map<Integer,ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	
	/*static ExtentReports extent = null;//new ExtentReports("C:\\Users\\E002226\\Documents\\workspace-sts-3.7.0.RELEASE\\ExtentReportsTestNG\\test-output\\ExtentReports.html"); 
	// ExtentTest extentTest=null;
	 public synchronized void createTestReport(String filePath){
    	  extent=new ExtentReports(filePath);
     }*/
	
	 public   ExtentTest createTest(String testName,String discription,ExtentReports extent){
		 //ExtentReports extent=ExtentReportFactory.getInstance().getReport();
		  ExtentTest extentTest =extent.startTest(testName,discription); 
		  extentTestMap.put((int) (long) (Thread.currentThread().getId()), extentTest);
		  return extentTest;
	 }
	 public   void endTest(ExtentReports extent){
		 //ExtentReports extent=ExtentReportFactory.getInstance().getReport();
		 extent.endTest(extentTestMap.get((int) (long) (Thread.currentThread().getId())));
	 }
	 
	 public void log(String status, String message,boolean screenShot,WindowsDriver<WebElement> driver,String fileLocation,ExtentTest extentTest) {
		 //ExtentReports extent=ExtentReportFactory.getInstance().getReport();
		// WebDriver driver = BrowserFactory.getInstance().getDriver();
		 
		 LogStatus logStatus=LogStatus.FAIL;
		 if(status.equalsIgnoreCase("pass")){
			 logStatus=LogStatus.PASS;
		 }else if(status.equalsIgnoreCase("info")){
			 logStatus=LogStatus.INFO;
		 }
			if(!screenShot)
				extentTest.log(logStatus, message);
			else {
				String imgPath = createScreenshot(driver,fileLocation);
				/*
				 * et.log(LogStatus.FAIL, "HTML", "<span>" + message +
				 * "Error Snapshot below</span><br/><img class='report-img' src=" +
				 * imgPath + ">");
				 */
				extentTest.log(logStatus, "HTML",
						"<span>" + message + "Error Snapshot below</span><br/>"
								+ extentTest.addScreenCapture(imgPath));
			}
			

		}

		public  String createScreenshot(WindowsDriver<WebElement> driver,String fileLocation) {
			//ExtentReports extent=ExtentReportFactory.getInstance().getReport();
			//WebDriver driver = BrowserFactory.getInstance().getDriver();

			UUID uuid = UUID.randomUUID();

			// generate screenshot as a file object
			File scrFile = (File) ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			try {
				// copy file object to designated location
				FileUtils.copyFile(scrFile, new File(fileLocation
						+ uuid + ".png"));
			} catch (IOException e) {
				System.out.println("Error while generating screenshot:\n"
						+ e.toString());
			}
			String path = fileLocation + uuid + ".png";
			path = path.replaceAll(" ", "%20");
			return path;
		}
		
		public void flush(ExtentReports extent){
			//ExtentReports extent=ExtentReportFactory.getInstance().getReport();
			extent.flush();
		}
}
