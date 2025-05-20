package listener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import util.ExtentManager;
import util.ExtentTestManager;

public class TestListener implements ITestListener {

	@Override
	public void onStart(ITestContext context) {
		ExtentManager.getInstance();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		ExtentTestManager.startTest(methodname);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.getTest().pass("Test is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentTestManager.getTest().fail("Test is failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentTestManager.getTest().skip("Test case is skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentTestManager.flushreport();
	}

}
