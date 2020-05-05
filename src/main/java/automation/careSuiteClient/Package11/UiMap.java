package automation.careSuiteClient.Package11;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public class UiMap {
	
	//Login window
	public By loginWindow=By.name("QuickMAR Login");
	public By titleLoginWindow=By.name("TitleBar");
	public By userNameTextboxLoginWindow=MobileBy.AccessibilityId("UserNameTextBox");
	public By passwordTextboxLoginWindow=MobileBy.AccessibilityId("PasswordTextBox");
	public By okButtonLoginWindow=MobileBy.AccessibilityId("FormOkButton");
	public By cancelButtonLoginWindow=MobileBy.AccessibilityId("FormCancelButton");//By.name("Cancel");
		
	//Home window
	//public By titleHomeWindow=By.name("  Park Place Manor 3  (Facil ID: 221)");
	public By titleHomeWindow=MobileBy.AccessibilityId("radTitleBar1");
	public By residentsMenu=By.name("Residents");
	public By manageOrdersOptionResidentsMenu=By.name("Manage Orders");
	public By passMedsMenu=By.name("Pass Meds");
	public By passMedsDropDown=By.name("Pass MedsDropDown");
	public By prnOptionPassMedsDropDown=By.name("PRN");
	public By logOffButtonHomeWindow=By.name("Log off");
	public By lateNightItemsWindow=By.name("Late Night Items");
	public By continueButtonLateNightItemsWindow=By.name("Continue");
	public By homeMenu=By.name("Home   ");
	public By dashboardButtonHomeScreen=MobileBy.AccessibilityId("btnDashboard");
	public By calendarButtonHomeScreen=MobileBy.AccessibilityId("btnPatientsCalendar");
	public By quickMarLogoHomeScreen=MobileBy.AccessibilityId("picPharmacyLogo");
		
	//Calendar screen
	public By calendarScreen=MobileBy.AccessibilityId("PatientsCalendar");
	public By addButtonCalendarScreen=MobileBy.AccessibilityId("btnAddAppointment");
	public By navigatorSectionCalendarScreen=MobileBy.AccessibilityId("schNavigator");
	public By filterTextboxNavigatorSectionCalendarScreen=By.xpath("//Edit");
	
	//Appointment window
	public By appointmentWindowCalendarScreen=MobileBy.AccessibilityId("PatientAppointmentDialog");
	public By residentDropdownAppointmentWindow=MobileBy.AccessibilityId("cbPatient");
	public By titleTextboxAppointmentWindow=MobileBy.AccessibilityId("edtTitle");
	public By preparationInstructionsTextboxAppointmentWindow=MobileBy.AccessibilityId("edtPreparationInstructions");
	public By scheduledDateFieldAppointmentWindow=MobileBy.AccessibilityId("dtScheduledDate");
	public By scheduledTimeFieldAppointmentWindow=MobileBy.AccessibilityId("dtScheduledTime");
	public By saveButtonAppointmentWindow=MobileBy.AccessibilityId("btnSave");
	public By deleteButtonAppointmentWindow=MobileBy.AccessibilityId("btnDelete");
	public By durationDropdownAppointmentWindow=MobileBy.AccessibilityId("cbDuration");
	public By durationDropdownComboboxAppointmentWindow=By.className("ComboLBox");
	public By durationDropdownComboboxOptionAppointmentWindow=By.name("15 minutes");
	
	//Appointment delete window
	public By confirmDeleteWindowAppointment=MobileBy.AccessibilityId("MessageBoxYesNo");
	public By yesButtonConfirmDeleteWindowAppointment=MobileBy.AccessibilityId("yesButton");
	
	
	//Ready To Log Off window
	public By yesButtonReadyToLogOffWindow=By.name("Yes");
	
	//MedPass screen
	public By medPassResidentsList=MobileBy.AccessibilityId("MedPassPatientButton");
	public By medPassResidentFullName=By.xpath("//*[@AutomationId='lblFullName']");
	public By saveSelectionsButtonMedPassScreen=MobileBy.AccessibilityId("btnSaveSelections");
	public By pushPinIconResidentTileMedPassScreen=By.xpath("//*[@AutomationId='lblTooltipUpperLeft']");
	
	//Medication selection window
	public By medicationsList=MobileBy.AccessibilityId("PickListItemButton");
	public By medicationTitleMedicationsList=By.xpath("//*[@AutomationId='lblTitle']");  //MobileBy.AccessibilityId("lblTitle");
	public By tickMarkMedicationsList=By.xpath("//*[@AutomationId='CheckPictureBox']");  //MobileBy.AccessibilityId("CheckPictureBox");
	public By historyButtonMedicationsList=By.xpath("//*[@AutomationId='btnHistory']");  //MobileBy.AccessibilityId("btnHistory");
	public By medTileDataMedicationsList=MobileBy.AccessibilityId("instructionsRTF");
	public By administrationHistoryWindow=MobileBy.AccessibilityId("HistoryFrm");
	public By timeRangeDropdownAdministrationHistoryWindow=MobileBy.AccessibilityId("cboTimeRange");
	public By openButtonTimeRangeDropdownAdministrationHistoryWindow=By.name("Open");
	public By listDropdownTimeRange=By.className("ComboLBox");
	public By optionListDropdownTimeRange=By.name("Last 15 days");
	public By closeButtonAdministrationHistoryWindow=MobileBy.AccessibilityId("btnCancel");
	public By nextButtonMedicationListWindow=By.name("Next  >");
	public By pickListExceptionsWindow=By.name("Pick List Exceptions");
	public By continueButtonPickListExceptionsWindow=By.name("Continue");
	public By dropDownListPickListExceptionsWindow=By.name("cboException");
	public By cancelButtonPickListExceptionsWindow=By.name("Cancel");
	public By skipAllButtonConfirmationScreen=MobileBy.AccessibilityId("SkipMedPassButton");
	public By skipAllDropDownConfirmationScreen=By.name("Skip AllDropDown");
	public By withheldPerRNDoctorsRequestOptionSkipAllDropDown=By.name("Withheld per RN/Doctor's request");
	public By myCustomExceptionOptionSkipAllDropDown=By.name("My Custom Exception");
	public By givenByHomeHealthOptionSkipAllDropDown=By.name("Given by Home Health");
	public By outOfFacilityOptionSkipAllDropDown=By.name("Out of facility");
	public By givenToFamilyToGiveLaterOptionSkipAllDropDown=By.name("Given to family to give later");
	public By residentRefusedOptionSkipAllDropDown=By.name("Resident refused");
	public By cannotCompleteMedPassWindow=By.name("Cannot Complete Med Pass");
	public By messageCannotCompleteMedPassWindow=MobileBy.AccessibilityId("message");
	public By OkButtonCannotCompleteMedPassWindow=MobileBy.AccessibilityId("btnOK");
	public By noteIconConfirmationScreen=MobileBy.AccessibilityId("picNote");
	public By addNoteWindowConfirmationScreen=By.name("Add Note");
	public By textAreaAddNoteWindow=MobileBy.AccessibilityId("txtNote");
	public By okButtonAddNoteWindow=MobileBy.AccessibilityId("FormOkButton");
	public By exceptionButtonDropdownConfirmationScreen=MobileBy.AccessibilityId("btnDropDown");
	public By showPRNButtonMedicationSelectionScreen=MobileBy.AccessibilityId("PRNToggleButton");
	public By whenAmountAlreadyExceededInLast24HoursConfirmMedPassWindow=By.name("Confirm Med Pass");
	public By okButtonWhenAmountAlreadyExceededInLast24HoursConfirmMedPassWindow=MobileBy.AccessibilityId("btnSave");
	public By cancelButtonWhenAmountAlreadyExceededInLast24HoursConfirmMedPassWindow=MobileBy.AccessibilityId("btnCancel");
	public By warningMessageWhenAmountAlreadyExceededInLast24HoursConfirmMedPassWindow=MobileBy.AccessibilityId("lblMessage");
	public By medicationNameWhenAmountAlreadyExceededInLast24HoursConfirmMedPassWindow=MobileBy.AccessibilityId("lblOrderTitle");
	public By suggestionMessageWhenAmountAlreadyExceededInLast24HoursConfirmMedPassWindow=MobileBy.AccessibilityId("lblLastGivenHours");
	public By performCancelOrOkMessageWhenAmountAlreadyExceededInLast24HoursConfirmMedPassWindow=MobileBy.AccessibilityId("lblConfirm");
	public By textBoxWhenAmountAlreadyExceededInLast24HoursConfirmMedPassWindow=MobileBy.AccessibilityId("txtNotes");
	public By strengthAndFormStoringElementMedicationsListScreen=By.xpath("//*[@AutomationId='DosageLabel']");//MobileBy.AccessibilityId("DosageLabel");
	public By routeStoringElementMedicationsListScreen=By.xpath("//*[@AutomationId='lblRoutine']");//MobileBy.AccessibilityId("lblRoutine");
	public By instructionsAndDiagnosisStoringElementMedicationsListScreen=By.xpath("//*[@AutomationId='instructionsRTF']");//MobileBy.AccessibilityId("instructionsRTF");
	public By informationButtonMedicationsListScreen=By.xpath("//*[@AutomationId='btnInformation']");//MobileBy.AccessibilityId("btnInformation");
	public By reorderButtonMedicationsListScreen=By.xpath("//*[@AutomationId='btnReorder']");//MobileBy.AccessibilityId("btnReorder");
	public By reorderWindowMedicationsListScreen=MobileBy.AccessibilityId("ReorderDialog");
	public By nameLabelReorderWindowMedicationsListScreen=MobileBy.AccessibilityId("lblTreatmentName");
	public By lastReorderLabelReorderWindowMedicationsListScreen=MobileBy.AccessibilityId("lblLastReorder");
	public By lastFilledLabelReorderWindowMedicationsListScreen=MobileBy.AccessibilityId("lblLastFilled");
	public By quantityTextboxReorderWindowMedicationsListScreen=By.xpath("//Edit[@Name='Quantity:']");
	public By messageTextboxReorderWindowMedicationsListScreen=MobileBy.AccessibilityId("txtMessage");
	public By historyButtonReorderWindowMedicationsListScreen=MobileBy.AccessibilityId("btnHistory");
	public By okButtonReorderWindowMedicationsListScreen=MobileBy.AccessibilityId("btnOk");
	public By cancelButtonReorderWindowMedicationsListScreen=MobileBy.AccessibilityId("btnBack");
	public By patientDetailsTileMedicationsListScreen=MobileBy.AccessibilityId("patientButton1");
	public By patientNamePatientDetailsTileMedicationsListScreen=By.xpath("//*[@AutomationId='lblFullName']");//MobileBy.AccessibilityId("lblFullName");
	public By backButtonMedicationsListScreen=MobileBy.AccessibilityId("btnBack");
	
	public By aMPMNoonNightSelectionButtonMedicationsListScreen=By.name("btnMedPass");
	public By aMPMNoonNightSelectionDropdownMedicationsListScreen=By.tagName("Menu");
		
	//Appointment Banner - Medication selection window
	public By appointmentBannersSectionMedicationsListScreen=MobileBy.AccessibilityId("tlpAppointmentBanners");
	public By appointmentBannerMedicationsListScreen=By.xpath("//*[contains(@AutomationId,'mpadb')]");
	public By titleAndScheduleTimeAppointmentBannerMedicationsListScreen=By.xpath("//*[@AutomationId='lblTitleAndSchedule']");//MobileBy.AccessibilityId("lblTitleAndSchedule");
	public By preparationInstructionsAppointmentBannerMedicationsListScreen=By.xpath("//*[@AutomationId='lblPreparationInstructions']");//MobileBy.AccessibilityId("lblPreparationInstructions");
	
	//Drug Information Window
	public By drugInformationWindowMedicationsListScreen=MobileBy.AccessibilityId("DrugInformationDialog");
	public By adverseEffectsSectionDrugInformationWindow=By.xpath("//*[@AutomationId='gbAdverseEffects']");//MobileBy.AccessibilityId("gbAdverseEffects");
	public By adverseEffectsTextAreaDrugInformationWindow=By.xpath("//*[@AutomationId='rtxtAdverseEffects']");//MobileBy.AccessibilityId("rtxtAdverseEffects");
	public By labelWarningsSectionDrugInformationWindow=By.xpath("//*[@AutomationId='gbLabelWarnings']");//MobileBy.AccessibilityId("gbLabelWarnings");
	public By labelWarningsTextAreaDrugInformationWindow=By.xpath("//*[@AutomationId='rtxtLabelWarning']");//MobileBy.AccessibilityId("rtxtLabelWarning");
	public By printButtonDrugInformationWindow=By.xpath("//*[@AutomationId='btnPrint']");//MobileBy.AccessibilityId("btnPrint");
	
	//Drug Information Printing Window
	public By drugInformationPrintingWindowMedicationsListScreen=MobileBy.AccessibilityId("DrugInformationPrintingDialog");
	public By adverseEffectsCheckboxDrugInformationPrintingWindow=By.xpath("//*[@AutomationId='chkAdverseEffects']");//MobileBy.AccessibilityId("chkAdverseEffects");
	public By labelWarningsCheckboxDrugInformationPrintingWindow=By.xpath("//*[@AutomationId='chkLabelWarnings']");//MobileBy.AccessibilityId("chkLabelWarnings");
	public By drugImageCheckboxDrugInformationPrintingWindow=By.xpath("//*[@AutomationId='chkDrugImage']");//MobileBy.AccessibilityId("chkDrugImage");
	public By printButtonDrugInformationPrintingWindow=By.xpath("//*[@AutomationId='btnOK']");//MobileBy.AccessibilityId("btnOK");
	public By cancelButtonDrugInformationPrintingWindow=By.xpath("//*[@AutomationId='btnCancel']");//MobileBy.AccessibilityId("btnCancel");
	
	//Print Window
	public By printWindow=By.name("Print");
	public By nameDropdownPrintWindow=By.className("ComboBox");
	public By nameDropdownComboboxPrintWindow=By.className("ComboLBox");
	public By nameDropdownOptionComboboxPrintWindow=By.name("Microsoft Print to PDF");
	public By okButtonPrintWindow=By.name("OK");
	public By cancelButtonPrintWindow=By.name("Cancel");
	
	//Save Print Output As Window
	public By savePrintOutputAsWindow=By.name("Save Print Output As");
	public By fileNameTextboxSavePrintOutputAsWindow=By.xpath("//*[@ClassName='Edit'][@Name='File name:']");
	public By searchTextboxSavePrintOutputAsWindow=By.className("SearchEditBox");
	public By addressBarSavePrintOutputAsWindow=By.className("Breadcrumb Parent");
	public By addressBarTextboxSavePrintOutputAsWindow=By.xpath("//Edit[@Name='Address']");
	public By saveButtonSavePrintOutputAsWindow=By.name("Save");
	
	//Manage Orders window
	public By firstResidentResidentsPane=MobileBy.AccessibilityId("ListViewSubItem-0");
	public By residentsPaneManageOrders=MobileBy.AccessibilityId("lvPatients");
	public By addDropdownButtonOrdersWindow=By.name("btnAddDropdown");
	public By medicationOptionAddDropdownButton=By.name("Medication");
	public By medicationTextboxParentOrdersWindow=MobileBy.AccessibilityId("txtDrugNameLookup");
	public By medicationTextboxOrdersWindow=By.xpath("//*[@AutomationId='txtEntry']");
	public By fromDropdownOrdersWindow=MobileBy.AccessibilityId("unitOfMeasureListRadComboBox");
	public By fromDropdownTextboxOrdersWindow=By.xpath("//Edit");
	public By startTimeDropdownOrdersWindow=MobileBy.AccessibilityId("cboTimeOfDay");
	public By openButtonStartTimeDropdownOrdersWindow=By.name("Open");
	public By listDropdownStartTimeOrdersWindow=By.className("ComboLBox");
	public By option12AMListDropdownStartTimeOrdersWindow=By.name("12:00 AM");
	public By prescriberTextboxParentOrdersWindow=MobileBy.AccessibilityId("ctlPrescriberName");
	public By prescriberTextboxOrdersWindow=By.xpath("//*[@AutomationId='txtEntry']");
	public By listDropdownPrescriberTextboxOrdersWindow=MobileBy.AccessibilityId("pnlDropDown");
	public By optionListDropdownPrescriberTextboxOrdersWindow=By.name("test, test");
	public By instructionsTextboxOrdersWindow=MobileBy.AccessibilityId("instructionsTextBox");
	public By controlledDrugCheckboxOrdersWindow=MobileBy.AccessibilityId("IsControlledCheckBox");
	public By prnButtonOrdersScreen=MobileBy.AccessibilityId("optPRN");
	public By addPartButtonOrdersScreen=MobileBy.AccessibilityId("btnAddSplit");
	public By minDosageTextboxParentOrdersWindow=MobileBy.AccessibilityId("numMinDosage");
	public By minDosageTextboxOrdersWindow=By.xpath("//Edit");
	public By maxDosageTextboxParentOrdersWindow=MobileBy.AccessibilityId("numMaxDosage");
	public By maxDosageTextboxOrdersWindow=By.xpath("//Edit");
	public By saveButtonOrdersWindow=MobileBy.AccessibilityId("btnSave");
	public By treatmentSchedulesSection=MobileBy.AccessibilityId("treatmentSchedulesControl1");
	public By addDropdownButtonTreatmentSchedulesSection=By.name("btnAddDropdown");
	public By addDropdownTreatmentSchedulesSection=By.name("DropDown");
	public By max24HourDosageCheckboxOrdersScreen=By.name("Max 24 hour dosage:");
	public By textBoxMax24HourDosageOrdersScreen=MobileBy.AccessibilityId("txtMax24HourDosage");
	public By startDateTimeSectionOrdersScreen=MobileBy.AccessibilityId("dtoStart");
	public By startDateTextboxOrdersScreen=By.xpath("//*[@AutomationId='dtpDate']"); //MobileBy.AccessibilityId("dtpDate");
	public By endDateTimeSectionOrdersScreen=MobileBy.AccessibilityId("dtoEndDate");
	public By endDateTextboxOrdersScreen=By.xpath("//*[@AutomationId='dtpDate']"); //MobileBy.AccessibilityId("dtpDate");
	public By medicationsListTableManageOrdersScreenOrdersTab=MobileBy.AccessibilityId("lvwTreatments");
	public By confirmEndDateWindowOrdersTab=MobileBy.AccessibilityId("MessageBoxOKCancel");
	public By noButtonConfirmEndDateWindowOrdersTab=MobileBy.AccessibilityId("btnCancel");
	public By recordCheckboxOrdersTab=MobileBy.AccessibilityId("chkbxRecord");
	public By vitalsWindowRecordCheckboxOrdersTab=MobileBy.AccessibilityId("pnlContents");
	public By vitalsFirstDropdownRecordCheckboxOrdersTab=By.xpath("//ComboBox[@AutomationId='cboVital0']");//MobileBy.AccessibilityId("cboVital0");
	public By vitalsFirstDropdownTextBoxRecordCheckboxOrdersTab=By.xpath("//Edit");
	public By panelTitleVitalsWindowRecordCheckboxOrdersTab=MobileBy.AccessibilityId("pnlTitle");
	public By xMarkPanelTitleVitalsWindowRecordCheckboxOrdersTab=By.name("X");
	public By strengthTextboxOrdersTab=MobileBy.AccessibilityId("DrugStrengthTextBox");
	public By diagnosisTextboxOrdersTab=MobileBy.AccessibilityId("txtDiagnosis");
	public By routeDropdownOrdersTab=MobileBy.AccessibilityId("cbRoute");
	public By routeDropdownComboboxOrdersTab=By.className("ComboLBox");
	public By optionRouteDropdownComboboxOrdersTab=By.name("FEEDING TUBE");
	
	//Profile tab - Manage Residents
	public By physicalIDTextboxProfileTab=MobileBy.AccessibilityId("physicalIDTextBox");
	public By saveButtonProfileTab=MobileBy.AccessibilityId("SaveButton");
	
	//Barcode screen
	public By debugDialogWindowBarcode=MobileBy.AccessibilityId("DebugDialog");
	public By sendBarcodeButtonDebugDialogWindow=By.xpath("//*[@AutomationId='btnSendBarcode']");//MobileBy.AccessibilityId("btnSendBarcode");
	public By closeButtonDebugDialogWindow=By.xpath("//*[@AutomationId='btnClose']");//MobileBy.AccessibilityId("btnClose");
	public By barcodeTesterWindow=MobileBy.AccessibilityId("GetInputDialog");
	public By textboxBarcodeTesterWindow=By.xpath("//*[@AutomationId='txtData']");//MobileBy.AccessibilityId("txtData");
	public By okButtonBarcodeTesterWindow=By.xpath("//*[@AutomationId='OKButton']");//MobileBy.AccessibilityId("OKButton");
	
	//Confirmation window - Pass meds
	public By confirmationWindow=MobileBy.AccessibilityId("lblConfirmation");
	public By amountTextboxConfirmationWindow=MobileBy.AccessibilityId("edtAmount");
	public By purposeDropdownConfirmationWindow=MobileBy.AccessibilityId("cbReason");
	public By purposeDropdownTextBoxConfirmationWindow=By.xpath("//Edit");
	public By listDropdownPurposeDropdownConfirmationWindow=By.tagName("List");
	public By recordAllButtonConfirmationWindow=MobileBy.AccessibilityId("RecordButton");
	public By confirmMedPassWindow=By.name("Confirm Med Pass");
	public By textboxConfirmMedPassWindow=MobileBy.AccessibilityId("txtNotes"); 
	public By okButtonConfirmMedPassWindow=MobileBy.AccessibilityId("btnOK"); 
	public By cancelButtonConfirmMedPassWindow=MobileBy.AccessibilityId("btnCancel"); 
	public By confirmMedPassWindow2=MobileBy.AccessibilityId("MedPassConfirmationDialog");
	public By okButtonConfirmMedPassWindow2=MobileBy.AccessibilityId("btnOK"); 
	public By exceptionPaneConfirmationScreen=MobileBy.AccessibilityId("ctlExceptions");
	public By exceptionButtonConfirmationScreen=By.name("Exception");
	public By exceptionDropdownConfirmationScreen=By.name("ExceptionDropDown");
	public By vitalsSectionConfirmationScreen=MobileBy.AccessibilityId("tlpBase");
	public By bPHigherValueTextboxVitalsSectionConfirmationScreen=By.xpath("//Edit[@Name='BP:']");
	public By bPLowerValueTextboxVitalsSectionConfirmationScreen=By.xpath("//Edit[@Name='/']");

	
	//No Inventory window
	public By noInventoryWindow=By.name("No Inventory");
	public By takeFromEBoxButtonNoInventoryWindow=By.name("Take from e-Box");
	
	//Inventory Tracking Acknowledgement window
	public By inventoryTrackingAcknowledgementWindow=By.name("InventoryTrackingAcknowledgement");
	public By yesButtonInventoryTrackingAcknowledgementWindow=By.name("Yes");
	public By noButtonInventoryTrackingAcknowledgementWindow=By.name("No");
	public By okButtonInventoryTrackingAcknowledgementWindow=By.name("OK");
	public By cancelButtonInventoryTrackingAcknowledgementWindow=By.name("Cancel");
	
	//Witness Confirmation window
	public By witnessConfirmationWindow=By.name("Witness Confirmation");
	public By userNameTextboxWitnessConfirmationWindow=MobileBy.AccessibilityId("txtUserName");
	public By passwordTextboxWitnessConfirmationWindow=MobileBy.AccessibilityId("txtPassword");
	public By okButtonWitnessConfirmationWindow=By.name("OK");
	public By cancelButtonWitnessConfirmationWindow=By.name("Cancel");
	
	//Meds Taken from E-Kit window
	public By medsTakenfromEKitWindow=By.name("Meds Taken from E-Kit");
	public By startDateTextFieldMedsTakenfromEKitWindow=By.xpath("//Edit[@Name='StartDate']");
	public By endDateTextFieldMedsTakenfromEKitWindow=By.xpath("//Edit[@Name='EndDate']");
	public By startDateDropDownIconMedsTakenfromEKitWindow=By.name("StartDate Drop Down Icon");
	public By endDateDropDownIconMedsTakenfromEKitWindow=By.name("EndDate Drop Down Icon");
	public By viewReportButtonMedsTakenfromEKitWindow=By.name("viewReport");
	
	//Dashboard screen
	public By allCartsDropdownDashboardScreen=By.name("All Carts");
	public By refreshButtonDashboardScreen=MobileBy.AccessibilityId("btnRefresh");
	public By printButtonDashboardScreen=MobileBy.AccessibilityId("btnPrint");
	public By tableDashboardScreen=By.name("Grid");
	
}
