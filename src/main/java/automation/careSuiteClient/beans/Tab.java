package automation.careSuiteClient.beans;

import java.util.List;

public class Tab {

	private String id;
	private String tabName;

	private List<Tab> childTabs;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public List<Tab> getChildTabs() {
		return childTabs;
	}

	public void setChildTabs(List<Tab> childTabs) {
		this.childTabs = childTabs;
	}

	@Override
	public String toString() {
		return "Tab [id=" + id + ", tabName=" + tabName + ", childTabs="
				+ childTabs + "]";
	}

}
