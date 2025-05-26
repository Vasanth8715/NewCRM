package com.HMS.Baseclass;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.HMS.ObjectRepositoryUtility.HomePage;
import com.HMS.ObjectRepositoryUtility.LoginPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.HMS.generic.databaseUtility.DataBaseUtility;
import com.comcast.HMS.generic.fileUtility.ExcelUtility;
import com.comcast.HMS.generic.fileUtility.FileUtility;
import com.comcast.HMS.generic.webdriverUtility.JavaUtility;
import com.comcast.HMS.generic.webdriverUtility.UtilityClassObject;

public class BaseClasss 
{
	// create object
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public DataBaseUtility dbLib = new DataBaseUtility();
	public FileUtility fLib = new FileUtility();
	public WebDriver driver = null;
	
	public static WebDriver sDriver = null;
	
	@BeforeSuite(groups = {"smokeTest", "regressionTest"})
	public void configBS() throws Throwable
	{
		System.out.println("===connect to DB , Report configaration ===");
		dbLib.getDbConnection();
	}
	
	// achive cross browser testing scripts:
	//	@Parameters ("BROWSER")
	//	@BeforeClass(groups = {"smokeTest", "regressionTest"})
	//	public void configBC(String browser) throws Throwable
	//	{
	//		System.out.println("==Launch the browser===");
	//		String BROWSER = browser;
	//		fLib.getDataFromPropertiesFile("browser");

	@BeforeClass(groups = {"smokeTest", "regressionTest"})
	public void configBC() throws Throwable
	{
		System.out.println("==Launch the browser===");
		// read data from property file
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		
		// read data from property file and command line
		// String BROWSER = System.setProperty("browser", fLib.getDataFromPropertiesFile("browser"));

		
		if(BROWSER.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(BROWSER.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(BROWSER.equals("edge"))
		{
			driver = new EdgeDriver();
		}
		else
		{
			driver = new ChromeDriver();
		}
		sDriver = driver;
		driver.manage().window().maximize();
		UtilityClassObject.setDriver(driver);
		
	}
	
	@BeforeMethod(groups = {"smokeTest", "regressionTest"})
	public void configBM() throws Throwable
	{
		System.out.println("Login");
		// read data from property file
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");
		
	    // read data from property file and command line
		// String URL = System.setProperty("url", fLib.getDataFromPropertiesFile("url"));
		// String USERNAME = System.setProperty("url", fLib.getDataFromPropertiesFile("username"));
		// String PASSWORD = System.setProperty("url", fLib.getDataFromPropertiesFile("password"));
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);
	}
	
	@AfterMethod(groups = {"smokeTest", "regressionTest"})
	public void configAM()
	{
		System.out.println("Logout");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}
	
	@AfterClass(groups = {"smokeTest", "regressionTest"})
	public void configAC()
	{
		System.out.println("==close the browser===");
		driver.quit();
	}
	
	@AfterSuite(groups = {"smokeTest", "regressionTest"})
	public void configAS() throws Throwable
	{
		System.out.println("===close the DB , Report backUp ===");
		dbLib.closeDbConnection();
	}
}
