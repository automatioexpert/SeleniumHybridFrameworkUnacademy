package baseClass;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import dataProvider.DataProviderFactory;
import factory.BrowserFactory;

public class BaseClass {

	public WebDriver driver;
	
	
	public WebDriver getDriver()
	{
		return driver;
	}
	
	
	@BeforeClass
	public WebDriver setup()
	{
		System.out.println("LOG:INFO- Browser is getting ready");
		
		driver= BrowserFactory.startBrowser(DataProviderFactory.getConfig().getProperty("browser"),
				DataProviderFactory.getConfig().getProperty("url"));
		
		System.out.println("LOG:INFO- Browser is ready");
		
		return driver;
	}
	
	@AfterClass
	public void tearDown()
	{	
	
		driver.quit();
		
		System.out.println("LOG:INFO- Browser closed");
		
	}
	
	
}
