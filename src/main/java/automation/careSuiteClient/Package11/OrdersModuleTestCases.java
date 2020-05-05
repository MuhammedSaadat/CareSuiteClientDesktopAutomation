package automation.careSuiteClient.Package11;

import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import automation.careSuiteClient.utility.Utils;
import io.appium.java_client.windows.WindowsDriver;

public class OrdersModuleTestCases {
	
	   //Reports creation objects	
		private static String reportingPath = Utils.loadProperty("reportingPath");
		private static String reportPath = Utils.loadProperty("reportPath");
		private ExtentReports extentReport;
		protected  Logger logger=new Logger();
		String destFilePath = null;
		ExtentTest et=null;
		private String moduleName="Orders";
		static String platform;
		static String seleniumGridNodeUrl;
		
		//Business class objects
		Keywords actions = new Keywords();
		UiMap map = new UiMap();
		MedPassModuleCommon medPasModCom = new MedPassModuleCommon();
		OrdersModuleCommon ordModCom = new OrdersModuleCommon();
		ClientCommon cliCom = new ClientCommon();
		WindowsDriver<WebElement> applicationSessionObject;
		WindowsDriver<WebElement> desktopSessionObject;
		
		//Hashtable for storing test case data
		private Hashtable<String, String> testCaseData;
		
		//Resident Names
		String residentName1Format1="ETHEL ANDERSON"; //"ASHLEY AINSWORTH"; //"ETHEL ANDERSON";
		String residentName1Format2="ANDERSON, ETHEL"; //"AINSWORTH, ASHLEY"; //"ANDERSON, ETHEL";
		static String medicationName;
		
		//WinAppDriver server object
		static Process p;
		
		//Storing medication data
		static LinkedHashMap<String, String> hashmapMedicationData = new LinkedHashMap<String, String>();
		
	
		@BeforeClass
		@Parameters({"platform","seleniumGridNodeUrl"})
		public void createReportAndLaunchApplication(String testNgplatform,String testNgseleniumGridNodeUrl) throws Exception
		{
			try
			{
			platform=testNgplatform;	
			seleniumGridNodeUrl=testNgseleniumGridNodeUrl;
			
			//Open report		
			reportingPath = Utils.loadProperty("reportingPath");
			reportPath = Utils.loadProperty("reportPath");
			ExtentReportFactory.getInstance().createTestReport(reportPath + "\\testReports.html");
			extentReport = ExtentReportFactory.getInstance().getReport();
			
			//Get test case data from excel
			testCaseData=new CSSAutomationTestCaseData().getTestCaseData(Utils.loadProperty("testCaseSheetPath"), moduleName);
	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
			
		}
		
		@AfterClass
		//@Parameters({"platform","seleniumGridNodeUrl"})
		//public void closeReport(String platform,String seleniumGridNodeUrl)
		public void closeReport()
		{
			try
			{
			//Close report
			destFilePath = Utils.createReportWithTimeStamp(reportPath + "\\testReports.html", platform,moduleName);
			
			/*//Close application
			//Create desktop session
			DriverFactoryDesktopSession.getInstance().setDriver(platform,seleniumGridNodeUrl);
			desktopSessionObject = DriverFactoryDesktopSession.getInstance().getDriver();
						
			//Clicking on 'Cancel' button		
			actions.waitForElementVisibility(map.loginWindow, "Name", desktopSessionObject, 15);
			desktopSessionObject.findElement(map.cancelButtonLoginWindow).click();*/
		
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
		
		@Test (enabled=true ,priority=1) 
		//@Parameters({"platform","seleniumGridNodeUrl"})
		//public void createAMedPass(String platform,String seleniumGridNodeUrl)
		public void verifyAddingOfOrderTypeMedicationToResident ()
		{
			try {
				String testCaseID="QM-5219";
				
				if(testCaseData.get(testCaseID).equals("Y"))
				{
				
				//Add test case to report				
				ExtentTest et = new Logger().createTest(
						"Verify the adding of Order type Medication to Resident ",
						"QM-5219",extentReport);
				
				//Login to application
				cliCom.loginToApplication(Utils.loadProperty("FacAdminUserId2"),Utils.loadProperty("FacAdminPassword2"),et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
				
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Create a med of type 'Medication'
				medPasModCom.createMedOfTypeMedicationInManageOrdersScreen("POWDER","12:00 AM","test, test",
						"DAILY","No","", et);
				
				//Select the medication from Orders tab
				medPasModCom.selectMedicationFromOrdersTabManageOrdersScreen(MedPassModuleTestCases.medicationName,"","Yes","",et);
				
				//Discontinue the created med
				medPasModCom.discontinueMedInOrdersScreen(-8,-8,et);
				
				//Logout from the application
				cliCom.logout(et);
				
				//Logger flush
				logger.endTest(extentReport);
				logger.flush(extentReport);
				
				//}			
				
			}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		@Test (enabled=true ,priority=2)
		//@Parameters({"platform","seleniumGridNodeUrl"})
		//public void createAMedPass(String platform,String seleniumGridNodeUrl)
		public void verifyFunctionalityOfDiscontinuingMedication()
		{
			try {
				String testCaseID="QM-5304";
				
				if(testCaseData.get(testCaseID).equals("Y"))
				{
					
				//Add test case to report				
				ExtentTest et = new Logger().createTest(
						"Verify the functionality of Discontinuing a medication",
						"QM-5304",extentReport);
				
				//Login to application
				cliCom.loginToApplication(Utils.loadProperty("FacAdminUserId2"),Utils.loadProperty("FacAdminPassword2"),et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
				
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Create a med of type 'Medication'
				medPasModCom.createMedOfTypeMedicationInManageOrdersScreen("POWDER","12:00 AM","test, test",
						"DAILY","No","", et);
				
				//Select the medication from Orders tab
				medPasModCom.selectMedicationFromOrdersTabManageOrdersScreen(MedPassModuleTestCases.medicationName,"","Yes","",et);
				
				//Discontinue the created med
				medPasModCom.discontinueMedInOrdersScreen(-8,-8,et);
				
				//Logout from the application
				cliCom.logout(et);
				
				//Logger flush
				logger.endTest(extentReport);
				logger.flush(extentReport);
				
				//}			
				
			}
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
}
