package com.cio.testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cio.base.TestBase;
import com.cio.utilities.TestUtils;

public class CreateTicketFinance extends TestBase {
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dp")
	public void CreateFinanceTicket(String Subject) {
		try {
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
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(dataProviderClass = TestUtils.class, dataProvider = "dp")
	public void DepartmentValues(String Dept, String location, String Ttype, String Category, String Subcategory,
			String description, String Subject) {

		System.out.println(Dept + " " + location + " " + Ttype + " " + Category + " " + Subcategory + " " + description
				+ " " + Subject);

		try {
			waitForElement("Departmentdropdown_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Departmentdropdown_XPATH"))));
			// waitForElement("Departmentdropdown_XPATH");
			Click("Departmentdropdown_XPATH");
			waitForElement("SearchBox_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("SearchBox_XPATH"))));
			searchBoxDropDown("SearchBox_XPATH", Dept);
			for (int i = 1; i <= 3; i++) {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
				WebElement selectedtext = driver.findElement(By.xpath(OR.getProperty("Departmentdropdown_XPATH")));
				String sselect = selectedtext.getText();
				if (sselect.equalsIgnoreCase(Dept) != true) {
					System.out.println("Loop Count is " + i);
					Click("CreateButton_XPATH");
					waitForElement("Departmentdropdown_XPATH");
					Click("Departmentdropdown_XPATH");
					Actions action = new Actions(driver);
					action.keyDown(Keys.ARROW_DOWN).perform();
					action.keyDown(Keys.ENTER).perform();
					test.log(Status.PASS, "Department Drop Down selected value is " + Dept);

				} else {
					break;
				}

			}
			// Location Drop Down
			waitForElement("Locationdropdown_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Locationdropdown_XPATH"))));
			WebElement loc = driver.findElement(By.xpath(OR.getProperty("Locationdropdown_XPATH")));
			// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			Click("Locationdropdown_XPATH");
			waitForElement("SearchBox_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("SearchBox_XPATH"))));
			searchBoxDropDown("SearchBox_XPATH", location);

			// Ticket type
			waitForElement("TicketDropdown_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TicketDropdown_XPATH"))));
			Click("TicketDropdown_XPATH");
			waitForElement("SearchBox_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("SearchBox_XPATH"))));
			searchBoxDropDown("SearchBox_XPATH", Ttype);

			// Category Drop down
			waitForElement("CategoryDropdown_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("CategoryDropdown_XPATH"))));
			Click("CategoryDropdown_XPATH");
			waitForElement("SearchBox_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("SearchBox_XPATH"))));
			searchBoxDropDown("SearchBox_XPATH", Category);

			// Sub Category
			waitForElement("SubCategory_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("SubCategory_XPATH"))));
			Click("SubCategory_XPATH");
			waitForElement("SearchBox_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("SearchBox_XPATH"))));
			searchBoxDropDown("SearchBox_XPATH", Subcategory);

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
				waitForElement("TicketViewStar_XPATH");
				Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TicketViewPage_XPATH"))));
				String TicketViewSub = driver.findElement(By.xpath(OR.getProperty("TicketViewPage_XPATH"))).getText();
				test.log(Status.PASS, "Ticket View Page Subject is " + TicketViewSub);
				driver.navigate().refresh();
				
			} catch (Throwable t) {
				// TODO Auto-generated catch block
				throw (t);

			}
		} catch (Throwable t) {
			// TODO Auto-generated catch block

		}
	}
}
