package com.contactTest;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.HMS.generic.fileUtility.ExcelUtility;
import com.comcast.HMS.generic.fileUtility.FileUtility;
import com.comcast.HMS.generic.webdriverUtility.JavaUtility;

public class CreateContactWithSupportDateTest 
{
	public static void main(String[] args) throws Throwable 
	{
		// create object
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
				
		// read common data from property file
		FileInputStream fis = new FileInputStream("./data/vTigercommonData.property");
		Properties p = new Properties();
		p.load(fis);
		
		String browserName = fLib.getDataFromPropertiesFile("browser");
		String urlName = fLib.getDataFromPropertiesFile("url");
		String userName = fLib.getDataFromPropertiesFile("username");
		String password = fLib.getDataFromPropertiesFile("password");
		
		// generate the random number 
		Random random = new Random();
		int randomInt = random.nextInt(1000);
		
		// read test script data from excel file
		FileInputStream fis1 = new FileInputStream("./data/vtigerTestScript.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		String lastName = wb.getSheet("contactModule").getRow(4).getCell(2).toString() + jLib.getRandomNumber();
		
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(urlName);
		driver.manage().window().maximize();
		
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		// step2: Navigate to Organization module
		driver.findElement(By.linkText("Contacts")).click();
		
		// step 3: click on "create Organization" Button.
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		// enter all the details and create new organization
		
		String startDate = jLib.getSystemDateYYYYDDMM();
		String endDate = jLib.getRequiredDateYYYYDDMM(30);
		
		
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		// verify Support Start Date message expected result
		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actStartDate.contains(startDate))
		{
			System.out.println("Start Date is displayed and pass: "+startDate);
		}
		else
		{
			System.out.println("Start Date is not displayed and fail: "+startDate);
		}
		
		// verify Support End Date message expected result
		String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actEndDate.contains(endDate))
		{
			System.out.println("End Date is displayed and pass: "+endDate);
		}
		else
		{
			System.out.println("End Date is not displayed and fail: "+endDate);
		}
		

				
		// step 5: logout
		// Actions act = new Actions(driver);
		// act.moveToElement(driver.findElement(By.xpath("//img[contains(@src,'themes/softed/images/user.PNG')]")));
		// driver.findElement(By.linkText("Sign Out")).click();
		
		driver.quit();
	}
}
