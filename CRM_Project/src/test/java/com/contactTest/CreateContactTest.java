package com.contactTest;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.HMS.Baseclass.BaseClasss;
import com.HMS.ObjectRepositoryUtility.ContactPage;
import com.HMS.ObjectRepositoryUtility.CreateNewContactPage;
import com.HMS.ObjectRepositoryUtility.CreatingNewOrganizationPage;
import com.HMS.ObjectRepositoryUtility.HomePage;
import com.HMS.ObjectRepositoryUtility.OrganizationsPage;

/**
 * 
 * @author Vasanthkumar R 
 *
 */
public class CreateContactTest extends BaseClasss 
{
	
	@Test(groups = "smokeTest")
	public void createContactTest() throws Throwable
	{
		/* read test script data from excel file */
		String LastName = eLib.getDataFromExcel("contactModule", 1, 2) + jLib.getRandomNumber();

		/* step2: Navigate to contact module */
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		/* Step3: click on "create contact" button */
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewOrgBtn().click();

		/* Step 4: enter all the details and create new contact */
		CreateNewContactPage ccp = new CreateNewContactPage(driver);
		ccp.getCreateNewContactTextBox(LastName);

		/* verify Header Last Name message expected result */
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (actLastName.equals(LastName)) 
		{
			System.out.println("Last Name is displayed and pass: " + LastName);
		} else 
		{
			System.out.println("Last Name is not displayed and fail: " + LastName);
		}
	}
	
	@Test(groups = "regressionTest")
	public void createContactWithSupportDateTest() throws Throwable
	{
		/* Step 1: read test script data from excel file */
		String LastName = eLib.getDataFromExcel("contactModule", 4, 2) + jLib.getRandomNumber();
		
		/* navigate to contact module */ 
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();;
		
		/* step 3: click on "create contact" button */
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewOrgBtn().click();
		
		/* step 4: enter alll the details & create new contact */
		String endDate = jLib.getSystemDateYYYYDDMM();
		String startDate = jLib.getRequiredDateYYYYDDMM(30);
		CreateNewContactPage ccp = new CreateNewContactPage(driver);
	    ccp.getCreateNewContactTextBox(LastName, endDate, startDate);
	    
		
		/* verify Header phone number info expected result: */
		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actStartDate.equals(startDate))
		{
			System.out.println("Information is verified and Pass: "+startDate);
		}
		else
		{
			System.out.println("Information is not verified and Fail: "+startDate);
		}
	}

	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() throws Throwable
	{
		/* read test script data from excel file */
		String orgName = eLib.getDataFromExcel("contactModule", 7, 2) + jLib.getRandomNumber();
		String contactLastName = eLib.getDataFromExcel("contactModule", 7, 3) + jLib.getRandomNumber();
		
		/* Step 2: navigate to organization module */
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		
		/* Step 3: click on "create organization" button */
		CreateNewContactPage cnp = new CreateNewContactPage(driver);
		cnp.getCreateNewContactButton().click();
		
		/* step 4: enter all the details & create new organization */
		CreateNewContactPage cnop = new CreateNewContactPage(driver);
		cnop.getCreateNewContactTextBox(orgName);
		
		/* verify Header orgName info expected result: */
		String actOrgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if(actOrgname.trim().equals(orgName))
		{
			System.out.println("information is created and pass: "+orgName);
		}
		else
		{
			System.out.println("information is not created and fail: "+orgName);
		}
		
		/* step 5: navigate to contact module */
		hp.getContactLink().click();
		
		/* step 6: click on "create contact button": */
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewOrgBtn().click();
		
		/* step 7: enter all the details and create new contact */
		CreateNewContactPage ccp = new CreateNewContactPage(driver);
		ccp.getCreateNewContactTextBox(contactLastName, orgName);
	}

}