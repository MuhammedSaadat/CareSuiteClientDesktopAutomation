package automation.careSuiteClient.beans;

public class TestDataForACC
{
	private String TestScenario;
	private String TestCase;
	private String UserName;
	private String Credential;
	private String Password;
	private String executeRegistry;
	
	public String getCredential() {
        return Credential;
    }
    public void setCredential(String credential) {
        Credential = credential;
    }
	
	public String getTestScenario() {
		return TestScenario;
	}
	public void setTestScenario(String testScenario) {
		TestScenario = testScenario;
	}
	public String getTestCase() {
		return TestCase;
	}
	public void setTestCase(String testCase) {
		TestCase = testCase;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	public String getExecuteRegistry() {
        return executeRegistry;
    }
    
    
    public void setExecuteRegistry(String executeRegistry) {
        this.executeRegistry = executeRegistry;
    }
	
	@Override
	public String toString() {
		return "TestDataForACC [TestScenario=" + TestScenario + ", TestCase="
				+ TestCase + ", UserName=" + UserName + ", Password="
				+ Password + ", Credential=" + Credential + "]";
	}
}
