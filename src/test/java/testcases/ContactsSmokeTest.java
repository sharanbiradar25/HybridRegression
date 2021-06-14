package testcases;

import testscripts.BaseLibrary;

import java.io.IOException;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import testscripts.Constants;
import testscripts.DriverScript;
import testscripts.FunctionLibrary;

public class ContactsSmokeTest extends DriverScript {

	public static String patientFirstName = null;
	public static String patientLastName;
	public static String fullName;
	public static String MRN;
	public static Boolean PatientCreatedByAutomationExists = false;
	public static String preChars;
	public static String dischargeCompleteHospitalName;
	public static String RUN_DATE;
	public static String dischargeConfig;
	public static String facilityId;
	public static String accountNumber;
	public static String sex;
	public static String admitType;
	public static String ADMISSION_DATE;
	public static String dischargeDate;
	public static String dg1;
	public static String securityId;
	public static int in1SetId;
	public static String in1PlanId;
	public static String ESTIMATED_DISCHARGE;
	public static String PATIENT_CLASS_CODE;
	public static String DOB;
	public static String PolicyNumber;

	// TestCases are listed in
	public static String assertContactsModuleUI()
			throws BiffException, InterruptedException, IOException, RowsExceededException, WriteException {

		FunctionLibrary.log("Executing test case : Asserting Contacts Module UI is as expected");

		return "Pass";
	}

	// Verify Contacts Module Tab.
	public static String verifyContactsModuleIsPresent()
			throws BiffException, InterruptedException, IOException, RowsExceededException, WriteException {

		FunctionLibrary.log("Executing test case : Verify Contacts Module button.");

		// Navigate to Propertyware App & Login
		methodReturnResult = BaseLibrary.navigateAndLoginToPropertywareApp(1);
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}

		// Assert
		Boolean NewWorkOrderButton = FunctionLibrary.isElementPresent(Constants.contactsModuleButton,
				"Contacts Module");

		// Logout of the app
		methodReturnResult = BaseLibrary.logout();
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}

		// Return Pass if Contacts Module button is present in Page
		if (NewWorkOrderButton) {
			FunctionLibrary.log("Pass: Contacts Module button is present in Page.");
			return "Pass: Contacts Module button is present in Page..";
		} else {
			FunctionLibrary.log("Fail: Contacts Module button is NOT present in Page");
			return "Fail: Contacts Module button is NOT present in Page.";
		}
	}

}
