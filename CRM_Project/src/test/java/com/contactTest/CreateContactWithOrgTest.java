package com.contactTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.HMS.generic.fileUtility.ExcelUtility;
import com.comcast.HMS.generic.fileUtility.FileUtility;
import com.comcast.HMS.generic.webdriverUtility.JavaUtility;
import com.comcast.HMS.generic.webdriverUtility.WebDriverUtility;

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws Throwable 
	{
		// create object
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
				
		String browserName = fLib.getDataFromPropertiesFile("browser");
		String urlName = fLib.getDataFromPropertiesFile("url");
		String userName = fLib.getDataFromPropertiesFile("username");
		String password = fLib.getDataFromPropertiesFile("password");
				
		// generate the random number 
		// Random random = new Random();
		// int randomInt = random.nextInt();
		
		// read test script data from excel file
		// FileInputStream fis1 = new FileInputStream("./data/vtigerTestScript.xlsx");
		// Workbook wb = WorkbookFactory.create(fis1);
		// String orgName = wb.getSheet("contactModule").getRow(8).getCell(2).toString() + jLib.getRandomNumber();
		// String contactLastName = wb.getSheet("contactModule").getRow(8).getCell(3).getStringCellValue();
		
		// read test script data from excel file
		String orgName = eLib.getDataFromExcel("contactModule", 7, 2) + jLib.getRandomNumber();	
		String contactLastName = eLib.getDataFromExcel("contactModule", 7, 3);
		
		
		WebDriver driver = null;
		
		if(browserName.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browserName.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(browserName.equals("edge"))
		{
			driver = new EdgeDriver();
		}
		else
		{
			driver = new ChromeDriver();
		}
		
		// step1: login to application 
		wLib.waitForPageToLoad(driver);
		driver.get(urlName);
		driver.manage().window().maximize();
		
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		// step2: Navigate to Organization module
		driver.findElement(By.linkText("Organizations")).click();
		
		// step 3: click on "create Organization" Button.
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		// step 4: enter all the details and create new organization 
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		// verify Header message expected result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		try {
		wLib.switchToAlertAndGetText(driver, headerInfo);
		}
		catch(Exception e)
		{
			
		}
		if(headerInfo.contains(orgName))
		{
			System.out.println("Header Verified == pass: "+orgName);
		}
		else
		{
			System.out.println("Header Verified == fail: "+orgName);
		}
		
		// step 5: navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();
		
		// step 6: click on "create contact" Button.
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		// Step 7: enter all the details and create new organization 
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		// switch to child window
		wLib.switchNewBrowserTab(driver, "module=Accounts");
				
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		// switch to Parent window
		wLib.switchToTabOnTitle(driver, "Contacts&action");
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		// verify Header Last Name message expected result
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if(actLastName.contains(contactLastName))
		{
			System.out.println("Last Name is displayed and pass: "+contactLastName);
		}
		else
		{
			System.out.println("Last Name is not displayed and fail: "+contactLastName);
		}
		// verify Header message expected result
		String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText().trim();
	//	System.out.println("Ac:"+actOrgName+" Exp:"+orgName);
		if(actOrgName.equals(orgName))
		{
			System.out.println("Information is created == pass: "+orgName);
		}
		else
		{
			System.out.println("Information is not created == fail: "+orgName);
		}
				
			// logout Application 
		 Actions act = new Actions(driver);
		 act.moveToElement(driver.findElement(By.xpath("//img[contains(@src,'themes/softed/images/user.PNG')]"))).perform();
		 driver.findElement(By.linkText("Sign Out")).click();
		}				

}