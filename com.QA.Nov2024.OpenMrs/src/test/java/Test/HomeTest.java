package Test;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Base.BaseTest;
import config.ConfigReader;
import page.HomePage;
import page.LoginPage;
import util.RedaDataFromExcel;

//Hometest Class
public class HomeTest extends BaseTest {
	WebDriver driver;
	LoginPage login;
	HomePage home;
	SoftAssert soft;
	String datapath = System.getProperty("user.dir") + "/TestData/testdatafile.xlsx";
	String getTitle = "HomePageTitle";

	@Parameters("browser")
	@BeforeMethod
	public void prerequisite(@Optional("chrome") String browser) {
		initialzedriver(browser);
		driver = getDriver();
		String url = ConfigReader.getProperty("url");
		geturl(url);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		home = new HomePage(driver);
		login = new LoginPage(driver);
		login.loginToapp(ConfigReader.getProperty("loginid"), ConfigReader.getProperty("passward"));
		soft = new SoftAssert();
	}

	@DataProvider
	public Object[][] getTiledata() {
		Object[][] obj = RedaDataFromExcel.readData(datapath, getTitle);
		return obj;
	}

	@Test(dataProvider = "getTiledata", groups = { "sanity" })
	public void test_verifyTitle(String title, String url) throws InterruptedException {
		String actualTitle = home.getPageTile();
		String expectedTitle = title;
		System.out.println("Expected Title : " + expectedTitle);
		System.out.println("Expected url : " + url);
		System.out.println(actualTitle);
		Assert.assertEquals(expectedTitle, actualTitle);
		Thread.sleep(10000);
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
