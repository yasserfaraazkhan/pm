package projectHooks;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import pages.PageInstances;
import utility.ConfigParser;
import utility.Log;
import webdriverfactory.WebDriverFactory;

public class Hooks {

	private static Logger log = Logger.getLogger(Hooks.class);

	/*******************************************************
	 * Functionality: This method starts the browser Instance and navigates to
	 * tool's home page
	 *******************************************************/

	@BeforeSuite
	public void setUp() throws Exception {
		ConfigParser.setConfigParser("/ClearTrip.properties");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverFactory.startChromeDriver();
		PageInstances.CommonNavigationPanel().navigateToHomePage();
	}

	/**
	 * Delete all cookies at the start of each scenario to avoid shared state
	 * between tests
	 */
	@BeforeMethod
	public void deleteAllCookies() {
		log.info("Deleting all cookies...");
		WebDriverFactory.getDriverInstance().manage().deleteAllCookies();
	}

	/*******************************************************
	 * Functionality:This method shuts down driver after saving the results of
	 * the suite
	 * 
	 *******************************************************/

	@AfterSuite
	public void tearDown() throws Exception {
		try {
			WebDriverFactory.getDriverInstance().quit();

		} catch (Exception e) {
			Log.getLogger().error(e.getMessage());
			throw e;
		}
	}

}