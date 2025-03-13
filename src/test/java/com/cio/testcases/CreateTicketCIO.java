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

public class CreateTicketCIO extends TestBase {

	@Test(dataProviderClass = TestUtils.class, dataProvider = "dp")
	public void CreateCIOTicket(String Subject){
		try {
			// clicking Create Ticket Icon
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

		} catch (Throwable t) {
			// TODO Auto-generated catch block
			System.out.println("Catch Block Executed");
			throw (t);

		}
	}

	@Test(dataProviderClass = TestUtils.class, dataProvider = "dp")
	public void DeptDDown1(String Text, String Location, String Modeofwork, String Building, String Business,
			String TicketType, String Severity, String Application, String Description, String Subject)
			throws Throwable {

		System.out.println(Text + " " + Location + " " + Modeofwork + " " + Building + " " + Business + " " + TicketType
				+ " " + Severity + " " + Application + " " + Description + " " + Subject);
		try {
			// Department Drop down
			waitForElement("Departmentdropdown_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Departmentdropdown_XPATH"))));
			HighlightWebElement("Departmentdropdown_XPATH");
			waitForElement("Departmentdropdown_XPATH");
			Click("Departmentdropdown_XPATH");
			searchBoxDropDown("SearchBox_XPATH", Text);

			for (int i = 1; i <= 3; i++) {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				WebElement selectedtext = driver.findElement(By.xpath(OR.getProperty("Departmentdropdown_XPATH")));
				String sselect = selectedtext.getText();
				if (sselect.equalsIgnoreCase(Text) != true) {
					System.out.println("Loop Count is " + i);
					Click("CreateButton_XPATH");
					Thread.sleep(3000);
					waitForElement("Departmentdropdown_XPATH");
					Click("Departmentdropdown_XPATH");
					Thread.sleep(3000);
					Actions action = new Actions(driver);
					action.keyDown(Keys.ARROW_DOWN).perform();
					action.keyDown(Keys.ENTER).perform();
					test.log(Status.PASS, "Department Drop Down selected value is " + Text);

				} else {
					break;
				}
			}

			// Requester Location
			waitForElement("RequestorLocation_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("RequestorLocation_XPATH"))));
			WebElement loc = driver.findElement(By.xpath(OR.getProperty("RequestorLocation_XPATH")));
			Actions action = new Actions(driver);
			action.moveToElement(loc).perform();
			Click("RequestorLocation_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("SearchBox_XPATH"))));
			searchBoxDropDown("SearchBox_XPATH", Location);

			// Mode of work drop down
			WebElement modeofwork = driver.findElement(By.xpath(OR.getProperty("ModeOfWork_XPATH")));
			Actions action1 = new Actions(driver);
			action1.moveToElement(modeofwork).perform();
			Click("ModeOfWork_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("SearchBox_XPATH"))));
			searchBoxDropDown("SearchBox_XPATH", Modeofwork);

			// Building Drop Down
			WebElement building = driver.findElement(By.xpath(OR.getProperty("Building_XPATH")));
			Actions action2 = new Actions(driver);
			action2.moveToElement(building).perform();
			Click("Building_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("SearchBox_XPATH"))));
			searchBoxDropDown("SearchBox_XPATH", Building);

			// Business unit drop down
			WebElement Business1 = driver.findElement(By.xpath(OR.getProperty("BusinessUnit_XPATH")));
			Actions action3 = new Actions(driver);
			action3.moveToElement(Business1).perform();
			Click("BusinessUnit_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("BusinessUnit_XPATH"))));
			searchBoxDropDown("SearchBox_XPATH", Business);

			// Ticket type drop down
			WebElement Tickettype = driver.findElement(By.xpath(OR.getProperty("TicketType_XPATH")));
			Actions action4 = new Actions(driver);
			action4.moveToElement(Tickettype).perform();
			Click("TicketType_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("TicketType_XPATH"))));
			searchBoxDropDown("SearchBox_XPATH", TicketType);

			// Severity drop down
			WebElement Sev = driver.findElement(By.xpath(OR.getProperty("Severity_XPATH")));
			Actions action5 = new Actions(driver);
			action5.moveToElement(Sev).perform();
			Click("Severity_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Severity_XPATH"))));
			searchBoxDropDown("SearchBox_XPATH", Severity);

			// application drop down
			WebElement app = driver.findElement(By.xpath(OR.getProperty("Application_XPATH")));
			Actions action6 = new Actions(driver);
			action6.moveToElement(app).perform();
			Click("Application_XPATH");
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Application_XPATH"))));
			searchBoxDropDown("SearchBox_XPATH", Application);

			// Description text box
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
			throw (t);
		}

	}
}
