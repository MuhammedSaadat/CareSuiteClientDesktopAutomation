package automation.careSuiteClient.Package11;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.relevantcodes.extentreports.ExtentTest;

import automation.careSuiteClient.utility.Utils;
import io.appium.java_client.windows.WindowsDriver;

public class ClientCommon {

	//Reports creation objects
	private static String reportingPath = Utils.loadProperty("reportingPath");
	private static String reportPath = Utils.loadProperty("reportPath");
	protected Logger logger = new Logger();

	//Business class objects
	Keywords actions = new Keywords();
	UiMap map = new UiMap();
	
	//Login to application
	//public void loginToApplication(String platform,String seleniumGridNodeUrl, ExtentTest et) {
		/**
		 * @param et
		 */
		public void loginToApplication(String userName, String password,ExtentTest et) {
		try {
			//Create application session object
			DriverFactoryApplicationSession.getInstance().setDriver(MedPassModuleTestCases.platform,MedPassModuleTestCases.seleniumGridNodeUrl);
			WindowsDriver<WebElement>applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
			
			//Create desktop session
			DriverFactoryDesktopSession.getInstance().setDriver(MedPassModuleTestCases.platform,MedPassModuleTestCases.seleniumGridNodeUrl);
			WindowsDriver<WebElement> desktopSessionObject = DriverFactoryDesktopSession.getInstance().getDriver();
			
			//Switching to Login window			
			actions.waitForElementVisibility(map.loginWindow,"Name",desktopSessionObject,20);
						
			// Entering credentials
			actions.setValue(map.userNameTextboxLoginWindow, "UserName field",userName, desktopSessionObject, et);
			actions.setValue(map.passwordTextboxLoginWindow, "Password field", password, desktopSessionObject, et);
			
			// Clicking on 'OK' button
			actions.click(map.okButtonLoginWindow, "OK button", desktopSessionObject, et);
			actions.waitForElementVisibility(map.titleHomeWindow,"AccessID",desktopSessionObject,30);
			
			// Switching to Home window
			applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
			Set<String> allwindowHandles  = applicationSessionObject.getWindowHandles();
			applicationSessionObject.switchTo().window(allwindowHandles.iterator().next());
			
			// Maximizing the window
			applicationSessionObject.manage().window().maximize();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
		/**
		 * @param et
		 */
		public void logout(ExtentTest et)
		{
			try
			{
			//Clicking on 'Log off' and 'Yes' button
			WindowsDriver<WebElement> desktopSessionObject = null;	
			WindowsDriver<WebElement> applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
			actions.waitForElementVisibility(map.logOffButtonHomeWindow, "Name", applicationSessionObject, 10);
			actions.click(map.logOffButtonHomeWindow, "Log off button", applicationSessionObject, et);
			
			//Clicking on 'Yes' button
			actions.waitForElementVisibility(map.yesButtonReadyToLogOffWindow, "Name", applicationSessionObject, 10);
			try {
				applicationSessionObject.findElement(map.yesButtonReadyToLogOffWindow).click();
				DriverFactoryDesktopSession.getInstance().setDriver(MedPassModuleTestCases.platform,MedPassModuleTestCases.seleniumGridNodeUrl);
				desktopSessionObject = DriverFactoryDesktopSession.getInstance().getDriver();
				logger.log("pass", "Successfully clicked on 'Yes Button' ", false, desktopSessionObject, reportingPath, et);

			} catch (Exception e2) {
				logger.log("fail", "Not clicked on 'Yes Button' ", true, desktopSessionObject, reportingPath, et);

			}
			
			//Create desktop session
			DriverFactoryDesktopSession.getInstance().setDriver(MedPassModuleTestCases.platform,MedPassModuleTestCases.seleniumGridNodeUrl);
			desktopSessionObject = DriverFactoryDesktopSession.getInstance().getDriver();
			actions.waitForElementVisibility(map.loginWindow, "Name", desktopSessionObject, 30);
			
			
			try {
			//Getting handle of 'Login Window'	
			String handle=Integer.toHexString(Integer. parseInt(desktopSessionObject.findElement(map.loginWindow).getAttribute("NativeWindowHandle")));
						
			//Attaching 'Login' window handle to opened Application session
			DesiredCapabilities loginWindowCapabilities = new DesiredCapabilities();
			loginWindowCapabilities.setCapability("platformName", "Windows");
			loginWindowCapabilities.setCapability("deviceName", "WindowsPC");
			loginWindowCapabilities.setCapability("appTopLevelWindow", handle);
		    WindowsDriver<WebElement> driver = new WindowsDriver<WebElement>(new URL("http://127.0.0.1:4723"), loginWindowCapabilities);
		    
		    //Clicking on 'Cancel' button
		    driver.findElement(map.loginWindow).findElement(By.xpath("//Button[@Name='Cancel']")).click();
		    logger.log("pass", "Successfully clicked on 'Cancel Button' ", false, driver, reportingPath, et);
			
			} catch (Exception e2) {
				e2.printStackTrace();
				logger.log("fail", "Not clicked on 'Cancel Button' ", true, desktopSessionObject, reportingPath, et);

			}
			
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		}
	
	/** Clicks on a menu and then clicks on an option.
	 * Be careful while passing correct attribute value of 'Name' of elements 
	 * @param navigationPath
	 * @param et
	 */
	public void navigateTo(String navigationPath,ExtentTest et) {
		
		try {
			//Application and desktop objects
			WindowsDriver<WebElement> applicationSessionObject;
			WindowsDriver<WebElement> desktopSession;
			
			//Storing menu names and their dropdown names in LinkedHashMap
			LinkedHashMap<String, String> hashmapMenuDropdownNames = new LinkedHashMap<String, String>();
			hashmapMenuDropdownNames.put("Pass Meds", "Pass MedsDropDown");
			hashmapMenuDropdownNames.put("Residents", "ResidentsDropDown");
			hashmapMenuDropdownNames.put("Admin", "AdminDropDown");
			hashmapMenuDropdownNames.put("Settings", "SettingsDropDown");
			hashmapMenuDropdownNames.put("Reports", "ReportsDropDown");
			hashmapMenuDropdownNames.put("Inventory", "InventoryDropDown");
			
			String[] value=navigationPath.split("!!");
			
			//Clicking on menu item
			applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
			actions.waitForElementVisibility(By.name(value[0].trim()), "Name", applicationSessionObject, 20);
			actions.click(By.name(value[0].trim()), value[0]+" menu", applicationSessionObject, et);
			
			
			//Clicking on menu option
			DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
			desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
			actions.waitForElementVisibility(By.name(hashmapMenuDropdownNames.get(value[0].trim())), "Name", desktopSession, 20);
			WebElement dropdown=desktopSession.findElement(By.name(hashmapMenuDropdownNames.get(value[0].trim())));
			WebElement dropdownoption=dropdown.findElement(By.name(value[1].trim()));
			actions.click(dropdownoption, value[1]+" option", applicationSessionObject, et);
			
			//If 'Late Night Items' window is displayed
			if(value[1].trim().equals("AM"))
			{
			actions.waitForElementVisibility(map.lateNightItemsWindow, "Name", applicationSessionObject, 5);
			if(actions.isElementPresent(map.lateNightItemsWindow, "Late Night Items window", false, applicationSessionObject, et))
			{
				WebElement continueButton=applicationSessionObject.findElement(map.lateNightItemsWindow).findElement(map.continueButtonLateNightItemsWindow);
				actions.click(continueButton, "Continue button", applicationSessionObject, et);
			}
			}
			
			//For clicking sub-menu within menu
			if(value.length==3)
			{
							
				//Clicking on menu option
				DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
				desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
				actions.waitForElementVisibility(By.name(hashmapMenuDropdownNames.get(value[1].trim())), "Name", desktopSession, 20);
				WebElement dropdown2=desktopSession.findElement(By.name(hashmapMenuDropdownNames.get(value[1].trim())));
				WebElement dropdownoption2=dropdown2.findElement(By.name(value[2].trim()));
				actions.click(dropdownoption2, value[2]+" option", applicationSessionObject, et);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
public void navigateToWorkAround(String navigationPath,ExtentTest et) {
		
		try {
			//Application
			WindowsDriver<WebElement> applicationSessionObject;
						
			
			//Clicking on menu item
			applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
			actions.waitForElementVisibility(By.name(navigationPath), "Name", applicationSessionObject, 20);
			actions.click(By.name(navigationPath), navigationPath+" menu", applicationSessionObject, et);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
public void selectingResidentFromResidentsListManageResidentsOrdersScreen(String reisdentName,ExtentTest et) {
		
		try {
			//Application and desktop objects
			WindowsDriver<WebElement> applicationSessionObject;
			
			//Selecting the resident
			applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
			actions.waitForElementVisibility(map.firstResidentResidentsPane,"AccessId",applicationSessionObject,40);
			actions.isElementPresent(map.residentsPaneManageOrders, "Residents list", true, applicationSessionObject, et);
			WebElement residentsPane=applicationSessionObject.findElement(map.residentsPaneManageOrders);
			WebElement selectResidentResidentsPane=residentsPane.findElement(By.name(reisdentName));
			actions.click(selectResidentResidentsPane, "resident "+reisdentName, applicationSessionObject, et);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

/** Date formats : "M/d/yyyy = 4/16/2020" , "d/MMMM/yyyy = 16/April/2020"
 * @param inputDate
 * @param inputDateFormat
 * @param systemDate
 * @param outputDateFormat
 * @param i
 * @return
 */
public static String addingOrSubtractingDays(String inputDate,String inputDateFormat,String systemDate,String outputDateFormat,int i) 
{
	String newDate = "";
	try {
		SimpleDateFormat sdf;
		Calendar c = null;
		
		//Calculates using system date
		if(!systemDate.isEmpty())
		{
		//Get system date
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yyyy");
		LocalDateTime now = LocalDateTime.now();
		
		//Adding system date to Calendar instance
		sdf = new SimpleDateFormat("M/d/yyyy");
		c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(dtf.format(now)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		}
		
		//Calculates using given input date and format
		if(systemDate.isEmpty())
		{
			//Adding given input date to Calendar instance
			sdf = new SimpleDateFormat(inputDateFormat);
			c = Calendar.getInstance();
			try
			{
				c.setTime(sdf.parse(inputDate));
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
		}

		//Add/subtract days
		c.add(Calendar.DAY_OF_MONTH, i);
		sdf = new SimpleDateFormat(outputDateFormat);
		newDate = sdf.format(c.getTime());

	} catch (Exception e) {
		e.printStackTrace();
	}
	return newDate;

}

public void navigateToHomeScreen(ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Click on 'Home' menu
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.homeMenu,"Name",applicationSessionObject,20);
		actions.click(map.homeMenu, "Home menu", applicationSessionObject, et);
		actions.waitForElementVisibility(map.quickMarLogoHomeScreen,"AccessId",applicationSessionObject,20);
		//actions.waitForElementVisibility(map.dashboardButtonHomeScreen,"AccessId",applicationSessionObject,20);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void navigateToDashboardViaHomeScreen(ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Click on 'Home' menu
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.dashboardButtonHomeScreen,"AccessId",applicationSessionObject,20);
		actions.click(map.dashboardButtonHomeScreen, "Dashboard button", applicationSessionObject, et);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void navigateToCalendarViaHomeScreen(ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Click on 'Home' menu
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.calendarButtonHomeScreen,"AccessId",applicationSessionObject,20);
		actions.click(map.calendarButtonHomeScreen, "Calendar button", applicationSessionObject, et);
		actions.waitForElementVisibility(map.calendarScreen,"AccessId",applicationSessionObject,20);
		actions.isElementPresent(map.calendarScreen, "Calendar screen", true, applicationSessionObject, et);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void openBarCodeWindowEnterBarCodeNumberAndSearch(String barCodeNumber,String option,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		WindowsDriver<WebElement> desktopSession;
		
		//Opening Bar code debug dialog window using 'Ctrl+Sht+D'
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		
		
		try
		{
		Actions actions = new Actions(applicationSessionObject);
		
		// Press SHIFT-CTRL-D           
		actions.keyDown(Keys.SHIFT)
		    .keyDown(Keys.CONTROL)
		    .sendKeys("d")
		    .build()
		    .perform();
        logger.log("pass", "Successfully pressed 'Sft+Ctr+D' ", false, applicationSessionObject, reportingPath, et);
        
        // Release SHIFT+CTRL keys   
        actions.keyUp(Keys.SHIFT)
         .keyUp(Keys.CONTROL)
         .build()
         .perform();
		}
		catch(Exception e)
		{
			logger.log("fail", "Successfully not pressed 'Sft+Ctr+D' ", true, applicationSessionObject, reportingPath, et);
			e.printStackTrace();
		}
		
			
		//Creating desktop session for Debug Dialog Window
		DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
		desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.debugDialogWindowBarcode,"AccessId",desktopSession,60);
		
		
		WindowsDriver<WebElement> driver = null;
		try {
		//Getting handle of 'Debug Dialog Window'	
		String handle=Integer.toHexString(Integer. parseInt(desktopSession.findElement(map.debugDialogWindowBarcode).getAttribute("NativeWindowHandle")));
					
		//Attaching 'Debug Dialog' window handle to opened Application session
		DesiredCapabilities loginWindowCapabilities = new DesiredCapabilities();
		loginWindowCapabilities.setCapability("platformName", "Windows");
		loginWindowCapabilities.setCapability("deviceName", "WindowsPC");
		loginWindowCapabilities.setCapability("appTopLevelWindow", handle);
	    driver = new WindowsDriver<WebElement>(new URL("http://127.0.0.1:4723"), loginWindowCapabilities);
	    
	   
		} catch (Exception e2) {
			e2.printStackTrace();
		

		}
		
		
		WebElement debugDialogWindow=driver.findElement(map.debugDialogWindowBarcode);
		
		//This code is executed when Debug Dialog Window is present
		if(actions.isElementPresent(debugDialogWindow, "Debug Dialog Window", true, driver, et))
		{
			//Clicking on Send Barcode Button
			actions.click(debugDialogWindow.findElement(map.sendBarcodeButtonDebugDialogWindow), "Send Barcode Button", driver, et);
			
			//Creating desktop session for Barcode Tester Window
			DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
			desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
			actions.waitForElementVisibility(map.barcodeTesterWindow,"AccessId",desktopSession,60);
			WebElement barcodeTesterWindow=desktopSession.findElement(map.barcodeTesterWindow);
			
			//This code is executed when Barcode Tester Window is present
			if(actions.isElementPresent(barcodeTesterWindow, "Barcode Tester Window", true, desktopSession, et))
			{
				//Enter text
				actions.setValue(barcodeTesterWindow.findElement(map.textboxBarcodeTesterWindow), "Barcode Tester Window textbox", barCodeNumber, desktopSession, et);
				
				//Click on 'OK' button
				actions.click(barcodeTesterWindow.findElement(map.okButtonBarcodeTesterWindow), "OK Button", desktopSession, et);
			}
			
			//Click on 'Close' button of Debug Dialog Window
			actions.click(debugDialogWindow.findElement(map.closeButtonDebugDialogWindow), "Close Button", desktopSession, et);
		}
		
	
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

}
