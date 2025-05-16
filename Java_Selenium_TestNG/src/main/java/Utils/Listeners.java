package Utils;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseTest implements ITestListener {

	ExtentTest test;
	ExtentReports extentReport = ExtentReporterHelper.getReporterObject();
	ThreadLocal<ExtentTest> thread = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		test = extentReport.createTest(result.getMethod().getMethodName());
		thread.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		thread.get().log(Status.PASS, "Test PASSED");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		thread.get().log(Status.FAIL, result.getThrowable());
		Object testInstance = result.getInstance();
		// Take Screenshot
		String filePathOfScreenshot = null;
		try {
			filePathOfScreenshot = ((BaseTest) testInstance).getScreenshot(result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		thread.get().addScreenCaptureFromPath(filePathOfScreenshot);
	}

	public void onTestSkipped(ITestResult result) {
		thread.get().log(Status.SKIP, result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
	}
}
