package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.BaseTest;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		driver = BaseTest.getDriver();
		this.driver = driver;  //to current instancres
		PageFactory.initElements(driver, this); //this : It is refering to current instance / object of the class

	}

	@FindBy(xpath = "//label[text()='Username']")
	private WebElement usernamalbl;

	@FindBy(id = "username")
	private WebElement userid;

	@FindBy(xpath = "//button[@type='button'][text()='Continue']")
	private WebElement continuBtn;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(xpath = "//button[@type='submit'][text()='Log in']")
	private WebElement loginbtn;

	public boolean checkUsernameTodisplay() {
		return usernamalbl.isDisplayed();
	}

	public void loginToapp(String id, String passwd) {

		userid.sendKeys(id);
		continuBtn.click();
		password.sendKeys(passwd);
		loginbtn.click();

	}

	public boolean checkPasswardIsdisplayed() {
		return password.isDisplayed();
	}

}
