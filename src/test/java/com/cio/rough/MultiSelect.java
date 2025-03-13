package com.cio.rough;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MultiSelect {
	
	public static WebDriver driver;
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/select-menu");
		
		WebElement mselect = driver.findElement(By.name("cars"));
		Select select = new Select(mselect);
		select.selectByIndex(0);
		Thread.sleep(2000);
		select.selectByIndex(1);
		Thread.sleep(2000);
		select.selectByIndex(2);
		Thread.sleep(2000);
		select.deselectAll();
		Thread.sleep(2000);
		select.selectByIndex(0);
		Thread.sleep(2000);
		select.selectByIndex(1);
		Thread.sleep(2000);
		select.selectByIndex(2);
		Thread.sleep(2000);
		select.deselectByValue("volvo");
		Thread.sleep(2000);
		select.deselectByVisibleText("Saab");

	}

}
