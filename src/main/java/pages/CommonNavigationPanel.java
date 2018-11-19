package pages;

import org.apache.log4j.Logger;

/*******************************************************
 * @author yasser.khan
 * This class has declaration of common methods used across the application
 *******************************************************/
public class CommonNavigationPanel extends PageInstances {
	private static Logger log = Logger.getLogger(CommonNavigationPanel.class);

	public void navigateToHomePage() {
		log.info("navigation to the webpage");
		PageInstances.LoginPage().navigateToLoginPage(getTestBaseUrl());
		waitForPageLoaded(DEFAULT_NAVIGATION_TIMEOUT_SEC);
	}

}
