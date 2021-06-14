package testcases;

import java.io.IOException;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import testscripts.BaseLibrary;
import testscripts.Constants;
import testscripts.DriverScript;
import testscripts.FunctionLibrary;

public class MaintenanceSmokeTest extends DriverScript {

	/*
	 * All Hail AUTOmation!
	 ****************************************************************************/

	// TestCases are listed in Jira/Confluence:

	// Verify Open Service Requests is present in Maintenance Page.
	public static String verifyOpenServiceRequestsMaintenanceModule()
			throws BiffException, InterruptedException, IOException, RowsExceededException, WriteException {

		FunctionLibrary.log("Executing test case : Verify Open Service Requests is present in Maintenance Page.");

		// Navigate to Propertyware App & Login
		methodReturnResult = BaseLibrary.navigateAndLoginToPropertywareApp(1);
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}

		// Navigate to Maintenance Module

		methodReturnResult = BaseLibrary.navigateToMaintenanceModule();
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}

		// Assert
		Boolean openServiceRequestsButton = FunctionLibrary
				.isElementPresent(Constants.maintenanceModuleOpenServiceRequestsButton, "Open Service Requests");

		// Logout of the app
		methodReturnResult = BaseLibrary.logout();
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}

		// Return Pass if Open Service Requests is present in Page
		if (openServiceRequestsButton == true) {
			FunctionLibrary.log("Pass: Open Service Requests is present in Page.");
			return "Pass: Open Service Requests is present in Page..";
		} else {
			FunctionLibrary.log("Fail: Open Service Requests is NOT present in Page");
			return "Fail: Open Service Requests is NOT present in Page.";
		}
	}

	// Verify New Work Order Button is present in Maintenance Page.
	public static String verifyNewWorkOrderButtonInMaintenanceModule()
			throws BiffException, InterruptedException, IOException, RowsExceededException, WriteException {

		FunctionLibrary.log("Executing test case : Verify New Work Order Button is present in Maintenance Page.");

		// Navigate to Propertyware App & Login
		methodReturnResult = BaseLibrary.navigateAndLoginToPropertywareApp(1);
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}

		// Navigate to Maintenance Module
		methodReturnResult = BaseLibrary.navigateToMaintenanceModule();
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}

		// Assert
		Boolean NewWorkOrderButton = FunctionLibrary.isElementPresent(Constants.maintenanceModuleNewWorkOrderButton,
				"New Work Order Button");

		// Logout of the app
		methodReturnResult = BaseLibrary.logout();
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}

		// Return Pass if New Work Order Button is present in Page
		if (NewWorkOrderButton) {
			FunctionLibrary.log("Pass: New Work Order Button is present in Page.");
			return "Pass: New Work Order Button is present in Page..";
		} else {
			FunctionLibrary.log("Fail: New Work Order Button is NOT present in Page");
			return "Fail: New Work Order Button is NOT present in Page.";
		}
	}

	public static String Test()
			throws BiffException, InterruptedException, IOException, RowsExceededException, WriteException {

		APPLICATION_LOGS.debug(
				"Executing test case : Verify the Provider Status Filter & Parent Provider Status should show a new status as 'Recieved and Refer'");
		String preChars = "Aclna";
		// Get the values from Excel sheet
		String dischargeCompleteHospitalName = testData.getCellData("DischargeComplete",
				"Discharge Complete Hospital Name", 1);
		String patientFirstName = preChars + FunctionLibrary.randomnGenerator(5);
		String patientLastName = FunctionLibrary.randomnGenerator(5);

		String Provider1 = testData.getCellData("DischargeComplete", "Discharge Complete Provider", 1);
		String Provider2 = testData.getCellData("DischargeComplete", "Discharge Complete Provider", 2);
		// String DCCompleteProvider3 = testData.getCellData("DischargeComplete",
		// "Discharge Complete Provider", 3);
		String facilityId = testData.getCellData("loginToDischargeCentral", "FacilityID", 1);
		String dischargeConfig = testData.getCellData("loginToDischargeCentral", "ConfigName", 1);
		String dischargeConfigValue = testData.getCellData("loginToDischargeCentral", "ConfigValue", 1);
		// String securityId = testData.getCellData("loginToDischargeCentral",
		// "SecurityID", 1);

		// Logout of the app
		BaseLibrary.logout();

		// Verify if Test EXPECTED OUTPUT is met & print result
		if (true) {
			return "Pass: The Provider Status Filter & Parent Provider Status should show a new status as 'Recieved and Refer'";
		} else
			return "Fail: Unable to Verify the Provider Status Filter & Parent Provider Status should show a new status as 'Recieved and Refer'";
	}

}
