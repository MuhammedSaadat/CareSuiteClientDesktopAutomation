package automation.careSuiteClient.Package11;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentTest;
import automation.careSuiteClient.utility.Utils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.windows.WindowsDriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
//Supports validating PDF content
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.interactions.TouchScreen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @Class : contains methods related to Selenium operations
 * @author E004014
 * @version 1.0
 */
public class Keywords {

	 //Reports creation objects	
	protected Logger logger = new Logger();
	private static String reportingPath = Utils.loadProperty("reportingPath");
	private static String reportPath = Utils.loadProperty("reportPath");
	
	

	/** Clicks on an element by taking a parameter as By
	 * @param sElement
	 * @param elementName
	 * @param driver
	 * @param et
	 */
	public void click(final By sElement,String elementName,WindowsDriver<WebElement> driver,  ExtentTest et) {
		//WebDriver driver = BrowserFactory.getInstance().getDriver();
		try {
			System.out.println("Clicking on '" + elementName + "'");
			
			driver.findElement(sElement).click();
			logger.log("pass", "Successfully clicked on '" + elementName + "'", false, driver, reportingPath, et);
			

		} catch (Exception e2) {
			logger.log("fail", "Not clicked on '" + elementName + "'", true, driver, reportingPath, et);

		}
		
	}
	
	
	/** Clicks on an element by taking a parameter as WebElement
	 * @param sElement
	 * @param elementName
	 * @param driver
	 * @param et
	 */
	public void click(final WebElement sElement,String elementName,WindowsDriver<WebElement> driver,  ExtentTest et) {
		//WebDriver driver = BrowserFactory.getInstance().getDriver();
		try {
			System.out.println("Clicking on '" + elementName + "'");
			sElement.click();
			logger.log("pass", "Successfully clicked on '" + elementName + "'", false, driver, reportingPath, et);

		} catch (Exception e2) {
			logger.log("fail", "Not clicked on '" + elementName + "'", true, driver, reportingPath, et);

		}
		
	}
	
	public void clear(final By sElement,String elementName,WindowsDriver<WebElement> driver,  ExtentTest et) {
		System.out.println("Clearing text from '" + elementName + "'");
		
		try {
			driver.findElement(sElement).clear();

			logger.log("pass", "Successfully cleared textbox '" + elementName + "'", false, driver, reportingPath, et);

		} catch (Exception e2) {
			logger.log("fail", "Failed to clear textBox '" + elementName + "'", true, driver, reportingPath, et);

		}
		
	}
	
	public void clear(final WebElement sElement,String elementName,WindowsDriver<WebElement> driver,  ExtentTest et) {
		System.out.println("Clearing text from '" + elementName + "'");
		
		try {
			sElement.clear();

			logger.log("pass", "Successfully cleared textbox '" + elementName + "'", false, driver, reportingPath, et);

		} catch (Exception e2) {
			logger.log("fail", "Failed to clear textBox '" + elementName + "'", true, driver, reportingPath, et);

		}
		
	}
	
	/** Enters data into a text field
	 * @param sElementName
	 * @param elementName
	 * @param sValue
	 * @param driver
	 * @param et
	 */
	public void setValue(final By sElementName, String elementName,  String sValue,WindowsDriver<WebElement> driver,  ExtentTest et) {
		//WebDriver driver = BrowserFactory.getInstance().getDriver();

		
			System.out.println("Entering text '" + sValue + "' " + " in '" + elementName + "'");
			try {
				driver.findElement(sElementName).sendKeys(sValue.toString().trim());

				logger.log("pass", "Successfully typed text '" + sValue.toString() + "' in the input element '"
						+ elementName + "'", false, driver, reportingPath, et);

			} catch (Exception e2) {
				logger.log("fail", "Error while typing text '" + sValue.toString() + "' in the input element '"
						+ elementName + "'", true, driver, reportingPath, et);

			}
			//logger.flush();

		} 
	
	public void setValue(WebElement sElementName, String elementName,  String sValue,WindowsDriver<WebElement> driver,  ExtentTest et) {
		//WebDriver driver = BrowserFactory.getInstance().getDriver();

		
			System.out.println("Entering text '" + sValue + "' " + " in '" + elementName + "'");
			try {
				sElementName.sendKeys(sValue.toString().trim());

				logger.log("pass", "Successfully Typed text '" + sValue.toString() + "' in the input element '"
						+ elementName + "'", false, driver, reportingPath, et);

			} catch (Exception e2) {
				logger.log("fail", "Error while typing text '" + sValue.toString() + "' in the input element '"
						+ elementName + "'", true, driver, reportingPath, et);

			}
			//logger.flush();

		} 

	/** Waits till the element is visible
	 * @param element
	 * @param locatorType
	 * @param driver
	 * @param time
	 */
	public void waitForElementVisibility(By element,String locatorType,WindowsDriver<WebElement> driver, int time) {
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,time); 
			if(locatorType.equalsIgnoreCase("Name"))
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			
			//AutomationID
			if(locatorType.equalsIgnoreCase("AccessId"))
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			
			if(locatorType.equalsIgnoreCase("ClassName"))
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			
			if(locatorType.equalsIgnoreCase("Xpath"))
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			
			if(locatorType.equalsIgnoreCase("TagName"))
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			
			
			
		}
		catch (Exception e)
		{
			//e.printStackTrace();
		}
	}



/** Generates random alpha string
 * @param length
 * @return
 */
public String generateRandomAlphaString(final int length)
{
	String strRet=null;
	try
	{
	char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	StringBuilder sb = new StringBuilder(20);
	Random random = new Random();
	for (int i = 0; i < length; i++)
	{
	    char c = chars[random.nextInt(chars.length)];
	    sb.append(c);
	}
	strRet= sb.toString();
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	return strRet;
}


/** Validates the presence of element
 * @param sElement
 * @param elementName
 * @param report
 * @param et
 * @return
 */
public Boolean isElementPresent(By sElement, String elementName, Boolean report,WindowsDriver<WebElement> driver, ExtentTest et) {

	Boolean returnFlag = null;

	try {
		if (driver.findElement(sElement).isDisplayed()) {
			if (report == true) {
				logger.log("pass", "Successfully displaying '" + elementName + "'", false, driver, reportingPath,
						et);
			}
			returnFlag = true;
		}
		//Updating this method to log result as fail when element in not displayed
		else {
			if (report == true) {
				logger.log("fail", "Not displaying '" + elementName + "'", true, driver, reportingPath, et);
			}
			returnFlag = false;
		}
	} catch (Exception e) {

		if (report == true) {
			logger.log("fail", "Not displaying '" + elementName + "'", true, driver, reportingPath, et);
		}
		returnFlag = false;
	}

	return returnFlag;

}

public Boolean isElementPresent(WebElement sElement, String elementName, Boolean report,WindowsDriver<WebElement> driver, ExtentTest et) {

	Boolean returnFlag = null;

	try {
		if (sElement.isDisplayed()) {
			if (report == true) {
				logger.log("pass", "Successfully displaying '" + elementName + "'", false, driver, reportingPath,
						et);
			}
			returnFlag = true;
		}
		//Updating this method to log result as fail when element in not displayed
		else {
			if (report == true) {
				logger.log("fail", "Not displaying '" + elementName + "'", true, driver, reportingPath, et);
			}
			returnFlag = false;
		}
	} catch (Exception e) {

		if (report == true) {
			logger.log("fail", "Not displaying '" + elementName + "'", true, driver, reportingPath, et);
		}
		returnFlag = false;
	}

	return returnFlag;

}

/** Validates the absence of element
 * @param sElement
 * @param elementName
 * @param report
 * @param et
 * @return
 */
public Boolean isElementAbsent(By sElement, String elementName, Boolean report,WindowsDriver<WebElement> driver, ExtentTest et) {

	Boolean returnFlag = null;
	try {
		if (!driver.findElement(sElement).isDisplayed()) {
			if (report == true) {
				logger.log("pass", "Successfully not displaying '" + elementName + "'", false, driver, reportingPath,
						et);
			}
			returnFlag = true;
		}
		//Updating this method to log result as fail when element in not displayed
		else {
			if (report == true) {
				logger.log("fail", "Displaying '" + elementName + "'", true, driver, reportingPath, et);
			}
			returnFlag = false;
		}
	} catch (Exception e) {

		if (report == true) {
			logger.log("pass", "Successfully not displaying '" + elementName + "'", false, driver, reportingPath, et);
		}
		returnFlag = true;
	}

	return returnFlag;

}

public Boolean isElementAbsent(WebElement sElement, String elementName, Boolean report,WindowsDriver<WebElement> driver, ExtentTest et) {

	Boolean returnFlag = null;
	try {
		if (!sElement.isDisplayed()) {
			if (report == true) {
				logger.log("pass", "Successfully not displaying '" + elementName + "'", false, driver, reportingPath,
						et);
			}
			returnFlag = true;
		}
		//Updating this method to log result as fail when element in not displayed
		else {
			if (report == true) {
				logger.log("fail", "Displaying '" + elementName + "'", true, driver, reportingPath, et);
			}
			returnFlag = false;
		}
	} catch (Exception e) {

		if (report == true) {
			logger.log("pass", "Successfully not displaying '" + elementName + "'", false, driver, reportingPath, et);
		}
		returnFlag = true;
	}

	return returnFlag;

}


/** Validates whether checkbox is selected
 * @param sElement
 * @param elementName
 * @param report
 * @param et
 * @return
 */
public Boolean isElementSelected(By sElement, String elementName, Boolean report,WindowsDriver<WebElement> driver,  ExtentTest et) {

	Boolean returnFlag = null;
	
	try {
		if (driver.findElement(sElement).isSelected()) {
			if (report == true) {
				logger.log("pass","Displaying '" + elementName + "' as selected", false, driver, reportingPath,
						et);
			}
			returnFlag = true;
		} else {
			if (report == true) {
				logger.log("fail", "Displaying '" + elementName + "' as not selected", true, driver, reportingPath,
						et);
			}
			returnFlag = false;
		}
	} catch (Exception e) {
		logger.log("fail","Error in validating whether the '" + elementName + "' is selected ", true, driver, reportingPath,et);
	}
	return returnFlag;
}


/** Validates whether checkbox is not selected
 * @param sElement
 * @param elementName
 * @param report
 * @param driver
 * @param et
 * @return
 */
public Boolean isElementNotSelected(By sElement, String elementName, Boolean report,WindowsDriver<WebElement> driver,  ExtentTest et) {
	Boolean returnFlag = null;
	try {
		if (driver.findElement(sElement).isSelected()) {
			if (report == true) {
				logger.log("fail","Displaying '" + elementName + "' as selected", true, driver, reportingPath,
						et);
			}
			returnFlag = false;
		} else {
			if (report == true) {
				logger.log("pass", "Displaying '" + elementName + "' as not selected", false, driver, reportingPath,
						et);
			}
			returnFlag = true;
		}
	} catch (Exception e) {
		logger.log("fail","Error in validating whether the '" + elementName + "' is not selected ", true, driver, reportingPath,et);
	}
	return returnFlag;
}

public Boolean isElementSelected(WebElement sElement, String elementName, Boolean report,WindowsDriver<WebElement> driver,  ExtentTest et) {

	Boolean returnFlag = null;
	
	try {
		if (sElement.isSelected()) {
			if (report == true) {
				logger.log("pass","Displaying '" + elementName + "' as selected", false, driver, reportingPath,
						et);
			}
			returnFlag = true;
		} else {
			if (report == true) {
				logger.log("fail", "Displaying '" + elementName + "' as not selected", true, driver, reportingPath,
						et);
			}
			returnFlag = false;
		}
	} catch (Exception e) {
		logger.log("fail","Error in validating whether the '" + elementName + "' is selected ", true, driver, reportingPath,et);
	}
	return returnFlag;
}


/** Validates whether checkbox is not selected
 * @param sElement
 * @param elementName
 * @param report
 * @param driver
 * @param et
 * @return
 */
public Boolean isElementNotSelected(WebElement sElement, String elementName, Boolean report,WindowsDriver<WebElement> driver,  ExtentTest et) {
	Boolean returnFlag = null;
	try {
		if (sElement.isSelected()) {
			if (report == true) {
				logger.log("fail","Displaying '" + elementName + "' as selected", true, driver, reportingPath,
						et);
			}
			returnFlag = false;
		} else {
			if (report == true) {
				logger.log("pass", "Displaying '" + elementName + "' as not selected", false, driver, reportingPath,
						et);
			}
			returnFlag = true;
		}
	} catch (Exception e) {
		logger.log("fail","Error in validating whether the '" + elementName + "' is not selected ", true, driver, reportingPath,et);
	}
	return returnFlag;
}

/** Validates whether element is enabled
 * @param sElement
 * @param elementName
 * @param report
 * @param et
 * @return
 */
public Boolean isElementEnabled(By sElement, String elementName, Boolean report, WindowsDriver<WebElement> driver,  ExtentTest et) {

	Boolean returnFlag = null;
	
	try {
		if (driver.findElement(sElement).isEnabled()) {
			if (report == true) {
				logger.log("pass","Successfully displaying '" + elementName + "' as Enabled", false, driver, reportingPath, et);
			}
			returnFlag = true;
		} else {
			if (report == true) {
				logger.log("fail","Successfully not displaying '" + elementName + "' as Enabled", true, driver, reportingPath,
						et);
			}
			returnFlag = false;
		}
	} catch (Exception e) {
		
		logger.log("fail","Error in validating status of '" + elementName + "' ", true, driver, reportingPath,et);

	}
	return returnFlag;
}



/** Validates whether element is enabled
 * @param sElement
 * @param elementName
 * @param report
 * @param et
 * @return
 */
public Boolean isElementDisabled(By sElement, String elementName, Boolean report, WindowsDriver<WebElement> driver,  ExtentTest et) {

	Boolean returnFlag = null;
	try {
		if (!driver.findElement(sElement).isEnabled()) {
			if (report == true) {
				logger.log("pass","Successfully displaying '" + elementName + "' as Disabled", false, driver, reportingPath, et);
			}
			returnFlag = true;
		} else {
			if (report == true) {
				logger.log("fail","Successfully not displaying '" + elementName + "' as Disabled", true, driver, reportingPath,et);
			}
			returnFlag = false;
		}
	} catch (Exception e)
	{
		logger.log("fail","Error in validating status of '" + elementName + "' ", true, driver, reportingPath,et);
	}
	return returnFlag;
}


/** Returns text value using getText()
 * @param sElement
 * @param driver
 * @return
 */
public String getTextValue(By sElement,String elementName,WindowsDriver<WebElement> driver,ExtentTest et) {
	String strReturn = "";
	
	try {

		strReturn = driver.findElement(sElement).getText();
	} catch (Exception e) {
		logger.log("fail","Error in getting text from '" + elementName + "' ", true, driver, reportingPath,et);
	}
	return strReturn;
}

public String getTextValue(WebElement sElement,String elementName,WindowsDriver<WebElement> driver,ExtentTest et) {
	String strReturn = "";
	
	try {

		strReturn = sElement.getText();
	} catch (Exception e) {
		logger.log("fail","Error in getting text from '" + elementName + "' ", true, driver, reportingPath,et);
	}
	return strReturn;
}


/** Returns Attribute value of web element
 * @param sElement
 * @param attribute
 * @param driver
 * @return
 */
public String getAttributeValue(By sElement,String elementName, String attribute,WindowsDriver<WebElement> driver,ExtentTest et) {
	String strReturn = "";
	
	try {

		strReturn = driver.findElement(sElement).getAttribute(attribute);
	} catch (Exception e) {
		logger.log("fail","Error in getting attribute value from '" + elementName + "' ", true, driver, reportingPath,et);
	}
	return strReturn;
 }

public String getAttributeValue(WebElement sElement,String elementName, String attribute,WindowsDriver<WebElement> driver,ExtentTest et) {
	String strReturn = "";
	
	try {

		strReturn = sElement.getAttribute(attribute);
	} catch (Exception e) {
		logger.log("fail","Error in getting attribute value from '" + elementName + "' ", true, driver, reportingPath,et);
	}
	return strReturn;
 }


/** Clicks on an element using action class and x ,y co-ordinates
 * @param sElement
 * @param elementName
 * @param driver
 * @param et
 */
public void actionsClassClickUsingXYCoOrdinates(final By sElement,String elementName,int x, int y,WindowsDriver<WebElement> driver,  ExtentTest et) {
	//WebDriver driver = BrowserFactory.getInstance().getDriver();
	try {
		System.out.println("Clicking on '" + elementName + "'");
		
		Actions act= new Actions(driver);
		act.moveToElement(driver.findElement(sElement), x, y).click().build().perform();
		logger.log("pass", "Successfully clicked on '" + elementName + "'", true, driver, reportingPath, et);

	} catch (Exception e2) {
		logger.log("fail", "Not clicked on '" + elementName + "'", true, driver, reportingPath, et);

	}
	
}

/** Clicks on an element using action class
 * @param sElement
 * @param elementName
 * @param driver
 * @param et
 */
public void actionsClassClick(final By sElement,String elementName,WindowsDriver<WebElement> driver,  ExtentTest et) {
	//WebDriver driver = BrowserFactory.getInstance().getDriver();
	try {
		System.out.println("Clicking on '" + elementName + "'");
		
		Actions act= new Actions(driver);
		act.moveToElement(driver.findElement(sElement)).click().build().perform();
		logger.log("pass", "Successfully clicked on '" + elementName + "'", true, driver, reportingPath, et);

	} catch (Exception e2) {
		logger.log("fail", "Not clicked on '" + elementName + "'", true, driver, reportingPath, et);

	}
	
}

public void scrollToElement(final By sElement,WindowsDriver<WebElement> driver) {
	try {
		System.out.println("Scrolling to element");
		
		
		/*TouchAction action = new TouchAction(driver);
		action.moveTo(driver.findElement(sElement));*/
		//WebElement element = driver.findElement(sElement);
		List<WebElement> ele=driver.findElements(sElement);
		ele.get(0).sendKeys("");
		
		//This code is scrolling the element to top of the page under header. Hence not visible in screenshots
		/*WebElement element = driver.findElement(sElement);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);*/
		
		/*//Working Code
		//This code is scrolling the element to bottom of the page making visible for screenshots 
		WebElement element2 = driver.findElement(sElement);
		Coordinates coordinate = (Coordinates) ((Locatable)element2).getCoordinates(); 
		coordinate.onPage(); 
		coordinate.inViewPort();
		Thread.sleep(500);*/
		
		
		/*//This code is exactly bringing the element to center of page, wherever it may be present
		WebElement element = driver.findElement(sElement);
		String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
		                                            + "var elementTop = arguments[0].getBoundingClientRect().top;"
		                                            + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

		((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
		Thread.sleep(250);*/

	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

public void closingWindowUsingActionsClass(final By sElement,String elementName,WindowsDriver<WebElement> driver,ExtentTest et) {
	
	try {
		//Clicking on the element to get its focus
		waitForElementVisibility(sElement, "Name", driver, 40);
		try
		{
		driver.findElement(sElement).click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//Closing using actions class
		try
		{
		Actions actions = new Actions(driver);
        actions.keyDown(Keys.ALT);
        actions.sendKeys(Keys.F4);
        actions.keyUp(Keys.ALT);
        actions.perform();
        logger.log("pass", "Successfully pressed 'Alt+F4' to close '"+elementName+"'", false, driver, reportingPath, et);
		}
		catch(Exception e)
		{
			logger.log("fail", "Successfully not pressed 'Alt+F4' to close '"+elementName+"'", true, driver, reportingPath, et);
			e.printStackTrace();
		}
		
		//Validate if window is closed
		isElementAbsent(sElement,elementName,true,driver,et);
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public String getLatestFileNameFromDir(String csvFilePath,WindowsDriver<WebElement> driver,ExtentTest et)
{
	String fileNameReturn="";
	try {
	File file = getLatestFilefromDir(csvFilePath,driver,et);
	String csvFileName = file.getName();
	fileNameReturn=csvFileName;
	System.out.println("Latest File Downloaded is :- "+fileNameReturn);
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	return fileNameReturn;
}

File getLatestFilefromDir(String dirPath,WindowsDriver<WebElement> driver,ExtentTest et){
    File dir = new File(dirPath);
    File[] files = dir.listFiles();
    if (files == null || files.length == 0) {
        return null;
    }

    File lastModifiedFile = files[0];
    for (int i = 1; i < files.length; i++) {
       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
           lastModifiedFile = files[i];
       }
    }
    return lastModifiedFile;
}
		
	public String getPDFFileData(String fileDownloadPath,String fileName,WindowsDriver<WebElement> driver,ExtentTest et)
	{
		String retData = null;
		try
		{
			File file = new File(fileDownloadPath+"\\"+fileName);
			
			//Get data if file exists
			if(file.exists()){
			 PDDocument pdDocument=PDDocument.load(file);
	            //count total pages
	            int totalPages= pdDocument.getNumberOfPages();
	            //print out total page numbers
	            System.out.println("Total pages: " +totalPages);
	            //define a PDF file stripper object
	            PDFTextStripper stripper = new PDFTextStripper();
	            //set start page to 1
	            stripper.setStartPage( 1 );
	            //set end page to total pages
	            stripper.setEndPage( totalPages );
	          String parsedText= stripper.getText(pdDocument);
	                   System.out.println(parsedText);
	                   retData=parsedText;

			
			
			//Closing the PDF doc
	            pdDocument.close();
			}
			else
			{
				logger.log("fail", "The file '"+fileName+"' is not present in the location '"+fileDownloadPath+"'", true, driver, reportingPath, et);
			}
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return retData;
	}
	
	public Boolean validatePresenceOrAbsenceOfFileInDir(String fileDownloadPath,String fileName,String presenceOrAbsence,WindowsDriver<WebElement> driver,Boolean report,ExtentTest et)
	{
		Boolean returnFlag = null;
		try
		{
			File file = new File(fileDownloadPath+"\\"+fileName);
			
			//Validate presence of file
			if(presenceOrAbsence.equalsIgnoreCase("Present"))
			{
			if(file.exists())
			{
				if (report == true) {
				logger.log("pass", "The file '"+fileName+"' is present in the location '"+fileDownloadPath+"'", false, driver, reportingPath, et);
				}
				returnFlag = true;
			}
			else
			{
				if (report == true) 
				{
				logger.log("fail", "The file '"+fileName+"' is not present in the location '"+fileDownloadPath+"'", true, driver, reportingPath, et);
				}
				returnFlag = false;
			}
			}
			
			//Validate absence of file
			if(presenceOrAbsence.equalsIgnoreCase("Absent"))
			{
			if(file.exists())
			{
				if (report == true) 
				{
				logger.log("fail", "The file '"+fileName+"' is present in the location '"+fileDownloadPath+"'", true, driver, reportingPath, et);
				}
				returnFlag = false;
			}
			else
			{
				if (report == true) 
				{
				logger.log("pass", "The file '"+fileName+"' is not present in the location '"+fileDownloadPath+"'", false, driver, reportingPath, et);
				}
				returnFlag = true;
			}
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return returnFlag;
	}
}






