/**
 * 
 */
package automation.careSuiteClient.Package11;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.relevantcodes.extentreports.ExtentReports;

/**
 * @author E004014
 *
 */
public class ExtentReportFactory
{
	 ThreadLocal<ExtentReports> extent = new ThreadLocal<ExtentReports>(); // thread local driver object for webdriver
   private ExtentReportFactory()
   {
      //Do-nothing..Do not allow to initialize this class from outside
   }
   private static ExtentReportFactory instance = new ExtentReportFactory();

   public static ExtentReportFactory getInstance()
   {
      return instance;
   }

  
   public final void createTestReport(String filePath) throws Exception
   
   {
	  
	  
	   extent.set(new ExtentReports(filePath));// can be replaced with other browser drivers
				
   }
   
   
   /*
      protected WebDriver initialValue()
      {
    	  System.setProperty("webdriver.chrome.driver",
    				 Constant.Path_TestData  "C:\\CareSuiteManagerAutomationFiles\\WebdriverFiles\\chromedriver_win32_latest\\chromedriver.exe");
    			
    			//To download PDF doc's automatically
    			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
    		   chromePrefs.put("plugins.plugins_disabled", new String[] {"Chrome PDF Viewer"});
    		   chromePrefs.put("profile.default_content_settings.popups", 0);
    		   chromePrefs.put("plugins.always_open_pdf_externally", true);
    		 	   
    		   ChromeOptions options = new ChromeOptions();
    		     
    		   options.setExperimentalOption("prefs", chromePrefs);
    		   
    		     DesiredCapabilities cap = DesiredCapabilities.chrome();
    		     cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    		     cap.setCapability(ChromeOptions.CAPABILITY, options);
    		     //driver = new ChromeDriver(cap);
         return  new ChromeDriver(cap);// can be replaced with other browser drivers
      }
  
*/
   public ExtentReports getReport() // call this method to get the driver object and launch the browser
   {
      return extent.get();
   }

  
}