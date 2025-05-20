package Test;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Base.BaseTest;
import config.ConfigReader;
import listener.TestListener;
import page.LoginPage;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
	WebDriver driver;
	LoginPage login;
	SoftAssert soft;

	@Parameters("browser")
	@BeforeMethod
	public void prerequisite(@Optional("chrome") String browser) {
		initialzedriver(browser);
		driver = getDriver();
		String url = ConfigReader.getProperty("url");
		geturl(url);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		login = new LoginPage(driver);
		soft = new SoftAssert();

	}

	@Test(priority = 1, groups = { "sanity" }, enabled = true)
	public void test_IsusernameDisplayed() throws InterruptedException {
		boolean username = login.checkUsernameTodisplay();
		Thread.sleep(5000);
		Assert.assertTrue(username); // User name should be displayed
		System.out.println("User name displayed is : " + username);

	}

	@Test(priority = 3, groups = { "regression" }, enabled = true)
	public void test_InvalidLogin() throws InterruptedException {
		login.loginToapp("qwerty", "qwerty123");
		// String expectedUrl = ConfigReader.getProperty("url");
		String expectedUrl = "https://o3.openmrs.org/openmrs/spa/login";
		String actualUrl = driver.getCurrentUrl();
		soft.assertEquals(expectedUrl, actualUrl);
		System.out.println(actualUrl);
		boolean username = login.checkUsernameTodisplay();
		Assert.assertTrue(username);
		Thread.sleep(5000);
		System.out.println(username);
		soft.assertAll("Soft Assert Executed");
	}

	@Test(priority = 2, dependsOnMethods = { "test_IsusernameDisplayed" }, groups = { "sanity" }, enabled = true)
	public void test_LoginToApplication() throws InterruptedException {
		login.loginToapp(ConfigReader.getProperty("loginid"), ConfigReader.getProperty("passward"));
		Assert.fail("Test case is failed");
		Thread.sleep(10000);
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
