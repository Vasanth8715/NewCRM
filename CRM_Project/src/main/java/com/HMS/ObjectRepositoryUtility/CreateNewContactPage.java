package com.HMS.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPage 
{
	WebDriver driver;
	public CreateNewContactPage(WebDriver driver)
	{
	//	PageFactory.initElements(driver, LoginPage.class);
		this.driver=driver;
		PageFactory.initElements(driver, this); //this keyword is current object reference
	}
	
	@FindBy(name = "lastname")
	private WebElement createNewContactTextBox;
	
	@FindBy(xpath ="//input[@title='Save [Alt+S]']") 
	private WebElement saveButton;
	
	@FindBy(xpath ="//img[@alt='Create Contact...']")
	private WebElement createNewContactButton;
	
	public WebElement getCreateNewContactButton() {
		return createNewContactButton;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getCreateNewContactTextBox() {
		return createNewContactTextBox;
	}

	public void getCreateNewContactTextBox(String lastName) 
	{
		createNewContactTextBox.sendKeys(lastName);
		saveButton.click();
	}

	public void getCreateNewContactTextBox(String lastName, String endDate, String startDate) 
	{
		createNewContactTextBox.sendKeys(lastName);
		createNewContactTextBox.sendKeys(endDate);
		createNewContactTextBox.sendKeys(startDate);
		saveButton.click();
		
	}

	public void getCreateNewContactTextBox(String contactLastName, String orgName) 
	{
		createNewContactTextBox.sendKeys(contactLastName);
		createNewContactTextBox.sendKeys(orgName);
	}
}
