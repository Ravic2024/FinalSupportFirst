package com.cio.rough;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasicMethodTwo {
	
	public static WebDriver driver;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com");
		
		driver.navigate().to("https://www.sugarcrm.com");
		
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println(windowHandles);
		
		
	}

}
