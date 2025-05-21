package com.comcast.HMS.orgTest;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.HMS.Baseclass.BaseClasss;
import com.HMS.ObjectRepositoryUtility.CreatingNewOrganizationPage;
import com.HMS.ObjectRepositoryUtility.HomePage;
import com.HMS.ObjectRepositoryUtility.OrganizationInformationPage;
import com.HMS.ObjectRepositoryUtility.OrganizationsPage;
import com.aventstack.extentreports.Status;
import com.comcast.HMS.generic.webdriverUtility.UtilityClassObject;

/**
 * 
 * @author Vasanthkumar R 
 *
 */
@Listeners(com.comcast.HMS.ListenerUtility.ListenerImplementationClass.class)
public class CreateOrgnizationTest extends BaseClasss
{
	
		@Test(groups = "smokeTest")
		public void createOrgTest() throws Throwable
		{
			UtilityClassObject.getTest().log(Status.INFO, "Read data from Excel");
			/* step 1: read test script data from excel file */
			String orgName = eLib.getDataFromExcel("orgNameModule", 1, 2) + jLib.getRandomNumber();	
		
			/* step 2: navigate to organization module */
			UtilityClassObject.getTest().log(Status.INFO, "Navigate to Organization page");
			HomePage hp = new HomePage(driver);
			hp.getOrgLink().click();
			
			/* step 3: click on "create organization" button */
			UtilityClassObject.getTest().log(Status.INFO, "Navigate to create organization page");
			OrganizationsPage cnp = new OrganizationsPage(driver);
			cnp.getCreateNewOrgButton().click();
			
			/* step 4: enter all the details and create new organization */
			UtilityClassObject.getTest().log(Status.INFO, "Create a new Organization");
			CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
			cnop.createOrg(orgName);
			UtilityClassObject.getTest().log(Status.INFO, orgName +"====>Create a new Organization");
			
			
			/* verify Header message expected result */
			OrganizationInformationPage oip = new OrganizationInformationPage(driver);
			String actOrgName = oip.getHeaderMeg().getText();
			if(actOrgName.contains(orgName))
			{
				System.out.println("name is verified and pass: "+orgName);
			}
			else
			{
				System.out.println("name is not verified and fail: "+orgName);
		
			}
	}
		@Test(groups = "regressionTest")
		public void CreateOrgnizationWithPhoneNumberTest(Object PhoneNumber) throws Throwable
		{
			/* step 1: read test script data from excel file */
			String orgName = eLib.getDataFromExcel("orgNameModule", 7, 2) + jLib.getRandomNumber();
			String phoneNumber = eLib.getDataFromExcel("orgNameModule", 7, 3);
			
			/* Step 2: navigate to organization module */
			HomePage hp = new HomePage(driver);
			hp.getOrgLink().click();
			
			/* step 3: click on "create orgnization */ 
			OrganizationsPage cnp = new OrganizationsPage(driver);
			cnp.getCreateNewOrgButton().click();
			
			/* step 4: enter all the details and create new organization */
			CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
			cnop.getCreateOrgPhoneNumber(orgName, phoneNumber);
			
			/* verify Header phone number info expected result: */
			String actPhoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
			if(actPhoneNumber.equals(phoneNumber))
			{
				System.out.println("information is verified and pass: "+phoneNumber);
			}
			else
			{
				System.out.println("information is verified and fail: "+phoneNumber);
			}
		}
		
		@Test(groups = "regressionTest")
		public void CreateOrgnizationWithIndustriesTest() throws Throwable
		{
			/* Step 1: read test script data from excel file */
			String orgName = eLib.getDataFromExcel("orgNameModule", 4, 2)  + jLib.getRandomNumber();
			String induestry = eLib.getDataFromExcel("orgNameModule", 4,3);
			String type = eLib.getDataFromExcel("orgNameModule", 4 , 4);
			
			/* Step 2: navigate to organization module */
			HomePage hp = new HomePage(driver);
			hp.getOrgLink().click();
			
			/* step 3: click on "create organization" button */
			OrganizationsPage cnp = new OrganizationsPage(driver);
			cnp.getCreateNewOrgButton().click();
			
			/* step 4: enter all the details and create new organization */
			CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
			cnop.createOrg(orgName, induestry, type);
			
			/* verify the industries and type info */
			String actIndustries  = driver.findElement(By.id("dtlview_Industry")).getText();
			if(actIndustries.equals(induestry))
			{
				System.out.println("information is verified and pass: "+induestry);
			}
			else
			{
				System.out.println("information is not verified and fail: "+induestry);
			}
			
			String actType  = driver.findElement(By.id("dtlview_Type")).getText();
			if(actType.equals(type))
			{
				System.out.println("information is verified and pass: "+type);
			}
			else
			{
				System.out.println("information is not verified and fail: "+type);
			}
		}
}
