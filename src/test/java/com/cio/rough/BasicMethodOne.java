package com.cio.rough;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasicMethodOne {
	
	public static WebDriver driver;
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com");
		
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getPageSource());
		System.out.println(driver.getTitle());
		Thread.sleep(2000);
		driver.navigate().to("https://www.google.com");
		
		String windowHandle = driver.getWindowHandle();
		System.out.println(windowHandle);
		
		driver.quit();
		
		
		
		
		

	}

}
