package automation.careSuiteClient.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;


   public class Constant {
	   
	   
	  
	   
	   public static String username =System.getProperty("user.name");
	   //public static WebDriver driver = null; //Instance variable
       //STAGE
      //public static final String URL = "https://stage.ncdr.com/webncdr/Login?ReturnUrl=/webncdr/home/registry-selection"; //"http://stage.cvquality.acc.org/NCDR-Home.aspx";
                 //Production
      //public static final String URL = "http://cvquality.acc.org/NCDR-Home.aspx";
      public static final String Path_TestData = "Q:\\QA Stuff\\Syam pithani\\testData\\";
      public static final String File_TestData = "TestData.xlsx";
      public static final String Report_Path="Q:\\QA Stuff\\Syam pithani\\NCDR\\NCDR\\WebContent\\WEB-INF\\views\\testReports.html";
   	  public static String htmlTemplatePath = "Q:\\QA Stuff\\Syam pithani\\testData\\htmlTemplate.html";//"C:\\Users\\"+username+"\\TestData\\htmlTemplate.html";
  	  public static String reportFilePath = "Q:\\QA Stuff\\Syam pithani\\testData";//"C:\\Users\\"+username+"\\TestData";
      public static final String ActionDQRFile = "Q:\\QA Stuff\\Syam pithani\\testData\\DQR\\";
      public static final String CathPCIDQRFile = "Q:\\QA Stuff\\Syam pithani\\testData\\DQR\\";
      public static final String ICDDQRFile = "Q:\\QA Stuff\\Syam pithani\\testData\\DQR\\";
      public static final String ImpactDQRFile = "Q:\\QA Stuff\\Syam pithani\\testData\\DQR\\";
      public static final String PVIDQRFile = "Q:\\QA Stuff\\Syam pithani\\testData\\DQR\\";
      public static final String TVTDQRFile = "Q:\\QA Stuff\\Syam pithani\\testData\\DQR\\";
      public static final String logFilePath = "Q:\\QA Stuff\\Syam pithani\\testData\\log\\testlog.log";
     // public static final String logFilePath = "C:\\Users\\"+username+"\\workspace\\DR_NCDR\\log\\testlog.log";
      
 
      


     /* need review
       public static void getExcelPath(String type)throws Exception{
    	   ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Login");
    	   String ACS = ExcelUtils.getCellData(6, 1) ;
    	   }
      */
      
      //To Open Internet Browser
    
      
      /*
      //Firefix Driver
      public static void OpenFFBrowser(WebDriver driver){
          driver = new FirefoxDriver();
          driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
          driver.get(URL);
      } 
      */
      
      
      ///isElementPresent Function
      public static boolean isElementPresent(WebDriver driver, By by) throws Exception{
    	  try {driver.findElement(by);
    	       return true; }  // Success!
    	    catch (NoSuchElementException ignored) 
    	    { return false;}
    	}//end of isElementPresent
      
      //Scroll Down function
      public static void scroll_Down(WebDriver driver)throws Exception{
    	  //Actions actions = new Actions(driver);
    	  //actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
    	   JavascriptExecutor jse = (JavascriptExecutor)driver;
    	  jse.executeScript("scroll(0, 500)"); //y value '250' can be altered
       	  
    	  } //Scroll down end
      
      

      
   }//End of class
   
   
   