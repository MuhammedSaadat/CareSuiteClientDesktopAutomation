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

public class MedPassModuleTestCases {
	
	   //Reports creation objects	
		private static String reportingPath = Utils.loadProperty("reportingPath");
		private static String reportPath = Utils.loadProperty("reportPath");
		private ExtentReports extentReport;
		protected  Logger logger=new Logger();
		String destFilePath = null;
		ExtentTest et=null;
		private String moduleName="MedPass";
		static String platform;
		static String seleniumGridNodeUrl;
		
		//Business class objects
		Keywords actions = new Keywords();
		UiMap map = new UiMap();
		MedPassModuleCommon medPasModCom = new MedPassModuleCommon();
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
		
		/*//Working code with 'console display' issues
		@BeforeSuite
		public void startWinAppDriver()
		{
		try 
		{
			
			String command = "C:\\Program Files (x86)\\Windows Application Driver\\WinAppDriver.exe";
			ProcessBuilder builder = new ProcessBuilder(command).inheritIO();
			p = builder.start();
			System.out.println("Started the server");
			
		}
		catch (Exception e) 	
		{
			e.printStackTrace();

		}
		}
		
		@AfterSuite
		public void stopWinAppDriver() throws IOException
		{
		try
		{
		p.destroy();
		System.out.println("Stopped the server");
		}
		catch (Exception e) 	
		{
			e.printStackTrace();

		}
		}*/
		 
		//Working code with no issues
		@BeforeSuite
		//Starts WinAppDriver using batch file
		public void startWinAppDriver()
		{
		try 
		{
			
			String command ="C:\\ClientAutomation\\careSuiteClient\\WinAppDriverServer\\Start_WinAppDriver.bat";
			Process p = Runtime.getRuntime().exec(command);
			p.waitFor();
			System.out.println("Started the server");
			
			
		}
		catch (Exception e) 	
		{
			e.printStackTrace();

		}
		}
		
		@AfterSuite
		//Stops WinAppDriver using batch file
		public void stopWinAppDriver() throws IOException
		{
		try
		{
			String command ="C:\\ClientAutomation\\careSuiteClient\\WinAppDriverServer\\Stop_WinAppDriver.bat";
			Process p = Runtime.getRuntime().exec(command);
			p.waitFor();
			System.out.println("Stopped the server");
		}
		catch (Exception e) 	
		{
			e.printStackTrace();

		}
		}
		
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
		public void verifyMedPassFunctionality()
		{
			try {
				String testCaseID="QM-5096";
				
				if(testCaseData.get(testCaseID).equals("Y"))
				{
				
				//Add test case to report				
				ExtentTest et = new Logger().createTest(
						"Verify the Med pass functionality",
						"QM-5096",extentReport);
				
				//Login to application
				cliCom.loginToApplication(Utils.loadProperty("FacAdminUserId2"),Utils.loadProperty("FacAdminPassword2"),et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
				
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Create a med of type 'Medication'
				medPasModCom.createMedOfTypeMedicationInManageOrdersScreen("POWDER","12:00 AM","test, test",
						"DAILY","No","", et);
								
				//Navigate to Pass Meds -> AM
				cliCom.navigateTo("Pass Meds!!AM", et);
				
				//Select the Resident
				medPasModCom.selectingResidentFromMedPassScreen(residentName1Format1,et);
				
				//Select the Medication
				medPasModCom.selectMedicationPassMedsScreen(medicationName,"Yes","validateTickMark",et);
				
				//Click on 'Next' button
				medPasModCom.clickNextButtonAndContinueButtonFromPickListExceptionsWindowIfPresent("Present",et);
				
				//Click on 'Record All' button
				medPasModCom.clickRecordAllButtonFromConfirmationScreen(et);
				
				//Click on 'OK' button from 'Confirm Med Pass' window
				medPasModCom.clickOKButtonConfirmMedPassWindow(et);
				
				//Validate display of 'Resident List' screen
				medPasModCom.validatePassMedsResidentScreenIsDisplayed(et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
							
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Select the medication from Orders tab
				medPasModCom.selectMedicationFromOrdersTabManageOrdersScreen(medicationName,"","Yes","",et);
				
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
		public void verifyRecordExceptionsFunctionalityDuringMedPass()
		{
			try {
				String testCaseID="QM-5136";
				
				if(testCaseData.get(testCaseID).equals("Y"))
				{
					
				//Add test case to report				
				ExtentTest et = new Logger().createTest(
						"Verify Record exceptions functionality during a Med pass",
						"QM-5136",extentReport);
				
				//Login to application
				cliCom.loginToApplication(Utils.loadProperty("FacAdminUserId2"),Utils.loadProperty("FacAdminPassword2"),et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
				
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Creating a med of type 'Medication'
				medPasModCom.createMedOfTypeMedicationInManageOrdersScreen("POWDER","12:00 AM","test, test",
						"DAILY","No","", et);
								
				//Navigate to Pass Meds -> AM
				cliCom.navigateTo("Pass Meds!!AM", et);
				
				//Select the Resident
				medPasModCom.selectingResidentFromMedPassScreen(residentName1Format1,et);
				
				//Select the Medication
				medPasModCom.selectMedicationPassMedsScreen(medicationName,"Yes","validateTickMark",et);
				
				//Click on 'Next' button
				medPasModCom.clickNextButtonAndContinueButtonFromPickListExceptionsWindowIfPresent("Present",et);
				
				//Create exception of type 'Resident refused'
				medPasModCom.createExceptiononfirmationScreen("Resident refused",et);
				
				//Click on 'Record All' button
				medPasModCom.clickRecordAllButtonFromConfirmationScreen(et);
				
				//Click on 'OK' button from 'Confirm Med Pass' window
				medPasModCom.clickOKButtonConfirmMedPassWindow(et);
				
				//Validate display of 'Resident List' screen
				medPasModCom.validatePassMedsResidentScreenIsDisplayed(et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
											
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
								
				//Select the medication from Orders tab
				medPasModCom.selectMedicationFromOrdersTabManageOrdersScreen(medicationName,"","Yes","",et);
								
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
		
		@Test (enabled=true ,priority=3)
		//@Parameters({"platform","seleniumGridNodeUrl"})
		//public void createAMedPass(String platform,String seleniumGridNodeUrl)
		public void verifyTrackingOfMedsTakenFromAnEmergencyKitInMedPassScreen()
		{
			try {
				String testCaseID="QM-5146";
				
				if(testCaseData.get(testCaseID).equals("Y"))
				{
					
				//Add test case to report				
				ExtentTest et = new Logger().createTest(
						"Verify the tracking of Meds Taken from an Emergency Kit in Med pass screen",
						"QM-5146",extentReport);
				
				//Login to application
				cliCom.loginToApplication(Utils.loadProperty("FacAdminUserId2"),Utils.loadProperty("FacAdminPassword2"),et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
				
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Creating a med of type 'Medication'
				medPasModCom.createMedOfTypeMedicationInManageOrdersScreen("POWDER","12:00 AM","test, test",
						"DAILY","Yes","", et);
								
				//Navigate to Pass Meds -> AM
				cliCom.navigateTo("Pass Meds!!AM", et);
				
				//Select the Resident
				medPasModCom.selectingResidentFromMedPassScreen(residentName1Format1,et);
				
				//Select the Medication
				medPasModCom.selectMedicationPassMedsScreen(medicationName,"Yes","validateTickMark",et);
				
				//Click on 'Next' button
				medPasModCom.clickNextButtonAndContinueButtonFromPickListExceptionsWindowIfPresent("Present",et);
				
				//Clicking 'Take from e-box' - 'No Inventory' window
				medPasModCom.clickingTakeFromEBoxNoInventoryWindow("Yes",et);			
				
				//Click on 'Record All' button
				medPasModCom.clickRecordAllButtonFromConfirmationScreen(et);
				
				//Validate data in 'Inventory Tracking Acknowledgement' window and click on 'OK' button
				medPasModCom.validateDataInInventoryTrackingAcknowledgementWindowAndClickOKButton(medicationName,"0.0 POWDER",et);
								
				//Validate details in 'Witness Confirmation' window and enter witness credentials
				medPasModCom.validateDetailsInWitnessConfirmationWindowAndEnterWitnessCredentials("","","",Utils.loadProperty("SupAdminUserId"),Utils.loadProperty("SupAdminPassword"),et);
				
				//Validate display of 'Resident List' screen
				medPasModCom.validatePassMedsResidentScreenIsDisplayed(et);
				
				//Navigate to 'Reports>>Inventory>>Meds taken from Ekit'
				cliCom.navigateTo("Reports!!Inventory!!Meds Taken from E-Kit", et);
				
				//Generate report for 'Meds Taken from E-Kit' and validate
				medPasModCom.generateReportForMedsTakenFromEKitAndValidate("4/2/2020","","","Yes", et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
											
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
								
				//Select the medication from Orders tab
				medPasModCom.selectMedicationFromOrdersTabManageOrdersScreen(medicationName,"","Yes","",et);
								
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
		
		@Test (enabled=true,priority=4)
		//@Parameters({"platform","seleniumGridNodeUrl"})
		//public void createAMedPass(String platform,String seleniumGridNodeUrl)
		public void verifyDisplayOfWitnessLoginPromptForControlledMedWhilePassingMed ()
		{
			try {
				String testCaseID="QM-5159";
				
				if(testCaseData.get(testCaseID).equals("Y"))
				{
					
				//Add test case to report				
				ExtentTest et = new Logger().createTest(
						"Verify the  display of witness login prompt for controlled med while passing med ",
						"QM-5159",extentReport);
				
				//Login to application
				cliCom.loginToApplication(Utils.loadProperty("FacAdminUserId2"),Utils.loadProperty("FacAdminPassword2"),et);
				
				//Navigate to Settings -> Facility Settings
				
				
				//Med Pass Customization
				
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
				
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Creating a med of type 'Medication'
				medPasModCom.createMedOfTypeMedicationInManageOrdersScreen("POWDER","12:00 AM","test, test",
						"DAILY","Yes","", et);
								
				//Navigate to Pass Meds -> AM
				cliCom.navigateTo("Pass Meds!!AM", et);
				
				//Select the Resident
				medPasModCom.selectingResidentFromMedPassScreen(residentName1Format1,et);
				
				//Select the Medication
				medPasModCom.selectMedicationPassMedsScreen(medicationName,"Yes","validateTickMark",et);
				
				//Click on 'Next' button
				medPasModCom.clickNextButtonAndContinueButtonFromPickListExceptionsWindowIfPresent("Present",et);
				
				//Clicking 'Take from e-box' - 'No Inventory' window
				medPasModCom.clickingTakeFromEBoxNoInventoryWindow("Yes",et);			
				
				//Click on 'Record All' button
				medPasModCom.clickRecordAllButtonFromConfirmationScreen(et);
				
				//Validate data in 'Inventory Tracking Acknowledgement' window and click on 'OK' button
				medPasModCom.validateDataInInventoryTrackingAcknowledgementWindowAndClickOKButton(medicationName,"0.0 POWDER",et);
								
				//Validate details in 'Witness Confirmation' window and enter witness credentials
				medPasModCom.validateDetailsInWitnessConfirmationWindowAndEnterWitnessCredentials("","","",Utils.loadProperty("SupAdminUserId"),Utils.loadProperty("SupAdminPassword"),et);
				
				//Validate display of 'Resident List' screen
				medPasModCom.validatePassMedsResidentScreenIsDisplayed(et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
											
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
								
				//Select the medication from Orders tab
				medPasModCom.selectMedicationFromOrdersTabManageOrdersScreen(medicationName,"","Yes","",et);
								
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
		
		@Test (enabled=true,priority=5)
		//@Parameters({"platform","seleniumGridNodeUrl"})
		//public void createAMedPass(String platform,String seleniumGridNodeUrl)
		public void verifyDisplayOfExceptionsInConfirmationScreenOnClickOfSkipAllButton ()
		{
			try {
				String testCaseID="QM-11171";
				String note="Test note"+actions.generateRandomAlphaString(5);
				
				if(testCaseData.get(testCaseID).equals("Y"))
				{
					
				//Add test case to report				
				ExtentTest et = new Logger().createTest(
						"Verify the display of Exceptions in Confirmation Screen on click of Skip All button",
						"QM-11171",extentReport);
				
				//Login to application
				cliCom.loginToApplication(Utils.loadProperty("FacAdminUserId2"),Utils.loadProperty("FacAdminPassword2"),et);
				
				
				//Navigate to Admin -> Settings -> Drop down menus
				
				
				//Select Require Note check box 
				
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
				
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Creating a med of type 'Medication'
				medPasModCom.createMedOfTypeMedicationInManageOrdersScreen("POWDER","12:00 AM","test, test",
						"DAILY","No","", et);
								
				//Navigate to Pass Meds -> AM
				cliCom.navigateTo("Pass Meds!!AM", et);
				
				//Select the Resident
				medPasModCom.selectingResidentFromMedPassScreen(residentName1Format1,et);
								
				//Click on 'Skip All' button,validate exceptions and select an exception
				medPasModCom.clickOnSkipAllButtonValidateExceptionsAndSelectAnExceptionInConfirmationScreen("ValidateOptions","Withheld per RN/Doctor's request",et);
				
				//Validate the exception name for all meds in confirmation screen
				medPasModCom.validateExceptionNameForAllMedsInConfirmationScreen("Withheld per RN/Doctor's request",et);
				
				//Click on 'Record All' button
				medPasModCom.clickRecordAllButtonFromConfirmationScreen(et);
								
				//Validate the warning message
				medPasModCom.validateWarningMessageInCannotCompleteMedPassWindow("A note is required for the selected exception on the order",et);
				
				//Select icon 'Add Note to One or more results icon' , add a note and click 'OK'
				medPasModCom.selectIconAddNoteAndAddNoteClickOk(note,et);
								
				//Click on 'Record All' button
				medPasModCom.clickRecordAllButtonFromConfirmationScreen(et);
				
				//Click on 'OK' button from 'Confirm Med Pass' window
				medPasModCom.clickOKButtonConfirmMedPassWindow(et);
				
				//Enter witness credentials  in 'Witness Confirmation' window if present 
				medPasModCom.ifWitnessConfirmationWindowIsPresentEnterWitnessCredentials("","","",Utils.loadProperty("SupAdminUserId"),Utils.loadProperty("SupAdminPassword"),et); 
				
				//Validate display of 'Resident List' screen
				medPasModCom.validatePassMedsResidentScreenIsDisplayed(et);
				
				//Select the Resident
				medPasModCom.selectingResidentFromMedPassScreen(residentName1Format1,et);
								
				//Validate that all the meds should be recorded
				medPasModCom.selectMedicationPassMedsScreen(medicationName,"No","validateTickMark",et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
											
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
								
				//Select the medication from Orders tab
				medPasModCom.selectMedicationFromOrdersTabManageOrdersScreen(medicationName,"","Yes","",et);
								
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
		
		@Test (enabled=true ,priority=6) 
		//@Parameters({"platform","seleniumGridNodeUrl"})
		//public void createAMedPass(String platform,String seleniumGridNodeUrl)
		public void verifyAvailabilityOfAmountGivenInLast24HoursForPRNMedAtMedTile()
		{
			try {
				String testCaseID="QM-6125";
				
				if(testCaseData.get(testCaseID).equals("Y"))
				{
					
				//Add test case to report				
				ExtentTest et = new Logger().createTest(
						"Verify the availability of amount given in the last 24 hours for PRN med at med Tile ",
						"QM-6125",extentReport);
				
				//Login to application
				cliCom.loginToApplication(Utils.loadProperty("FacAdminUserId2"),Utils.loadProperty("FacAdminPassword2"),et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
				
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Create a med of type 'Medication' with PRN
				medPasModCom.createMedOfTypePRNMedicationInManageOrdersScreen("TAB","12:00 AM","test, test",
						"","","Yes","2","",et);
								
				//Navigate to Pass Meds -> PRN
				cliCom.navigateTo("Pass Meds!!PRN", et);
				
				//Select the Resident
				medPasModCom.selectingResidentFromMedPassScreen(residentName1Format1,et);
				
				//Select the Medication
				medPasModCom.selectMedicationPassMedsScreen(medicationName,"Yes","validateTickMark",et);
				
				//Click on 'Next' button
				medPasModCom.clickNextButtonAndContinueButtonFromPickListExceptionsWindowIfPresent("Absent",et);
				
				//Enter details amount, purpose in confirmation screen
				medPasModCom.enterDetailsAmountPurposeInConfirmationScreen("1","FEVER","",et);
				
				//Click on 'Record All' button
				medPasModCom.clickRecordAllButtonFromConfirmationScreen(et);
				
				//Click on 'OK' button from 'Confirm Med Pass' window
				medPasModCom.clickOKButtonConfirmMedPassWindow(et);
				
				//Validate display of 'Resident List' screen
				medPasModCom.validatePassMedsResidentScreenIsDisplayed(et);
				
				//Select the Resident
				medPasModCom.selectingResidentFromMedPassScreen(residentName1Format1,et);
				
				//Validate medication data of PRN medication
				medPasModCom.validateMedicationDataInPassMedsScreen(medicationName,"24","1","TAB","",et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
											
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
								
				//Select the medication from Orders tab
				medPasModCom.selectMedicationFromOrdersTabManageOrdersScreen(medicationName,"","Yes","",et);
								
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
		
		@Test (enabled=true ,priority=7) 
		//@Parameters({"platform","seleniumGridNodeUrl"})
		//public void createAMedPass(String platform,String seleniumGridNodeUrl)
		public void verifyDisplayOfWarningMessageBoxWhenAmountAlreadyExceededInLast24Hours()
		{
			try {
				String testCaseID="QM-6129";
				
				if(testCaseData.get(testCaseID).equals("Y"))
				{
					
				//Add test case to report				
				ExtentTest et = new Logger().createTest(
						"Verify the display of Warning message box When Amount Already Exceeded  in last 24 hours.",
						"QM-6129",extentReport);
				
				//Login to application
				cliCom.loginToApplication(Utils.loadProperty("FacAdminUserId2"),Utils.loadProperty("FacAdminPassword2"),et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
				
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Create a med of type 'Medication' with PRN
				medPasModCom.createMedOfTypePRNMedicationInManageOrdersScreen("TAB","12:00 AM","test, test",
						"","","Yes","2","",et);
								
				//Navigate to Pass Meds -> PRN
				cliCom.navigateTo("Pass Meds!!PRN", et);
				
				//Select the Resident
				medPasModCom.selectingResidentFromMedPassScreen(residentName1Format1,et);
				
				//Select the Medication
				medPasModCom.selectMedicationPassMedsScreen(medicationName,"Yes","validateTickMark",et);
				
				//Click on 'Next' button
				medPasModCom.clickNextButtonAndContinueButtonFromPickListExceptionsWindowIfPresent("Absent",et);
				
				//Enter details amount, purpose in confirmation screen
				medPasModCom.enterDetailsAmountPurposeInConfirmationScreen("2","FEVER","",et);
				
				//Click on 'Record All' button
				medPasModCom.clickRecordAllButtonFromConfirmationScreen(et);
				
				//Click on 'OK' button from 'Confirm Med Pass' window
				medPasModCom.clickOKButtonConfirmMedPassWindow(et);
				
				//Navigate to Pass Meds -> AM
				cliCom.navigateTo("Pass Meds!!AM", et);
				
				//Select the Resident
				medPasModCom.selectingResidentFromMedPassScreen(residentName1Format1,et);
				
				//Click 'Show PRN' button in med selection screen
				medPasModCom.clickShowPRNButtonInMedicationSelectionScreen(et);
				
				//Select the Medication
				medPasModCom.selectMedicationPassMedsScreen(medicationName,"Yes","validateTickMark",et);
				
				//Click on 'Next' button
				medPasModCom.clickNextButtonAndContinueButtonFromPickListExceptionsWindowIfPresent("Present",et);
				
				//Validate data in 'Confirm Med Pass' window when Amount Already Exceeded In Last 24 Hours and click 'OK' button
				medPasModCom.validateDataInConfirmMedPassWindowWhenAmountAlreadyExceededInLast24HoursEnterTextClickOkButton(medicationName,"24","2","TAB","","","",et);
								
				//Enter details amount, purpose in confirmation screen
				medPasModCom.enterDetailsAmountPurposeInConfirmationScreen("2","FEVER","",et);
				
				//Click on 'Record All' button
				medPasModCom.clickRecordAllButtonFromConfirmationScreen(et);
				
				//Validate data in 'Confirm Med Pass' window when Amount Already Exceeded In Last 24 Hours, enter text and click 'OK' button
				medPasModCom.validateDataInConfirmMedPassWindowWhenAmountAlreadyExceededInLast24HoursEnterTextClickOkButton(medicationName,"24","2","TAB","Test reason","","",et);
				
				//Click on 'OK' button from 'Confirm Med Pass' window
				medPasModCom.clickOKButtonConfirmMedPassWindow(et);
				
				//Validate display of 'Resident List' screen
				medPasModCom.validatePassMedsResidentScreenIsDisplayed(et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
											
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
								
				//Select the medication from Orders tab
				medPasModCom.selectMedicationFromOrdersTabManageOrdersScreen(medicationName,"","Yes","",et);
								
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
		
		@Test (enabled=true ,priority=8) 
		//@Parameters({"platform","seleniumGridNodeUrl"})
		//public void createAMedPass(String platform,String seleniumGridNodeUrl)
		public void verifyMedpassProcessOfDiscontinuedMeds()
		{
			try {
				String testCaseID="QM-5182";
				
				if(testCaseData.get(testCaseID).equals("Y"))
				{
					
				//Add test case to report				
				ExtentTest et = new Logger().createTest(
						"Verify the Medpass process of Discontinued meds",
						"QM-5182",extentReport);
				
				//Login to application
				cliCom.loginToApplication(Utils.loadProperty("FacAdminUserId2"),Utils.loadProperty("FacAdminPassword2"),et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
				
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Create a med of type 'Medication'
				medPasModCom.createMedOfTypeMedicationInManageOrdersScreen("POWDER","12:00 AM","test, test",
						"DAILY","No","", et);
								
				//Discontinue the created med
				medPasModCom.discontinueMedInOrdersScreen(-2,-2,et);
				
				//Navigate to Pass Meds -> AM
				cliCom.navigateTo("Pass Meds!!AM", et);
				
				//Select the Resident
				medPasModCom.selectingResidentFromMedPassScreen(residentName1Format1,et);
				
				//Validate the medication should be disabled
				medPasModCom.selectMedicationPassMedsScreen(medicationName,"Yes","validateTickMarkAbsence",et);
				
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

		@Test (enabled=true ,priority=9) 
		//@Parameters({"platform","seleniumGridNodeUrl"})
		//public void createAMedPass(String platform,String seleniumGridNodeUrl)
		public void verifySaveSelectionsButtonFunctionalityInResidentMedPassScreen()
		{
			try {
				String testCaseID="QM-5108";
				
				if(testCaseData.get(testCaseID).equals("Y"))
				{
					
				//Add test case to report				
				ExtentTest et = new Logger().createTest(
						"Verify the Save Selections button functionality in Resident Med pass  screen",
						"QM-5108",extentReport);
				
				//Login to application
				cliCom.loginToApplication(Utils.loadProperty("FacAdminUserId2"),Utils.loadProperty("FacAdminPassword2"),et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
				
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Create a med of type 'Medication'
				medPasModCom.createMedOfTypeMedicationInManageOrdersScreen("POWDER","12:00 AM","test, test",
						"DAILY","No","", et);
								
				//Navigate to Pass Meds -> AM
				cliCom.navigateTo("Pass Meds!!AM", et);
				
				//Select the Resident
				medPasModCom.selectingResidentFromMedPassScreen(residentName1Format1,et);
				
				//Select the Medication
				medPasModCom.selectMedicationPassMedsScreen(medicationName,"Yes","validateTickMark",et);
				
				//Click on 'Save Selections' button
				medPasModCom.clickSaveSelectionsButtonMedicationListScreen(et);
				
				//Validate display of 'Resident List' screen
				medPasModCom.validatePassMedsResidentScreenIsDisplayed(et);
				
				//Validate the presence of 'Push Pin' icon for resident and click on 'Push pin' icon
				medPasModCom.validatePushPinIconForResidentAndClick(residentName1Format1,"Present","Select","",et);
				
				//Validate the presence of 'Tick Mark' for Medication
				medPasModCom.selectMedicationPassMedsScreen(medicationName,"No","validateTickMark",et);
				
				//Validate the presence of 'Push Pin' icon for selected medication
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
							
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Select the medication from Orders tab
				medPasModCom.selectMedicationFromOrdersTabManageOrdersScreen(medicationName,"","Yes","",et);
				
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
		
		@Test (enabled=true ,priority=10) 
		//@Parameters({"platform","seleniumGridNodeUrl"})
		//public void createAMedPass(String platform,String seleniumGridNodeUrl)
		public void verifyDisplayOfPRNAndRoutineSplitOrdersInMedpassScreen()
		{
			try {
				String testCaseID="QM-5172";
				
				if(testCaseData.get(testCaseID).equals("Y"))
				{
					
				//Add test case to report				
				ExtentTest et = new Logger().createTest(
						"Verify the display of PRN and Routine split orders in  medpass screen",
						"QM-5172",extentReport);
				
				//Login to application
				cliCom.loginToApplication(Utils.loadProperty("FacAdminUserId2"),Utils.loadProperty("FacAdminPassword2"),et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
				
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Create a med of type 'Medication' with split orders (PRN and Routine) -- Update 
				medPasModCom.createMedOfTypePRNMedicationInManageOrdersScreen("TAB","12:00 AM","test, test",
						"","","","","AddPartRoutineDaily",et);
								
				//Navigate to Pass Meds -> AM
				cliCom.navigateTo("Pass Meds!!AM", et);
				
				//Select the Resident
				medPasModCom.selectingResidentFromMedPassScreen(residentName1Format1,et);
				
				//Validate presence of Medication
				medPasModCom.selectMedicationPassMedsScreen(medicationName,"No","validateMedicationPresent",et);
				
				//Click 'Show PRN' button in med selection screen
				medPasModCom.clickShowPRNButtonInMedicationSelectionScreen(et);
				
				//Validate presence of Medication
				medPasModCom.selectMedicationPassMedsScreen(medicationName,"No","validateMedicationPresent",et);
				
				//Select the Medication
				medPasModCom.selectMedicationPassMedsScreen(medicationName,"Yes","validateTickMark",et);
				
				//Click on 'Next' button
				medPasModCom.clickNextButtonAndContinueButtonFromPickListExceptionsWindowIfPresent("Present",et);
				
				//Click on 'Record All' button
				medPasModCom.clickRecordAllButtonFromConfirmationScreen(et);
				
				//Click on 'OK' button from 'Confirm Med Pass' window
				medPasModCom.clickOKButtonConfirmMedPassWindow(et);
				
				//Validate display of 'Resident List' screen
				medPasModCom.validatePassMedsResidentScreenIsDisplayed(et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
							
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Select the medication from Orders tab
				medPasModCom.selectMedicationFromOrdersTabManageOrdersScreen(medicationName,"","Yes","",et);
				
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
		
		@Test (enabled=true ,priority=11) 
		//@Parameters({"platform","seleniumGridNodeUrl"})
		//public void createAMedPass(String platform,String seleniumGridNodeUrl)
		public void verifyFunctionalityOfRecordingRoutineMedWithAnExceptionAndVitalValuesAtMedPassScreen()
		{
			try {
				String testCaseID="QM-7671";
				
				if(testCaseData.get(testCaseID).equals("Y"))
				{
					
				//Add test case to report				
				ExtentTest et = new Logger().createTest(
						"Verify the Functionality of Recording a Routine Med with an Exception and vital values at Med pass Screen",
						"QM-7671",extentReport);
				
				//Login to application
				cliCom.loginToApplication(Utils.loadProperty("FacAdminUserId2"),Utils.loadProperty("FacAdminPassword2"),et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
				
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Create a routine med of type 'Medication' with vital value 'BP'
				medPasModCom.createMedOfTypeMedicationWithVitalsInManageOrdersScreen("POWDER","12:00 AM","test, test",
						"DAILY","Yes","BP","",et);
								
				//Navigate to Pass Meds -> AM
				cliCom.navigateTo("Pass Meds!!AM", et);
				
				//Select the Resident
				medPasModCom.selectingResidentFromMedPassScreen(residentName1Format1,et);
				
				//Select the Medication
				medPasModCom.selectMedicationPassMedsScreen(medicationName,"Yes","validateTickMark",et);
				
				//Click on 'Next' button
				medPasModCom.clickNextButtonAndContinueButtonFromPickListExceptionsWindowIfPresent("Present",et);
				
				//Create exception of type 'Resident refused'
				medPasModCom.createExceptiononfirmationScreen("Resident refused",et);
				
				//Enter vital values
				medPasModCom.enterBPVitalDetaiInConfirmationScreen("120","80","",et);
				
				//Click on 'Record All' button
				medPasModCom.clickRecordAllButtonFromConfirmationScreen(et);
				
				//Click on 'OK' button from 'Confirm Med Pass' window
				medPasModCom.clickOKButtonConfirmMedPassWindow(et);
				
				//Validate display of 'Resident List' screen
				medPasModCom.validatePassMedsResidentScreenIsDisplayed(et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
							
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Select the medication from Orders tab
				medPasModCom.selectMedicationFromOrdersTabManageOrdersScreen(medicationName,"","Yes","",et);
				
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
		
		@Test (enabled=true ,priority=12) 
		//@Parameters({"platform","seleniumGridNodeUrl"})
		//public void createAMedPass(String platform,String seleniumGridNodeUrl)
		public void verifyReorderButtonFunctionalityInMedTile()
		{
			try {
				String testCaseID="QM-5106";
				
				if(testCaseData.get(testCaseID).equals("Y"))
				{
				//Test data for creating medication
				String randomValue=actions.generateRandomAlphaString(5);
				String instructions="Ins "+randomValue;
				String strength="Str "+randomValue;
				String diagnosis="Dia "+randomValue;
				String route="FEEDING TUBE";
				hashmapMedicationData.put("Strength", strength);
				hashmapMedicationData.put("Diagnosis", diagnosis);
				hashmapMedicationData.put("Route", route);
					
				//Add test case to report				
				ExtentTest et = new Logger().createTest(
						"Verify the Reorder button functionality in Med tile",
						"QM-5106",extentReport);
				
				//Login to application
				cliCom.loginToApplication(Utils.loadProperty("FacAdminUserId2"),Utils.loadProperty("FacAdminPassword2"),et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
				
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Create a med of type 'Medication'
				medPasModCom.createMedOfTypeMedicationInManageOrdersScreen("POWDER","12:00 AM","test, test",
						"DAILY","No","EnterStrDiagRou", et);
								
				//Navigate to Pass Meds -> AM
				cliCom.navigateTo("Pass Meds!!AM", et);
				
				//Select the Resident
				medPasModCom.selectingResidentFromMedPassScreen(residentName1Format1,et);
				
				//Validate medication data
				medPasModCom.validateMedicationDataPassMedsScreen(medicationName,"Strength/Route/Instructions/Diagnosis/Info/Reorder/History",et); 
				
				//Click on 'Reorder' button, validate UI and enter quantity
				medPasModCom.clickReorderValidateUIEnterQuantityInReorderMedicationWindow(medicationName,"Yes","40","",et);
				
				//Navigate to Home screen
				cliCom.navigateToHomeScreen(et);
				
				//Navigate to dashboard
				cliCom.navigateToDashboardViaHomeScreen(et);
												
				//Validate elements in Dashboard screen
				medPasModCom.validateElementsInDashboardScreen("",et);
				
				//Select 'Reorders Sent' tab
				
				
				//Validate the medication reordered
				
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateToWorkAround("Residents", et);
				cliCom.navigateTo("Residents!!Manage Orders", et);
							
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Select the medication from Orders tab
				medPasModCom.selectMedicationFromOrdersTabManageOrdersScreen(medicationName,"","Yes","",et);
				
				//Discontinue the created med
				medPasModCom.discontinueMedInOrdersScreen(-8,-8,et);
				
				//Logout from the application
				cliCom.logout(et);
				
				//Logger flush
				logger.endTest(extentReport);
				logger.flush(extentReport);
				
				//Clearing linked data from hashmap
				hashmapMedicationData.clear();
				
				//}			
				
			}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		@Test (enabled=true ,priority=13) 
		//@Parameters({"platform","seleniumGridNodeUrl"})
		//public void createAMedPass(String platform,String seleniumGridNodeUrl)
		public void verifyFunctionalityOfPrintButtonToPrintAdverseAffectsInDrugInformationPrintingPopUpWindowUnderMedTile()
		{
			try {
				String testCaseID="QM-6049";
				
				if(testCaseData.get(testCaseID).equals("Y"))
				{
				//Test data for creating medication
				String randomValue=actions.generateRandomAlphaString(5);
				String instructions="Ins "+randomValue;
				String strength="Str "+randomValue;
				String diagnosis="Dia "+randomValue;
				String route="FEEDING TUBE";
				hashmapMedicationData.put("Strength", strength);
				hashmapMedicationData.put("Diagnosis", diagnosis);
				hashmapMedicationData.put("Route", route);
				String drugInformationfileName="DrugInformation"+System.currentTimeMillis()+".pdf";
				hashmapMedicationData.put("DrugInformationFileName", drugInformationfileName);
				
				//Add test case to report				
				ExtentTest et = new Logger().createTest(
						"Verify the Functionality of print button to Print Adverse affects in the Drug information printing Pop Up window under med tile",
						"QM-6049",extentReport);
				
				//Login to application
				cliCom.loginToApplication(Utils.loadProperty("FacAdminUserId2"),Utils.loadProperty("FacAdminPassword2"),et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
				
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Create a med of type 'Medication'
				medPasModCom.createMedOfTypeMedicationInManageOrdersScreen("POWDER","12:00 AM","test, test",
						"DAILY","No","EnterStrDiagRou", et);
								
				//Navigate to Pass Meds -> AM
				cliCom.navigateTo("Pass Meds!!AM", et);
				
				//Select the Resident
				medPasModCom.selectingResidentFromMedPassScreen(residentName1Format1,et);
				
				//Validate medication data
				medPasModCom.validateMedicationDataPassMedsScreen(medicationName,"Strength/Route/Instructions/Diagnosis/Info/Reorder/History",et); 
				
				//Click on 'Info' button, validate UI, capture data and click print button -- Update
				medPasModCom.clickOnInfoButtonValidateUICaptureDataAndClickPrintButton(medicationName,"Yes","Yes","Yes","",et);
								
				//Validate 'Drug Information Printing ' window UI and click print
				medPasModCom.validateDrugInformationPrintingWindowUIAndClickPrint("Yes","Yes","",et);
								
				//Download PDF file , 'Drug Information Printing ' window
				medPasModCom.downloadPDFFileDrugInformationPrintingWindow("Microsoft Print to PDF",Utils.loadProperty("downloadPath"),drugInformationfileName,"Yes","",et);
				
				//Validate download PDF file data - 'Drug Information Printing ' window
				medPasModCom.validateDownloadedPDFFileDrugInformation(Utils.loadProperty("downloadPath"),drugInformationfileName,"Yes","Yes","Yes","",et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
							
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Select the medication from Orders tab
				medPasModCom.selectMedicationFromOrdersTabManageOrdersScreen(medicationName,"","Yes","",et);
				
				//Discontinue the created med
				medPasModCom.discontinueMedInOrdersScreen(-8,-8,et);
				
				//Logout from the application
				cliCom.logout(et);
				
				//Logger flush
				logger.endTest(extentReport);
				logger.flush(extentReport);
				
				//Clearing linked data from hashmap
				hashmapMedicationData.clear();
				
				//}			
				
			}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		@Test (enabled=true ,priority=14) 
		//@Parameters({"platform","seleniumGridNodeUrl"})
		//public void createAMedPass(String platform,String seleniumGridNodeUrl)
		public void verifyForDisplayOfMedPassBannerAppointmentForResidentInMedPassScreenForSameMedPassRangeOfAppointment()
		{
			try {
				String testCaseID="QM-9604";
				
				if(testCaseData.get(testCaseID).equals("Y"))
				{
				//Test data for creating medication
				String randomValue=actions.generateRandomAlphaString(5);
				String appointmentTitle="Appointment "+randomValue;
				String appointmentInstructions="Instructions "+randomValue;
				String appointmentDuration="15 minutes";
								
				//Add test case to report				
				ExtentTest et = new Logger().createTest(
						"Verify for the display of Med Pass Banner Appointment for a resident in Med pass screen for same med pass range of an appointment",
						"QM-9604",extentReport);
				
				//Login to application
				cliCom.loginToApplication(Utils.loadProperty("FacAdminUserId2"),Utils.loadProperty("FacAdminPassword2"),et);
				
				//Navigate to Calendar page via Home screen
				cliCom.navigateToCalendarViaHomeScreen(et);
				
				//Create an appointment
				medPasModCom.createAppointmentInCalendarScreen(residentName1Format2,appointmentTitle,"","",
						appointmentInstructions,"","","15 minutes","captureDateTime", et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
				
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Navigate to appointment tab
				
				//Validate the presence of appointment
				
				//Navigate to Pass Meds -> AM
				cliCom.navigateTo("Pass Meds!!AM", et);
				
				//Select the Resident
				medPasModCom.selectingResidentFromMedPassScreen(residentName1Format1,et);
				
				//Validate the presence of appointment banner
				medPasModCom.validateDataInAppointmentBannerInMedicationSelectionScreen("Title/Date/Time/Instructions",et);
				
				//Navigate to home screen
				cliCom.navigateToHomeScreen(et);
				
				//Navigate to Calendar page via Home screen
				cliCom.navigateToCalendarViaHomeScreen(et);
				
				//Delete the created appointment in Calendar screen
				//medPasModCom.deleteAppointmentInCalendarScreen(MedPassModuleTestCases.hashmapMedicationData.get("AppointmentTitle"),et);
				
				//Logout from the application
				cliCom.logout(et);
				
				//Logger flush
				logger.endTest(extentReport);
				logger.flush(extentReport);
				
				//Clearing linked data from hashmap
				hashmapMedicationData.clear();
				
				//}			
				
			}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		@Test (enabled=true ,priority=15) 
		//@Parameters({"platform","seleniumGridNodeUrl"})
		//public void createAMedPass(String platform,String seleniumGridNodeUrl)
		public void verifyDisplayOfResidentInMedPassScreenWhenUserScansRelatedBarcodeFromHomeScreenForPMMeds()
		{
			try {
				String testCaseID="QM-5471";
				
				if(testCaseData.get(testCaseID).equals("Y"))
				{
				String physicalID="B5515986";
								
				//Add test case to report				
				ExtentTest et = new Logger().createTest(
						"Verify the display of Resident  in Med pass screen when user scans the related barcode from Home screen for PM meds",
						"QM-5471",extentReport);
				
				//Login to application
				cliCom.loginToApplication(Utils.loadProperty("FacAdminUserId2"),Utils.loadProperty("FacAdminPassword2"),et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
				
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Create a med of type 'Medication'
				medPasModCom.createMedOfTypeMedicationInManageOrdersScreen("POWDER","12:00 AM","test, test",
						"DAILY","No","", et);
				
				//Navigate to home screen
				cliCom.navigateToHomeScreen(et);
				
				//Navigate to Residents -> Manage Residents
				cliCom.navigateToWorkAround("Residents", et);
				cliCom.navigateTo("Residents!!Manage Residents", et);
				
				//Select resident from Residents list -> Manage Residents screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Enter Physical ID and save
				medPasModCom.enterPhysicalIDInResidentProfileScreenAndSave (physicalID,"",et);
				
				//Navigate to home screen
				cliCom.navigateToHomeScreen(et);
								
				//Open the bar code window, enter the details and search
				cliCom.openBarCodeWindowEnterBarCodeNumberAndSearch(physicalID,"",et);
				
				//Validate the resident name in Medication selection screen
				medPasModCom.validateResidentNameInMedicationSelectionScreen(residentName1Format1,"",et);
				
				//Change the time to 'AM' in Medication selection screen
				medPasModCom.changeMeridianTimeInMedicationSelectionScreen("AM","",et);
				
				//Validate the presence of medication  in Medication selection screen
				medPasModCom.selectMedicationPassMedsScreen(medicationName,"No","validateMedicationPresent",et);
				
				//Validate the absence of tick mark for medication  in Medication selection screen
				medPasModCom.selectMedicationPassMedsScreen(medicationName,"No","validateTickMarkAbsence",et);
				
				//Select 'Back' button
				medPasModCom.selectBackButtonInMedicationSelectionScreen("",et);
				
				//Validate display of 'Resident List' screen
				medPasModCom.validatePassMedsResidentScreenIsDisplayed(et);
				
				//Open the bar code window, enter the details and search
				cliCom.openBarCodeWindowEnterBarCodeNumberAndSearch(physicalID,"",et);
				
				//Validate the resident name in Medication selection screen
				medPasModCom.validateResidentNameInMedicationSelectionScreen(residentName1Format1,"",et);
				
				//Change the time to 'AM' in Medication selection screen
				medPasModCom.changeMeridianTimeInMedicationSelectionScreen("AM","",et);
				
				//Validate the presence of medication  in Medication selection screen
				medPasModCom.selectMedicationPassMedsScreen(medicationName,"No","validateMedicationPresent",et);
				
				//Validate the absence of tick mark for medication  in Medication selection screen
				medPasModCom.selectMedicationPassMedsScreen(medicationName,"No","validateTickMarkAbsence",et);
				
				//Navigate to Residents -> Manage Orders
				cliCom.navigateTo("Residents!!Manage Orders", et);
							
				//Select resident from Residents list -> Manage Orders screen 
				cliCom.selectingResidentFromResidentsListManageResidentsOrdersScreen(residentName1Format2,et);
				
				//Select the medication from Orders tab
				medPasModCom.selectMedicationFromOrdersTabManageOrdersScreen(medicationName,"","Yes","",et);
				
				//Discontinue the created med
				medPasModCom.discontinueMedInOrdersScreen(-8,-8,et);
							
				//Logout from the application
				cliCom.logout(et);
				
				//Logger flush
				logger.endTest(extentReport);
				logger.flush(extentReport);
				
				//Clearing linked data from hashmap
				hashmapMedicationData.clear();
				
				//}			
				
			}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
}
