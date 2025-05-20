package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import Base.BaseTest;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		driver = BaseTest.getDriver();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getPageTile() {
		return driver.getTitle();
	}

}
