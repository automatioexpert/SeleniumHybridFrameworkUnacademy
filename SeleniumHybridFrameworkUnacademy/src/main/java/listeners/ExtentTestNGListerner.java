package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import factory.BrowserFactory;
import utility.Utility;

public class ExtentTestNGListerner implements ITestListener 
{
	private static ExtentReports extent = ExtentManager.createInstance();
		
	ThreadLocal<ExtentTest> parentTest=new ThreadLocal<>();
	

	public synchronized void onFinish(ITestContext context) {
		
		System.out.println("LOG:INFO- Reports Getting Ready");
		
		extent.flush();
		
		System.out.println("LOG:INFO-Reports Generated");
		
	}
	

	public synchronized void onTestStart(ITestResult result) 
	{
		
		System.out.println("LOG:INFO- Starting Test "+result.getMethod().getMethodName());
		
		ExtentTest child= extent.createTest(result.getMethod().getMethodName());
		
		parentTest.set(child);		
	}
	
	public synchronized void onTestSuccess(ITestResult result) {
		
		parentTest.get().pass("Test Passed");
		System.out.println("LOG:PASS- Test Passed "+result.getMethod().getMethodName());
		
	}

	
	public synchronized void onTestFailure(ITestResult result) 
	{
		WebDriver driver=null;
				
		 try 
		 {
			driver= BrowserFactory.getDriver();
			
		} catch (Exception e) {
			
		}			
		
		parentTest.get().fail("Test Failed "+result.getThrowable().getMessage(),
				MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.captureScreenshotsInBase64(driver)).build());
				
		//MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.captureScreenshotsInBase64(driver)).build());
		
	}

	
	public synchronized void onTestSkipped(ITestResult result) {	
	
		parentTest.get().skip("Test Skipped"+result.getThrowable().getMessage());
		System.out.println("LOG:SKIP-Test Skipped "+result.getMethod().getMethodName());
	}



}
