package utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility 
{
	
	
	public static void clickElement(WebDriver driver,By locator)
	{
		/*
		 *   Try to add until clickable condition
		 * 
		 */
		
		try 
		{
			driver.findElement(locator).click();
			
		} catch (Exception e) 
		{
			System.out.println("WebElement Click Failed - Trying with JS  click "+e.getMessage());
			
			 JavascriptExecutor js=(JavascriptExecutor)driver;
			
			 js.executeScript("arguments[0].click()",driver.findElement(locator));
			
			try 
			{
				
			} catch (Exception e1) 
			{
				
				
				//wait(2);
				System.out.println("JS Click Failed - Trying with Actions Class click");
				 
				 new Actions(driver)
					.moveToElement(driver.findElement(locator))
					.click()
					.build()
					.perform();
			}
			
		}
		
		
		
		
	}
	
	
	
	public static WebElement highLightElement(WebDriver driver, WebElement element)
	{
	JavascriptExecutor js=(JavascriptExecutor)driver; 

	js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);

	try 
	{
	Thread.sleep(500);
	} 
	catch (InterruptedException e) {

	System.out.println(e.getMessage());
	} 

	js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element); 
	
	return element;

	}
	
	public static String captureScreenshotInBase64(WebDriver driver) {
		
		TakesScreenshot ts = (TakesScreenshot) driver;

		return ts.getScreenshotAs(OutputType.BASE64);

	}
	
	
	public static String getCurrentDateAndTime()
	{
		return new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
	}
	
	
	public static String getCurrentDateAndTimeOld()
	{
		Date date=new Date();
				
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		
		String myNewDate=dateFormat.format(date);
		
		return myNewDate;
		
	}
	
	
	public static void captureScreenshotold(WebDriver driver)
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		File src=ts.getScreenshotAs(OutputType.FILE);
		
		try 
		{
			File destination= new File(System.getProperty("user.dir")+"/Screenshots/screenshot"+System.currentTimeMillis()+".png");
			
			FileHandler.copy(src,destination);
			
		} catch (IOException e) 
		{
			System.out.println("Could not save screenshot "+e.getMessage());
		}
	}
	
	public static void captureScreenshot(WebDriver driver)
	{
		try 
		{
			
			
			FileHandler.copy(((TakesScreenshot)driver)
					.getScreenshotAs(OutputType.FILE),new File(System.getProperty("user.dir")+"/Screenshots/screenshot"
					+Utility.getCurrentDateAndTime()+".png"));
			
		} catch (IOException e) 
		{
			System.out.println("Could not save screenshot "+e.getMessage());
		}
	}
	
	public static String captureScreenshotsInBase64(WebDriver driver)
	{
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		return ts.getScreenshotAs(OutputType.BASE64);
		
	}
	
	
	
	
	
	// This method can be used whenever you need to select specific element from list
	public static void selectValueFromList(WebDriver driver,By locator,String textToMatch)
	{
		
		List<WebElement> autoSuggestions=driver.findElements(locator);
			
		for(WebElement ele:autoSuggestions)
		{
		
			if(ele.getText().contains(textToMatch))
			{
				ele.click();
				break;
			}
			
		}
		
	}
	
	// This method can be used whenever you need to get count of list
		public static int checkListCount(WebDriver driver,By locator)
		{
			List<WebElement> allElements=driver.findElements(locator);
				
			return allElements.size();
			
		}
		
		
		public static void wait(int timeInSeconds)
		{
			
			//System.out.println("Wait for "+timeInSeconds+ " seconds");
			try 
			{
				Thread.sleep(timeInSeconds*1000);
			} catch (InterruptedException e) {
				
			}
		}

		
		public static String captureTextAndHandleAlert(WebDriver driver,int timeinSeconds)
		{
			String alt_text=null;
			
			for (int i = 0; i < timeinSeconds; i++) 
			{
				
				try 
				{
					Alert alt=driver.switchTo().alert();
					alt_text =alt.getText();
					System.out.println("Alert text is "+alt.getText());
					alt.accept();
					break;
				
				}
				catch(NoAlertPresentException e)
				{
					System.out.println("Still waiting for alert to appear -- Counter value "+i);
				}
				
				wait(1);
				
				
			}
			
			return alt_text;
			
			
		}
		
		
		public static boolean waitForWebElement(WebDriver driver,By locator,int timeInSeconds)
		{
			boolean status=false;
			
			for (int i = 0; i < timeInSeconds; i++) 
			{
				try 
				{
					status= driver.findElement(locator).isDisplayed();
					
					System.out.println("Element found "+status);
					
					break;
				
				}
				catch(NoSuchElementException e)
				{
					System.out.println("Still waiting for element to appear -- Counter value "+i);
				}
				
				wait(1);
				
			}
			
			return status;
			
			
		}
		
		public static boolean waitForWebElement(WebDriver driver,By locator)
		{
			boolean status=false;
			
			for (int i = 0; i < 20; i++) 
			{
				try 
				{
					status= driver.findElement(locator).isDisplayed();
					
					System.out.println("Element found "+status);
					
					break;
				
				}
				catch(NoSuchElementException e)
				{
					System.out.println("Still waiting for element to appear -- Counter value "+i);
				}
				
				wait(1);
				
			}
			
			return status;
			
			
		}
		
		public static WebElement waitForWebElementForAction(WebDriver driver,By locator)
		{
			
			return new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(locator));
				
		}
		
		public static WebElement waitForWebElementForAction(WebDriver driver,By locator,int timeInSeconds)
		{
			
			return new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds)).until(ExpectedConditions.elementToBeClickable(locator));
				
		}
		
		
		public static void switchToFrame(WebDriver driver,WebElement ele)
		{
			
			for(int i=0;i<5;i++)
			{
				try 
				{
					driver.switchTo().frame(ele);
					break;
					
				} catch (NoSuchFrameException e) 
				{
					System.out.println("No Frame Found - Retrying - Count ");
					wait(1);
				}
			}
			
			
		}
		
		public static WebDriver switchToFrameUsingExplicitWait(WebDriver driver,WebElement ele)
		{
			
			return new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ele));
			
		}
		
		
		
		public static void switchToChildWindow(WebDriver driver,int index)
		{
		
			Set<String> allHandles=driver.getWindowHandles();
		
			// Based on Index
			// First way to create list from Set
			List<String> allChildWindow=new ArrayList<>(allHandles);
			
			
			// another way to add all values from Set to List
			//allChildWindow.addAll(allHandles);
			
			// get specific window based on index
			String childWindow=allChildWindow.get(index);
			
			// pass window handle while switching
			driver.switchTo().window(childWindow);
					
		}
		
		public static void switchToChildWindowNew(WebDriver driver,int index)
		{
			/*
			 *  1- This will get all Window Handles
			 *  2- Convert Into List
			 *  3- Get the index
			 *  4- Switch 
			 * 	 
			 * 
			 */
	
			driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(index));
							
		}
		
		public static void switchToChildWindow(WebDriver driver,String title)
		{
			
			String parentWindowHandle=driver.getWindowHandle();
			
			Set<String> allWindows=driver.getWindowHandles();
			
			Iterator<String> itr=allWindows.iterator();
			
			while(itr.hasNext())
			{
				String childWindow=itr.next();
				
				if(!childWindow.equalsIgnoreCase(parentWindowHandle))
				{
						driver.switchTo().window(childWindow);
						if(driver.getTitle().contains(title))
						{
							System.out.println("Switched to specified window");
							break;
						}
						else
						{
							driver.switchTo().window(parentWindowHandle);
						}
						
				}
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
