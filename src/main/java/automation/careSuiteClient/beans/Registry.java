package automation.careSuiteClient.beans;

import java.util.Set;

public class Registry {

	private Long registryId;
	private String registryName;
	private String registryWelcomeLabel;
	private String registryWelcomeLabelXpath;
	private String executeRegistry;
	private String headers;
	private String links;

	public Long getRegistryId() {
		return registryId;
	}

	public void setRegistryId(Long registryId) {
		this.registryId = registryId;
	}

	public String getRegistryName() {
		return registryName;
	}

	public void setRegistryName(String registryName) {
		this.registryName = registryName;
	}

	public String getRegistryWelcomeLabel() {
		return registryWelcomeLabel;
	}

	public void setRegistryWelcomeLabel(String registryWelcomeLabel) {
		this.registryWelcomeLabel = registryWelcomeLabel;
	}

	public String getRegistryWelcomeLabelXpath() {
		return registryWelcomeLabelXpath;
	}

	public void setRegistryWelcomeLabelXpath(String registryWelcomeLabelXpath) {
		this.registryWelcomeLabelXpath = registryWelcomeLabelXpath;
	}

	public String getExecuteRegistry() {
		return executeRegistry;
	}

	public void setExecuteRegistry(String executeRegistry) {
		this.executeRegistry = executeRegistry;
	}

	@Override
	public String toString() {
		return "Registry [registryId=" + registryId + ", registryName="
				+ registryName + ", registryWelcomeLabel="
				+ registryWelcomeLabel + ", registryWelcomeLabelXpath="
				+ registryWelcomeLabelXpath + ", executeRegistry="
				+ executeRegistry + ", headers=" + headers + ", links=" + links
				+ "]";
	}

	public String getHeaders() {
		return headers;
	}

	public void setHeaders(String headers) {
		this.headers = headers;
	}

	public String getLinks() {
		return links;
	}

	public void setLinks(String links) {
		this.links = links;
	}

	
}
