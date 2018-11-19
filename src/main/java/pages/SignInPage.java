package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/*******************************************************
 * @author yasser.khan
 * This class has declaration of all the elements and functionality related to signing in
 *******************************************************/
public class SignInPage extends PageInstances {

	@FindBy(linkText = "Your trips")
	private WebElement yourTripsLink;

	@FindBy(id = "SignIn")
	private WebElement signInButton;

	@FindBy(id = "s")
	private WebElement errorsBlock;

	@FindBy(id = "signInButton")
	private WebElement signInButtonOnAlert;

	private static Logger log = Logger.getLogger(SignInPage.class);

	public WebElement getSignInButtonOnAlert() {
		return signInButtonOnAlert;
	}

	public WebElement getErrorsBlock() {
		return errorsBlock;
	}

	public WebElement getYourTripsLink() {
		return yourTripsLink;
	}

	public WebElement getSignInButton() {
		return signInButton;
	}

	public void signIntoApplication() throws Exception {
		clickOnElement(getYourTripsLink());
		clickOnElement(getSignInButton());
		if (isAlertPresent()) {
			clickOnElement(getSignInButtonOnAlert());
			Assert.assertTrue(getErrorsBlock().getText().contains("There were errors in your submission"));
		}
		log.info("Successfully tested sign in errors scenario");
	}
}
