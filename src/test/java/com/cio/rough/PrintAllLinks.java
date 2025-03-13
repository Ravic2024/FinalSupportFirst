package com.cio.rough;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PrintAllLinks {
	
	public static WebDriver driver;
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.sugarcrm.com/au/request-demo/");
		Thread.sleep(5000);
		List<WebElement> oLinks = driver.findElements(By.tagName("option"));
		System.out.println(oLinks.size());
		for(int i=0;i<oLinks.size();i++) {
			//System.out.println(oLinks.get(i).getAttribute("href"));
			System.out.println(oLinks.get(i).getText());
		}
		

	}

}
