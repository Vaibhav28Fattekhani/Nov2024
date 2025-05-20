package Base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
	// To manage multiple threads of your webdriver
	private static ThreadLocal<WebDriver> tldriver = new ThreadLocal();

	public static void initialzedriver(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriver driver = new ChromeDriver();
			tldriver.set(driver); // set method is used to set thread for provided browser
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriver driver = new EdgeDriver();
			tldriver.set(driver);
		}

		else if (browser.equalsIgnoreCase("firefox")) {
			WebDriver driver = new FirefoxDriver();
			tldriver.set(driver);
		} else {
			throw new IllegalArgumentException("Browser not supported " + browser);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	public static WebDriver getDriver() {
		return tldriver.get();
	}

	public static void geturl(String url) {
		getDriver().get(url);
	}

}
