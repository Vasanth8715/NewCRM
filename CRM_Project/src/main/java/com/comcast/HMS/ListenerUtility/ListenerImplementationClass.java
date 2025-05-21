package com.comcast.HMS.ListenerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.HMS.Baseclass.BaseClasss;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.HMS.generic.webdriverUtility.UtilityClassObject;

public class ListenerImplementationClass implements ITestListener, ISuiteListener{

	public ExtentReports report;
	public static ExtentTest test;
	
	public void onStart(ISuite suite) 
	{
		System.out.println("Report Configuration");
		
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		
		// spark report config:
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
						
		// add environment information and create test 
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER","CHROME-100");
	}


	public void onFinish(ISuite suite) 
	{
		System.out.println("Report backUp");
		
		report.flush();
	}


	public void onTestStart(ITestResult result) 
	{
		System.out.println("======== ======> "+result.getMethod().getMethodName()+">====Start=======");
		
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName() +"======> STARTED <=======");
		
	}


	public void onTestSuccess(ITestResult result) 
	{
		System.out.println("======== ======> "+result.getMethod().getMethodName()+">====End=======");
		
		test.log(Status.PASS, result.getMethod().getMethodName() +"======> COMPLETED <=======");
	}


	public void onTestFailure(ITestResult result) 
	{
		String testName = result.getMethod().getMethodName();
		TakesScreenshot t = (TakesScreenshot) UtilityClassObject.getDriver();
		String filePath = t.getScreenshotAs(OutputType.BASE64);
		
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		
		test.addScreenCaptureFromBase64String(filePath, testName + "_" +time);
		
		test.log(Status.FAIL, result.getMethod().getMethodName() +"======> FAILED <=======");
				
	}

	
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
