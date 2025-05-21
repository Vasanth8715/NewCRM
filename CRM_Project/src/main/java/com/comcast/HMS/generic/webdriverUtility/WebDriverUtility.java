package com.comcast.HMS.generic.webdriverUtility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility 
{
	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void wairForElementPresent(WebDriver driver , WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void switchNewBrowserTab(WebDriver driver , String partialURL)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		
		while(it.hasNext())
		{
			String windowId = it.next();
			driver.switchTo().window(windowId);
			
			String acrUrl = driver.getCurrentUrl();
			if(acrUrl.contains(partialURL))
			{
				break;
			}
		}
	}
	
	public void switchToTabOnTitle(WebDriver driver , String partialTitle)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		
		while(it.hasNext())
		{
			String windowId = it.next();
			driver.switchTo().window(windowId);
			
			String acrUrl = driver.getTitle();
			if(acrUrl.contains(partialTitle))
			{
				break;
			}
		}
	}
	
	// IFrame Methods:
	public void switchToFrame(WebDriver driver , int index)
	{
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver , String nameId)
	{
		driver.switchTo().frame(nameId);
	}
	
	public void switchToFrame(WebDriver driver , WebElement element)
	{
		driver.switchTo().frame(element);
	}
		 
	public void switchToParentFrame(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}
	
	public void switchToDefaultContent(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	// close
	
	// Alerts methods 
	public void switchToAlertAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	public void switchToAlertAndCancel(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();		
	}
	
	public void switchToAlertAndGetText(WebDriver driver , String text)
	{
		driver.switchTo().alert().sendKeys(text);
	}
	
	public void select(WebElement element , String text)
	{
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}
	
	public void select(WebElement element , int index)
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	
	// Actions class methods.
	public void mousemoveOnElement(WebDriver driver , WebElement element) 
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).build().perform();
	}
	

	public void doubleClickMethod(WebDriver driver , WebElement element) 
	{
		Actions act = new Actions(driver);
		act.doubleClick(element).build().perform();
	}
	
	public void rightClick(WebDriver driver)
	{
		Actions act1 = new Actions(driver);
		act1.contextClick().build().perform();
	}
	
	public void dragAndDropMethod(WebDriver driver , WebElement source , WebElement destination)
	{
		Actions act2 = new Actions(driver);
		act2.dragAndDrop(source, destination).build().perform();
	}
	// close
	
	
}
