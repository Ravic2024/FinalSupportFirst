package com.cio.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cio.base.TestBase;
import com.cio.utilities.TestUtils;

public class SupportFirst extends TestBase {

	@Test(dataProviderClass = TestUtils.class, dataProvider = "dp")
	public void login(String username, String password) {
		try {
			test.log(Status.PASS, "SupportFirst Application Launched Successfully");
			waitForElement("UserName_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("UserName_XPATH"))));
			test.log(Status.PASS, "Username Textbox Field is Present");
			waitForElement("UserName_XPATH");
			Type("UserName_XPATH", username);
			waitForElement("Password_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Password_XPATH"))));
			test.log(Status.PASS, "Password Textbox Field is Present");
			waitForElement("Password_XPATH");
			Type("Password_XPATH", password);
			waitForElement("btnLogin_XPATH");
			HighlightWebElement("btnLogin_XPATH");
			waitForElement("btnLogin_XPATH");
			Click("btnLogin_XPATH");
		} catch (Throwable t) {
			// TODO Auto-generated catch block
			System.out.println("Catch Block Executed");
			throw (t);

		}
	}

}
