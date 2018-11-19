package utility;

import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.BasePage;
import webdriverfactory.WebDriverFactory;

public abstract class WebElementWait extends BasePage {
	private static Logger log = Logger.getLogger(WebElementWait.class);

	private static WebDriver webDriver = WebDriverFactory.getDriverInstance();
	public static final int DEFAULT_NAVIGATION_TIMEOUT_SEC = 120;
	private static final int DEFAULT_POLL_TIMEOUT_MS = 200;
	private static final long ALERT_WAIT_TIMEOUT_SEC = 60;


	public WebElementWait() {
	}

	public WebDriverWait getWebDriverWait(WebDriver driver, long timeOutInSeconds, long sleepInMillis) {
		return new WebDriverWait(driver, timeOutInSeconds, sleepInMillis);
	}

	public static WebDriverWait getWebDriverWait() {
		return new WebDriverWait(webDriver, DEFAULT_NAVIGATION_TIMEOUT_SEC, DEFAULT_POLL_TIMEOUT_MS);
	}

	public WebElement waitForVisibilityOfElement(final WebElement element) {
		return getWebDriverWait().until(ExpectedConditions.visibilityOf(element));
	}

	public WebElement waitForElementToBeClickable(final WebElement element) {
		return getWebDriverWait().until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForTextToBePresentInElementValue(final WebElement element, final String text) {
		getWebDriverWait().until(ExpectedConditions.textToBePresentInElementValue(element, text));
	}

	public void waitForTitle(final String title) {
		getWebDriverWait().until(PageUtils.titleEquals(title));
	}

	public void waitForUrl(final String urlSubstring) {
		WebDriverWait wait = new WebDriverWait(webDriver, DEFAULT_NAVIGATION_TIMEOUT_SEC, DEFAULT_POLL_TIMEOUT_MS);
		wait.until(PageUtils.urlContains(urlSubstring));
	}

	public void waitForElementToBeActive(final WebElement element) {
		getWebDriverWait().until(PageUtils.elementToBeActive(element));
	}

	protected void waitForClosableAlert(WebDriver webDriver, By alertLocator) throws TimeoutException {
		WebDriverWait wait = new WebDriverWait(webDriver, ALERT_WAIT_TIMEOUT_SEC, DEFAULT_POLL_TIMEOUT_MS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(alertLocator));

		By closeLocator = By.className("close");
		if (closeLocator != null) {
			for (WebElement e : webDriver.findElements(closeLocator)) {
				wait.until(ExpectedConditions.elementToBeClickable(e));
				e.click();
			}
			wait.until(PageUtils.absenseOfElementLocatedBy(alertLocator));
		}
	}

	public void waitForClosableAlertToBeClick(WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(webDriver, DEFAULT_NAVIGATION_TIMEOUT_SEC, DEFAULT_POLL_TIMEOUT_MS);
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}
}