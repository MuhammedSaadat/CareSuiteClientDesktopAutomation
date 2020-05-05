package automation.careSuiteClient.appModule;

import java.util.List;

public class Configuration {

	private String url;
	private String browser;
	private String AllChecked;
	private String participantId;
	private String userName;
	private String password;
	private String scenarios;
	private String[] module;
	private Boolean emailReport;
	private String mailTo;
	private List<String> scenario;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getAllChecked()
	{
		return AllChecked;
	}
	public void setAllChecked(String AllChecked)
	{
		this.AllChecked= AllChecked;
	}
	

	public String getParticipantId() {
		return participantId;
	}

	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getScenarios() {
		return scenarios;
	}
	public void setModule(String[] module) {
		this.module=module;
	}
	public String[] getModule() {
		return module;
	}
	
	public void setScenarios(String scenarios) {
		this.scenarios = scenarios;
	}

	public Boolean getEmailReport() {
		return emailReport;
	}

	public void setEmailReport(Boolean emailReport) {
		this.emailReport = emailReport;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	@Override
	public String toString() {
		return "Configuration [AllChecked=" + AllChecked + ",url=" + url + ", browser=" + browser
				+ ", participantId=" + participantId + ", userName=" + userName
				+ ", password=" + password + ", scenarios=" + scenarios
				+ ", emailReport=" + emailReport + ", mailTo=" + mailTo + "]";
	}

}
