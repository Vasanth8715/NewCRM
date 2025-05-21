package com.HMS.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.comcast.HMS.generic.webdriverUtility.WebDriverUtility;

/**
 * 
 * @author Vasanthkumar R
 * 
 * Contains Login page elements & business library like LoginPage()
 * 
 */
public class LoginPage extends WebDriverUtility
{
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
	//	PageFactory.initElements(driver, LoginPage.class);
		this.driver=driver;
		PageFactory.initElements(driver, this); //this keyword is current object reference
	}
	
	@FindBy(name = "user_name")
	private WebElement userNameEdit;
	
	@FindBy(name = "user_password")
	private WebElement passwordEdit;
	
	@FindBy(id = "submitButton")
	private WebElement LoginBtn;

	// Rule 4: Object Encapsulation
	public WebElement getUserNameEdit() {
		return userNameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginBtn() {
		return LoginBtn;
	}
	
	/**
	 * login to application based username, password, url arguments.
	 * @param url
	 * @param username
	 * @param password
	 */
	// Rule 5: provide action  and this is called business library 
	@Test
	public void loginToApp(String url, String username, String password)
	{
		waitForPageToLoad(driver);
		driver.get(url);
		userNameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		LoginBtn.click();
		
	}
	
	
}
