package testscripts;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import datatable.XlsReader;
import jxl.JXLException;
import jxl.read.biff.BiffException;
import reports.ReportUtil;
import util.TestUtil;

public class DriverScript {

	public static Properties CONFIG;
	public static String isRemote;
	public static Properties OR;
	public static Properties APPTEXT;
	public static Properties LOG;
	public static String testCaseRunMode;
	public static XlsReader controller;
	public static XlsReader testData;
	public static WebDriver wbdv = null;
	public static WebDriver wbdv2 = null;
	public static EventFiringWebDriver driver = null;
	public static EventFiringWebDriver driver2 = null;
	public static Logger APPLICATION_LOGS = Logger.getLogger("Debugger");
	public static String currentTest;
	public static int testRepeat;
	public static String object;
	public static int Data_Row_No;
	public static String keyword;
	public static String currentTSID;
	public static String stepDescription;
	public static String proceedOnFail;
	public static String testStatus;
	public static DesiredCapabilities dc = null;
	public static URL remote_url = null;
	public static java.sql.Statement stmt;
	public static ResultSet rs;
	public static ResultSet rs1;
	public static ResultSet rs2;
	public static Boolean presenseStatus;
	public static Connection con;
	public static Connection mysqlconn;
	public static String defaultWindow;
	public static String firstSheetName;
	public static String failTest = "Fail";
	public static String methodReturnResult = null;
	public static Boolean booleanReturnResult = null;
	public static String currentSystemDate = null;
	public static String facilityId = null;
	public static String securityId = null;
	public static String facilityName = null;
	public static Boolean checkStatus = null;
	public static Boolean checkAlertPresent = false;
	public static Boolean highlightElement = true;
	public static String randomMRNNo = null;
	public static String env;
	public static String DBSchema;
	public static String USER;
	public static String PASSword;
	public static String DB_URL;

	// Extent Reports
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent = new ExtentReports();
	public static ExtentTest logger;

	// public static String RUN_DATE = TestUtil.now("ddMMyyhhmmss").toString();
	public static String screenshotPath = System.getProperty("user.dir") + "/Report/";

	// Database connection
	public static String hostname = null, sid = null, port = null, connectionString = null;
	public static String dbUsername = null, dbPassword = null, Host;
	public static String URL = null, WebsiteName = "testSiteURL";
	public static String log4jConfPath = "src/test/java/Log4j.properties";

	@BeforeClass
	public static void initialize() throws IOException, InterruptedException {

		reporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\Result\\Results.html");
		extent.attachReporter(reporter);

		// Override default J2SE built-in workable logger built-in
		Properties prop = new Properties();
		prop.setProperty("log4j.rootLogger", "WARN");
		PropertyConfigurator.configure(prop);
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
		LOG = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/Log4j.properties");
		LOG.load(file);
		LOG.setProperty("log4j.appender.dest1.File",
				System.getProperty("user.dir") + "/src/test/java/config/application.log");
		LOG.store(new FileOutputStream(System.getProperty("user.dir") + "/src/test/java/Log4j.properties"), null);

		// Locates and loads the config properties
		CONFIG = new Properties();
		FileInputStream fs = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/java/config/config.properties");
		CONFIG.load(fs);

		isRemote = CONFIG.getProperty("is_remote");

		// Locates controller sheet
		controller = new XlsReader(System.getProperty("user.dir") + "/src/test/java/config/controller.xls");

		// Check Test Env
		env = CONFIG.getProperty("env");

		// Locates testData sheet
		if (env.contains("SAT") || env.contains("SAT")) {
			testData = new XlsReader(System.getProperty("user.dir") + "/src/test/java/config/testData_SAT.xls");
		}

		if (env.contains("SIT")) {
			testData = new XlsReader(System.getProperty("user.dir") + "/src/test/java/config/testData_SIT.xls");
		}

		if (env.contains("Staging")) {
			testData = new XlsReader(System.getProperty("user.dir") + "/src/test/java/config/testData_Staging.xls");
		}

		if (CONFIG.containsKey(env + "_" + WebsiteName)) {
			URL = CONFIG.getProperty(env + "_" + WebsiteName);
		}

		Host = "Local System";

		if (isRemote.equals("true")) {
			Host = CONFIG.getProperty("remote_ip");
		}

		// Start the process of HTML report generation
		ReportUtil.startTesting(System.getProperty("user.dir") + "/Report/index.html",
				TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), env, CONFIG.getProperty("version"), Host,
				CONFIG.getProperty("test_browser"), URL);

	}

	@Test
	public void testApp() throws NumberFormatException, BiffException, JXLException, IOException {
		String startTime = null;

		// Get the first sheet name under 'controller.xls'
		firstSheetName = controller.getFirstSheetname();

		ReportUtil.startSuite(firstSheetName);

		for (int tcid = 1; tcid < controller.getRowCount(firstSheetName); tcid++) {

			// Stores the current sub-module
			currentTest = controller.getCellData(firstSheetName, "TCID", tcid).trim();

			// Runs respective sub-module if Runmode for the
			// sub-module is 'Y'
			if (controller.getCellData(firstSheetName, "Runmode", tcid).equals("Y")) {

				APPLICATION_LOGS.debug("Executing test suite: " + currentTest);
				APPLICATION_LOGS.debug("Execution Host: " + Host);

				// Initialize start time of test
				startTime = TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa");

				// Implement keyword
				for (int tsid = 1; tsid < controller.getRowCount(currentTest); tsid++) {

					// values from xls
					// Stores the current keyword
					keyword = controller.getCellData(currentTest, "Keyword", tsid).trim();

					// Stores the current TSID
					currentTSID = controller.getCellData(currentTest, "TSID", tsid).trim();

					// Stores the current description
					stepDescription = controller.getCellData(currentTest, "Description", tsid).trim();
					testCaseRunMode = controller.getCellData(currentTest, "RunMode", tsid).trim();

					try {

						if (testCaseRunMode.equalsIgnoreCase("Y") || testCaseRunMode.equalsIgnoreCase("Yes")
								|| testCaseRunMode.equalsIgnoreCase("True")) {

							logger = extent.createTest(currentTSID + ": " + keyword, stepDescription);

							Method method = Keyword.class.getMethod(keyword);
							String result = (String) method.invoke(method);

							APPLICATION_LOGS.debug("Result of test case execution - " + result);

							if (!result.startsWith("Pass")) {
								ReportUtil.addKeyword(stepDescription, keyword, result, null);
								logger.log(Status.PASS, result);
							}

							// Take screenshot - only on
							// error
							if (result.startsWith("Fail")) {

								logger.log(Status.FAIL, result);

								testStatus = "Fail";
								FunctionLibrary.log("Result of execution :" + result);
								// Give a fileName for
								// the screenshot and
								// store
								String fileName = "Suite1_TC" + tcid + "_TS" + tsid + "_" + keyword + testRepeat
										+ ".jpg";
								String path = screenshotPath + fileName;

								TestUtil.takeScreenShot(path);
								APPLICATION_LOGS.debug("SCREENSHOT taken under : " + path);

								// Write the test result
								// to HTML report
								ReportUtil.addKeyword(stepDescription, keyword, result, fileName);

							}

							if (wbdv != null) {
								FunctionLibrary.closeDriver();
							}
							if (wbdv2 != null) {
								FunctionLibrary.driver2CloseDriver();
							}
						}
					}

					catch (Throwable testException) {
						APPLICATION_LOGS.debug("Error came : " + testException.getMessage());
					}

				} // keywords -inner for loop

				// Report pass or fail
				if (testStatus == null) {
					testStatus = "Pass";
				}

				APPLICATION_LOGS.debug("Result of the '" + currentTest + "' test suite execution - " + testStatus);

				ReportUtil.addTestCase(currentTest, startTime, TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), testStatus);

			}

			else {

				APPLICATION_LOGS.debug("Skipping the test : " + currentTest);
				testStatus = "Skip";

				// Report skipped
				ReportUtil.addTestCase(currentTest, TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"),
						TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), testStatus);

			}

			testStatus = null;

		}

		// End test reporting
		ReportUtil.endSuite();
	}

	@AfterClass
	public static void endScript() throws BiffException, IOException, InterruptedException {

		extent.flush();

		// Update test end time under HTML test report
		ReportUtil.updateEndTime(TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"));

		// Close all test browser sessions
		if (wbdv != null || driver != null) {
			FunctionLibrary.closeDriver();
		}

		// Purge data

		APPLICATION_LOGS.debug(":- Test Execution Complete -:");

	}

}
