package com.cio.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cio.base.TestBase;

public class Amazon extends TestBase  {
	
	@Test
	public void AmazonHeader() {
		
		try {
			Thread.sleep(4000);
			
			List<WebElement> oList = driver.findElements(By.xpath("//*[starts-with(@id,'CardInstance')]/div[1]/h2"));
			System.out.println(oList.size());
			for(int i=1;i<oList.size();i++) {
				System.out.println(oList.get(i).getText().toString());
				test.log(Status.PASS, "Amazon Header Value "+oList.get(i).getText().toString());
			}
			
			
			for(WebElement webElement:oList) {
				String li = webElement.getText();
				test.log(Status.PASS, "Amazon Header Value "+li);
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
