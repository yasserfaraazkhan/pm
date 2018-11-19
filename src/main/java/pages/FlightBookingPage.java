package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class FlightBookingPage extends PageInstances {

	@FindBy(id = "OneWay")
	private WebElement oneWayWebElement;

	@FindBy(id = "FromTag")
	private WebElement fromSourceLocationTextElement;

	@FindBy(id = "ToTag")
	private WebElement toDestinationLocationTextElement;

	@FindBy(id = "ui-id-1")
	private WebElement fromDestinationDropDown;

	@FindBy(id = "ui-id-2")
	private WebElement toSourceDropDown;

	@FindBy(id = "DepartDate")
	private WebElement datePickerBox;

//	December 22
	@FindBy(xpath = "//div[@class='monthBlock last']/child::table/child::tbody/child::tr/td/a[contains(text(),'22')]")
	private WebElement selectedDatePickerElement;

	@FindBy(id = "SearchBtn")
	private WebElement searchButtonElement;

	@FindBy(className = "pageLoader relative")
	private WebElement progressBar;

	@FindBy(className = "searchSummary")
	private WebElement searchSummaryElement;

	public WebElement getDatePickerBox() {
		return datePickerBox;
	}

	public WebElement getSearchSummaryElement() {
		return searchSummaryElement;
	}

	public WebElement getProgressBar() {
		return progressBar;
	}

	public WebElement getSearchButtonElement() {
		return searchButtonElement;
	}

	public WebElement getFromDestinationDropDown() {
		return fromDestinationDropDown;
	}

	public WebElement getToSourceDropDown() {
		return toSourceDropDown;
	}

	public WebElement getOneWayWebElement() {
		return oneWayWebElement;
	}

	public WebElement getFromSourceLocationTextElement() {
		return fromSourceLocationTextElement;
	}

	public WebElement getToDestinationLocationTextElement() {
		return toDestinationLocationTextElement;
	}

	public WebElement getSelectedDatePickerElement() {
		return selectedDatePickerElement;
	}

	public void testOneWayFlightResults() throws Exception {
		
		clickOnElement(getOneWayWebElement());
		getFromSourceLocationTextElement().clear();
		enterText(getFromSourceLocationTextElement(), "Bangalore");

		List<WebElement> originOptions = getFromDestinationDropDown().findElements(By.tagName("li"));
		originOptions.get(0).click();

		getToDestinationLocationTextElement().clear();
		enterText(getToDestinationLocationTextElement(), "Delhi");

		// select the first item from the destination auto complete list
		List<WebElement> destinationOptions = getToSourceDropDown().findElements(By.tagName("li"));
		destinationOptions.get(0).click();

		clickOnElement(getDatePickerBox());
		
		clickOnElement(getSelectedDatePickerElement());
		
		// all fields filled in. Now click on search
		clickOnElement(getSearchButtonElement());

		Assert.assertTrue(getSearchSummaryElement().isDisplayed());
	}

}
