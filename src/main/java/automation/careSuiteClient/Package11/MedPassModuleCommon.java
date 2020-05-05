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

public class MedPassModuleCommon {
	
	//Reports creation objects
	private static String reportingPath = Utils.loadProperty("reportingPath");
	private static String reportPath = Utils.loadProperty("reportPath");
	protected Logger logger = new Logger();

	//Business class objects
	ClientCommon cliCom = new ClientCommon();
	Keywords actions = new Keywords();
	UiMap map = new UiMap();
	
public void selectingResidentFromMedPassScreen(String reisdentName,ExtentTest et) {
		
		try {
			//Application and desktop objects
			WindowsDriver<WebElement> applicationSessionObject;
			
			//Selecting the resident
			applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
			actions.waitForElementVisibility(map.medPassResidentsList,"AccessId",applicationSessionObject,20);
			actions.isElementPresent(map.medPassResidentsList, "Residents list", true, applicationSessionObject, et);
			List<WebElement> residentList=applicationSessionObject.findElements(map.medPassResidentsList);
			int cnt = 0;
			for (int i=0;i<residentList.size();i++)
			{   
				if (residentList.get(i).findElement(map.medPassResidentFullName).getText().trim().equals(reisdentName))
				{
					actions.click(residentList.get(i), "resident "+reisdentName, applicationSessionObject, et);
					cnt=1;
					break;
				}
			}
			
			//Report if resident not present
			if(cnt==0)
			{
				logger.log("fail", "The resident '"+reisdentName+"' is not present in Pass Meds screen", true, applicationSessionObject, reportingPath, et);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


public void selectMedicationPassMedsScreen(String medicationName,String selectMedication,String option,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		
		
		//Selecting a med
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.medicationsList,"AccessId",applicationSessionObject,20);
		List<WebElement> medItems=applicationSessionObject.findElements(map.medicationsList);
		int cnt = 0;
		int j=0;
		for (int i=0;i<medItems.size();i++)
		{
			if (medItems.get(i).findElement(map.medicationTitleMedicationsList).getText().trim().equals(medicationName))
			{
				//Select medication
				if(selectMedication.trim().equalsIgnoreCase("Yes"))
				{
				actions.click(medItems.get(i), "medication "+medicationName, applicationSessionObject, et);
				}
				cnt=1;
				j=i;
				break;
			}
		}
		
		//This code is used to validate presence/absence of tick mark
		if(option.trim().equalsIgnoreCase("validateTickMark") || option.trim().equalsIgnoreCase("validateTickMarkAbsence"))
		{
		//Report if medication is not present
		if(cnt==0)
		{
			logger.log("fail", "The medication '"+medicationName+"' is not present in Pass Meds screen", true, applicationSessionObject, reportingPath, et);
		}
		
		else
		{
		//Validate presence of tick mark
		if(option.trim().equalsIgnoreCase("validateTickMark"))
		{
		WebElement tickMark=medItems.get(j).findElement(map.tickMarkMedicationsList);
		actions.isElementPresent(tickMark, "Tick mark for medication '"+medicationName+"'", true, applicationSessionObject, et);
		}
		
		//Validate absence of tick mark
		if(option.trim().equalsIgnoreCase("validateTickMarkAbsence"))
		{
		try
		{
		WebElement tickMark=medItems.get(j).findElement(map.tickMarkMedicationsList);
		logger.log("fail", "Tick mark for medication '"+medicationName+"' is displayed", true, applicationSessionObject, reportingPath, et);
		}
		catch (Exception e)
		{
		logger.log("pass", "Successfully tick mark for medication '"+medicationName+"' is not displayed", false, applicationSessionObject, reportingPath, et);	
		}
		}
		}
		}
		
		//This code is used to validate presence/absence of medication
		if(option.trim().equalsIgnoreCase("validateMedicationPresent") || option.trim().equalsIgnoreCase("validateMedicationAbsent"))
		{
			//Validate presence of medication
			if (option.trim().equalsIgnoreCase("validateMedicationPresent"))
				{
				if(cnt!=0)
				{
					logger.log("pass", "Successfully displaying medication '"+medicationName+"' ", false, applicationSessionObject, reportingPath, et);
				}
				else
				{
					logger.log("fail", "Successfully not displaying medication '"+medicationName+"' ", true, applicationSessionObject, reportingPath, et);
				}
				}
			
			//Validate absence of medication
			if (option.trim().equalsIgnoreCase("validateMedicationAbsent"))
				{
				if(cnt==0)
				{
					logger.log("pass", "Successfully not displaying medication '"+medicationName+"' ", false, applicationSessionObject, reportingPath, et);
				}
				else
				{
					logger.log("fail", "Displaying medication '"+medicationName+"' ", true, applicationSessionObject, reportingPath, et);
				}
				}
			
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void validatePassMedsResidentScreenIsDisplayed(ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.medPassResidentsList,"AccessId",applicationSessionObject,20);
		actions.isElementPresent(map.medPassResidentsList, "Residents list", true, applicationSessionObject, et);
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void clickNextButtonAndContinueButtonFromPickListExceptionsWindowIfPresent(String pickListExceptionsWindow,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Click on Next button
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.nextButtonMedicationListWindow,"Name",applicationSessionObject,20);
		actions.click(map.nextButtonMedicationListWindow, "Next button", applicationSessionObject, et);
		
		//Click on 'Pick List Exceptions' Continue button if present
		if(pickListExceptionsWindow.trim().equalsIgnoreCase("Present"))
		{
		actions.waitForElementVisibility(map.pickListExceptionsWindow, "Name", applicationSessionObject, 20);
		if(actions.isElementPresent(map.pickListExceptionsWindow, "Pick List Exceptions Window", false, applicationSessionObject, et))
		{
			actions.isElementPresent(map.pickListExceptionsWindow, "Pick List Exceptions Window", true, applicationSessionObject, et);
			actions.click(map.continueButtonPickListExceptionsWindow, "Continue button", applicationSessionObject, et);
		}
		}
		
		//Validate elements in 'Pick List Exceptions' window and click 'Continue' button
		if(pickListExceptionsWindow.trim().equalsIgnoreCase("ValidateUIPickList"))
		{
		actions.waitForElementVisibility(map.pickListExceptionsWindow, "Name", applicationSessionObject, 20);
		actions.isElementPresent(map.pickListExceptionsWindow, "Pick List Exceptions Window", true, applicationSessionObject, et);
		actions.isElementPresent(map.dropDownListPickListExceptionsWindow, "Drop Down List", true, applicationSessionObject, et);
		actions.isElementPresent(map.continueButtonPickListExceptionsWindow, "Continue Button", true, applicationSessionObject, et);
		actions.isElementPresent(map.cancelButtonPickListExceptionsWindow, "Cancel Button", true, applicationSessionObject, et);
		actions.click(map.continueButtonPickListExceptionsWindow, "Continue button", applicationSessionObject, et);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void clickRecordAllButtonFromConfirmationScreen(ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Click on Record All button
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.confirmationWindow,"AccessId",applicationSessionObject,20);
		actions.isElementPresent(map.confirmationWindow, "Confirmation screen", true, applicationSessionObject, et);
		actions.waitForElementVisibility(map.recordAllButtonConfirmationWindow,"AccessId",applicationSessionObject,20);
		actions.click(map.recordAllButtonConfirmationWindow, "Record All button", applicationSessionObject, et);
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void clickOKButtonConfirmMedPassWindow(ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Validate elements in 'Confirm Med Pass' window
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.confirmMedPassWindow,"Name",applicationSessionObject,20);
		actions.isElementPresent(map.confirmMedPassWindow, "Confirm Med Pass window", true, applicationSessionObject, et);
		actions.isElementPresent(map.okButtonConfirmMedPassWindow, "OK Button", true, applicationSessionObject, et);
		actions.isElementPresent(map.cancelButtonConfirmMedPassWindow, "Cancel Button", true, applicationSessionObject, et);
		
		//Click on 'Ok' button
		actions.click(map.okButtonConfirmMedPassWindow, "OK Button", applicationSessionObject, et);
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void createMedOfTypeMedicationInManageOrdersScreen(String fromDropdownValue,String startTimeDropdownValue,String prescriber,
		String routineType,String controlledDrug,String option,	ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		WindowsDriver<WebElement> desktopSession;
		
		//Storing values
		String randomString=actions.generateRandomAlphaString(5);
		String medicationNamelocal="Test Medication "+randomString;
		MedPassModuleTestCases.medicationName=medicationNamelocal;
		String instructions="Test Instructions "+randomString;
				
		
		//Click on 'Add' button
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.addDropdownButtonOrdersWindow,"Name",applicationSessionObject,20); 
		actions.click(map.addDropdownButtonOrdersWindow, "Add dropdown button", applicationSessionObject, et);
		
		//Select 'Medication' option
		DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
		desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.medicationOptionAddDropdownButton,"Name",desktopSession,20); 
		actions.click(map.medicationOptionAddDropdownButton, "Medication option", desktopSession, et);
		
		//Enter Medication name
		actions.waitForElementVisibility(map.medicationTextboxParentOrdersWindow,"AccessId",applicationSessionObject,20);
		WebElement medicationNameParentTextbox=applicationSessionObject.findElement(map.medicationTextboxParentOrdersWindow);
		WebElement medicationNameTextbox=medicationNameParentTextbox.findElement(map.medicationTextboxOrdersWindow);
		actions.setValue(medicationNameTextbox, "Medication textfield", medicationNamelocal, applicationSessionObject, et);
		
		//Select option from 'From' dropdown
		WebElement fromDropdown = applicationSessionObject.findElement(map.fromDropdownOrdersWindow);
		fromDropdown.click();
		WebElement editBoxFromDropdown = fromDropdown.findElement(map.fromDropdownTextboxOrdersWindow);
		int cnt=0;
		while (true) {
			editBoxFromDropdown.sendKeys(Keys.DOWN);
			if (editBoxFromDropdown.getAttribute("Value.Value").equalsIgnoreCase(fromDropdownValue.trim()))
			{
				cnt=cnt+1;
				break;
			}	
		}
		if(cnt==1)
		{
			logger.log("pass", "Successfully selected value '"+fromDropdownValue+"' in the 'From' dropdown", false, applicationSessionObject, reportingPath, et);
		}
		else
		{
			logger.log("fail", "Successfully not selected value '"+fromDropdownValue+"' in the 'From' dropdown", true, applicationSessionObject, reportingPath, et);
		}
		
		//Select time from 'Start' dropdown
		WebElement timeDropdown = applicationSessionObject.findElement(map.startTimeDropdownOrdersWindow);
		WebElement openButtonTimeDropdown = timeDropdown.findElement(map.openButtonStartTimeDropdownOrdersWindow);
		actions.click(openButtonTimeDropdown, "Start time dropdown", applicationSessionObject, et);
		DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
		desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
		WebElement timeListDropdown = desktopSession.findElement(map.listDropdownStartTimeOrdersWindow);
		WebElement optionTimeListDropdown = timeListDropdown.findElement(By.name(startTimeDropdownValue));
		actions.click(optionTimeListDropdown, "option "+startTimeDropdownValue, desktopSession, et);
		
		//Selecting Prescriber
		WebElement prescriberParentTextbox=applicationSessionObject.findElement(map.prescriberTextboxParentOrdersWindow);
		WebElement prescriberTextbox=prescriberParentTextbox.findElement(map.prescriberTextboxOrdersWindow);
		actions.setValue(prescriberTextbox, "Prescriber text field", prescriber, applicationSessionObject, et);
		DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
		desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.listDropdownPrescriberTextboxOrdersWindow,"AccessId",desktopSession,20);
		WebElement prescriberDropdown=desktopSession.findElement(map.listDropdownPrescriberTextboxOrdersWindow);
		WebElement optionPrescriberDropdown=prescriberDropdown.findElement(By.name(prescriber));
		actions.click(optionPrescriberDropdown, "option '"+prescriber+"'", desktopSession, et);
		
		//Entering Instructions
		actions.setValue(map.instructionsTextboxOrdersWindow, "Instructions text field", instructions, applicationSessionObject, et);
		
		//Click on 'Add' dropdown button and select 'Daily' option
		WebElement treatmentSchedulesSection = applicationSessionObject.findElement(map.treatmentSchedulesSection);
		WebElement addDropdown = treatmentSchedulesSection.findElement(map.addDropdownButtonTreatmentSchedulesSection);
		actions.click(addDropdown, "Add dropdown", applicationSessionObject, et);
		DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
		desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.addDropdownTreatmentSchedulesSection,"Name",desktopSession,20);
		WebElement dropdown = desktopSession.findElement(map.addDropdownTreatmentSchedulesSection);
		WebElement dropdownOption = dropdown.findElement(By.name(routineType));
		actions.click(dropdownOption, "option '"+routineType+"'", desktopSession, et);
		
		//Check 'Controlled Drug' check box if required
		if(controlledDrug.trim().equalsIgnoreCase("Yes"))
		{
			actions.click(map.controlledDrugCheckboxOrdersWindow, "Controlled Drug Checkbox", applicationSessionObject, et);
		}
		
		//Entering data in Strength, Diagnosis and Route fields
		if(option.trim().equalsIgnoreCase("EnterStrDiagRou"))
		{
			//Storing instructions data
			MedPassModuleTestCases.hashmapMedicationData.put("Instructions", instructions);
			
			//Entering strength and diagnosis
			actions.setValue(map.strengthTextboxOrdersTab, "Strength text field", MedPassModuleTestCases.hashmapMedicationData.get("Strength"), applicationSessionObject, et);
			actions.setValue(map.diagnosisTextboxOrdersTab, "Diagnosis text field", MedPassModuleTestCases.hashmapMedicationData.get("Diagnosis"), applicationSessionObject, et);
			
			//Clicking on 'Route' dropdown and selecting an option
			actions.click(map.routeDropdownOrdersTab, "Route Dropdown", applicationSessionObject, et);
			DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
			desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
			actions.waitForElementVisibility(map.routeDropdownComboboxOrdersTab,"ClassName",desktopSession,20);
			WebElement optionRouteDropdown = desktopSession.findElement(map.routeDropdownComboboxOrdersTab).findElement(By.name(MedPassModuleTestCases.hashmapMedicationData.get("Route")));
			actions.click(optionRouteDropdown, "option '"+MedPassModuleTestCases.hashmapMedicationData.get("Route")+"'", desktopSession, et);
			
			
		}
		
		//Click on 'Save' button
		actions.click(map.saveButtonOrdersWindow, "Save button", applicationSessionObject, et);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void createExceptiononfirmationScreen(String exceptionType,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		WindowsDriver<WebElement> desktopSession;
		
		//Clicking on 'Exception' button
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.exceptionPaneConfirmationScreen,"AccessId",applicationSessionObject,20); 
		WebElement exceptionButton=applicationSessionObject.findElement(map.exceptionPaneConfirmationScreen).findElement(map.exceptionButtonConfirmationScreen);
		actions.click(exceptionButton, "Exception button", applicationSessionObject, et);
		
		//Selecting option from dropdown
		DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
		desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.exceptionDropdownConfirmationScreen,"Name",desktopSession,20); 
		WebElement exceptionOption=desktopSession.findElement(map.exceptionDropdownConfirmationScreen).findElement(By.name(exceptionType));
		actions.click(exceptionOption, "option '"+exceptionType+"'", desktopSession, et);
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void clickingTakeFromEBoxNoInventoryWindow(String validateUI,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Validate the elements in 'No Inventory' window
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		if(validateUI.trim().equalsIgnoreCase("Yes"))
		{
			actions.waitForElementVisibility(map.noInventoryWindow,"Name",applicationSessionObject,20);
			actions.isElementPresent(map.noInventoryWindow, "No Inventory window", true, applicationSessionObject, et);
			WebElement takeFromEBoxButton=applicationSessionObject.findElement(map.noInventoryWindow).findElement(map.takeFromEBoxButtonNoInventoryWindow);
			actions.isElementPresent(takeFromEBoxButton, "Take from e-Box button", true, applicationSessionObject, et);
		}
		
		
		//Click on 'Take From E Box' button
		WebElement takeFromEBoxButton=applicationSessionObject.findElement(map.noInventoryWindow).findElement(map.takeFromEBoxButtonNoInventoryWindow);
		actions.click(takeFromEBoxButton, "Take from e-Box button", applicationSessionObject, et);
			
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void validateDataInInventoryTrackingAcknowledgementWindowAndClickOKButton(String itemValue,String remainingInventoryValue,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Validate elements in 'InventoryTrackingAcknowledgement' window
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.inventoryTrackingAcknowledgementWindow,"Name",applicationSessionObject,20);
		WebElement inventoryTrackingAcknowledgementWindow=applicationSessionObject.findElement(map.inventoryTrackingAcknowledgementWindow);
		actions.isElementPresent(map.inventoryTrackingAcknowledgementWindow, "Inventory Tracking Acknowledgement Window", true, applicationSessionObject, et);
		WebElement okButton=inventoryTrackingAcknowledgementWindow.findElement(map.okButtonInventoryTrackingAcknowledgementWindow);
		actions.isElementPresent(okButton, "OK Button", true, applicationSessionObject, et);
		WebElement cancelButton=inventoryTrackingAcknowledgementWindow.findElement(map.cancelButtonInventoryTrackingAcknowledgementWindow);
		actions.isElementPresent(cancelButton, "Cancel Button", true, applicationSessionObject, et);
		
		//Validate itemValue and remainingInventoryValue
		WebElement itemCellValue=inventoryTrackingAcknowledgementWindow.findElement(By.xpath("//Text[@Name='"+itemValue+"']"));
		actions.isElementPresent(itemCellValue, "item value as '"+itemValue+"'", true, applicationSessionObject, et);
		WebElement remainingInventoryCellValue=inventoryTrackingAcknowledgementWindow.findElement(By.xpath("//Text[@Name='"+remainingInventoryValue+"']"));
		actions.isElementPresent(remainingInventoryCellValue, "remaining inventory value as '"+remainingInventoryValue+"'", true, applicationSessionObject, et);
		WebElement yesButton=inventoryTrackingAcknowledgementWindow.findElement(map.yesButtonInventoryTrackingAcknowledgementWindow);
		actions.isElementPresent(yesButton, "Yes Button", true, applicationSessionObject, et);
		WebElement noButton=inventoryTrackingAcknowledgementWindow.findElement(map.noButtonInventoryTrackingAcknowledgementWindow);
		actions.isElementPresent(noButton, "No Button", true, applicationSessionObject, et);
		
		//Click on 'Yes' button
		actions.click(yesButton, "Yes button", applicationSessionObject, et);
		
		//Click on 'OK' button
		actions.click(okButton, "OK button", applicationSessionObject, et);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void validateDetailsInWitnessConfirmationWindowAndEnterWitnessCredentials(String itemValue,String exceptionvalue,String amountGivenvalue,String userName,String password,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Validate elements in 'Witness Confirmation' window
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.witnessConfirmationWindow, "Name", applicationSessionObject, 20);
		actions.isElementPresent(map.witnessConfirmationWindow, "Witness Confirmation Window", true, applicationSessionObject, et);
		actions.isElementPresent(map.cancelButtonWitnessConfirmationWindow, "Cancel Button", true, applicationSessionObject, et);
		actions.isElementPresent(map.okButtonWitnessConfirmationWindow, "OK button", true, applicationSessionObject, et);		
		
		//Enter credentials
		actions.setValue(map.userNameTextboxWitnessConfirmationWindow, "UserName Textbox", userName, applicationSessionObject, et);
		actions.setValue(map.passwordTextboxWitnessConfirmationWindow, "Password Textbox", password, applicationSessionObject, et);
		
		//Click on 'OK' button
		actions.click(map.okButtonWitnessConfirmationWindow, "OK button", applicationSessionObject, et);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void generateReportForMedsTakenFromEKitAndValidate(String startDate,String endDate,String cartName,String validateUI,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		WindowsDriver<WebElement> desktopSessionObject;
		
		//Validate elements in 'MedsTakenFromEKit' window
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.medsTakenfromEKitWindow, "Name", applicationSessionObject, 40);
		actions.isElementPresent(map.medsTakenfromEKitWindow, "Meds Taken From E-Kit window", true, applicationSessionObject, et);
		if(validateUI.trim().equalsIgnoreCase("Yes"))
		{
			actions.isElementPresent(map.startDateTextFieldMedsTakenfromEKitWindow, "Start Date Textfield", true, applicationSessionObject, et);
			actions.isElementPresent(map.endDateTextFieldMedsTakenfromEKitWindow, "End Date Textfield", true, applicationSessionObject, et);
			actions.isElementPresent(map.startDateDropDownIconMedsTakenfromEKitWindow, "Start Date Calendar Icon", true, applicationSessionObject, et);
			actions.isElementPresent(map.endDateDropDownIconMedsTakenfromEKitWindow, "End Date Calendar Icon", true, applicationSessionObject, et);
			actions.isElementPresent(map.viewReportButtonMedsTakenfromEKitWindow, "View Report Button", true, applicationSessionObject, et);
		}
		
		//Select start date and End date
		actions.setValue(map.startDateTextFieldMedsTakenfromEKitWindow, "Start Date Textfield", "4/2/2020", applicationSessionObject, et);
		
		//Click on 'View Report' button
		actions.click(map.viewReportButtonMedsTakenfromEKitWindow, "View Report Button", applicationSessionObject, et);
		Thread.sleep(10000);
		
		//Validate report
		
		
		//Close window
		DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
		desktopSessionObject = DriverFactoryDesktopSession.getInstance().getDriver();
		actions.closingWindowUsingActionsClass(map.medsTakenfromEKitWindow,"Meds Taken From E-Kit window",desktopSessionObject,et);
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void ifWitnessConfirmationWindowIsPresentEnterWitnessCredentials(String itemValue,String exceptionvalue,String amountGivenvalue,String userName,String password,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Enter credentials if window is present
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.witnessConfirmationWindow, "Name", applicationSessionObject, 20);
		if(actions.isElementPresent(map.witnessConfirmationWindow, "Witness Confirmation Window", false, applicationSessionObject, et))
		{
		validateDetailsInWitnessConfirmationWindowAndEnterWitnessCredentials(itemValue,exceptionvalue,amountGivenvalue,userName,password,et);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void clickOnSkipAllButtonValidateExceptionsAndSelectAnExceptionInConfirmationScreen(String validateOptions,String selectOption,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		WindowsDriver<WebElement> desktopSession;
		
		//Click on 'Skip All' button
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.skipAllButtonConfirmationScreen, "AccessId", applicationSessionObject, 20);
		actions.click(map.skipAllButtonConfirmationScreen, "Skip All button", applicationSessionObject, et);
		
		//Validate options
		if(!validateOptions.isEmpty())
		{
			DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
			desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
			actions.waitForElementVisibility(map.skipAllDropDownConfirmationScreen, "Name", desktopSession, 20);
			WebElement skipAllDropdown=desktopSession.findElement(map.skipAllDropDownConfirmationScreen);
			actions.isElementPresent(map.skipAllDropDownConfirmationScreen, "Skip All DropDown", true, desktopSession, et);
			actions.isElementPresent(skipAllDropdown.findElement(map.withheldPerRNDoctorsRequestOptionSkipAllDropDown), "Withheld per RN/Doctor's request option", true, desktopSession, et);
			actions.isElementPresent(skipAllDropdown.findElement(map.myCustomExceptionOptionSkipAllDropDown), "My Custom Exception option", true, desktopSession, et);
			actions.isElementPresent(skipAllDropdown.findElement(map.givenByHomeHealthOptionSkipAllDropDown), "Given by Home Health option", true, desktopSession, et);
			actions.isElementPresent(skipAllDropdown.findElement(map.outOfFacilityOptionSkipAllDropDown), "Out of facility option", true, desktopSession, et);
			actions.isElementPresent(skipAllDropdown.findElement(map.givenToFamilyToGiveLaterOptionSkipAllDropDown), "Given to family to give later option", true, desktopSession, et);
			actions.isElementPresent(skipAllDropdown.findElement(map.residentRefusedOptionSkipAllDropDown), "Resident refused option", true, desktopSession, et);
		}
		
		//Click the selected option
		if(!selectOption.isEmpty())
		{
			DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
			desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
			actions.waitForElementVisibility(map.skipAllDropDownConfirmationScreen, "Name", desktopSession, 20);
			WebElement skipAllDropdown=desktopSession.findElement(map.skipAllDropDownConfirmationScreen);
			actions.click(skipAllDropdown.findElement(By.name(selectOption)), " option '"+selectOption+"'", desktopSession, et);
		}
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void validateWarningMessageInCannotCompleteMedPassWindow(String expMessage,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Validate the warning message
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.cannotCompleteMedPassWindow,"Name",applicationSessionObject,20);
		actions.isElementPresent(map.cannotCompleteMedPassWindow, "Cannot Complete Med Pass window", true, applicationSessionObject, et);
		String actMessage=actions.getTextValue(map.messageCannotCompleteMedPassWindow, "Message", applicationSessionObject,et);
		if(actMessage.trim().contains(expMessage.trim()))
		{
			logger.log("pass", "Successfully displaying exp warning message '"+expMessage+"'", false, applicationSessionObject, reportingPath, et);
		}
		else
		{
			logger.log("fail", "Successfully not displaying exp warning message '"+expMessage+"'", true, applicationSessionObject, reportingPath, et);
		}
		
		//Click on 'OK' button
		actions.click(map.OkButtonCannotCompleteMedPassWindow, "OK button", applicationSessionObject, et);
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void selectIconAddNoteAndAddNoteClickOk(String note,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Clicking on 'Add Note' button
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.noteIconConfirmationScreen,"AccessId",applicationSessionObject,20);
		actions.click(map.noteIconConfirmationScreen, "Add Note Icon", applicationSessionObject, et);
		
		//Enter note
		actions.waitForElementVisibility(map.addNoteWindowConfirmationScreen,"Name",applicationSessionObject,20);
		actions.isElementPresent(map.addNoteWindowConfirmationScreen, "Add Note Window", true, applicationSessionObject, et);
		actions.setValue(map.textAreaAddNoteWindow, "Text area", note, applicationSessionObject, et);
		
		//Click on 'OK' button
		actions.click(map.okButtonAddNoteWindow, "OK Button", applicationSessionObject, et);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void validateAllMedsAreRecordedInMedPassScreen(ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Validate all meds are recorded 
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.medicationsList,"AccessId",applicationSessionObject,20);
		List<WebElement> medItems=applicationSessionObject.findElements(map.medicationsList);
		List<WebElement> medItemsTickMark=applicationSessionObject.findElements(map.tickMarkMedicationsList);
		if(medItems.size()==medItemsTickMark.size())
		{
			logger.log("pass", "Successfully all the meds are recorded in Med Pass screen", false, applicationSessionObject, reportingPath, et);
		}
		else
		{
			logger.log("fail", "Successfully all the meds are not recorded in Med Pass screen", true, applicationSessionObject, reportingPath, et);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void validateExceptionNameForAllMedsInConfirmationScreen(String exceptionName,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
				
		//Validate exception name 
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.exceptionButtonDropdownConfirmationScreen, "AccessId", applicationSessionObject, 20);
		List<WebElement> exceptionDropdowns=applicationSessionObject.findElements(map.exceptionButtonDropdownConfirmationScreen);
		int cnt=0;
		for(int i=0;i<exceptionDropdowns.size();i++)
		{
			if(exceptionDropdowns.get(i).getText().trim().contains(exceptionName.trim()))
			{
				cnt++;
			}
		}
		if(exceptionDropdowns.size()==cnt)
		{
			logger.log("pass", "Successfully all the meds in Med Pass screen are having exception name as '"+exceptionName+"'", false, applicationSessionObject, reportingPath, et);
		}
		else
		{
			logger.log("fail", "Successfully all the meds in Med Pass screen are not having exception name as '"+exceptionName+"'", true, applicationSessionObject, reportingPath, et);
		}
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void createMedOfTypePRNMedicationInManageOrdersScreen(String fromDropdownValue,String startTimeDropdownValue,String prescriber,
		String minDosage,String maxDosage,String max24HourDosageCheckbox,String max24HourDosageValue,String option,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		WindowsDriver<WebElement> desktopSession;
		
		//Storing values
		String randomString=actions.generateRandomAlphaString(5);
		String medicationNamelocal="Test Medication "+randomString;
		MedPassModuleTestCases.medicationName=medicationNamelocal;
		String instructions="Test Instructions "+randomString;
				
		
		//Click on 'Add' button
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.addDropdownButtonOrdersWindow,"Name",applicationSessionObject,20); 
		actions.click(map.addDropdownButtonOrdersWindow, "Add dropdown button", applicationSessionObject, et);
		
		//Select 'Medication' option
		DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
		desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.medicationOptionAddDropdownButton,"Name",desktopSession,20); 
		actions.click(map.medicationOptionAddDropdownButton, "Medication option", desktopSession, et);
		
		//Enter Medication name
		actions.waitForElementVisibility(map.medicationTextboxParentOrdersWindow,"AccessId",applicationSessionObject,20);
		WebElement medicationNameParentTextbox=applicationSessionObject.findElement(map.medicationTextboxParentOrdersWindow);
		WebElement medicationNameTextbox=medicationNameParentTextbox.findElement(map.medicationTextboxOrdersWindow);
		actions.setValue(medicationNameTextbox, "Medication textfield", medicationNamelocal, applicationSessionObject, et);
		
		//Select option from 'From' dropdown
		WebElement fromDropdown = applicationSessionObject.findElement(map.fromDropdownOrdersWindow);
		fromDropdown.click();
		WebElement editBoxFromDropdown = fromDropdown.findElement(map.fromDropdownTextboxOrdersWindow);
		int cnt=0;
		while (true) {
			editBoxFromDropdown.sendKeys(Keys.DOWN);
			if (editBoxFromDropdown.getAttribute("Value.Value").equalsIgnoreCase(fromDropdownValue.trim()))
			{
				cnt=cnt+1;
				break;
			}	
		}
		if(cnt==1)
		{
			logger.log("pass", "Successfully selected value '"+fromDropdownValue+"' in the 'From' dropdown", false, applicationSessionObject, reportingPath, et);
		}
		else
		{
			logger.log("fail", "Successfully not selected value '"+fromDropdownValue+"' in the 'From' dropdown", true, applicationSessionObject, reportingPath, et);
		}
		
		//Select time from 'Start' dropdown
		WebElement timeDropdown = applicationSessionObject.findElement(map.startTimeDropdownOrdersWindow);
		WebElement openButtonTimeDropdown = timeDropdown.findElement(map.openButtonStartTimeDropdownOrdersWindow);
		actions.click(openButtonTimeDropdown, "Start time dropdown", applicationSessionObject, et);
		DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
		desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
		WebElement timeListDropdown = desktopSession.findElement(map.listDropdownStartTimeOrdersWindow);
		WebElement optionTimeListDropdown = timeListDropdown.findElement(By.name(startTimeDropdownValue));
		actions.click(optionTimeListDropdown, "option "+startTimeDropdownValue, desktopSession, et);
		
		//Selecting Prescriber
		WebElement prescriberParentTextbox=applicationSessionObject.findElement(map.prescriberTextboxParentOrdersWindow);
		WebElement prescriberTextbox=prescriberParentTextbox.findElement(map.prescriberTextboxOrdersWindow);
		actions.setValue(prescriberTextbox, "Prescriber text field", prescriber, applicationSessionObject, et);
		DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
		desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.listDropdownPrescriberTextboxOrdersWindow,"AccessId",desktopSession,20);
		WebElement prescriberDropdown=desktopSession.findElement(map.listDropdownPrescriberTextboxOrdersWindow);
		WebElement optionPrescriberDropdown=prescriberDropdown.findElement(By.name(prescriber));
		actions.click(optionPrescriberDropdown, "option '"+prescriber+"'", desktopSession, et);
		
		//Entering Instructions
		actions.setValue(map.instructionsTextboxOrdersWindow, "Instructions text field", instructions, applicationSessionObject, et);
		
		//Click on 'PRN' button
		actions.click(map.prnButtonOrdersScreen, "PRN Button", applicationSessionObject, et);
				
		//Check 'Max 24 hour dosage' check box if required
		if(max24HourDosageCheckbox.trim().equalsIgnoreCase("Yes"))
		{
			actions.click(map.max24HourDosageCheckboxOrdersScreen, "Max 24 hour dosage checkbox", applicationSessionObject, et);
		}
		
		//Enter 'Max 24 hour dosage' textbox value
		if(!max24HourDosageValue.isEmpty())
		{
			actions.setValue(map.textBoxMax24HourDosageOrdersScreen, "Max 24 hour dosage textbox", max24HourDosageValue, applicationSessionObject, et);
		}
		
		//Clicking 'Add Part' and selecting 'Daily' routine med
		if(option.trim().equalsIgnoreCase("AddPartRoutineDaily"))
		{
			//Clicking 'Add Part' button
			actions.click(map.addPartButtonOrdersScreen, "Add Part button", applicationSessionObject, et);
			
			//Entering Instructions
			actions.setValue(map.instructionsTextboxOrdersWindow, "Instructions text field", instructions, applicationSessionObject, et);
			actions.waitForElementVisibility(map.instructionsTextboxOrdersWindow,"AccessId",desktopSession,20);
			
			//Click on 'Add' dropdown button and select 'Daily' option
			WebElement treatmentSchedulesSection = applicationSessionObject.findElement(map.treatmentSchedulesSection);
			WebElement addDropdown = treatmentSchedulesSection.findElement(map.addDropdownButtonTreatmentSchedulesSection);
			actions.click(addDropdown, "Add dropdown", applicationSessionObject, et);
			DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
			desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
			actions.waitForElementVisibility(map.addDropdownTreatmentSchedulesSection,"Name",desktopSession,20);
			WebElement dropdown = desktopSession.findElement(map.addDropdownTreatmentSchedulesSection);
			WebElement dropdownOption = dropdown.findElement(By.name("DAILY"));
			actions.click(dropdownOption, "option '"+"DAILY"+"'", desktopSession, et);
		}
		
		//Click on 'Save' button
		actions.click(map.saveButtonOrdersWindow, "Save button", applicationSessionObject, et);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void enterDetailsAmountPurposeInConfirmationScreen(String amount,String purpose,String option,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Enter amount
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.amountTextboxConfirmationWindow,"AccessId",applicationSessionObject,20);
		actions.setValue(map.amountTextboxConfirmationWindow, "Amount Textbox", amount, applicationSessionObject, et);
		
		//Select purpose
		WebElement purposeDropdown = applicationSessionObject.findElement(map.purposeDropdownConfirmationWindow);
		purposeDropdown.click();
		WebElement editBoxPurposeDropdown = purposeDropdown.findElement(map.purposeDropdownTextBoxConfirmationWindow);
		int cnt=0;
		while (true) {
			editBoxPurposeDropdown.sendKeys(Keys.DOWN);
			if (editBoxPurposeDropdown.getAttribute("Value.Value").equalsIgnoreCase(purpose.trim()))
			{
				cnt=cnt+1;
				break;
			}	
		}
		if(cnt==1)
		{
			logger.log("pass", "Successfully selected value '"+purpose+"' in the 'Purpose' dropdown", false, applicationSessionObject, reportingPath, et);
		}
		else
		{
			logger.log("fail", "Successfully not selected value '"+purpose+"' in the 'Purpose' dropdown", true, applicationSessionObject, reportingPath, et);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void validateMedicationDataInPassMedsScreen(String medicationName,String duration,String amount,String medIntakeTpye,String option,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
				
		//Selecting a med
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.medicationsList,"AccessId",applicationSessionObject,20);
		List<WebElement> medItems=applicationSessionObject.findElements(map.medicationsList);
		int cnt = 0;
		int j=0;
		for (int i=0;i<medItems.size();i++)
		{
			if (medItems.get(i).findElement(map.medicationTitleMedicationsList).getText().trim().equals(medicationName))
			{
				//Validate med data
				String extData=duration+"-hr Total: "+amount+" "+medIntakeTpye;
				String actData=actions.getTextValue(map.medTileDataMedicationsList, "Med tile", applicationSessionObject, et);
				if(actData.trim().contains(extData))
				{
					logger.log("pass", "Successfully displaying exp data '"+extData+"' in Med tile for medication '"+medicationName+"'", false, applicationSessionObject, reportingPath, et);
				}
				else
				{
					logger.log("fail", "Successfully not displaying exp data '"+extData+"' in Med tile for medication '"+medicationName+"'", true, applicationSessionObject, reportingPath, et);
				}
				cnt=1;
				j=i;
				break;
			}
		}
		
		//Report if medication is not present
		if(cnt==0)
		{
			logger.log("fail", "The medication '"+medicationName+"' is not present in Pass Meds screen", true, applicationSessionObject, reportingPath, et);
		}
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void clickShowPRNButtonInMedicationSelectionScreen(ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Click on Record All button
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.showPRNButtonMedicationSelectionScreen,"AccessId",applicationSessionObject,20);
		actions.click(map.showPRNButtonMedicationSelectionScreen, "Show PRN Button", applicationSessionObject, et);
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void validateDataInConfirmMedPassWindowWhenAmountAlreadyExceededInLast24HoursEnterTextClickOkButton(String medicationName,String duration,String amount,String medIntakeTpye,String reason,String date,String option,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
				
		//Validate elements in 'Confirm Med Pass' window
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.whenAmountAlreadyExceededInLast24HoursConfirmMedPassWindow,"Name",applicationSessionObject,20);
		actions.isElementPresent(map.whenAmountAlreadyExceededInLast24HoursConfirmMedPassWindow, "Confirm Med Pass window", true, applicationSessionObject, et);
		actions.isElementPresent(map.okButtonWhenAmountAlreadyExceededInLast24HoursConfirmMedPassWindow, "OK button", true, applicationSessionObject, et);
		actions.isElementPresent(map.cancelButtonWhenAmountAlreadyExceededInLast24HoursConfirmMedPassWindow, "Cancel button", true, applicationSessionObject, et);
		
		//Calculating exp messages
		String expMessage1="You are not to exceed "+amount+" "+medIntakeTpye+" in a "+duration+" hour period for";
		String expMessage2=medicationName;
		String expMessage3="You can give up to "+amount+" "+medIntakeTpye+" no sooner than";
		String expMessage4="Cancel, or click OK to give it anyway.";
		
		//-----------
		//pnlMain
		//System.out.println("Text Data is "+actions.getTextValue(MobileBy.AccessibilityId("pnlMain"), "", applicationSessionObject, et));
		//-----------
		//Getting actual messages
		String actMessage1=actions.getTextValue(map.warningMessageWhenAmountAlreadyExceededInLast24HoursConfirmMedPassWindow, "", applicationSessionObject, et).trim();
		String actMessage2=actions.getTextValue(map.medicationNameWhenAmountAlreadyExceededInLast24HoursConfirmMedPassWindow, "", applicationSessionObject, et).trim();
		String actMessage3=actions.getTextValue(map.suggestionMessageWhenAmountAlreadyExceededInLast24HoursConfirmMedPassWindow, "", applicationSessionObject, et).trim();
		String actMessage4=actions.getTextValue(map.performCancelOrOkMessageWhenAmountAlreadyExceededInLast24HoursConfirmMedPassWindow, "", applicationSessionObject, et).trim();
		
		//Validating message 1
		if(actMessage1.contains(expMessage1))
		{
			logger.log("pass", "Successfully displaying exp message '"+expMessage1+"' in 'Confirm Med Pass window'", false, applicationSessionObject, reportingPath, et);
		}
		else
		{
			logger.log("fail", "Successfully not displaying exp message '"+expMessage1+"' in 'Confirm Med Pass window'", true, applicationSessionObject, reportingPath, et);
		}
		
		//Validating message 2
		if(actMessage2.contains(expMessage2))
		{
			logger.log("pass", "Successfully displaying exp message '"+expMessage2+"' in 'Confirm Med Pass window'", false, applicationSessionObject, reportingPath, et);
		}
		else
		{
			logger.log("fail", "Successfully not displaying exp message '"+expMessage2+"' in 'Confirm Med Pass window'", true, applicationSessionObject, reportingPath, et);
		}
		
		//Validating message 3
		if(actMessage3.contains(expMessage3))
		{
			logger.log("pass", "Successfully displaying exp message '"+expMessage3+"' in 'Confirm Med Pass window'", false, applicationSessionObject, reportingPath, et);
		}
		else
		{
			logger.log("fail", "Successfully not displaying exp message '"+expMessage3+"' in 'Confirm Med Pass window'", true, applicationSessionObject, reportingPath, et);
		}
		
		//Validating message 4
		if(actMessage4.contains(expMessage4))
		{
			logger.log("pass", "Successfully displaying exp message '"+expMessage4+"' in 'Confirm Med Pass window'", false, applicationSessionObject, reportingPath, et);
		}
		else
		{
			logger.log("fail", "Successfully not displaying exp message '"+expMessage4+"' in 'Confirm Med Pass window'", true, applicationSessionObject, reportingPath, et);
		}
		
		//Enter reason if required
		if(!reason.isEmpty())
		{
			actions.setValue(map.textBoxWhenAmountAlreadyExceededInLast24HoursConfirmMedPassWindow, "Text box", reason, applicationSessionObject, et);
		}
		
		//Click on 'Ok' button
		actions.click(map.okButtonWhenAmountAlreadyExceededInLast24HoursConfirmMedPassWindow, "OK button", applicationSessionObject, et);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void discontinueMedInOrdersScreen(int numOfStartDays,int numOfEndDays,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Calculate Start date to be set
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		String[] startDate = cliCom.addingOrSubtractingDays("","","systemDate","M/d/yyyy",numOfStartDays).split("/");
		
		//Calculate End date to be set
		String[] endDate = cliCom.addingOrSubtractingDays("","","systemDate","M/d/yyyy",numOfEndDays).split("/");
		
		//Enter Start Date
		WebElement startDateTextField = applicationSessionObject.findElement(map.startDateTimeSectionOrdersScreen).findElement(map.startDateTextboxOrdersScreen);
		actions.click(startDateTextField, "Start Date Text Field", applicationSessionObject, et);
		actions.setValue(startDateTextField, "Year field", endDate[2], applicationSessionObject, et);
		startDateTextField.sendKeys(Keys.ARROW_RIGHT);
		actions.setValue(startDateTextField, "Month field", endDate[0], applicationSessionObject, et);
		startDateTextField.sendKeys(Keys.ARROW_RIGHT);
		actions.setValue(startDateTextField, "Date field", endDate[1], applicationSessionObject, et);
		
		//Enter End Date
		WebElement endDateTextField = applicationSessionObject.findElement(map.endDateTimeSectionOrdersScreen).findElement(map.endDateTextboxOrdersScreen);
		actions.click(endDateTextField, "End Date Text Field", applicationSessionObject, et);
		actions.setValue(endDateTextField, "Year field", endDate[2], applicationSessionObject, et);
		endDateTextField.sendKeys(Keys.ARROW_RIGHT);
		actions.setValue(endDateTextField, "Month field", endDate[0], applicationSessionObject, et);
		endDateTextField.sendKeys(Keys.ARROW_RIGHT);
		actions.setValue(endDateTextField, "Date field", endDate[1], applicationSessionObject, et);
		
		//Click on 'Save' button
		actions.click(map.saveButtonOrdersWindow, "Save button", applicationSessionObject, et);
		
		//Click 'No' button if 'Confirm New End Date' window is present
		actions.waitForElementVisibility(map.confirmEndDateWindowOrdersTab,"AccessId",applicationSessionObject,5);
		if(actions.isElementPresent(map.confirmEndDateWindowOrdersTab, "Confirm New End Date window", false, applicationSessionObject, et))
		{
			actions.isElementPresent(map.confirmEndDateWindowOrdersTab, "Confirm New End Date window", true, applicationSessionObject, et);
			actions.click(map.noButtonConfirmEndDateWindowOrdersTab, "No button", applicationSessionObject, et);
			actions.click(map.saveButtonOrdersWindow, "Save button", applicationSessionObject, et);
			
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void selectMedicationFromOrdersTabManageOrdersScreen(String medicationName,String validateMedicationPresentOrAbsent,String selectMedication,String option,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Finding the medication
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.medicationsListTableManageOrdersScreenOrdersTab,"AccessId",applicationSessionObject,20);
		WebElement medicationsListTable=applicationSessionObject.findElement(map.medicationsListTableManageOrdersScreenOrdersTab);
		List<WebElement> medItems=medicationsListTable.findElements(By.xpath("//ListItem[contains(@AutomationId,'ListViewItem')]"));
		int cnt = 0;
		int j=0;
		for (int i=0;i<medItems.size();i++)
		{
			if (medItems.get(i).getText().trim().equals(medicationName))
			{
				cnt=1;
				j=i;
				break;
			}
		}
		
		//Validate presence/absence of element
		if(!validateMedicationPresentOrAbsent.isEmpty())
		{
			//Validate presence of element
			if(validateMedicationPresentOrAbsent.trim().equalsIgnoreCase("Present"))
			{
				if(cnt==0)
				{
					logger.log("fail", "The medication '"+medicationName+"' is not present in Orders tab", true, applicationSessionObject, reportingPath, et);
				}
				else
				{
					logger.log("pass", "The medication '"+medicationName+"' is present in Orders tab", false, applicationSessionObject, reportingPath, et);
				}
			}
			
			else if (validateMedicationPresentOrAbsent.trim().equalsIgnoreCase("Absent"))
			{
				if(cnt==0)
				{
					logger.log("pass", "The medication '"+medicationName+"' is not present in Orders tab", false, applicationSessionObject, reportingPath, et);
				}
				else
				{
					logger.log("fail", "The medication '"+medicationName+"' is present in Orders tab", true, applicationSessionObject, reportingPath, et);
				}
			}
			
			
		}
		
		//Selecting the medication
		if(!selectMedication.isEmpty())
		{
			if(cnt==0)
			{
				logger.log("fail", "The medication '"+medicationName+"' is not present in Orders tab", true, applicationSessionObject, reportingPath, et);
			}
			else
			{
				actions.click(medItems.get(j),"medication '"+medicationName+"'", applicationSessionObject, et);
			}
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void clickSaveSelectionsButtonMedicationListScreen(ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Click on 'Save Selections' button
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.saveSelectionsButtonMedPassScreen,"AccessId",applicationSessionObject,20);
		actions.click(map.saveSelectionsButtonMedPassScreen, "Save Selections button", applicationSessionObject, et);
				
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void validatePushPinIconForResidentAndClick(String reisdentName,String validatePushPinIconPresentOrAbsent,String selectPushPinIcon,String option,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Finding the resident
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.medPassResidentsList,"AccessId",applicationSessionObject,20);
		List<WebElement> residentList=applicationSessionObject.findElements(map.medPassResidentsList);
		int cntRes = 0;
		int j=0;
		for (int i=0;i<residentList.size();i++)
		{   
			if (residentList.get(i).findElement(map.medPassResidentFullName).getText().trim().equals(reisdentName))
			{
				cntRes=1;
				j=i;
				break;
			}
		}
		
		//This code is executed when resident is present
		if(cntRes!=0)
		{
		//Validate presence/absence of Push Pin icon
		if(!validatePushPinIconPresentOrAbsent.isEmpty())
		{
			WebElement pushPinIcon=residentList.get(j).findElement(map.pushPinIconResidentTileMedPassScreen);
			
			//Validate presence of element
			if(validatePushPinIconPresentOrAbsent.trim().equalsIgnoreCase("Present"))
			{
				actions.isElementPresent(pushPinIcon, "Push Pin icon for resident '"+reisdentName+"'", true, applicationSessionObject, et);
								
				//Select 'Push Pin' icon
				if(!selectPushPinIcon.isEmpty())
				{
					if(selectPushPinIcon.trim().equalsIgnoreCase("Select"))
					{
						actions.click(pushPinIcon, "Push Pin icon for resident '"+reisdentName+"'", applicationSessionObject, et);
					}
				}
			}
			//Validate absence of element
			else if (validatePushPinIconPresentOrAbsent.trim().equalsIgnoreCase("Absent"))
			{
				actions.isElementAbsent(pushPinIcon, "Push Pin icon for resident '"+reisdentName+"'", true, applicationSessionObject, et);
			}
			
			
		}
		
		
		}
		else
		{
			logger.log("fail", "The resident '"+reisdentName+"' is not present in Meds Pass screen", true, applicationSessionObject, reportingPath, et);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void enterBPVitalDetaiInConfirmationScreen(String bPUpperValue,String bPLowerValue,String option,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Enter BP upper value and lower value 
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.vitalsSectionConfirmationScreen,"AccessId",applicationSessionObject,20);
		WebElement bPUpperValueTextboxVitalsSection=applicationSessionObject.findElement(map.vitalsSectionConfirmationScreen).findElement(map.bPHigherValueTextboxVitalsSectionConfirmationScreen);
		WebElement bPLowerValueTextboxVitalsSection=applicationSessionObject.findElement(map.vitalsSectionConfirmationScreen).findElement(map.bPLowerValueTextboxVitalsSectionConfirmationScreen);
		actions.setValue(bPUpperValueTextboxVitalsSection, "BP upper value textbox", bPUpperValue, applicationSessionObject, et);
		actions.setValue(bPLowerValueTextboxVitalsSection, "BP lower value textbox", bPLowerValue, applicationSessionObject, et);
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void createMedOfTypeMedicationWithVitalsInManageOrdersScreen(String fromDropdownValue,String startTimeDropdownValue,String prescriber,
		String routineType,String recordCheckbox,String vital,String option,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		WindowsDriver<WebElement> desktopSession;
		
		//Storing values
		String randomString=actions.generateRandomAlphaString(5);
		String medicationNamelocal="Test Medication "+randomString;
		MedPassModuleTestCases.medicationName=medicationNamelocal;
		String instructions="Test Instructions "+randomString;
				
		
		//Click on 'Add' button
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.addDropdownButtonOrdersWindow,"Name",applicationSessionObject,20); 
		actions.click(map.addDropdownButtonOrdersWindow, "Add dropdown button", applicationSessionObject, et);
		
		//Select 'Medication' option
		DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
		desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.medicationOptionAddDropdownButton,"Name",desktopSession,20); 
		actions.click(map.medicationOptionAddDropdownButton, "Medication option", desktopSession, et);
		
		//Enter Medication name
		actions.waitForElementVisibility(map.medicationTextboxParentOrdersWindow,"AccessId",applicationSessionObject,20);
		WebElement medicationNameParentTextbox=applicationSessionObject.findElement(map.medicationTextboxParentOrdersWindow);
		WebElement medicationNameTextbox=medicationNameParentTextbox.findElement(map.medicationTextboxOrdersWindow);
		actions.setValue(medicationNameTextbox, "Medication textfield", medicationNamelocal, applicationSessionObject, et);
		
		//Select option from 'From' dropdown
		try
		{
		WebElement fromDropdown = applicationSessionObject.findElement(map.fromDropdownOrdersWindow);
		fromDropdown.click();
		WebElement editBoxFromDropdown = fromDropdown.findElement(map.fromDropdownTextboxOrdersWindow);
		int cnt=0;
		while (true) {
			editBoxFromDropdown.sendKeys(Keys.DOWN);
			if (editBoxFromDropdown.getAttribute("Value.Value").equalsIgnoreCase(fromDropdownValue.trim()))
			{
				cnt=cnt+1;
				break;
			}	
		}
		if(cnt==1)
		{
			logger.log("pass", "Successfully selected value '"+fromDropdownValue+"' in the 'From' dropdown", false, applicationSessionObject, reportingPath, et);
		}
		else
		{
			logger.log("fail", "Successfully not selected value '"+fromDropdownValue+"' in the 'From' dropdown", true, applicationSessionObject, reportingPath, et);
		}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logger.log("fail", "Error in selecting value '"+fromDropdownValue+"' from the 'Form' dropdown", true, applicationSessionObject, reportingPath, et);
		}
		
		//Select time from 'Start' dropdown
		WebElement timeDropdown = applicationSessionObject.findElement(map.startTimeDropdownOrdersWindow);
		WebElement openButtonTimeDropdown = timeDropdown.findElement(map.openButtonStartTimeDropdownOrdersWindow);
		actions.click(openButtonTimeDropdown, "Start time dropdown", applicationSessionObject, et);
		DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
		desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
		WebElement timeListDropdown = desktopSession.findElement(map.listDropdownStartTimeOrdersWindow);
		WebElement optionTimeListDropdown = timeListDropdown.findElement(By.name(startTimeDropdownValue));
		actions.click(optionTimeListDropdown, "option "+startTimeDropdownValue, desktopSession, et);
		
		//Selecting Prescriber
		WebElement prescriberParentTextbox=applicationSessionObject.findElement(map.prescriberTextboxParentOrdersWindow);
		WebElement prescriberTextbox=prescriberParentTextbox.findElement(map.prescriberTextboxOrdersWindow);
		actions.setValue(prescriberTextbox, "Prescriber text field", prescriber, applicationSessionObject, et);
		DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
		desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.listDropdownPrescriberTextboxOrdersWindow,"AccessId",desktopSession,20);
		WebElement prescriberDropdown=desktopSession.findElement(map.listDropdownPrescriberTextboxOrdersWindow);
		WebElement optionPrescriberDropdown=prescriberDropdown.findElement(By.name(prescriber));
		actions.click(optionPrescriberDropdown, "option '"+prescriber+"'", desktopSession, et);
		
		//Entering Instructions
		actions.setValue(map.instructionsTextboxOrdersWindow, "Instructions text field", instructions, applicationSessionObject, et);
		
		//Click on 'Add' dropdown button and select 'Daily' option
		WebElement treatmentSchedulesSection = applicationSessionObject.findElement(map.treatmentSchedulesSection);
		WebElement addDropdown = treatmentSchedulesSection.findElement(map.addDropdownButtonTreatmentSchedulesSection);
		actions.click(addDropdown, "Add dropdown", applicationSessionObject, et);
		DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
		desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.addDropdownTreatmentSchedulesSection,"Name",desktopSession,20);
		WebElement dropdown = desktopSession.findElement(map.addDropdownTreatmentSchedulesSection);
		WebElement dropdownOption = dropdown.findElement(By.name(routineType));
		actions.click(dropdownOption, "option '"+routineType+"'", desktopSession, et);
		
		//Check 'Record' check box if required
		if(recordCheckbox.trim().equalsIgnoreCase("Yes"))
		{
			//Check 'Record' check box
			actions.click(map.recordCheckboxOrdersTab, "Record Checkbox", applicationSessionObject, et);
						
			try
			{
			//Select vital
			DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
			desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
			actions.waitForElementVisibility(map.vitalsWindowRecordCheckboxOrdersTab,"AccessId",desktopSession,20);
			WebElement vitalsWindowRecordCheckboxOrdersTab=desktopSession.findElement(map.vitalsWindowRecordCheckboxOrdersTab);
			WebElement vitalsFirstDropdownRecordCheckboxOrdersTab=vitalsWindowRecordCheckboxOrdersTab.findElement(map.vitalsFirstDropdownRecordCheckboxOrdersTab);
			vitalsFirstDropdownRecordCheckboxOrdersTab.click();
			WebElement editBoxVitalsDropdown = vitalsFirstDropdownRecordCheckboxOrdersTab.findElement(map.vitalsFirstDropdownTextBoxRecordCheckboxOrdersTab);
			int cnt=0;
			while (true) {
				editBoxVitalsDropdown.sendKeys(Keys.DOWN);
				if (editBoxVitalsDropdown.getAttribute("Value.Value").equalsIgnoreCase(vital.trim()))
				{
					cnt=cnt+1;
					break;
				}	
			}
			if(cnt==1)
			{
				logger.log("pass", "Successfully selected value '"+vital+"' from the 'Vital' dropdown", false, applicationSessionObject, reportingPath, et);
			}
			else
			{
				logger.log("fail", "Successfully not selected value '"+vital+"' from the 'Vital' dropdown", true, applicationSessionObject, reportingPath, et);
			}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				logger.log("fail", "Error in selecting value '"+vital+"' from the 'Vital' dropdown", true, applicationSessionObject, reportingPath, et);
			}
			
						
			//Close the opened pane
			WebElement xMarkPanelTitle=desktopSession.findElement(map.panelTitleVitalsWindowRecordCheckboxOrdersTab).findElement(map.xMarkPanelTitleVitalsWindowRecordCheckboxOrdersTab);
			actions.click(xMarkPanelTitle, "X mark of vital selection window", desktopSession, et);
			
		}
		
		//Click on 'Save' button
		actions.click(map.saveButtonOrdersWindow, "Save button", applicationSessionObject, et);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void validateMedicationDataPassMedsScreen(String medicationName,String option,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Finding the med
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.medicationsList,"AccessId",applicationSessionObject,20);
		List<WebElement> medItems=applicationSessionObject.findElements(map.medicationsList);
		int cnt = 0;
		int j=0;
		for (int i=0;i<medItems.size();i++)
		{
			if (medItems.get(i).findElement(map.medicationTitleMedicationsList).getText().trim().equals(medicationName))
			{
				cnt=1;
				j=i;
				break;
			}
		}
			
		//Validating the data in the med tile
		if(cnt!=0)
		{
			//Validating Strength
			if(option.trim().contains("Strength"))
			{
				String actData=actions.getTextValue(medItems.get(j).findElement(map.strengthAndFormStoringElementMedicationsListScreen), "", applicationSessionObject, et);
				String expData=MedPassModuleTestCases.hashmapMedicationData.get("Strength");	
				if(actData.trim().contains(expData))
				{
					logger.log("pass", "Successfully displaying Strength value as '"+expData+"' for medication '"+medicationName+"'", false, applicationSessionObject, reportingPath, et);
				}
				else
				{
					logger.log("fail", "Successfully not displaying Strength value as '"+expData+"' for medication '"+medicationName+"'", true, applicationSessionObject, reportingPath, et);
				}
			}
			
			//Validating Route
			if(option.trim().contains("Route"))
			{
				String actData=actions.getTextValue(medItems.get(j).findElement(map.routeStoringElementMedicationsListScreen), "", applicationSessionObject, et);
				String expData=MedPassModuleTestCases.hashmapMedicationData.get("Route");	
				if(actData.trim().contains(expData))
				{
					logger.log("pass", "Successfully displaying Route value as '"+expData+"' for medication '"+medicationName+"'", false, applicationSessionObject, reportingPath, et);
				}
				else
				{
					logger.log("fail", "Successfully not displaying Route value as '"+expData+"' for medication '"+medicationName+"'", true, applicationSessionObject, reportingPath, et);
				}
			}
			
			
			//Validating Instructions
			if(option.trim().contains("Instructions"))
			{
				String actData=actions.getTextValue(medItems.get(j).findElement(map.instructionsAndDiagnosisStoringElementMedicationsListScreen), "", applicationSessionObject, et);
				String expData=MedPassModuleTestCases.hashmapMedicationData.get("Instructions");	
				if(actData.trim().contains(expData))
				{
					logger.log("pass", "Successfully displaying Instructions value as '"+expData+"' for medication '"+medicationName+"'", false, applicationSessionObject, reportingPath, et);
				}
				else
				{
					logger.log("fail", "Successfully not displaying Instructions value as '"+expData+"' for medication '"+medicationName+"'", true, applicationSessionObject, reportingPath, et);
				}
			}
			
			
			//Validating Diagnosis
			if(option.trim().contains("Diagnosis"))
			{
				String actData=actions.getTextValue(medItems.get(j).findElement(map.instructionsAndDiagnosisStoringElementMedicationsListScreen), "", applicationSessionObject, et);
				String expData=MedPassModuleTestCases.hashmapMedicationData.get("Diagnosis");	
				if(actData.trim().contains(expData))
				{
					logger.log("pass", "Successfully displaying Diagnosis value as '"+expData+"' for medication '"+medicationName+"'", false, applicationSessionObject, reportingPath, et);
				}
				else
				{
					logger.log("fail", "Successfully not displaying Diagnosis value as '"+expData+"' for medication '"+medicationName+"'", true, applicationSessionObject, reportingPath, et);
				}
			}
			
			//Validating presence of Info button
			if(option.trim().contains("Info"))
			{
				actions.isElementPresent(medItems.get(j).findElement(map.informationButtonMedicationsListScreen), "Information Button", true, applicationSessionObject, et);
			}
			
			//Validating presence of History button
			if(option.trim().contains("History"))
			{
				actions.isElementPresent(map.historyButtonMedicationsList, "History Button",  true, applicationSessionObject, et);
			}
			
			//Validating presence of Reorder button
			if(option.trim().contains("Reorder"))
			{
				actions.isElementPresent(medItems.get(j).findElement(map.reorderButtonMedicationsListScreen), "Reorder Button",  true, applicationSessionObject, et);
			}
			
		}
		else
		{
			logger.log("fail", "The medication '"+medicationName+"' is not present in the medication list", true, applicationSessionObject, reportingPath, et);
		}
	} catch (Exception e) {
		e.printStackTrace();
		
	}
}

public void clickReorderValidateUIEnterQuantityInReorderMedicationWindow(String medicationName,String validateUI,String quantity,String option,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Finding the med
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.medicationsList,"AccessId",applicationSessionObject,20);
		List<WebElement> medItems=applicationSessionObject.findElements(map.medicationsList);
		int cnt = 0;
		int j=0;
		for (int i=0;i<medItems.size();i++)
		{
			if (medItems.get(i).findElement(map.medicationTitleMedicationsList).getText().trim().equals(medicationName))
			{
				cnt=1;
				j=i;
				break;
			}
		}
			
		//Validating the data in the med tile
		if(cnt!=0)
		{
			//Click Reorder button
			WebElement reorderButton=medItems.get(j).findElement(map.reorderButtonMedicationsListScreen);
			actions.click(reorderButton, "Reorder Button", applicationSessionObject, et);
			
			//Validate UI
			if(validateUI.trim().equalsIgnoreCase("Yes"))
			{
				actions.waitForElementVisibility(map.reorderWindowMedicationsListScreen,"AccessId",applicationSessionObject,20);
				actions.isElementPresent(map.reorderWindowMedicationsListScreen, "Reorder Medication window", true, applicationSessionObject, et);
				actions.isElementPresent(map.nameLabelReorderWindowMedicationsListScreen, "Name field", true, applicationSessionObject, et);
				actions.isElementPresent(map.lastReorderLabelReorderWindowMedicationsListScreen, "Last Reorder field", true, applicationSessionObject, et);
				actions.isElementPresent(map.lastFilledLabelReorderWindowMedicationsListScreen, "Last Filled field", true, applicationSessionObject, et);
				actions.isElementPresent(map.quantityTextboxReorderWindowMedicationsListScreen, "Quantity textbox", true, applicationSessionObject, et);
				actions.isElementPresent(map.messageTextboxReorderWindowMedicationsListScreen, "Message textbox", true, applicationSessionObject, et);
				actions.isElementPresent(map.historyButtonReorderWindowMedicationsListScreen, "History Button", true, applicationSessionObject, et);
				actions.isElementPresent(map.okButtonReorderWindowMedicationsListScreen, "OK Button", true, applicationSessionObject, et);
				actions.isElementPresent(map.cancelButtonReorderWindowMedicationsListScreen, "Cancel Button", true, applicationSessionObject, et);
			}
			
			//Enter quantity
			if(!quantity.isEmpty())
			{
				actions.waitForElementVisibility(map.quantityTextboxReorderWindowMedicationsListScreen,"AccessId",applicationSessionObject,20);
				actions.clear(map.quantityTextboxReorderWindowMedicationsListScreen, "Quantity textbox", applicationSessionObject, et);
				actions.setValue(map.quantityTextboxReorderWindowMedicationsListScreen, "Quantity textbox", quantity, applicationSessionObject, et);
				
			}
			
			//Click OK button
			actions.click(map.okButtonReorderWindowMedicationsListScreen, "OK button", applicationSessionObject, et);
		}
		else
		{
			logger.log("fail", "The medication '"+medicationName+"' is not present in the medication list", true, applicationSessionObject, reportingPath, et);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void validateElementsInDashboardScreen(String option,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Validate the elements
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.allCartsDropdownDashboardScreen,"Name",applicationSessionObject,20);
		/*actions.waitForElementVisibility(map.tableDashboardScreen,"Name",applicationSessionObject,20);
		Thread.sleep(15000);*/
		actions.isElementPresent(map.allCartsDropdownDashboardScreen, "All Carts dropdown", true, applicationSessionObject, et);
		actions.isElementPresent(map.refreshButtonDashboardScreen, "Refresh button", true, applicationSessionObject, et);
		actions.isElementPresent(map.printButtonDashboardScreen, "Print button", true, applicationSessionObject, et);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void clickOnInfoButtonValidateUICaptureDataAndClickPrintButton(String medicationName,String validateUI,String captureData,String clickPrint,String option,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		WindowsDriver<WebElement> desktopSessionObject;
		
		//Finding the med
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.medicationsList,"AccessId",applicationSessionObject,20);
		List<WebElement> medItems=applicationSessionObject.findElements(map.medicationsList);
		int cnt = 0;
		int j=0;
		for (int i=0;i<medItems.size();i++)
		{
			if (medItems.get(i).findElement(map.medicationTitleMedicationsList).getText().trim().equals(medicationName))
			{
				cnt=1;
				j=i;
				break;
			}
		}
			
		//Click Info button
		if(cnt!=0)
		{
			//Click Info button
			WebElement infoButton=medItems.get(j).findElement(map.informationButtonMedicationsListScreen);
			actions.click(infoButton, "Info Button", applicationSessionObject, et);
			
			//Create desktop session
			DriverFactoryDesktopSession.getInstance().setDriver(MedPassModuleTestCases.platform,MedPassModuleTestCases.seleniumGridNodeUrl);
			desktopSessionObject = DriverFactoryDesktopSession.getInstance().getDriver();
			actions.waitForElementVisibility(map.drugInformationWindowMedicationsListScreen,"AccessId",desktopSessionObject,20);
			WebElement drugInformationWindow=desktopSessionObject.findElement(map.drugInformationWindowMedicationsListScreen);
			
			//Validate UI
			if(validateUI.trim().equalsIgnoreCase("Yes"))
			{
				
				actions.isElementPresent(drugInformationWindow, "Drug Information window", true, desktopSessionObject, et);
				actions.isElementPresent(drugInformationWindow.findElement(map.adverseEffectsSectionDrugInformationWindow), "Adverse Effects section", true, applicationSessionObject, et);
				actions.isElementPresent(drugInformationWindow.findElement(map.labelWarningsSectionDrugInformationWindow), "Label Warnings section", true, applicationSessionObject, et);
				actions.isElementPresent(drugInformationWindow.findElement(map.printButtonDrugInformationWindow), "Print button", true, applicationSessionObject, et);
				
			}
			
			//CaptureData
			if(captureData.trim().equalsIgnoreCase("Yes"))
			{
				String actDataAdverseEffects=actions.getTextValue(drugInformationWindow.findElement(map.adverseEffectsTextAreaDrugInformationWindow), "Adverse Effects TextArea", applicationSessionObject, et);
				String actDataLabelWarnings=actions.getTextValue(drugInformationWindow.findElement(map.labelWarningsTextAreaDrugInformationWindow), "Label Warnings TextArea", applicationSessionObject, et);
				
				//Storing in Hashmap
				MedPassModuleTestCases.hashmapMedicationData.put("MedicationName", MedPassModuleTestCases.medicationName.trim());
				MedPassModuleTestCases.hashmapMedicationData.put("AdverseEffects", actDataAdverseEffects.trim());
				MedPassModuleTestCases.hashmapMedicationData.put("LabelWarnings", actDataLabelWarnings.trim());
			}
			
			//Click 'Print' button
			if(clickPrint.trim().equalsIgnoreCase("Yes"))
			{
			
				actions.click(drugInformationWindow.findElement(map.printButtonDrugInformationWindow), "Print Button", applicationSessionObject, et);
			}
		}
		else
		{
			logger.log("fail", "The medication '"+medicationName+"' is not present in the medication list", true, applicationSessionObject, reportingPath, et);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void validateDrugInformationPrintingWindowUIAndClickPrint(String validateUI,String clickPrint,String option,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		WindowsDriver<WebElement> desktopSessionObject;
		
		//Create desktop session
		DriverFactoryDesktopSession.getInstance().setDriver(MedPassModuleTestCases.platform,MedPassModuleTestCases.seleniumGridNodeUrl);
		desktopSessionObject = DriverFactoryDesktopSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.drugInformationPrintingWindowMedicationsListScreen,"AccessId",desktopSessionObject,20);
		WebElement drugInformationWindow=desktopSessionObject.findElement(map.drugInformationPrintingWindowMedicationsListScreen);
		
		//Validate UI
		if(validateUI.trim().equalsIgnoreCase("Yes"))
		{
			actions.isElementPresent(drugInformationWindow, "Drug Information Printing window", true, desktopSessionObject, et);
			actions.isElementSelected(drugInformationWindow.findElement(map.adverseEffectsCheckboxDrugInformationPrintingWindow), "Adverse Effects Checkbox", true, desktopSessionObject, et);
			actions.isElementSelected(drugInformationWindow.findElement(map.labelWarningsCheckboxDrugInformationPrintingWindow), "Label Warnings Checkbox", true, desktopSessionObject, et);
			actions.isElementNotSelected(drugInformationWindow.findElement(map.drugImageCheckboxDrugInformationPrintingWindow), "Drug Image Checkbox", true, desktopSessionObject, et);
			actions.isElementPresent(drugInformationWindow.findElement(map.printButtonDrugInformationPrintingWindow), "Print button", true, desktopSessionObject, et);
			actions.isElementPresent(drugInformationWindow.findElement(map.cancelButtonDrugInformationPrintingWindow), "Cancel button", true, desktopSessionObject, et);
			
		}
		
		//Click 'Print' button
		if(clickPrint.trim().equalsIgnoreCase("Yes"))
		{
		
			actions.click(drugInformationWindow.findElement(map.printButtonDrugInformationPrintingWindow), "Print button", desktopSessionObject, et);
		}

		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void downloadPDFFileDrugInformationPrintingWindow(String printOption,String downloadFilePath,String fileName,String closeWindow,String option,ExtentTest et) {
	
	try {
		//Application and desktop objects
		//WindowsDriver<WebElement> applicationSessionObject;
		WindowsDriver<WebElement> desktopSession;
	
		//Select Print option
		DriverFactoryDesktopSession.getInstance().setDriver(MedPassModuleTestCases.platform,MedPassModuleTestCases.seleniumGridNodeUrl);
		desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.printWindow,"Name",desktopSession,20);
		WebElement printWindow=desktopSession.findElement(map.printWindow);
		actions.isElementPresent(printWindow, "Print window", true, desktopSession, et);
		WebElement nameDropdown=printWindow.findElement(map.nameDropdownPrintWindow);
		actions.click(nameDropdown, "Name dropdown", desktopSession, et);
		DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
		desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.nameDropdownComboboxPrintWindow,"ClassName",desktopSession,20);
		if(printOption.trim().equalsIgnoreCase("Microsoft Print to PDF"))
		{
		WebElement printToPDFOption=desktopSession.findElement(map.nameDropdownComboboxPrintWindow).findElement(map.nameDropdownOptionComboboxPrintWindow);
		actions.click(printToPDFOption, "Microsoft Print to PDF option", desktopSession, et);
		}
		
		//Click on 'OK' button
		actions.click(printWindow.findElement(map.okButtonPrintWindow), "OK button", desktopSession, et);

		//Select file download path
		DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
		desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.savePrintOutputAsWindow,"Name",desktopSession,20);
		WebElement savePrintOutputAsWindow=desktopSession.findElement(map.savePrintOutputAsWindow);
		actions.isElementPresent(savePrintOutputAsWindow, "Save Print Output As window", true, desktopSession, et);
		//As a work around entering dir path in search filter
		actions.clear(savePrintOutputAsWindow.findElement(map.searchTextboxSavePrintOutputAsWindow), "Search Filter", desktopSession, et);
		actions.setValue(savePrintOutputAsWindow.findElement(map.searchTextboxSavePrintOutputAsWindow), "Search Filter", downloadFilePath, desktopSession, et);
		try
		{
		//savePrintOutputAsWindow.findElement(map.searchTextboxSavePrintOutputAsWindow).sendKeys(Keys.ENTER);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(8000);
		
		//Entering dir path in address bar
		actions.click(savePrintOutputAsWindow.findElement(map.addressBarSavePrintOutputAsWindow), "Address bar", desktopSession, et);
		actions.waitForElementVisibility(map.addressBarTextboxSavePrintOutputAsWindow,"Xpath",desktopSession,10);
		actions.clear(savePrintOutputAsWindow.findElement(map.addressBarTextboxSavePrintOutputAsWindow), "Search Filter", desktopSession, et);
		actions.setValue(savePrintOutputAsWindow.findElement(map.addressBarTextboxSavePrintOutputAsWindow), "Address bar", downloadFilePath, desktopSession, et);
		try
		{
		savePrintOutputAsWindow.findElement(map.addressBarTextboxSavePrintOutputAsWindow).sendKeys(Keys.ENTER);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(8000);
		//Select File name
		actions.setValue(savePrintOutputAsWindow.findElement(map.fileNameTextboxSavePrintOutputAsWindow), "File Name textbox", fileName, desktopSession, et);
		
		//Click 'Save' button
		actions.click(savePrintOutputAsWindow.findElement(map.saveButtonSavePrintOutputAsWindow), "Save button", desktopSession, et);
		
		//Close window
		if(closeWindow.trim().equalsIgnoreCase("Yes"))
		{
		DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
		desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
		actions.closingWindowUsingActionsClass(map.drugInformationWindowMedicationsListScreen,"Drug Information window",desktopSession,et);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void validateDownloadedPDFFileDrugInformation(String fileDownloadPath,String fileName,String validateDrugName,String validateAdverseEffects,String validateLabelWarnings,String option,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Validate presence of file
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		if(actions.validatePresenceOrAbsenceOfFileInDir(fileDownloadPath, fileName, "Present", applicationSessionObject, true, et))
		{
			//Get PDF file data
			String actPdfData=actions.getPDFFileData(fileDownloadPath, fileName, applicationSessionObject, et);
			
			//Validate Drug Name
			if(validateDrugName.trim().equalsIgnoreCase("Yes"))
			{
				String expDrugName=MedPassModuleTestCases.hashmapMedicationData.get("MedicationName");
				if(actPdfData.trim().contains(expDrugName))
				{
					logger.log("pass", "Exp Drug Name '"+expDrugName+"' is displayed in PDF file", false, applicationSessionObject, reportingPath, et);
				}
				else
				{
					logger.log("fail", "Exp Drug Name '"+expDrugName+"' is not displayed in PDF file", true, applicationSessionObject, reportingPath, et);
				}
			}
			
			//Validate Adverse Effects
			if(validateAdverseEffects.trim().equalsIgnoreCase("Yes"))
			{
				String expAdverseEffects=MedPassModuleTestCases.hashmapMedicationData.get("AdverseEffects");
				if(actPdfData.trim().contains(expAdverseEffects))
				{
					logger.log("pass", "Exp Adverse Effects data '"+expAdverseEffects+"' is displayed in PDF file", false, applicationSessionObject, reportingPath, et);
				}
				else
				{
					logger.log("fail", "Exp Adverse Effects data '"+expAdverseEffects+"' is not displayed in PDF file", true, applicationSessionObject, reportingPath, et);
				}
			}
			
			//Validate Label Warnings
			if(validateLabelWarnings.trim().equalsIgnoreCase("Yes"))
			{
				String expLabelWarnings=MedPassModuleTestCases.hashmapMedicationData.get("LabelWarnings");
				if(actPdfData.trim().contains(expLabelWarnings))
				{
					logger.log("pass", "Exp Label Warnings data '"+expLabelWarnings+"' is displayed in PDF file", false, applicationSessionObject, reportingPath, et);
				}
				else
				{
					logger.log("fail", "Exp Label Warnings data '"+expLabelWarnings+"' is not displayed in PDF file ", true, applicationSessionObject, reportingPath, et);
				}
			}
		}
		
		
		
		
				
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void createAppointmentInCalendarScreen(String residentName,String appointmentTitle,String appointmentLocation,String appointmentDetails,
		String appointmentInstructions,String appointmentDate,String appointmentTime,String appointmentDuration,String option,ExtentTest et) {
		try {
			// Application and desktop objects
			WindowsDriver<WebElement> applicationSessionObject;
			WindowsDriver<WebElement> desktopSession;
			
			//Test data for creating appointment
			String randomValue=actions.generateRandomAlphaString(5);

			//Click on 'Add' button
			applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
			actions.waitForElementVisibility(map.addButtonCalendarScreen,"AccessId",applicationSessionObject,20); 
			actions.click(map.addButtonCalendarScreen, "Add button in Calendar screen", applicationSessionObject, et);
			
			//Enter details in 'Appointment' window
			actions.waitForElementVisibility(map.appointmentWindowCalendarScreen,"AccessId",applicationSessionObject,20); 
			actions.isElementPresent(map.appointmentWindowCalendarScreen, "Appointment window", true, applicationSessionObject, et);
			
			//Select resident
			if(!residentName.isEmpty())
			{
				actions.click(map.residentDropdownAppointmentWindow, "Resident dropdown", applicationSessionObject, et);
				try
				{
				WebElement residentDropdown = applicationSessionObject.findElement(map.residentDropdownAppointmentWindow);
				//residentDropdown.click();
				int cnt=0;
				while (true) {
					residentDropdown.sendKeys(Keys.DOWN);
					if (residentDropdown.getAttribute("Value.Value").equalsIgnoreCase(residentName.trim()))
					{
						cnt=cnt+1;
						break;
					}	
				}
				if(cnt==1)
				{
					logger.log("pass", "Successfully selected '"+residentName+"' from resident dropdown", false, applicationSessionObject, reportingPath, et);
				}
				else
				{
					logger.log("fail", "Successfully not selected '"+residentName+"' from resident dropdown", true, applicationSessionObject, reportingPath, et);
				}
				}
				catch (Exception e)
				{
					e.printStackTrace();
					logger.log("fail", "Error in selecting '"+residentName+"' from resident dropdown", true, applicationSessionObject, reportingPath, et);
				}
				actions.click(map.residentDropdownAppointmentWindow, "Resident dropdown", applicationSessionObject, et);
			}
			
			
			//Enter Title
			if(!appointmentTitle.isEmpty())
			{
				MedPassModuleTestCases.hashmapMedicationData.put("AppointmentTitle", appointmentTitle);
				actions.setValue(map.titleTextboxAppointmentWindow, "Title textbox", appointmentTitle, applicationSessionObject, et);
			}
			
			//Enter Instructions
			if(!appointmentInstructions.isEmpty())
			{
				MedPassModuleTestCases.hashmapMedicationData.put("AppointmentInstructions", appointmentInstructions);
				actions.setValue(map.preparationInstructionsTextboxAppointmentWindow, "Preparation Instructions textbox", appointmentInstructions, applicationSessionObject, et);
			}
			
			//Selecting duration
			if(!appointmentDuration.isEmpty())
			{
				actions.click(map.durationDropdownAppointmentWindow, "Duration Dropdown", applicationSessionObject, et);
				DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
				desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
				actions.waitForElementVisibility(map.durationDropdownComboboxAppointmentWindow,"ClassName",desktopSession,20);
				WebElement optionDurationDropdown = desktopSession.findElement(map.durationDropdownComboboxAppointmentWindow).findElement(By.name(appointmentDuration));
				actions.click(optionDurationDropdown, "option '"+appointmentDuration+"'", desktopSession, et);
			}
			
			//Capture date and time
			if(option.equalsIgnoreCase("captureDateTime"))
			{
				String scheduleDate=actions.getTextValue(map.scheduledDateFieldAppointmentWindow, "Scheduled Date", applicationSessionObject, et);
				String scheduleTime=actions.getTextValue(map.scheduledTimeFieldAppointmentWindow, "Scheduled Time", applicationSessionObject, et);
				MedPassModuleTestCases.hashmapMedicationData.put("ScheduleDate", scheduleDate);
				MedPassModuleTestCases.hashmapMedicationData.put("ScheduleTime", scheduleTime);
			}
			
			//Click on 'Save' button
			actions.click(map.saveButtonAppointmentWindow, "Save button", applicationSessionObject, et);
			
			
			
		} catch (Exception e) {
		e.printStackTrace();
	}
}

public void validateDataInAppointmentBannerInMedicationSelectionScreen(String option,ExtentTest et) {
		try {
			//Application and desktop objects
			WindowsDriver<WebElement> applicationSessionObject;
			
			//Finding the appointment from appointment banner
			applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
			actions.waitForElementVisibility(map.appointmentBannerMedicationsListScreen,"Xpath",applicationSessionObject,20);
			List<WebElement> appointmentBanners=applicationSessionObject.findElement(map.appointmentBannersSectionMedicationsListScreen).findElements(map.appointmentBannerMedicationsListScreen);
			int cnt = 0;
			int j=0;
			for (int i=0;i<appointmentBanners.size();i++)
			{
				if (appointmentBanners.get(i).findElement(map.titleAndScheduleTimeAppointmentBannerMedicationsListScreen).getText().trim().contains(MedPassModuleTestCases.hashmapMedicationData.get("AppointmentTitle")))
				{
					cnt=1;
					j=i;
					break;
				}
			}
				
			//Validating the data in the appointment banner
			if(cnt!=0)
			{
				//Validating appointment title
				if(option.trim().contains("Title"))
				{
					String actData=actions.getTextValue(appointmentBanners.get(j).findElement(map.titleAndScheduleTimeAppointmentBannerMedicationsListScreen), "Appointment title section", applicationSessionObject, et);
					String expData=MedPassModuleTestCases.hashmapMedicationData.get("AppointmentTitle");	
					if(actData.trim().contains(expData))
					{
						logger.log("pass", "Successfully displaying appointment title as '"+expData+"' in appointment banner", false, applicationSessionObject, reportingPath, et);
					}
					else
					{
						logger.log("fail", "Successfully not displaying appointment title as '"+expData+"' in appointment banner", true, applicationSessionObject, reportingPath, et);
					}
				}
				
				//Validating appointment date
				if(option.trim().contains("Date"))
				{
					String actData=actions.getTextValue(appointmentBanners.get(j).findElement(map.titleAndScheduleTimeAppointmentBannerMedicationsListScreen), "Appointment date section", applicationSessionObject, et);
					String expData=MedPassModuleTestCases.hashmapMedicationData.get("ScheduleDate");	
					if(actData.trim().contains(expData))
					{
						logger.log("pass","Successfully displaying appointment date as '"+expData+"' in appointment banner", false, applicationSessionObject, reportingPath, et);
					}
					else
					{
						logger.log("fail","Successfully not displaying appointment date as '"+expData+"' in appointment banner", true, applicationSessionObject, reportingPath, et);
					}
				}
				
				
				//Validating appointment time
				if(option.trim().contains("Time"))
				{
					String actData=actions.getTextValue(appointmentBanners.get(j).findElement(map.titleAndScheduleTimeAppointmentBannerMedicationsListScreen), "Appointment time section", applicationSessionObject, et);
					String expData=MedPassModuleTestCases.hashmapMedicationData.get("ScheduleTime");	
					if(actData.trim().contains(expData))
					{
						logger.log("pass","Successfully displaying appointment time as '"+expData+"' in appointment banner", false, applicationSessionObject, reportingPath, et);
					}
					else
					{
						logger.log("fail","Successfully not displaying appointment time as '"+expData+"' in appointment banner", true, applicationSessionObject, reportingPath, et);
					}
				}
				
				
				//Validating appointment Instructions
				if(option.trim().contains("Instructions"))
				{
					String actData=actions.getTextValue(appointmentBanners.get(j).findElement(map.preparationInstructionsAppointmentBannerMedicationsListScreen), "Appointment instructions section", applicationSessionObject, et);
					String expData=MedPassModuleTestCases.hashmapMedicationData.get("AppointmentInstructions");	
					if(actData.trim().contains(expData))
					{
						logger.log("pass","Successfully displaying appointment instructions as '"+expData+"' in appointment banner", false, applicationSessionObject, reportingPath, et);
					}
					else
					{
						logger.log("fail","Successfully not displaying appointment instructions as '"+expData+"' in appointment banner", true, applicationSessionObject, reportingPath, et);
					}
				}
				
				
				
			}
			else
			{
				logger.log("fail", "The appointment '"+MedPassModuleTestCases.hashmapMedicationData.get("AppointmentTitle")+"' is not present in the medication list screen", true, applicationSessionObject, reportingPath, et);
			}
		} catch (Exception e) {
		e.printStackTrace();
	}
}

public void deleteAppointmentInCalendarScreen(String appointmentTitle,ExtentTest et) {
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Entering appointment title in search filter
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.navigatorSectionCalendarScreen,"AccessId",applicationSessionObject,20);
		actions.setValue(applicationSessionObject.findElement(map.navigatorSectionCalendarScreen).findElement(map.filterTextboxNavigatorSectionCalendarScreen), "Search filter", appointmentTitle, applicationSessionObject, et);
		Thread.sleep(8000);
		System.out.println(applicationSessionObject.getPageSource());
	} catch (Exception e) {
	e.printStackTrace();
}
}

public void selectBackButtonInMedicationSelectionScreen(String option,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Click on 'Back' button
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.backButtonMedicationsListScreen,"AccessId",applicationSessionObject,20);
		actions.click(map.backButtonMedicationsListScreen, "Back Button", applicationSessionObject, et);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void validateResidentNameInMedicationSelectionScreen(String residentName,String option,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Validate the resident name
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.patientDetailsTileMedicationsListScreen,"AccessId",applicationSessionObject,60);
		WebElement residentTile=applicationSessionObject.findElement(map.patientDetailsTileMedicationsListScreen).findElement(map.patientNamePatientDetailsTileMedicationsListScreen);
		if(residentTile.getText().trim().equalsIgnoreCase(residentName.trim()))
		{
			logger.log("pass", "Successfully displaying exp resident name as '"+residentName+"' in medication selection screen ",false, applicationSessionObject, reportingPath, et);
		}
		else
		{
			logger.log("fail", "Successfully not displaying exp resident name as '"+residentName+"' in medication selection screen ",true, applicationSessionObject, reportingPath, et);
		}

		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void enterPhysicalIDInResidentProfileScreenAndSave (String physicalID,String option,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		
		//Enter physical id
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.physicalIDTextboxProfileTab,"AccessId",applicationSessionObject,20);
		actions.clear(map.physicalIDTextboxProfileTab, "Physical ID Textbox", applicationSessionObject, et);
		actions.setValue(map.physicalIDTextboxProfileTab, "Physical ID Textbox", physicalID, applicationSessionObject, et);
		
		//Click on 'Save' button
		actions.click(map.saveButtonProfileTab, "Save Button", applicationSessionObject, et);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void changeMeridianTimeInMedicationSelectionScreen(String meridianTime,String option,ExtentTest et) {
	
	try {
		//Application and desktop objects
		WindowsDriver<WebElement> applicationSessionObject;
		WindowsDriver<WebElement> desktopSession;
		
		//Clicking on Meridian Time button
		applicationSessionObject = DriverFactoryApplicationSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.aMPMNoonNightSelectionButtonMedicationsListScreen,"Name",applicationSessionObject,20);
		actions.click(map.aMPMNoonNightSelectionButtonMedicationsListScreen, "Meridian Time button", applicationSessionObject, et);
		
		//Selecting option from dropdown
		DriverFactoryDesktopSession.getInstance().setDriver("Windows", "local");
		desktopSession = DriverFactoryDesktopSession.getInstance().getDriver();
		actions.waitForElementVisibility(map.aMPMNoonNightSelectionDropdownMedicationsListScreen,"TagName",desktopSession,20);
		WebElement timeOption = desktopSession.findElement(map.aMPMNoonNightSelectionDropdownMedicationsListScreen).findElement(By.name(meridianTime));
		actions.click(timeOption, "option "+meridianTime, desktopSession, et);
		
		//If 'Late Night Items' window is displayed
		actions.waitForElementVisibility(map.lateNightItemsWindow, "Name", applicationSessionObject, 5);
		if(actions.isElementPresent(map.lateNightItemsWindow, "Late Night Items window", false, applicationSessionObject, et))
		{
			WebElement continueButton=applicationSessionObject.findElement(map.lateNightItemsWindow).findElement(map.continueButtonLateNightItemsWindow);
			actions.click(continueButton, "Continue button", applicationSessionObject, et);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
