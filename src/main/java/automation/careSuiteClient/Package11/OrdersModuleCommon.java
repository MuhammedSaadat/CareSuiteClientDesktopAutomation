package automation.careSuiteClient.Package11;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentTest;

import automation.careSuiteClient.utility.Utils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.windows.WindowsDriver;

public class OrdersModuleCommon {
	
	//Reports creation objects
	private static String reportingPath = Utils.loadProperty("reportingPath");
	private static String reportPath = Utils.loadProperty("reportPath");
	protected Logger logger = new Logger();

	//Business class objects
	ClientCommon cliCom = new ClientCommon();
	Keywords actions = new Keywords();
	UiMap map = new UiMap();
	

}
