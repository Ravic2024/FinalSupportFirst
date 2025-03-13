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

public class ProcurementTake extends TestBase {
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dp")

	public void ProcureTake(String Subject, String department, String location, String modeofwork, String tickettype,
			String purchase, String Appcat, String Description) throws Throwable {
		
		// CreateTicketIcon
		test.log(Status.PASS, "Create Ticket Page Displayed Successfully");
		waitForElement("Createticketicon_XPATH");
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Createticketicon_XPATH"))));
		Click("Createticketicon_XPATH");

		// SubjectTextField
		test.log(Status.PASS, "Subject Textbox Field Displayed Successfully");
		waitForElement("Subjectfield_XPATH");
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Subjectfield_XPATH"))));
		test.log(Status.PASS, "Subject Provided Successfully");
		waitForElement("Subjectfield_XPATH");
		Type("Subjectfield_XPATH", Subject);
		try {
			// DepartmentDropdown
			test.log(Status.PASS, "Department Dropdown Displayed Successfully");
			waitForElement("Departmentdropdown_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Departmentdropdown_XPATH"))));
			Click("Departmentdropdown_XPATH");
			searchBoxDropDown("Searchbox_XPATH", department);

			// for Department Dropdown Fail

			for (int i = 1; i <= 3; i++) {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				WebElement selectedtext = driver.findElement(By.xpath(OR.getProperty("Departmentdropdown_XPATH")));
				String sselect = selectedtext.getText();
				if (sselect.contains(department) != true) {
					System.out.println("Loop Count is " + i);
					Click("btncreate_XPATH");
					Thread.sleep(3000);
					waitForElement("Departmentdropdown_XPATH");
					Click("Departmentdropdown_XPATH");
					Actions action = new Actions(driver);
					action.keyDown(Keys.ARROW_DOWN).perform();
					action.keyDown(Keys.ENTER).perform();
				} else {
					break;
				}
			}
			// LocationDropdown
			test.log(Status.PASS, "Location Dropdown Displayed Successfully");
			waitForElement("Locationdropdown_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Locationdropdown_XPATH"))));
			waitForElement("Locationdropdown_XPATH");
			Click("Locationdropdown_XPATH");
			searchBoxDropDown("Searchbox_XPATH", location);

			// ModeofWork Dropdown
			test.log(Status.PASS, "Mode of Work Dropdown Displayed Successfully");
			waitForElement("ProcureMOWDDown_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("ProcureMOWDDown_XPATH"))));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			HighlightWebElement("ProcureMOWDDown_XPATH");
			WebElement tic = driver.findElement(By.xpath(OR.getProperty("ProcureMOWDDown_XPATH")));
			Actions action = new Actions(driver);
			action.moveToElement(tic).perform();
			Click("ProcureMOWDDown_XPATH");
			searchBoxDropDown("Searchbox_XPATH", modeofwork);

			// TicketType Dropdown
			test.log(Status.PASS, " Ticket Type Dropdown Displayed Successfully");
			waitForElement("ProcureTTypeDDown_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("ProcureTTypeDDown_XPATH"))));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			//HighlightWebElement("ProcureTTypeDDown_XPATH");
			WebElement tic1 = driver.findElement(By.xpath(OR.getProperty("ProcureTTypeDDown_XPATH")));
			Actions action1 = new Actions(driver);
			action1.moveToElement(tic1).perform();
			Click("ProcureTTypeDDown_XPATH");
			searchBoxDropDown("Searchbox_XPATH", tickettype);

			// Type of Purchase Dropdown
			test.log(Status.PASS, " Type of Purchase Dropdown Displayed Successfully");
			waitForElement("Purchase_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Purchase_XPATH"))));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			HighlightWebElement("Purchase_XPATH");
			WebElement tic2 = driver.findElement(By.xpath(OR.getProperty("Purchase_XPATH")));
			Actions action2 = new Actions(driver);
			action2.moveToElement(tic2).perform();
			Click("Purchase_XPATH");
			searchBoxDropDown("Searchbox_XPATH", purchase);

			// Application Category Dropdown
			test.log(Status.PASS, " Application Category Dropdown Displayed Successfully");
			waitForElement("APPCATDDown_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("APPCATDDown_XPATH"))));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			HighlightWebElement("APPCATDDown_XPATH");
			WebElement tic3 = driver.findElement(By.xpath(OR.getProperty("APPCATDDown_XPATH")));
			Actions action3 = new Actions(driver);
			action3.moveToElement(tic3).perform();
			Click("APPCATDDown_XPATH");
			searchBoxDropDown("Searchbox_XPATH", Appcat);

			// Description TextArea
			test.log(Status.PASS, "Description Textbox Field Displayed Successfully");
			waitForElement("Description_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Description_XPATH"))));
			test.log(Status.PASS, "Description Provided Successfully");
			waitForElement("Description_XPATH");
			Type("Description_XPATH", Description);
			// CreateButton
			test.log(Status.PASS, "Ticket Created Successfully");
			waitForElement("btncreate_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("btncreate_XPATH"))));
			waitForElement("btncreate_XPATH");
			HighlightWebElement("btncreate_XPATH");
			Click("btncreate_XPATH");

			// Create Success Pop-Up
			waitForElement("Popup_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Popup_XPATH"))));
			// waitForElement("Popup_XPATH");
			WebElement Success = driver.findElement(By.xpath(OR.getProperty("Popup_XPATH")));
			Actions action4 = new Actions(driver);
			action4.moveToElement(Success).perform();
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
			waitForElement("TicketQueueList_XPATH");
			driver.navigate().refresh();
			waitForElement("TicketQueueList_XPATH");

			// To Check the the Ticket ID
			try {
				List<WebElement> oList = driver.findElements(By.xpath(OR.getProperty("TicketQueueList_XPATH")));
				System.out.println(oList.size());

				for (int i = 0; i < oList.size(); i++) {
					String strCard = oList.get(i).getText().toString();
					if (strCard.contains(TicketID) == true) {
						System.out.println("Loop count is " + i);
						String ticketIDXPath = OR.getProperty("TicketIDNumber_XPATH") + "[" + (i + 1) + "]";
						WebElement ticId = driver.findElement(By.xpath(ticketIDXPath));
						waitForElement(ticId.toString());
						Actions ac = new Actions(driver);
						ac.click(ticId).perform();
						test.log(Status.PASS, "Clicked Card is " + strCard);
						test.log(Status.PASS, "Clicked Ticket ID is " + TicketID);
						break;
					}
				}
				waitForElement("TicketViewStar_XPATH");
				Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TicketViewPage_XPATH"))));
				String TicketViewSub = driver.findElement(By.xpath(OR.getProperty("TicketViewPage_XPATH"))).getText();
				test.log(Status.PASS, "Ticket View Page Subject is " + TicketViewSub);

			} catch (Throwable t) {
				// TODO Auto-generated catch block
				throw (t);
			}
			// Click Take Button
			waitForElement("TakeButton_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TakeButton_XPATH"))));
			HighlightWebElement("TakeButton_XPATH");
			Click("TakeButton_XPATH");
			// Click Take Button's Yes Button
			waitForElement("TakePOPYesbtn_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TakePOPYesbtn_XPATH"))));
			HighlightWebElement("TakePOPYesbtn_XPATH");
			Click("TakePOPYesbtn_XPATH");

			// To Get Ticket Status
			waitForElement("TicketViewStar_XPATH");
			waitForElement("TICStatus_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TICStatus_XPATH"))));
			HighlightWebElement("TICStatus_XPATH");
			String TicStatus = (driver.findElement(By.xpath(OR.getProperty("TICStatus_XPATH")))).getText().toString();
			System.out.println(TicStatus);
			test.log(Status.PASS, "Ticket Status is  " + TicStatus);

			// To Get Assignee Name
			waitForElement("TICAssignee_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TICAssignee_XPATH"))));
			HighlightWebElement("TICAssignee_XPATH");

			// To Get Text From Disabled Field
			String TicAssignee = driver.findElement(By.xpath(OR.getProperty("TICAssignee_XPATH")))
					.getAttribute("value");
			test.log(Status.PASS, "Ticket Assignee is  " + TicAssignee);

		} catch (Throwable t) {
			// TODO Auto-generated catch block
			throw (t);
		}
	}

}
