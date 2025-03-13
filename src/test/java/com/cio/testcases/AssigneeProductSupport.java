package com.cio.testcases;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cio.base.TestBase;
import com.cio.utilities.TestUtils;

public class AssigneeProductSupport extends TestBase {
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dp")
	public void ProductSupport(String Subject, String Department, String Application, String Description,
			String AssignRole, String AssignUser)  throws Throwable {
		test.log(Status.PASS, "Create Ticket Page Displayed Successfully");
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Createticketicon_XPATH"))));
		waitForElement("Createticketicon_XPATH");
		HighlightWebElement("Createticketicon_XPATH");
		Click("Createticketicon_XPATH");
		windowHandles();

		// Subject Text Field
		test.log(Status.PASS, "Subject Textbox Field Displayed Successfully");
		waitForElement("Subjectfield_XPATH");
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Subjectfield_XPATH"))));
		test.log(Status.PASS, "Subject Provided Successfully");
		waitForElement("Subjectfield_XPATH");
		Type("Subjectfield_XPATH", Subject);

		waitForElement("Departmentdropdown_XPATH");
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Departmentdropdown_XPATH"))));  
		HighlightWebElement("Departmentdropdown_XPATH");
		waitForElement("Departmentdropdown_XPATH");
		Click("Departmentdropdown_XPATH");
		searchBoxDropDown("SearchBox_XPATH", Department);

		for (int i = 1; i <= 3; i++) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			WebElement selectedtext = driver.findElement(By.xpath(OR.getProperty("Departmentdropdown_XPATH")));
			String sselect = selectedtext.getText();
			if (sselect.equalsIgnoreCase(Department) != true) {
				System.out.println("Loop Count is " + i);
				Click("CreateButton_XPATH");
				waitForElement("Departmentdropdown_XPATH");
				Click("Departmentdropdown_XPATH");
				Actions action = new Actions(driver);
				action.keyDown(Keys.ARROW_DOWN).perform();
				action.keyDown(Keys.ENTER).perform();
				test.log(Status.PASS, "Department Drop Down selected value is " + Department);

			} else {
				break;
			}
		}

		WebElement app = driver.findElement(By.xpath(OR.getProperty("App_XPATH")));
		Actions action6 = new Actions(driver);
		action6.moveToElement(app).perform();
		Click("App_XPATH");
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("App_XPATH"))));
		searchBoxDropDown("App_XPATH", Application);

		waitForElement("Description_XPATH");
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Description_XPATH"))));
		waitForElement("Description_XPATH");
		Type("Description_XPATH", Description);

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

		try {
			List<WebElement> oList = driver.findElements(By.xpath(OR.getProperty("TicketQueueList_XPATH")));
			System.out.println(oList.size());

			for (int i = 0; i < oList.size(); i++) {
				String Card = oList.get(i).getText().toString();
				if (Card.contains(TicketID) == true) {
					System.out.println("Loop count is " + i);
					String ticketIDXPath = OR.getProperty("TicketIDNumber_XPATH") + "[" + (i + 1) + "]";
					WebElement ticId = driver.findElement(By.xpath(ticketIDXPath));
					waitForElement(ticId.toString());
					Actions ac = new Actions(driver);
					ac.click(ticId).perform(); 
					test.log(Status.PASS, "Clicked Card is " + Card);
					test.log(Status.PASS, "Clicked Ticket ID is " + TicketID);
					break;
				}
			}
			Thread.sleep(3000);
			waitForElement("TicketViewStar_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TicketViewPage_XPATH"))));
			String TicketViewSub = driver.findElement(By.xpath(OR.getProperty("TicketViewPage_XPATH"))).getText();
			test.log(Status.PASS, "Ticket View Page Subject is " + TicketViewSub);

			waitForElement("Assignebutton_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Assignebutton_XPATH"))));
			HighlightWebElement("Assignebutton_XPATH");
			Click("Assignebutton_XPATH");

			// TO Select Role
			waitForElement("AssignRoledown_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("AssignRoledown_XPATH"))));
			HighlightWebElement("AssignRoledown_XPATH");
		    Click("AssignRoleDDown_XPATH");
			selectDropdownWithoutSearch("AssignRoledown_XPATH", AssignRole);

			// To Select User
			// waitForElement("AssignUserDDown_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("AssignUserDDown_XPATH"))));
			waitForElement("AssignUserDDown_XPATH");
			HighlightWebElement("AssignUserDDown_XPATH");
			Click("AssignUserDDown_XPATH");
			selectEmailAddress("Searchbox_XPATH", AssignUser);

			// Click Assign Button's Pop Assign Button
			waitForElement("Assignpopupbtn_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Assignpopupbtn_XPATH"))));
			HighlightWebElement("Assignpopupbtn_XPATH");
			Click("Assignpopupbtn_XPATH");

			// To Get Ticket Status
			waitForElement("TicketViewStar_XPATH");
			waitForElement("TICStatus_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TICStatus_XPATH"))));
			String TicStatus = driver.findElement(By.xpath(OR.getProperty("TICStatus_XPATH"))).getText();
			System.out.println(TicStatus);
			test.log(Status.PASS, "Ticket Status is  " + TicStatus);

			// To Get Assignee Name
			waitForElement("TICAssignee_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TICAssignee_XPATH"))));
			HighlightWebElement("TICAssignee_XPATH");

			// To Get Text From Disabled Field
			String TicAssignee = driver.findElement(By.xpath(OR.getProperty("TICAssignee_XPATH"))).getAttribute("value");
			test.log(Status.PASS, "Ticket Assigned to   " + TicAssignee);
		
		} catch (Throwable t) {
			// TODO Auto-generated catch block
			throw (t);

		}

	}
	   
	}



