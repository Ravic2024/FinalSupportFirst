package com.cio.testcases;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.cio.base.TestBase;
import com.cio.utilities.TestUtils;

public class AdvanceFilter extends TestBase
{
	@Test
	public void TicketQueIcon() throws Throwable 
	{
		try {
			waitForElement("TicketQueueIcon_XPATH");
			Thread.sleep(3000);
			Click("TicketQueueIcon_XPATH");
		} catch (Throwable t) {
			// TODO Auto-generated catch block
			throw(t);
		}
	}
	@Test//(dataProviderClass = TestUtils.class, dataProvider = "dp")
	public void AdvanceFilter(String Resolve) 
	{
		//AdvanceFilter Icon 
		waitForElement("AF_Icon_XPATH");
		Click("AF_Icon_XPATH");
		
		//My Ticket 
		waitForElement("MyTicket_XPATH");
		HighlightWebElement("MyTicket_XPATH");
		Click("MyTicket_XPATH");
		waitForElement("SearchBox_XPATH");
		Type("SearchBox_XPATH", Resolve);
//		Actions act = new Actions(driver);
//		act.keyDown(Keys.ENTER).perform();
		
		
	
	}
}
