
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

public class ServerNetworktransferIT extends TestBase {
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dp")

	public void ITTransfer(String Subject, String department, String location, String Description, String TransferDept,
			String TransferLoc) throws Throwable {

		// CreateTicketIcon
		test.log(Status.PASS, "Create Ticket Page Displayed Successfully");
		waitForElement("Createticketicon_XPATH");
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Createticketicon_XPATH"))));
		Click("Createticketicon_XPATH");
		windowHandles();

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

			// Click Transfer Button
			waitForElement("Transferbtn_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Transferbtn_XPATH"))));
			HighlightWebElement("Transferbtn_XPATH");
			Click("Transferbtn_XPATH");

			// DepartmentDropdown
			test.log(Status.PASS, "Department Dropdown Displayed Successfully");
			waitForElement("TransferDept_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TransferDept_XPATH"))));
			Click("TransferDept_XPATH");
			searchBoxDropDown("Searchbox_XPATH", TransferDept);

			//				// for Department Dropdown Fail
			//
			//				for (int i = 1; i <= 3; i++) {
			//					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			//					WebElement selectedtext = driver.findElement(By.xpath(OR.getProperty("TransferDept_XPATH")));
			//					String sselect = selectedtext.getText();
			//					if (sselect.contains(department) != true) {
			//						System.out.println("Loop Count is " + i);
			//						Click("btncreate_XPATH");
			//						Thread.sleep(3000);
			//						waitForElement("Departmentdropdown_XPATH");
			//						Click("TransferDept_XPATH");
			//						Actions action = new Actions(driver);
			//						action.keyDown(Keys.ARROW_DOWN).perform();
			//						action.keyDown(Keys.ENTER).perform();
			//					} else {
			//						break;
			//					}
			//				}
			//			

			// Transfer Location Dropdown
			test.log(Status.PASS, "Location Dropdown Displayed Successfully");
			waitForElement("TransferLoc_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TransferLoc_XPATH"))));
			waitForElement("TransferLoc_XPATH");
			Click("TransferLoc_XPATH");
			searchBoxDropDown("Searchbox_XPATH", TransferLoc);

			// Click Transfer Ticket Button

			waitForElement("TransferTicBtn_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TransferTicBtn_XPATH"))));
			HighlightWebElement("TransferTicBtn_XPATH");
			Click("TransferTicBtn_XPATH");

			// To Get Ticket Status
			waitForElement("TicketViewStar_XPATH");
			waitForElement("TICStatus_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TICStatus_XPATH"))));
			HighlightWebElement("TICStatus_XPATH");
			String TicStatus = (driver.findElement(By.xpath(OR.getProperty("TICStatus_XPATH")))).getText().toString();
			System.out.println(TicStatus);
			test.log(Status.PASS, "Ticket Status is  " + TicStatus);

			// To Get Text From Disabled Field	

			waitForElement("TicketViewStar_XPATH");
			String TransferredDept = driver.findElement(By.xpath(OR.getProperty("DisabledDeptDDown")))
					.getText();
			System.out.println(TransferredDept);
			test.log(Status.PASS, "Ticket Transferred to " + TransferredDept + " Department");

			waitForElement("DisabledLocDDown");
			String TransferredLoc = driver.findElement(By.xpath(OR.getProperty("DisabledLocDDown")))
					.getText();

			test.log(Status.PASS, "Ticket Transferred to " +  TransferredDept +" - "+ TransferredLoc  + " Location");
			
			driver.navigate().refresh();

		} catch (Throwable t) {
			// TODO Auto-generated catch block
			throw (t);
		}
	}
}


