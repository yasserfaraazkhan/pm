package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utility.ActionHandler;

public class HotelBookingPage extends PageInstances {

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

	public WebElement getHotelLink() {
		return hotelLink;
	}

	public WebElement getLocalityTextBox() {
		return localityTextBox;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}

	public WebElement getTravellerSelection() {
		return travellerSelection;
	}

	public void searchForHotels() throws Exception {
		clickOnElement(getHotelLink());
		enterText(getLocalityTextBox(), "Indiranagar, Bangalore");
		ActionHandler.selectByVisibleText(getTravellerSelection(), "1 room, 2 adults");
	    clickOnElement(getSearchButton());

	}
    
}
