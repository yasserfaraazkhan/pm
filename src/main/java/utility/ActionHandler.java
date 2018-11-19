package utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ActionHandler extends WebElementWait {

	private static Logger log = Logger.getLogger(ActionHandler.class);
	private static Robot robotInstance = null;

	public ActionHandler() throws IOException {
		super();
	}

	static Actions actions = new Actions(getWebDriver());

	/**
	 * Selects the value from dropdown based on text passed
	 * 
	 * @param weblement 
	 *            select element present on page
	 * @param textToSelect
	 *            text to be selected from the dropdown
	 */
	public static void selectByVisibleText(WebElement element, String textToSelect) {
		Select select = new Select(element);
		select.selectByVisibleText(textToSelect);
	}
	
	/**
	 * Moves to and clicks on the provided x,y offset on the provided web
	 * element
	 * 
	 * @param we
	 *            the web element on which the action needs to be taken
	 * @param xoffset
	 *            x coordinate on the web element
	 * @param yoffset
	 *            y coordinate on the web element
	 */

	public static void moveAndClick(WebElement we, int xoffset, int yoffset) throws Exception {
		try {
			actions.moveToElement(we, xoffset, yoffset).click().perform();
		} catch (Exception e) {
			Log.getLogger().error(e.getMessage());
			throw e;
		}
	}

	/**
	 * Moves to and clicks on the provided web element
	 * 
	 * @param we
	 *            the web element on which the action needs to be taken
	 */

	public static void moveAndClick(WebElement we) throws Exception {
		try {
			actions.moveToElement(we).click().perform();
		} catch (Exception e) {
			Log.getLogger().error(e.getMessage());
			throw e;
		}
	}

	/**
	 * Moves to, release and clicks on the provided web element
	 * 
	 * @param we
	 *            the web element on which the action needs to be taken
	 */

	public static void moveReleaseAndClick(WebElement we) throws Exception {
		try {
			actions.moveToElement(we).release().click().build().perform();
		} catch (Exception e) {
			Log.getLogger().error(e.getMessage());
			throw e;
		}
	}

	/**
	 * Moves click and hold on the provided x,y offset on the provided web
	 * element
	 * 
	 * @param we
	 *            the web element on which the action needs to be taken
	 * @param xoffset
	 *            x coordinate on the web element
	 * @param yoffset
	 *            y coordinate on the web element
	 */

	public static void moveClickAndHold(WebElement we, int xoffset, int yoffset) throws Exception {
		try {
			actions.moveToElement(we, xoffset, yoffset).clickAndHold().build().perform();
		} catch (Exception e) {
			Log.getLogger().error(e.getMessage());
			throw e;
		}
	}

	/**
	 * Moves to and releases on the provided x,y offset on the provided web
	 * element
	 * 
	 * @param we
	 *            the web element on which the action needs to be taken
	 * @param xoffset
	 *            x coordinate on the web element
	 * @param yoffset
	 *            y coordinate on the web element
	 */

	public static void moveAndRelease(WebElement we, int xoffset, int yoffset) throws Exception {
		try {
			actions.moveToElement(we, xoffset, yoffset).release().build().perform();
		} catch (Exception e) {
			Log.getLogger().error(e.getMessage());
			throw e;
		}
	}

	/**
	 * Double click on the provided web element
	 * 
	 * @param loc
	 *            the web element on which the action needs to be taken
	 */

	public static void doubleClick(By loc) throws Exception {
		try {
			actions.doubleClick(getWebDriver().findElement(loc)).perform();
		} catch (Exception e) {
			Log.getLogger().error(e.getMessage());
			throw e;
		}
	}

	/**
	 * Double click on the provided web element
	 * 
	 * @param loc
	 *            the web element on which the action needs to be taken
	 */

	public static void doubleClick(WebElement Element) throws Exception {
		try {
			actions.doubleClick(Element).build().perform();
		} catch (Exception e) {
			Log.getLogger().error(e.getMessage());
			throw e;
		}
	}

	/**
	 * Hovers on the provided web element
	 * 
	 * @param we
	 *            the web element on which the action needs to be taken
	 */

	public static void hover(WebElement we) throws Exception {
		try {
			actions.moveToElement(we).build().perform();

		} catch (Exception e) {

			Log.getLogger().error(e.getMessage());
			throw e;
		}
	}

	/**
	 * ClickAndHold the WebElement and then Move to Element and release
	 * 
	 * @param weToClickAndHold
	 *            :- the web element on which the clickAndHold action needs to
	 *            be taken weToMoveToElement :- the web element on which the
	 *            above element will be moved and released
	 */

	public static void clickAndHold_Move_Release(WebElement weToClickAndHold, WebElement weToMoveToElement)
			throws Exception {
		try {
			actions.clickAndHold(weToClickAndHold).moveToElement(weToMoveToElement).release().build().perform();

		} catch (Exception e) {

			Log.getLogger().error(e.getMessage());
			throw e;
		}
	}

	public static void dragDrop(WebElement source, WebElement target) {
		try {
			actions.dragAndDrop(source, target).build().perform();
		} catch (Exception e) {
			Log.getLogger().info("Something Went Wrong in dragDrop Method in ActionHandler");
			Log.getLogger().error(e.getMessage());
			throw e;
		}
	}

	public static void holdControlKey() {
		actions.keyUp(Keys.CONTROL);
	}

	public static void releaseControlKey() {
		actions.keyDown(Keys.CONTROL);
	}

	public static void pressEnter() throws AWTException {
		if(robotInstance==null){
			robotInstance = new Robot();
		}
		log.info("Pressing Enter Key");
		robotInstance.keyPress(KeyEvent.VK_ENTER);
	}

	public static void pressBackSpace() throws AWTException {
		if(robotInstance==null){
			robotInstance = new Robot();
		}
		log.info("Pressing Back Space Key");
		robotInstance.keyPress(KeyEvent.VK_BACK_SPACE);
	}

	public static void clickOnWebElement(WebElement webElement) {
		waitTillVisible(webElement);
		log.info("Clicking on "+ webElement.getText());
		webElement.click();
	}

	public static void sendKeys(WebElement webElement, String dataToEnter) {
		waitTillVisible(webElement);
		webElement.clear();
		webElement.sendKeys(dataToEnter);
		log.info("Entering "+dataToEnter+" in "+ webElement+ " field");
	}

	public static boolean isAttribtuePresent(WebElement element, String attribute) {
	    Boolean result = false;
	    try {
	        String value = element.getAttribute(attribute);
	        if (value != null){
	            result = true;
	        }
	    } catch (Exception e) {}

	    return result;
	}
	
}
