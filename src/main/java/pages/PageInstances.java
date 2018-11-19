package pages;

import org.openqa.selenium.support.PageFactory;

import utility.WebElementWait;

/*******************************************************
 * @author yasser.khan
 * This class has declaration of all the web page instances required in step
 * definition .
 *******************************************************/
public class PageInstances extends WebElementWait {

	private static CommonNavigationPanel navigationBarPage;// = new CommonNavigationPanel();
	private static HotelBookingPage hotelBookingPage;// = new HomePage();
	private static LoginPage loginPage ;//= new LoginPage();
	private static SignInPage signinPage ;//= new SignInPage();
	private static FlightBookingPage flightBookingPage;// = new FlightBookingPage();
	
	public PageInstances () {
		PageFactory.initElements(getWebDriver(), this);
	}

	public static CommonNavigationPanel CommonNavigationPanel() {
		if (navigationBarPage == null) {
			navigationBarPage = new CommonNavigationPanel();
		}
		return navigationBarPage;
	}

	public static SignInPage SignInPage() {
		if (signinPage == null) {
			signinPage = new SignInPage();
		}
		return signinPage;
	}

	public static FlightBookingPage FlightBookingPage() {
		if (flightBookingPage == null) {
			flightBookingPage = new FlightBookingPage();
		}
		return flightBookingPage;
	}

	public static HotelBookingPage HotelBookingPage() {
		if (hotelBookingPage == null) {
			hotelBookingPage = new HotelBookingPage();
		}
		return hotelBookingPage;
	}

	public static LoginPage LoginPage() {
		if (loginPage == null) {
			loginPage = new LoginPage();
		}
		return loginPage;
	}

}
