package utility;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import pages.BasePage;


public class PageUtils extends BasePage {
	private static Logger log = Logger.getLogger(PageUtils.class);

	/**
	 * Used in WebDriverWait to wait for an existing element to no longer exist.
	 */
	public static ExpectedCondition<Boolean> absenseOfElementLocatedBy(final By locator) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				boolean elementIsGone = false;
				try {
					driver.findElement(locator);
				} catch (NoSuchElementException e) {
					elementIsGone = true;
				}
				return elementIsGone;
			}
		};
	}

	public static ExpectedCondition<Boolean> absenseOfAllElementsLocatedBy(final By locator) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				List<WebElement> elements = driver.findElements(locator);
				return elements.size() == 0 ? true : false;
			}
		};
	}

	public static ExpectedCondition<Boolean> titleEquals(final String title) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.getTitle().equals(title);
			}
		};
	}

	public static ExpectedCondition<Boolean> elementContainsText(final By locator) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElement(locator).getText().length() > 0;
			}
		};
	}

	public static ExpectedCondition<Boolean> urlContains(final String urlSubstring) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.getCurrentUrl().contains(urlSubstring);
			}
		};
	}

	/**
	 * Used in WebDriverWait to wait for an existing element to be active
	 */
	public static ExpectedCondition<Boolean> elementToBeActive(final WebElement webElement) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.switchTo().activeElement() != null
						&& driver.switchTo().activeElement().equals(webElement);
			}
		};
	}

}
