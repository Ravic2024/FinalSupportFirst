package com.cio.rough;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropDown {
	
	public static WebDriver driver;
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.sugarcrm.com/au/request-demo/");
		Thread.sleep(2000);
		WebElement ddown = driver.findElement(By.id("field-15436"));
		Select select = new Select(ddown);
		System.out.println(select.getFirstSelectedOption().getText());
		Thread.sleep(2000);
		select.selectByIndex(3);
		Thread.sleep(2000);
		select.selectByValue("Sugar Sell");
		Thread.sleep(2000);
		select.selectByVisibleText("Customer Service");
		
		
	}

}
