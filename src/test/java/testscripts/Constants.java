package testscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Constants {

	public static final String EXPECTEDEDDALERTMESSAGE = "Demo Alert Message";

	// Expected page titles
	public static final String loginPageTitle = "Propertyware Login Page";

	/* ... Locators ... */
	public static final String c = "C";
	public static final By propertywareloginPageSignInMessage = By
			.xpath("//h4[contains(text(),'Sign in to propertyware')]");
	public static final By propertywareloginField = By.xpath("//input[@id='loginEmail']");
	public static final By propertywarePasswordField = By.xpath("//input[@name='password']");
	public static final By propertywareSignInButton = By.xpath("//input[@value='Sign Me In']");
	public static final By propertywareHomePageLogo = By.xpath("//img[@src='/pw/images/partner/1/pw-logo.gif']");
	public static final By logoutButton = By.xpath("//span[contains(text(),'Logout')]");
	public static final By maintenanceModuleButton = By.xpath("//a[contains(text(),'Maintenance')]");
	public static final By maintenanceModuleNewWorkOrderButton = By.xpath("//input[@value='New Work Order']");
	public static final By maintenanceModuleOpenServiceRequestsButton = By
			.xpath("//td[contains(text(),'Open Service Requests')]");
	public static final By contactsModuleButton = By.xpath("//a[contains(text(),'Contacts')]");
	public static final By customersModuleButton = By.xpath("//a[contains(text(),'Customers')]");
	public static final By NewCustomersWizardButton = By.xpath("//a[contains(text(),'New Customer Wizard')]");
	public static final By companyNameField = By.xpath("//input[@id='companyName']");
	public static final By companyAddressField = By.xpath("//input[@id='companyAddress']");
	public static final By companyPhoneNoField = By.xpath("//input[@id='companyPhone']");
	public static final By companyCityField = By.xpath("//input[@id='companyCity']");
	public static final By companyStateField = By.xpath("//input[@id='companyState']");
	public static final By companyZIPField = By.xpath("//input[@id='companyZip']");
	public static final By companyCountyDropdown = By.xpath("//select[@id='companyCountry']");
	public static final By companyAddressContField = By.xpath("//input[@id='companyAddressCont']");
	public static final By companySFIDField = By.xpath("//input[@id='sfdcAccountId']");
	public static final By billingFirstNameField = By.xpath("//input[contains(@name,'firstName')]");
	public static final By billingLastNameField = By.xpath("//input[contains(@name,'lastName')]");
	public static final By billingEmailAddressField = By.xpath("//input[contains(@name,'emailAddress')]");
	public static final By billingSameAsAboveCheckBox = By.xpath("//input[@id='useAltEmail']");
	public static final By physicalAddressSameAsAboveRadioButton = By.xpath("//input[@value='CompanyAddress']");
	public static final WebElement sameAsAboveRadioButton = (WebElement) By.xpath("//input[@value='CompanyAddress']");
	public static final By nextButton = By.xpath("//div[@id='next']//img[@class='subsButton']");

	public static final By subscriptionTypeDropdown = By.xpath("//select[@id='subscriptionType']");
	public static final By packageSubscription = By.xpath("//select[@id='subscriptionPackage']");
	public static final By immidiateAccessCheckbox = By.xpath("//input[@id='immediateAccess']");
	public static final By CreditCardCheckbox = By.xpath("//input[@id='cc']");
	public static final By maxUnitsField = By.xpath("//input[@id='maxUnits1']");
	public static final By calculateTaxButton = By.xpath("//img[@id='calculatetax']");
	public static final By confirmationPageNextButton = By.xpath("//img[@id='save']");
	public static final By mailSendButton = By.xpath("//div[@style='text-align:right;']//img[@class='subsButton']");
	public static final By resetPasswordLink = By.xpath("//a[contains(@href,'reset')]");

	public static final By newPassword = By.xpath("//input[@id='newPasswd']");
	public static final By newPasswdConfirmation = By.xpath("//input[@id='newPasswdConfirmation']");
	public static final By submitButton = By.xpath("//img[@src='/pw/images/btn-submit-red.gif']");
	public static final By SuccessMessage = By
			.xpath("//*[contains(text(),'Your new password was successfully updated')]");

	/* WebElements names present on the WebPage */
	public static final String signInButton = "Sign In Button";
	public static final String loginPageSignInText = "Sign in to propertyware";

}
