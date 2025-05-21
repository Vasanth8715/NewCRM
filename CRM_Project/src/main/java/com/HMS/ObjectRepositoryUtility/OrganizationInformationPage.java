package com.HMS.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage // Rule : Create a separate java class 
{
	WebDriver driver;
	public OrganizationInformationPage(WebDriver driver)
	{
	//	PageFactory.initElements(driver, LoginPage.class);
		this.driver=driver;
		PageFactory.initElements(driver, this); //this keyword is current object reference
	}
	
	@FindBy(className = "dvHeaderText")
	private WebElement headerMeg;

	public WebElement getHeaderMeg() {
		return headerMeg;
	}
	
	
	
 
	
	
	
}
