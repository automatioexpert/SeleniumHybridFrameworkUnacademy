package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	WebDriver driver = null;

	public LoginPage(WebDriver driver) 
	{
		this.driver = driver;
	}

	By user = By.name("username");

	By pass = By.name("password");

	By loginButton = By.xpath("//button[contains(@class,'button')]");

	public DashboardPage loginToApplication(String uname, String password) 
	{
		driver.findElement(user).sendKeys(uname);
		driver.findElement(pass).sendKeys(password);
		driver.findElement(loginButton).click();
		
		return new DashboardPage();
		
	}

}
