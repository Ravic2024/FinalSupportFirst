package com.cio.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cio.base.TestBase;
import com.cio.utilities.TestUtils;


public class GoogleSearch extends TestBase {

	@Test(dataProviderClass = TestUtils.class, dataProvider = "dp")
	public void openGoogle(String inputText) {

		try {
			test.log(Status.PASS, "Google Application Launched Successfully");
			waitForElement("textInput_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("textInput_XPATH"))));
			test.log(Status.PASS, "Search text field present");
			waitForElement("textInput_XPATH");
			Type("textInput_XPATH", inputText);
			waitForElement("btnGoogle_XPATH");
			HighlightWebElement("btnGoogle_XPATH");
			waitForElement("btnGoogle_XPATH");
			Click("btnGoogle_XPATH");

		} catch (Throwable t) {
			// TODO Auto-generated catch block
			System.out.println("catch block executed");
			throw(t);
		}
	}

}
