package webdriverfactory;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sun.javafx.PlatformUtil;

public class BrowserInstance extends Browser {

	private static Logger log = Logger.getLogger(BrowserInstance.class);

	private static String downloadFilePath = System.getProperty("user.dir") + "\\ExportedFiles";

	public BrowserInstance(BrowserType type) {
		super(type);
	}

	private void setDriverPath() {
		System.out.println(" setting chromedriver Path for ");
		if (PlatformUtil.isMac()) {
			System.out.println(" MAC");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//src//test//resources//chromedriver");
		}
		if (PlatformUtil.isWindows()) {
			System.out.println(" Windows");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//src//test//resources//chromedriver.exe");
		}
		if (PlatformUtil.isLinux()) {
			System.out.println(" Linux");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//src//test//resources//chromedriver_linux");
		}
	}

	@Override
	public WebDriver startChromeBrowser() {
		return startBrowser(BrowserType.CHROME);
	}

	@Override
	public WebDriver startFireFoxBrowser() {
		return startBrowser(BrowserType.FIRE_FOX);
	}

	@Override
	protected WebDriver startBrowser(BrowserType browser) {
		setDriverPath();
		log.info("Starting Browser with desired options");
		HashMap<String, Object> browserPrefs = new HashMap<String, Object>();
		browserPrefs.put("download.default_directory", downloadFilePath);
		browserPrefs.put("credentials_enable_service", false);
		browserPrefs.put("profile.password_manager_enabled", false);

		if (browser.equals(BrowserType.CHROME)) {
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", browserPrefs);
			System.setProperty("webdriver.chrome.args", "--disable-logging");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			options.addArguments("--test-type");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-extensions");
			options.addArguments("disable-infobars");
			return new ChromeDriver(options);
		} else {
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("permissions.default.desktop-notification", 1);
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability(FirefoxDriver.PROFILE, profile);
			return new FirefoxDriver(capabilities);
		}
	}

}
