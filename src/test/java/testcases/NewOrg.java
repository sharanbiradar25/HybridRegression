package testcases;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import testscripts.BaseLibrary;
import testscripts.FunctionLibrary;
import testscripts.Keyword;
import testscripts.DriverScript;
import testscripts.Constants;

public class NewOrg extends DriverScript {

	public static By contactsModuleButton = By.xpath("//a[contains(text(),'Contacts')]");
	public static By customersModuleButton = By.xpath("//a[contains(text(),'Customers')]");
	public static By NewCustomersWizardButton = By.xpath("//a[contains(text(),'New Customer Wizard')]");
	public static By companyNameField = By.xpath("//input[@id='companyName']");
	public static By companyAddressField = By.xpath("//input[@id='companyAddress']");
	public static By companyPhoneNoField = By.xpath("//input[@id='companyPhone']");
	public static By companyCityField = By.xpath("//input[@id='companyCity']");
	public static By companyStateField = By.xpath("//input[@id='companyState']");
	public static By companyZIPField = By.xpath("//input[@id='companyZip']");
	public static By companyCountyDropdown = By.xpath("//select[@id='companyCountry']");
	public static By companyAddressContField = By.xpath("//input[@id='companyAddressCont']");
	public static By companySFIDField = By.xpath("//input[@id='sfdcAccountId']");
	public static By billingFirstNameField = By.xpath("//input[contains(@name,'firstName')]");
	public static By billingLastNameField = By.xpath("//input[contains(@name,'lastName')]");
	public static By billingEmailAddressField = By.xpath("//input[contains(@name,'emailAddress')]");
	public static By billingSameAsAboveCheckBox = By.xpath("//input[@id='useAltEmail']");
	public static By physicalAddressSameAsAboveRadioButton = By.xpath("//input[@value='CompanyAddress']");
	public static By nextButton = By.xpath("//div[@id='next']//img[@class='subsButton']");

	public static By subscriptionTypeDropdown = By.xpath("//select[@id='subscriptionType']");
	public static By packageSubscription = By.xpath("//select[@id='subscriptionPackage']");
	public static By immidiateAccessCheckbox = By.xpath("//input[@id='immediateAccess']");
	public static By CreditCardCheckbox = By.xpath("//input[@id='cc']");
	public static By maxUnitsField = By.xpath("//input[@id='maxUnits1']");
	public static By calculateTaxButton = By.xpath("//img[@id='calculatetax']");
	public static By confirmationPageNextButton = By.xpath("//img[@id='save']");
	public static By mailSendButton = By.xpath("//div[@style='text-align:right;']//img[@class='subsButton']");
	public static By locatorResetPasswordLink = By.xpath("//a[contains(@href,'reset')]");

	public static By newPassword = By.xpath("//input[@id='newPasswd']");
	public static By newPasswdConfirmation = By.xpath("//input[@id='newPasswdConfirmation']");
	public static By submitButton = By.xpath("//img[@src='/pw/images/btn-submit-red.gif']");
	public static By locatorSuccessMessage = By
			.xpath("//*[contains(text(),'Your new password was successfully updated.')]");

	public static String CreateNewOrg() throws BiffException, InterruptedException, IOException, RowsExceededException,
			WriteException, SQLException {

		FunctionLibrary.log("Executing : Creating New Org");

		// Navigate to Propertyware App & Login
		methodReturnResult = BaseLibrary.navigateAndLoginToPropertywareApp(1);
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}

		methodReturnResult = FunctionLibrary.clickAndWait(customersModuleButton, "Customers Module button");
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		methodReturnResult = FunctionLibrary.clickAndWait(NewCustomersWizardButton, "New Customers Wizard button");
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		// Initializing Company Address
		String CompanyName = "AutoCorp" + FunctionLibrary.randomnGenerator(4);
		String CompanyAddress = "JANE ROE";
		String CompanyCity = "PHOENIX";
		String CompanyAddressCont = "200 E MAIN ST";
		String CompanyPhoneNumber = FunctionLibrary.randomnGenerator(10);
		String CompanyState = "AZ";
		String CompanyZIP = "85123";
		String CompanyCountry = "United States";
		String CompanySalesForceID = "0018000000xq4DA";

		methodReturnResult = FunctionLibrary.clearAndInput(companyNameField, "Company Name", CompanyName);
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		methodReturnResult = FunctionLibrary.clearAndInput(companyAddressField, "Company Address", CompanyAddress);
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		methodReturnResult = FunctionLibrary.clearAndInput(companyAddressContField, "Company Address Cont.",
				CompanyAddressCont);
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		methodReturnResult = FunctionLibrary.clearAndInput(companyCityField, "Company City", CompanyCity);
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		methodReturnResult = FunctionLibrary.clearAndInput(companyPhoneNoField, "Company Phone Number",
				CompanyPhoneNumber);
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		methodReturnResult = FunctionLibrary.clearAndInput(companyStateField, "Company State", CompanyState);
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		methodReturnResult = FunctionLibrary.clearAndInput(companyZIPField, "Company PIN", CompanyZIP);
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		methodReturnResult = FunctionLibrary.selectValueByVisibleText(companyCountyDropdown, CompanyCountry,
				"Company Country");
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		methodReturnResult = FunctionLibrary.clearAndInput(companySFIDField, "Company Salesforce ID",
				CompanySalesForceID);
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		methodReturnResult = FunctionLibrary.clearAndInput(companyZIPField, "Company ZIP", CompanyZIP);
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		String FirstName = FunctionLibrary.generateRandomString(6);
		String LastName = FunctionLibrary.generateRandomString(6);
		String Email = "testorg" + FunctionLibrary.generateRandomString(5) + "@auto.com";

		// Initializing Billing Administrator
		methodReturnResult = FunctionLibrary.clearAndInput(billingFirstNameField, "First Name", FirstName);
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		methodReturnResult = FunctionLibrary.clearAndInput(billingLastNameField, "Last Name", LastName);
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		methodReturnResult = FunctionLibrary.clearAndInput(billingEmailAddressField, "Email Address", Email);
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		methodReturnResult = FunctionLibrary.checkCheckBox(billingSameAsAboveCheckBox, "Same As Above CheckBox");
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		// Initializing Physical address

		WebElement sameAsCompanyAddressRadioButton = driver.findElement(By.xpath("//input[@value='CompanyAddress']"));
		sameAsCompanyAddressRadioButton.click();

		// click on Next button
		methodReturnResult = FunctionLibrary.clickAndWait(nextButton, "Next Button");
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		FunctionLibrary.waitForPageToLoad();

		// Subscription Settings
		methodReturnResult = FunctionLibrary.selectValueByVisibleText(subscriptionTypeDropdown, "Annual",
				"Subscription Type");
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		methodReturnResult = FunctionLibrary.selectValueByVisibleText(packageSubscription, "Premium",
				"Package Subscription");
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		methodReturnResult = FunctionLibrary.checkCheckBox(immidiateAccessCheckbox, "Immidiate Access CheckBox");
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		methodReturnResult = FunctionLibrary.checkCheckBox(CreditCardCheckbox, "Credit Card CheckBox");
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		methodReturnResult = FunctionLibrary.clearAndInput(maxUnitsField, "Max Units", "200");
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		methodReturnResult = FunctionLibrary.clickAndWait(calculateTaxButton, "Calculate Tax Button");
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		FunctionLibrary.waitForPageToLoad();

		methodReturnResult = FunctionLibrary.clickAndWait(confirmationPageNextButton, "Confirmation Page Next Button");
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		// Alternate step
		/*
		 * methodReturnResult = FunctionLibrary.clickAndWait(mailSendButton,
		 * "Mail Send Button"); if (methodReturnResult.contains("Fail")) { return
		 * methodReturnResult; }
		 */

		// switch to the Iframe
		driver.switchTo().frame("editor_ifr");

		// Alternate step to get the password reset link
		/*
		 * System.out.println(driver.findElement(By.cssSelector(
		 * "body.mce-content-body:nth-child(2) table.mce-item-table:nth-child(1) table.mce-item-table tbody:nth-child(1) tr:nth-child(4) td:nth-child(1) p:nth-child(1) > a:nth-child(4)"
		 * )) .getAttribute("href"));
		 */

		if (FunctionLibrary.isElementDisplayed(locatorResetPasswordLink, "Set Password Link")) {
			System.out.println("Password Reset Link Present on page");
		}

		// retrieve password reset link
		String resetPasswordLink = driver.findElement(locatorResetPasswordLink).getAttribute("href");

		System.out.println("Password Reset Link: " + resetPasswordLink);

		// navigate to password reset link
		driver.navigate().to(resetPasswordLink);

		FunctionLibrary.waitForPageToLoad();

		String NewPassword = "Realpage!12";
		String SuccessMessage = null;
		Boolean SuccessMessageStatus = false;

		if (FunctionLibrary.isElementDisplayed(newPassword, "New Password Field")) {
			methodReturnResult = FunctionLibrary.clearAndInput(newPassword, "New Password Field", NewPassword);
			if (methodReturnResult.contains("Fail")) {
				return methodReturnResult;
			}

			methodReturnResult = FunctionLibrary.clearAndInput(newPasswdConfirmation, "Confirm New Password Field",
					NewPassword);
			if (methodReturnResult.contains("Fail")) {
				return methodReturnResult;
			}

			methodReturnResult = FunctionLibrary.clickAndWait(submitButton, "Submit Button");
			if (methodReturnResult.contains("Fail")) {
				return methodReturnResult;
			}

			FunctionLibrary.waitForPageToLoad();

			if (FunctionLibrary.isElementPresent(locatorSuccessMessage, "Success Message")) {
				SuccessMessageStatus = true;
				SuccessMessage = driver.findElement(locatorSuccessMessage).getText();

			}

		} else {
			return "Fail: Unable to naviagte to reset pasword link";
		}

		// Assert success message displayed
		if (SuccessMessage.contentEquals("Your new password was successfully updated.")
				|| SuccessMessageStatus == true) {
			FunctionLibrary.log("Credentials- Email: " + Email + " | Password: " + NewPassword);
			return "Pass: Successfully set new password for a Newly Created Org";
		} else {
			return "Fail: Unable to reset pasword link";
		}

	}

}
