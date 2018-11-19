package testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import pages.CommonNavigationPanel;

public class SignInTest extends CommonNavigationPanel {
	private static Logger log = Logger.getLogger(SignInTest.class);

	@Test(testName="SignINTest")
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() throws Exception {
		log.info("Starting Sign In test");
		SignInPage().signIntoApplication();
	}
}
