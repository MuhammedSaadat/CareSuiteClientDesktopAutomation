package automation.careSuiteClient.appModule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

public class DateTime {
	
public static void Execute(WebDriver driver) throws Exception{
	
	String dQRDate = null;
	String CDate =null;
	
	Date d1 = null;
	Date d2 = null;
		
		try{

			SimpleDateFormat format  = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
			dQRDate="8/27/2015 12:09:02 PM";
			//String matchDateTime = sdf.parse("8/27/2015 12:09:02");
			//Date matchDateTime = null;
			//System.out.println(ddd);
			
			Date dt =new Date();
			
			d1=format.parse(dQRDate);
		
			System.out.println(d1);
			System.out.println(dt);
			
			if(d1.compareTo(dt)<=0) 
			{System.out.println("Pass message");
		}else 
		{System.out.println("Failed message");}
			
					}catch		
	    (Exception exp){	 

		
	    	}
	
    
}//end Method
}
