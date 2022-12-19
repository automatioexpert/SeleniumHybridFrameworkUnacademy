package factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import dataProvider.DataProviderFactory;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory 
{
	
	public static ThreadLocal<WebDriver> tl=new ThreadLocal<>();
	

	public static WebDriver startBrowser(String browserName,String url)
	{

		WebDriver driver = null;
		
		if(browserName.equalsIgnoreCase("Chrome") || browserName.equalsIgnoreCase("GC") || browserName.equalsIgnoreCase("Google Chrome"))
		{
			WebDriverManager.chromedriver().setup();
		
			 driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("Firefox"))
		{
			 WebDriverManager.firefoxdriver().setup();
				
			 driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("Edge"))
		{
			WebDriverManager.edgedriver().setup(); 
			
			 driver=new EdgeDriver();
		}
		else
		{
			System.out.println("Sorry we support only Chrome, FF, Edge Browser");
		}
		
		driver.manage().window().maximize();

		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
		//String timeout=DataProviderFactory.getConfig().getProperty("pageLoadTimeOut");
		
		//int time=Integer.parseInt(timeout);
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(DataProviderFactory.getConfig().getProperty("pageLoadTimeOut"))));
		
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(time));
		
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Integer.parseInt(DataProviderFactory.getConfig().getProperty("scriptTimeOut"))));
		
		driver.get(url);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(DataProviderFactory.getConfig().getProperty("implicitWait"))));
	
		tl.set(driver);
		
		return driver;
		
	}

	public static WebDriver getDriver() {
			return tl.get();
	}
	

}
