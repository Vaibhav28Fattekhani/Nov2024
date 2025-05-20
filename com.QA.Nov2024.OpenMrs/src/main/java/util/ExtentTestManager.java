package util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	private static ExtentReports extent = ExtentManager.getInstance();

	public static ExtentTest getTest() {

		return extentTest.get();
	}

	public static void startTest(String testcasename) {
		ExtentTest test = extent.createTest(testcasename);
		extentTest.set(test);
	}

	public static void flushreport() {
		extent.flush();
	}
}