package webdriverfactory;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory {
	private static Logger log = Logger.getLogger(WebDriverFactory.class);

	private static HashMap<Long, WebDriver> map = new HashMap<Long, WebDriver>();

	private static WebDriver webDriver;
	public static String env;

	public static WebDriver getDriverInstance() {
		WebDriver driver = map.get(Thread.currentThread().getId());
		return driver;
	}

	public static WebDriver startWebDriver(String type) {
		WebDriver driver = null;
		Browser browser = null;
		
		if (type.equalsIgnoreCase("Chrome")) {
			browser = new BrowserInstance(BrowserType.CHROME);
			driver = browser.startChromeBrowser();
			map.put(Thread.currentThread().getId(), driver);
			log.info("Started Chrome browser");
		} 
		else if (type.equalsIgnoreCase("FireFox")) {
			browser = new BrowserInstance(BrowserType.FIRE_FOX);
			driver = browser.startFireFoxBrowser();
			map.put(Thread.currentThread().getId(), driver);
			log.info("Started firefox browser");
		}
		else if (type.equalsIgnoreCase("ie")) {
		}
		else {
			throw new IllegalArgumentException("Browser type not supported: " + type);
		}
		return driver;
	}

	public static void stopWebDriver() {
		WebDriver driver = map.get(Thread.currentThread().getId());
		driver.quit();
	}

	public static void stopWebDriver(WebDriver driver) {
		driver.quit();
	}

	public static void startChromeDriver() {
		webDriver = startWebDriver("Chrome");
	}

	public static void startFireFoxDriver() {
		webDriver = startWebDriver("FireFox");
	}

	public static void stopChromeDriver() {
		webDriver.quit();
	}

	public static String getEnv() {
		env = System.getProperty("environment");
		return env;
	}
}