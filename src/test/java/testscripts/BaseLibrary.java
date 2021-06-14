package testscripts;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import java.util.logging.Level;

import datatable.XlsReader;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import testcases.MaintenanceSmokeTest;
import util.TestUtil;
import testscripts.FunctionLibrary;
import testscripts.DriverScript;
import testscripts.Constants;

public class BaseLibrary extends DriverScript {

	// Stores current window handle
	public static String currentWindowHandle;

	// Store method return result
	public static String methodReturnResult = null;
	public static Boolean present;
	public static Boolean actionStatus;

	// Expected page titles
	public static String expectedLoginTitle = "Propertyware Login Page";
	public static String expectedTitle = null;

	/* . Locators for the test . */
	public static By locatorUserNameInputBox = By.xpath("//input[@id='userName']");
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

	/* . Name of the WebElements present on the WebPage . */
	public static String nameUserNameInputBox = "'Username' Input-box";
	public static String namePasswordInputBox = "'Password' Input-box";

	// Retrieving URL from Config.properties File
	public static String getTestAppURL() throws MalformedURLException, InterruptedException {

		try {
			URL = testData.getCellData("Login", "URL", 1);

			if (URL != null) {
				// URL = CONFIG.getProperty(env + "_" + WebsiteName);

				FunctionLibrary.AppLog("Env : " + env + " | " + WebsiteName + " : URL : " + URL);
				return URL;

			} else {
				FunctionLibrary.AppLog("NO SUCH URL FOUND IN Config.properties File: " + env + "_" + WebsiteName);
				System.out.println("NO SUCH URL FOUND IN Config.properties File: " + env + "_" + WebsiteName);
				return null;
			}
		}

		catch (Exception E) {
			return "Error came while retieving URL: " + E;
		}

	}

	// Create a browser instance and navigate to the App
	public static String navigateAndLoginToPropertywareApp(int Data_Row_No)
			throws MalformedURLException, InterruptedException {

		FunctionLibrary.log("Creating a browser instance and navigating to the test site ...");

		// Disable log messages
		java.util.logging.Logger.getLogger("org.apache.http.impl.client").setLevel(java.util.logging.Level.WARNING);

		if (wbdv == null) {

			try {

				if (CONFIG.getProperty("is_remote").equals("true")) {

					// Generate Remote address
					String remote_address = "http://" + CONFIG.getProperty("remote_ip") + ":4444/wd/hub";
					remote_url = new URL(remote_address);

					if (CONFIG.getProperty("test_browser").contains("Internet Explorer")) {

						dc = DesiredCapabilities.internetExplorer();
						dc.setCapability("silent", true);

					} else if (CONFIG.getProperty("test_browser").toLowerCase().contains("chrome")) {

						// System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") +
						// "/chromedriver.exe");
						dc = DesiredCapabilities.chrome();
						wbdv = new ChromeDriver(dc);
						driver = new EventFiringWebDriver(wbdv);

					}

					else {

						dc = DesiredCapabilities.firefox();
						System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
					}

					wbdv = new RemoteWebDriver(remote_url, dc);
					driver = new EventFiringWebDriver(wbdv);
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
					System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "false");
					java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

				}

				else {

					if (CONFIG.getProperty("test_browser").toLowerCase().contains("internet explorer")
							|| CONFIG.getProperty("test_browser").toLowerCase().contains("ie")) {
						dc = DesiredCapabilities.internetExplorer();
						dc.setCapability("silent", true);
						wbdv = new InternetExplorerDriver(dc);
						driver = new EventFiringWebDriver(wbdv);

					}

					else if (CONFIG.getProperty("test_browser").toLowerCase().contains("firefox")
							|| CONFIG.getProperty("test_browser").toLowerCase().contains("ff")) {

						ProfilesIni allProfiles = new ProfilesIni();
						FirefoxProfile profile = allProfiles.getProfile("default");
						profile.setAcceptUntrustedCertificates(true);
						profile.setAssumeUntrustedCertificateIssuer(false);

						System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

						wbdv = new FirefoxDriver();
						driver = new EventFiringWebDriver(wbdv);

						System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

					} else if (CONFIG.getProperty("test_browser").toLowerCase().contains("chrome")) {

						/*
						 * System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
						 * + "/chromedriver.exe");
						 */
						dc = DesiredCapabilities.chrome();
						wbdv = new ChromeDriver(dc);
						driver = new EventFiringWebDriver(wbdv);

					}

				}

			}

			catch (Throwable initBrowserException) {

				APPLICATION_LOGS
						.debug("Error came while creating a browser instance : " + initBrowserException.getMessage());

				return failTest + " : Error came while creating a browser instance : "
						+ initBrowserException.getMessage();

			}

		}

		FunctionLibrary.log("Created browser instance successfully");

		try {

			// Implicitly wait for 30 seconds for browser to open
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			// Delete all browser cookies
			driver.manage().deleteAllCookies();

			// Navigate to Propertyware application
			// driver.navigate().to(CONFIG.getProperty("PWURL"));

			driver.navigate().to(getTestAppURL());

			// Maximize browser window
			FunctionLibrary.log("Maximizing Browser window...");
			driver.manage().window().maximize();
			FunctionLibrary.log("Browser window is maximized");

		}

		catch (Throwable navigationError) {

			FunctionLibrary.log("Error came while navigating to the site : " + navigationError.getMessage());
			return failTest + " : Error came while navigating to the site.";
		}

		Thread.sleep(5000L);

		// Verify Login page appears
		methodReturnResult = FunctionLibrary.assertTitle(expectedLoginTitle);
		if (methodReturnResult.contains(failTest)) {

			// Log result
			FunctionLibrary.log("Not navigated to Login page");
			return methodReturnResult;

		}

		FunctionLibrary.log("Navigated to Login page");
		FunctionLibrary.log("Logging in to the App...");

		String userName = null;
		String password = null;

		try {

			userName = testData.getCellData("Login", "UserName", Data_Row_No);
			password = testData.getCellData("Login", "Password", Data_Row_No);

			FunctionLibrary.AppLog("Successfully Retrieved data from Xls File :-  Username : " + userName
					+ " and Password : " + password);

		}

		catch (Throwable fetchExcelDataError) {

			FunctionLibrary.AppLog("Error while retrieving data from xls file : " + fetchExcelDataError.getMessage());
			return failTest + " : Error while retrieving data from xls file : " + fetchExcelDataError.getMessage();
		}

		// Clear Username input-box and input username
		methodReturnResult = FunctionLibrary.clearAndInput(propertywareloginField, "UserName/ID Field", userName);
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}

		// Clear Password input-box and input password
		methodReturnResult = FunctionLibrary.clearAndInput(propertywarePasswordField, "Password Field", password);
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}

		// Click on Sign In button
		methodReturnResult = FunctionLibrary.clickAndWait(propertywareSignInButton, "Sign In Button");
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}

		FunctionLibrary.waitForElementToLoad(propertywareHomePageLogo);

		// Verify login was successful
		expectedTitle = null;
		String actualTitle = null;

		// verify user has logged in by checking App Logo appears
		actionStatus = FunctionLibrary.isElementDisplayed(propertywareHomePageLogo, "Propertyware Logo");
		if (actionStatus == false) {
			return "Fail: User not able to login";
		}

		FunctionLibrary.log("Logged into Propertyware App Home Page");
		FunctionLibrary.log("Navigating to Propertyware App Home Page ...");

		return "Pass : Navigated to Propertyware Home Page";
	}

	// Navigate to Maintenance Module
	public static String navigateToMaintenanceModule() throws InterruptedException, BiffException, IOException {
		APPLICATION_LOGS.debug("Navigating to Maintenance Module");

		// Verifying if Maintenance Module Button is present
		if (!FunctionLibrary.isElementPresent(maintenanceModuleButton, "Maintenance Module button")) {
			return failTest + " : Maintenance Module button is not Present on the page";
		}

		// Clicking at Maintenance Module Button and waiting for page to load
		methodReturnResult = FunctionLibrary.clickLink(maintenanceModuleButton, "Maintenance Module button");
		if (methodReturnResult.contains("Fail")) {
			return methodReturnResult;
		}

		FunctionLibrary.waitForPageToLoad();

		// Verifying if Maintenance Module Button is present
		if (!FunctionLibrary.isElementPresent(maintenanceModuleNewWorkOrderButton,
				"Maintenance Module UI - Verify : 'New Work Order' button")) {
			return failTest
					+ " : Couldn't verify navigation of Maintenance Module - 'New Work Order' button is not present.";
		}

		return "Pass. " + "Navigated to Maintenance Module";
	}

	// Logout from the Propertyware application
	public static String logout() throws InterruptedException {

		// Check whether the logout link is present or not
		actionStatus = FunctionLibrary.isElementDisplayed(logoutButton, "Logout Button");
		if (!actionStatus) {
			return failTest + " : Logout link is not present on the page.";
		}

		// Click Logout link
		methodReturnResult = FunctionLibrary.clickLink(logoutButton, "Logout Button");
		if (methodReturnResult.contains(failTest))
			return methodReturnResult;

		/*
		 * // Wait for the Logout Button to appear
		 * FunctionLibrary.waitForElementToDisappear(Constants.logoutButton,
		 * "Logout Button");
		 */
		FunctionLibrary.waitForPageToLoad();

		// Check whether logged out by verifying the Sign-In button
		actionStatus = FunctionLibrary.isElementDisplayed(propertywareloginField,
				"Login Page UI - Verifying : UserName/ID Field");
		if (!actionStatus) {
			return failTest + " : Could not logout of the app.";
		}

		return "Pass : Logged out successfully..";
	}

}
