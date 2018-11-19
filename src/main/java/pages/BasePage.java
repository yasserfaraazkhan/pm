package pages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import projectHooks.Hooks;
import utility.ConfigParser;
import webdriverfactory.WebDriverFactory;

/*******************************************************
 * Class name: Attributes: Methods: Functionality:This is the base class for all
 * pages modelled under the SUT. All the pages defined should be derived from
 * this class. This class has all the wrapper methods for accessing selenium
 * webdriver. This class also contains common methods across all the page
 * classes.
 *******************************************************/
public class BasePage extends Hooks{
	private static Logger log = Logger.getLogger(BasePage.class);

	public BasePage() {
	}

	/*******************************************************
	 * Functionality:This method returns Base Url for HP SDS TOOL.
	 * 
	 * @param:None
	 * @return:Base URL of HP SDS TOOL
	 *******************************************************/

	public String getTestBaseUrl() {
		return ConfigParser.getProperty("baseUrl");
	}

	/*******************************************************
	 * Functionality:This method returns selenium webdriver instance.
	 * 
	 * @param:None
	 * @return:Webdriver
	 *******************************************************/
	protected static WebDriver getWebDriver() {
		return WebDriverFactory.getDriverInstance();
	}

	/*******************************************************
	 * Functionality:This method opens the page url in a maximuzed browser
	 * window
	 * 
	 * @param:String
	 * @return
	 * @return:None
	 *******************************************************/
	protected void getPageURL(String url) {
//		getWebDriver().manage().window().maximize();
		getWebDriver().get(url);
	}

	/*******************************************************
	 * Functionality:This method opens the page url in a Maximised browser
	 * window
	 * 
	 * @param:String
	 * @return:None
	 * @throws Exception
	 *******************************************************/
	protected void enterText(WebElement element, String value) throws Exception {
		if (isElementPresent(element)) {
			element.clear();
			highlightElement(element);
			element.sendKeys(value);
			implicitWait(Integer.parseInt(ConfigParser.getProperty("common_wait_timeout")));
		} else {
			throw new Exception(element + " not found");
		}
	}

	/*******************************************************
	 * Functionality:This method highlights and clicks on a WebElement if same
	 * is present on a page
	 * 
	 * @param:WebElement
	 * @return:None
	 * @throws Exception
	 *******************************************************/
	protected void clickOnElement(WebElement element) throws Exception {
		log.info("clicking on "+ element.getText());
		if (isElementPresent(element)) {
			waitTillVisible(element);
			element.click();
			implicitWait(Integer.parseInt(ConfigParser.getProperty("common_wait_timeout")));
		} else {
			throw new Exception(element + " not found");
		}
	}

	/*******************************************************
	 * Functionality:This method checks if a webelement present in a page
	 * 
	 * @param:WebElement
	 * @return:boolean
	 *******************************************************/
	protected boolean isElementPresent(WebElement webelement) {
		int count = 0;
		boolean exists = false;
		while (count < 4) {
			try {
				implicitWait(2);
				webelement.getTagName();
				highlightElement(webelement);
				exists = true;
				count = count + 4;
			} catch (NoSuchElementException e) {

				count++;
			} catch (StaleElementReferenceException e) {

				count = count + 1;
			} catch (UnhandledAlertException e) {

				count = count + 1;

			}
		}
		return exists;
	}
	/*******************************************************
	 * Functionality:This method will wait for element to be visible
	 * 
	 * @param:WebElement
	 * @return:newelement
	 *******************************************************/
	protected static WebElement waitTillVisible(WebElement element) {
		WebElement newelement = (new WebDriverWait(getWebDriver(), 30)).until(ExpectedConditions.visibilityOf(element));
		return newelement;
	}
	/*******************************************************
	 * Functionality:This method highlights a webelement when selected
	 * 
	 * @param:WebElement
	 * @return:None
	 *******************************************************/
	protected void highlightElement(WebElement element) {

		for (int i = 0; i < 2; i++) {

			JavascriptExecutor js = (JavascriptExecutor) getWebDriver();

			js.executeScript("arguments[0].setAttribute('style', arguments[1]);",

					element, "color: black; border: 2px solid black; background-color: yellow;");

			js.executeScript("arguments[0].setAttribute('style', arguments[1]);",

					element, "");

		}

	}

	/*******************************************************
	 * Functionality:This method adds implicit wait after any operation in sec
	 * 
	 * @param:int
	 * @return:None
	 *******************************************************/
	protected void implicitWait(int sec) {
		getWebDriver().manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
	}

	/*******************************************************
	 * Functionality:This method accepts a popup in a page
	 * 
	 * @param:None
	 * @return:None
	 *******************************************************/
	protected boolean isAlertPresent() {
		boolean status = false;
		try {
			String str = getWebDriver().switchTo().alert().getText();

			System.out.println("Popup string - #" + str);
			if (str != null && !str.isEmpty())
				status = true;

		} catch (NoAlertPresentException Ex) {
			status = false;
		} catch (UnhandledAlertException Ex) {
			status = false;
		}
		return status;
	}

	protected void waitForPageLoaded(int time) {

		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};

		Wait<WebDriver> wait = new WebDriverWait(getWebDriver(), time);
		try {
			wait.until(expectation);
		} catch (Throwable error) {

		}
	}

}