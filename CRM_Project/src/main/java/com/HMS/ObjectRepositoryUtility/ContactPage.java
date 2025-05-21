package com.HMS.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage 
{
	WebDriver driver;
	public ContactPage(WebDriver driver)
	{
	//	PageFactory.initElements(driver, LoginPage.class);
		this.driver=driver;
		PageFactory.initElements(driver, this); //this keyword is current object reference
	}
	
	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement createNewOrgBtn;
	
	public WebElement getCreateNewOrgBtn() {
		return createNewOrgBtn;
	}
	
	
	
	
}
