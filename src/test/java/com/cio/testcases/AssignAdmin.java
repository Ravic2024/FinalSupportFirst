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

public class AssignAdmin extends TestBase {

	@Test(dataProviderClass = TestUtils.class, dataProvider = "dp")
	public void AdminAssign(String Subject, String department, String location, String Team, String tickettype,
			String Admcat, String Description, String AssignRole, String AssignUser) throws Throwable {
		try {
			test.log(Status.PASS, "Create Ticket Page Displayed Successfully");
			waitForElement("Createticketicon_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Createticketicon_XPATH"))));
			waitForElement("Createticketicon_XPATH");
			HighlightWebElement("Createticketicon_XPATH");
			Click("Createticketicon_XPATH");

			// SubjectTextField
			test.log(Status.PASS, "Subject Textbox Field Displayed Successfully");
			waitForElement("Subjectfield_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Subjectfield_XPATH"))));
			waitForElement("Subjectfield_XPATH");
			Type("Subjectfield_XPATH", Subject);
			test.log(Status.PASS, "Subject Provided Successfully");

		} catch (Throwable t) {
			// TODO Auto-generated catch block
			throw (t);

		}

		try {
			// DepartmentDropdown
			test.log(Status.PASS, "Department Dropdown Displayed Successfully");
			waitForElement("Departmentdropdown_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Departmentdropdown_XPATH"))));
			waitForElement("Departmentdropdown_XPATH");
			Click("Departmentdropdown_XPATH");
			searchBoxDropDown("Searchbox_XPATH", department);

			// for Department Dropdown Fail

			for (int i = 1; i <= 3; i++) {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				WebElement selectedtext = driver.findElement(By.xpath(OR.getProperty("Departmentdropdown_XPATH")));
				String sselect = selectedtext.getText();
				if (sselect.equalsIgnoreCase(department) != true) {
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

			// TeamDropdown
			test.log(Status.PASS, "Team Dropdown Displayed Successfully");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Teamdropdown_XPATH"))));
			waitForElement("Teamdropdown_XPATH");
			HighlightWebElement("Teamdropdown_XPATH");
			waitForElement("Teamdropdown_XPATH");
			Click("Teamdropdown_XPATH");
			HighlightWebElement("Searchbox_XPATH");
			searchBoxDropDown("Searchbox_XPATH", Team);

			// Admin-->Custom Field(Ticket Type(Dropdown))
			test.log(Status.PASS, "Ticket Type Dropdown Displayed Successfully");
			waitForElement("Tickettypedropdown_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Tickettypedropdown_XPATH"))));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			HighlightWebElement("Tickettypedropdown_XPATH");
			WebElement tic = driver.findElement(By.xpath(OR.getProperty("Tickettypedropdown_XPATH")));
			Actions action = new Actions(driver);
			action.moveToElement(tic).perform();
			Click("Tickettypedropdown_XPATH");
			searchBoxDropDown("Searchbox_XPATH", tickettype);

			// Admin --> Custom Field(Admin Category(Dropdown))
			test.log(Status.PASS, "Admin Category Dropdown Displayed Successfully");
			waitForElement("Admincategorydropdown_XPATH");
			HighlightWebElement("Admincategorydropdown_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Admincategorydropdown_XPATH"))));
			WebElement cat = driver.findElement(By.xpath(OR.getProperty("Admincategorydropdown_XPATH")));
			Actions action1 = new Actions(driver);
			action1.moveToElement(cat).perform();
			Click("Admincategorydropdown_XPATH");
			searchBoxDropDown("Searchbox_XPATH", Admcat);

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

			waitForElement("Popup_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Popup_XPATH"))));
			waitForElement("Popup_XPATH");
			WebElement Success = driver.findElement(By.xpath(OR.getProperty("Popup_XPATH")));
			Actions action2 = new Actions(driver);
			action2.moveToElement(Success).perform();
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

				// To Get Requestor Name
				waitForElement("TICRequestor_XPATH");
				Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TICRequestor_XPATH"))));
				HighlightWebElement("TICRequestor_XPATH");

				// To Get Text From Disabled Field
				String TicReq = driver.findElement(By.xpath(OR.getProperty("TICRequestor_XPATH")))
						.getAttribute("value");
				test.log(Status.PASS, "Ticket Requestor is  " + TicReq);

			} catch (Throwable t) {
				// TODO Auto-generated catch block
				throw (t);
			}

			// Assign Button
			waitForElement("Assignbtn_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Assignbtn_XPATH"))));
			HighlightWebElement("Assignbtn_XPATH");
			Click("Assignbtn_XPATH");

			// TO Select Role
			waitForElement("AssignRoleDDown_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("AssignRoleDDown_XPATH"))));
			HighlightWebElement("AssignRoleDDown_XPATH");
			selectDropdownWithoutSearch("AssignRoleDDown_XPATH", AssignRole);

			// To Select User
			waitForElement("AssignUserDDown_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("AssignUserDDown_XPATH"))));
			waitForElement("AssignUserDDown_XPATH");
			HighlightWebElement("AssignUserDDown_XPATH");
			Click("AssignUserDDown_XPATH");
			selectEmailAddress("Searchbox_XPATH", AssignUser);

			// Click Assign Button's Pop Assign Button
			waitForElement("AssignPopbtn_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("AssignPopbtn_XPATH"))));
			HighlightWebElement("AssignPopbtn_XPATH");
			Click("AssignPopbtn_XPATH");

			// To Get Ticket Status
			waitForElement("TicketViewStar_XPATH");
			waitForElement("TICStatus_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TICStatus_XPATH"))));
			String TicStatus = driver.findElement(By.xpath(OR.getProperty("TICStatus_XPATH"))).getAttribute("value");
			System.out.println(TicStatus);
			test.log(Status.PASS, "Ticket Status is  " + TicStatus);

			// To Get Assignee Name
			waitForElement("TICAssignee_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TICAssignee_XPATH"))));
			HighlightWebElement("TICAssignee_XPATH");

			// To Get Text From Disabled Field
			String TicAssignee = driver.findElement(By.xpath(OR.getProperty("TICAssignee_XPATH")))
					.getAttribute("value");
			test.log(Status.PASS, "Ticket Assigned to   " + TicAssignee);

		} catch (Throwable t) {
			// TODO Auto-generated catch block
			throw (t);
		}
	}

}
