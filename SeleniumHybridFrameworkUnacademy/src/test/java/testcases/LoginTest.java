package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import dataProvider.CustomDataProvider;
import pages.LoginPage;

public class LoginTest extends BaseClass
{

	public WebDriver driver;
	
	@BeforeMethod
	public void setupDriver()
	{
		driver=getDriver();
	}
	
	@Test(dataProvider = "login",dataProviderClass = CustomDataProvider.class)
	public void loginToApplication(String uname,String pass)
	{
		LoginPage login=PageFactory.initElements(driver, LoginPage.class);
		
		login.loginToApplication(uname,pass);
		
		Assert.assertTrue(driver.getCurrentUrl().contains("viewEmployeeList"),"Url mismatched - Login failed");
		
	}
	
}
