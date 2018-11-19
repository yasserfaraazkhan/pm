package pages;

import org.apache.log4j.Logger;

public class LoginPage extends CommonNavigationPanel {
	private static Logger log = Logger.getLogger(LoginPage.class);

	public LoginPage() {
		
	}

	public void navigateToLoginPage(String url) {
		log.info("Navigation to -> "+ url);
		getPageURL(url);
		waitForPageLoaded(15);
	}
}