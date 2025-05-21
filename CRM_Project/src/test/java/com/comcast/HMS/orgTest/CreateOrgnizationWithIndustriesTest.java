package com.comcast.HMS.orgTest;

import java.io.FileInputStream;
import java.time.Duration;
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
import org.openqa.selenium.support.ui.Select;

import com.comcast.HMS.generic.fileUtility.ExcelUtility;
import com.comcast.HMS.generic.fileUtility.FileUtility;
import com.comcast.HMS.generic.webdriverUtility.JavaUtility;

public class CreateOrgnizationWithIndustriesTest 
{
	public static void main(String[] args) throws Throwable 
	{
		// create object
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		
		String browserName = fLib.getDataFromPropertiesFile("browser");
		String urlName = fLib.getDataFromPropertiesFile("url");
		String userName = fLib.getDataFromPropertiesFile("username");
		String password = fLib.getDataFromPropertiesFile("password");
		
		// generate the random number 
		Random random = new Random();
		int randomInt = random.nextInt();
		
		// read test script data from excel file
	    FileInputStream fis1 = new FileInputStream("./data/vtigerTestScript.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		String orgName = wb.getSheet("orgNameModule").getRow(4).getCell(2).toString() + randomInt;
		String induestry = wb.getSheet("orgNameModule").getRow(4).getCell(3).getStringCellValue();
		String type = wb.getSheet("orgNameModule").getRow(4).getCell(4).getStringCellValue();
	
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(urlName);
		driver.manage().window().maximize();
		
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		// step2: Navigate to Organization module
		driver.findElement(By.linkText("Organizations")).click();
		
		// step 3: click on "create Organization" Button.
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		// enter all the details and create new organization 
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		WebElement wbsele1 = driver.findElement(By.name("industry"));
		Select sel1 = new Select(wbsele1);
		sel1.selectByVisibleText(induestry);
		
		WebElement wbsele2 = driver.findElement(By.name("accounttype"));
		Select sel2 = new Select(wbsele2);
		sel2.selectByVisibleText(type);
		
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		// Verify the industry and Type info:
		String actIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
		System.out.println("Act:"+actIndustries+" exp:"+induestry);
		if(actIndustries.equals(induestry))
		{
			System.out.println(induestry + ":information is verified and Pass");
			
		}
		else
		{
			System.out.println(induestry + ":information is not verified and fail");
		}
		
		String actType = driver.findElement(By.id("dtlview_Type")).getText();
		System.out.println("Act:"+actType+" Exp:"+type);
		if(actType.equals(type))
		{
			System.out.println(type + " :information is verified and Pass");
		}
		else
		{
			System.out.println(type + " :information is not verified and fail");
		}
	
		// step 5: logout
		// Actions act = new Actions(driver);
		// act.moveToElement(driver.findElement(By.xpath("//img[contains(@src,'themes/softed/images/user.PNG')]")));
		// driver.findElement(By.linkText("Sign Out")).click();
		
		  driver.quit();
	}

}
