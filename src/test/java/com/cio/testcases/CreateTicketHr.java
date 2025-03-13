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

public class CreateTicketHr extends TestBase {
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dp")
	public void CustomFields(String Subject,String Department, String Location, String Mow, String HRA, String TT, String Dec) throws Throwable {

		test.log(Status.PASS, "Create Ticket Page Displayed Successfully");
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

		System.out.println(Department + " " + Location + " " + Mow + " " + HRA + " " + TT + " " + Dec + " " + Subject);
		try {
			waitForElement("Departmentdropdown_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Departmentdropdown_XPATH"))));
			waitForElement("Departmentdropdown_XPATH");
			Click("Departmentdropdown_XPATH");
			waitForElement("SearchBox_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("SearchBox_XPATH"))));
			searchBoxDropDown("SearchBox_XPATH", Department);

			for (int i = 1; i <= 3; i++) {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
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
			// Location
			waitForElement("Locationdropdown_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Locationdropdown_XPATH"))));
			WebElement loc = driver.findElement(By.xpath(OR.getProperty("Locationdropdown_XPATH")));
			// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			Click("Locationdropdown_XPATH");
			waitForElement("SearchBox_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("SearchBox_XPATH"))));
			searchBoxDropDown("SearchBox_XPATH", Location);

			// Mode of work
			WebElement modeofwork = driver.findElement(By.xpath(OR.getProperty("MOW_XPATH")));
			Actions action1 = new Actions(driver);
			action1.moveToElement(modeofwork).perform();
			Click("MOW_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("SearchBox_XPATH"))));
			searchBoxDropDown("SearchBox_XPATH", Mow);

			// HRA Category
			WebElement cat = driver.findElement(By.xpath(OR.getProperty("HRACategory_XPATH")));
			Actions action2 = new Actions(driver);
			action1.moveToElement(cat).perform();
			Click("HRACategory_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("HRACategory_XPATH"))));
			searchBoxDropDown("HRACategory_XPATH", HRA);

			// Ticket Type
			WebElement Tic = driver.findElement(By.xpath(OR.getProperty("tickettype_XPATH")));
			Actions action3 = new Actions(driver);
			action1.moveToElement(Tic).perform();
			Click("tickettype_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("tickettype_XPATH"))));
			searchBoxDropDown("HRACategory_XPATH", TT);

			// Description
			waitForElement("Description_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Description_XPATH"))));
			Type("Description_XPATH", Dec);

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
			throw (t);

		}
	}
}
