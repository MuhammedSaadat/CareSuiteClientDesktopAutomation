package automation.careSuiteClient.utility;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reporter {
	
	private static List<Result>details;
	private static final String resultPlaceholder = "<!-- INSERT_RESULTS -->";
	private String htmlTemplatePath; 
	// = "Z:\\Documents\\Bas\\blog\\files\\report_template.html";
	private String reportFilePath; 
	
	public Reporter(String htmlTemplatePath, String reportFilePath) {
		this.htmlTemplatePath = htmlTemplatePath;
		this.reportFilePath = reportFilePath;	
		details = new ArrayList<Result>();		
	}
	
	
	public void initialize() {
		details = new ArrayList<Result>();
	}
	
	public void report(String testCategory, String testScenario, String testResult) {
		Result r = new Result(testCategory,testScenario,testResult);
		details.add(r);
	}
	
	public void showResults() {
		for (int i = 0;i < details.size();i++) {
			System.out.println(details.get(i).getResultCategory()+ ": "+ details.get(i).getResultText() + ": " + details.get(i).getResult());
		}
	}
	
	public void writeResults() {
		try {
			String reportIn = new String(Files.readAllBytes(Paths.get(htmlTemplatePath)));
			for (int i = 0; i < details.size();i++) {
				reportIn = reportIn.replaceFirst(resultPlaceholder,"<tr><td>" + details.get(i).getResultCategory() 
						+ "</td><td>" + details.get(i).getResultText() + "</td><td>" + details.get(i).getResult() 
						+ "</td></tr>" + resultPlaceholder);
			}
			
			String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			String reportPath = reportFilePath + "\\report_" + currentDate + ".html";
			Files.write(Paths.get(reportPath),reportIn.getBytes(),StandardOpenOption.CREATE);
			
		} catch (Exception e) {
			System.out.println("Error when writing report file:\n" + e.toString());
		}
	}
}