package util;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.BaseTest;

public class Utilities {
	private static WebDriverWait wait;
	private static WebDriver driver;

	public static WebDriverWait explicitwait(int sec) {
		driver = BaseTest.getDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		return wait;
	}

	public static void waitforelement(WebElement wb, int sec) {
		explicitwait(sec).until(ExpectedConditions.visibilityOf(wb));
	}

	public static void waitforelementtobeclickable(WebElement wb, int sec) {
		explicitwait(sec).until(ExpectedConditions.elementToBeClickable(wb));
	}

	public static String getCurrentDate(String pattern) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		String timestamp = now.format(formatter);
		return timestamp;
	}

	public static void captureScreenshot(String methodname, String pattern) {
		driver = BaseTest.getDriver();
		String path = System.getProperty("user.dir") + "//screenshot" + methodname + getCurrentDate(pattern) + ".png";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(path);
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
