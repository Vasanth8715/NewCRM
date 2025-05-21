package com.HMS.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage 
{
	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver)
	{
	//	PageFactory.initElements(driver, LoginPage.class);
		this.driver=driver;
		PageFactory.initElements(driver, this); //this keyword is current object reference
	}
	
	@FindBy(name = "accountname")
	private WebElement orgName;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name = "industry")
	private WebElement industryDB;
	
	@FindBy(name = "phone")
	private WebElement createOrgPhoneNumber;
	
	@FindBy(name = "accounttype")
	private WebElement type;
	

	public WebElement getType() {
		return type;
	}

	public void setType(WebElement type) {
		this.type = type;
	}

	public WebElement getCreateOrgPhoneNumber() {
		return createOrgPhoneNumber;
	}

	public void setCreateOrgPhoneNumber(WebElement createOrgPhoneNumber) {
		this.createOrgPhoneNumber = createOrgPhoneNumber;
	}

	public WebElement getOrgNameEdit() {
		return orgName;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createOrg(String orgname)
	{
		orgName.sendKeys(orgname);
		saveBtn.click();	
	}
	
	public void createOrg(String orgname, String industry, String typee)
	{
		orgName.sendKeys(orgname);
		Select sel = new Select(industryDB);
		sel.selectByVisibleText(industry);	
		sel=new Select(type);
		sel.selectByVisibleText(typee);
		saveBtn.click();
	}

	public void getCreateOrgPhoneNumber(String orgname, String phoneNumber) 
	{
		orgName.sendKeys(orgname);
		createOrgPhoneNumber.sendKeys(phoneNumber);
		saveBtn.click();
	}
}
