package webdriverfactory;

import org.openqa.selenium.WebDriver;

public abstract class Browser {

	protected abstract WebDriver startBrowser(BrowserType browser);
	protected abstract WebDriver startFireFoxBrowser();
	protected abstract WebDriver startChromeBrowser();
	private BrowserType type = null;
	public Browser(BrowserType type) {
		this.type = type;
	}

	public BrowserType getBrowsr() {
		return type;
	}

	public void setBrowser(BrowserType type) {
		this.type = type;
	}

}
