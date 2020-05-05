/**
 * 
 */
package automation.careSuiteClient.Package11;

import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.windows.WindowsDriver;

/**
 * @author E004014
 *
 */
public class DriverFactoryDesktopSession
{
	
	//WindowsDriver<WebElement> driver;
	 ThreadLocal<WindowsDriver<WebElement>> driver = new ThreadLocal<WindowsDriver<WebElement>>(); // thread local driver object for webdriver
   private DriverFactoryDesktopSession()
   {
      //Do-nothing..Do not allow to initialize this class from outside
   }
   private static DriverFactoryDesktopSession instance = new DriverFactoryDesktopSession();

   public static DriverFactoryDesktopSession getInstance()
   {
      return instance;
   }

  
   public final void setDriver(String platform,String seleniumGridNodeUrl) throws Exception
   
	{
	   DesiredCapabilities appCapabilities = null;
	   
	   if(platform.trim().equalsIgnoreCase("Windows")) {
		   appCapabilities = new DesiredCapabilities();
			appCapabilities.setCapability("app", "Root");
			
			if (seleniumGridNodeUrl.equalsIgnoreCase("local"))
			{
			driver.set(new WindowsDriver<WebElement>(new URL("http://127.0.0.1:4723"), appCapabilities));
			}
			

		

		}
		
		/*//Setting driver in node
		if (!seleniumGridNodeUrl.equalsIgnoreCase("local")) {
			driver.set(new RemoteWebDriver(new URL(seleniumGridNodeUrl), cap));
		}*/
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
   public WindowsDriver<WebElement> getDriver() // call this method to get the driver object and launch the browser
   {
      return driver.get();
   }

   public void removeDriver() // Quits the driver and closes the browser
   {
      driver.get().quit();
      driver.remove();
   }
}