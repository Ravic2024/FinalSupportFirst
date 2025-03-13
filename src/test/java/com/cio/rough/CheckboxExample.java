package com.cio.rough;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckboxExample {
	
	public static WebDriver driver;
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.sugarcrm.com/au/request-demo/");
		Thread.sleep(5000);
		WebElement chbx = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[8]/div/div[3]/div/div/a"));
		Actions action = new Actions(driver);
		action.moveToElement(chbx).perform();
		Thread.sleep(2000);
		//chbx.click();
		
		
	}

}
