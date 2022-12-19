package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utility.Utility;

public class ExtentManager {
	
	public static ExtentReports extent;
	
	
	public static ExtentReports getInstance()
	{
		
		if(extent==null)
		{
			extent=createInstance();
			
			return extent;
		}
		else
		{
			return extent;
		}
		
	}
	
	
	public static ExtentReports createInstance()
	{
		
		ExtentSparkReporter reporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/Automation_"+Utility.getCurrentDateAndTime()+".html");
		
		reporter.config().setDocumentTitle("Automation Report");
		
		reporter.config().setReportName("OrangeHRM Report");
		
		reporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		
		extent.attachReporter(reporter);
		
		return extent;
	}
	
	

}
