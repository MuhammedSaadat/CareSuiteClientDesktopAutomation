package automation.careSuiteClient.appModule;

import java.applet.Applet;
import java.util.Random;

public class Test  {

	public static void main(String args[]) {
		/*
		 * Logger logger = new Logger();
		 * 
		 * logger.createTestReport("Q:\\QA Stuff\\Syam pithani\\testReports.html"
		 * ); logger.createTest("Positive login",
		 * "This test is a positive login test for ParaBank");
		 * logger.log("pass", "hi", false, new FirefoxDriver(), "");
		 * logger.log("fail", "hi", false, new FirefoxDriver(), "");
		 * logger.flush(); logger.createTest("Positive logout",
		 * "This test is a positive login test for ParaBank");
		 * logger.log("pass", "hi", false, new FirefoxDriver(), "");
		 * logger.log("fail", "hi", false, new FirefoxDriver(), "");
		 * logger.flush();
		 */

		/*String sample = "hi#id##hello";
		String[] split = sample.split("##");
		System.out.println(split[0] + " " + split[1]);
		if (sample.contains("##")) {
			System.out.println("contains");
		}

		String s = "a,b";
		String[] sp = s.split("\\,");
		System.out.println(sp[0] + " " + sp[1]);*/

		/*List<Registry> registries = ExcelUtils.getRegisteries();
		Map<String, List<Tab>> map = Utils
				.getTabsAndChildrenFromRegistry(registries);
		System.out.println(map);*/
		
		/*String reg="ActionAdmin";
		if(reg.contains("Admin"))
			System.out.println("contains");*/
		
		/*String url="https://stage.ncdr.com/ACTIONDCT/Episode/Edit/702";
		String[] buffer=url.split("/");
		
		System.out.println(buffer[buffer.length-1]);*/
		
		String[] registries = { "ACTION Registry - GWTG", "CathPCI Registry",
				"Diabetes Collaborative Registry", "IMPACT Registry",
				"LAAO Registry", "PINNACLE Registry", "PVI Registry",
				"STS/ACC TVT Registry", "ICD Registry",
				"AFib Ablation Registry" };
		
		int randomIndex =new Random().nextInt(registries.length);
		System.out.println(randomIndex);
		
	}
	
	public void CallFromConfig(){
		System.out.println("In CallFromConfig");
	}
	
	

}
