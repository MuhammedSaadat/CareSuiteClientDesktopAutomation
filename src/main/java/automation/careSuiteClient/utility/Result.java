package automation.careSuiteClient.utility;

public class Result {

	private String resultCategory;
	private String result;
	private String resultText;
	
	public Result(String resultCategory, String resultText, String result) {
		this.resultCategory = resultCategory;
		this.resultText = resultText;
		this.result = result;
	}
	
	public String getResultCategory() {
		return resultCategory;
	}

	public void setResultCategory(String resultCategory) {
		this.resultCategory = resultCategory;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getResult() {
		return this.result;
	}
	
	public void setResultText(String resultText) {
		this.resultText = resultText;
	}
	
	public String getResultText() {
		return this.resultText;
	}	
}
