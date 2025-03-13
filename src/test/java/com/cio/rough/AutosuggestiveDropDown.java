package com.cio.rough;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutosuggestiveDropDown {
	
	public static WebDriver driver;
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.makemytrip.com/");
		Thread.sleep(10000);
		WebElement from = driver.findElement(By.id("fromCity"));
		Actions action = new Actions(driver);
		Thread.sleep(2000);
		action.moveToElement(from).perform();
		//Thread.sleep(2000);
		action.click(from).perform();
		Thread.sleep(2000);
		action.keyDown(Keys.SHIFT).sendKeys("sydney").build().perform();
		Thread.sleep(2000);
		action.keyDown(Keys.ARROW_DOWN).perform();
		action.keyDown(Keys.ENTER).perform();
	}

}
