package com.cio.testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cio.base.TestBase;
import com.cio.utilities.TestUtils;

public class TakeTicketServerNetwork extends TestBase {
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dp")
	public void ServerNetwork(String Subject, String Values, String location, String description) throws Throwable {
		
			test.log(Status.PASS, "Create Ticket Page Displayed Successfully");
			waitForElement("Createticketicon_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Createticketicon_XPATH"))));
			waitForElement("Createticketicon_XPATH");
			HighlightWebElement("Createticketicon_XPATH");
			Click("Createticketicon_XPATH");

			// Subject Text Field
			test.log(Status.PASS, "Subject Textbox Field Displayed Successfully");
			waitForElement("Subjectfield_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Subjectfield_XPATH"))));
			test.log(Status.PASS, "Subject Provided Successfully");
			waitForElement("Subjectfield_XPATH");
			Type("Subjectfield_XPATH", Subject);
			
			//Department
			waitForElement("Departmentdropdown_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Departmentdropdown_XPATH"))));
			Click("Departmentdropdown_XPATH");
			waitForElement("SearchBox_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("SearchBox_XPATH"))));
			searchBoxDropDown("SearchBox_XPATH", Values);
			
			for (int i = 1; i <= 3; i++) {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
				WebElement selectedtext = driver.findElement(By.xpath(OR.getProperty("Departmentdropdown_XPATH")));
				String sselect = selectedtext.getText();
				if (sselect.matches(Values) != true) {
					System.out.println("Loop Count is " + i);
					Click("CreateButton_XPATH");
					waitForElement("Departmentdropdown_XPATH");
					Click("Departmentdropdown_XPATH");
					Actions action = new Actions(driver);
					action.keyDown(Keys.ARROW_DOWN).perform();
					action.keyDown(Keys.ENTER).perform();
					test.log(Status.PASS, "Department Drop Down selected value is " + Values);

				} else {
					break;
				}
			}
		
			
			//Location
			waitForElement("Locationdropdown_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Locationdropdown_XPATH"))));
			WebElement loc = driver.findElement(By.xpath(OR.getProperty("Locationdropdown_XPATH")));
			// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			Click("Locationdropdown_XPATH");
			waitForElement("SearchBox_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("SearchBox_XPATH"))));
			searchBoxDropDown("SearchBox_XPATH", location);
			
			
			
			// Description
			waitForElement("Description_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Description_XPATH"))));
			Type("Description_XPATH", description);

			// Clicking on create button
			waitForElement("CreateButton_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("CreateButton_XPATH"))));
			waitForElement("CreateButton_XPATH");
			Click("CreateButton_XPATH");

			// Success pop Ticket

			waitForElement("Popup_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Popup_XPATH"))));
			waitForElement("Popup_XPATH");
			WebElement Success = driver.findElement(By.xpath(OR.getProperty("Popup_XPATH")));
			Actions act1 = new Actions(driver);
			act1.moveToElement(Success).perform();
			String Successpopup = Success.getText().toString();
			System.out.println(Successpopup);
			// HighlightWebElement("PopupBtnViewTic_XPATH");
			HighlightWebElement("PopupBtnClose_XPATH");
			Click("PopupBtnClose_XPATH");
			Thread.sleep(3000);
			String regex = "[\\s]";
			String[] arrString = Successpopup.split(regex);
			String TicketID = arrString[5];

			// Ticket Queue Icon
			test.log(Status.PASS, "Ticket Queue Page Displayed Successfully");
			waitForElement("TicketQueueIcon_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TicketQueueIcon_XPATH"))));
			HighlightWebElement("TicketQueueIcon_XPATH");
			Click("TicketQueueIcon_XPATH");
			driver.navigate().refresh();
			waitForElement("TicketQueueList_XPATH");

			try {
				List<WebElement> oList = driver.findElements(By.xpath(OR.getProperty("TicketQueueList_XPATH")));
				System.out.println(oList.size());

				for (int j = 0; j < oList.size(); j++) {
					String Card = oList.get(j).getText().toString();
					if (Card.contains(TicketID) == true) {
						System.out.println("Loop count is " + j);
						String ticketIDXPath = OR.getProperty("TicketIDNumber_XPATH") + "[" + (j + 1) + "]";
						WebElement ticId = driver.findElement(By.xpath(ticketIDXPath));
						waitForElement(ticId.toString());
						Actions ac = new Actions(driver);
						ac.click(ticId).perform();
						test.log(Status.PASS, "Clicked Card is " + Card);
						test.log(Status.PASS, "Clicked Ticket ID is " + TicketID);
						break;
					}
				}
	
				waitForElement("TicketViewStar_XPATH");
				Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TicketViewPage_XPATH"))));
				String TicketViewSub = driver.findElement(By.xpath(OR.getProperty("TicketViewPage_XPATH"))).getText();
				test.log(Status.PASS, "Ticket View Page Subject is " + TicketViewSub);
			
			} catch (Throwable t){
				throw(t);

			}
	
			//Take Button
			waitForElement("TakeButton_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TakeButton_XPATH"))));
			HighlightWebElement("TakeButton_XPATH");
			Click("TakeButton_XPATH");
			
			//Yes Button
			waitForElement("YesButton_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("YesButton_XPATH"))));
			HighlightWebElement("YesButton_XPATH");
			Click("YesButton_XPATH");
			
			waitForElement("TicketViewStar_XPATH");
			waitForElement("TICAssignee_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TICAssignee_XPATH"))));
			String Assignee = driver.findElement(By.xpath(OR.getProperty("TICAssignee_XPATH"))).getAttribute("value");
			System.out.println(Assignee);
			test.log(Status.PASS, "Assignee is " + Assignee);
			
			waitForElement("TicketViewStar_XPATH");
			waitForElement("TICStatus_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TICStatus_XPATH"))));
			String STATUS = driver.findElement(By.xpath(OR.getProperty("TICStatus_XPATH"))).getText();
			System.out.println(STATUS);
			test.log(Status.PASS, "Status is " + STATUS);	
			driver.navigate().refresh();
	
	}
}

	
		
			
		
	


