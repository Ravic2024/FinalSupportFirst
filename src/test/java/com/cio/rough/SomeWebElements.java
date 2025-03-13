package com.cio.rough;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SomeWebElements {
	
	public static WebDriver driver;
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.sugarcrm.com/au/request-demo/");
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"field1\"]/div/input")).getAttribute("class"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"field1\"]/div/input")).getCssValue("background-color"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"field1\"]/div/input")).getSize());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"field1\"]/div/input")).getTagName());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"field1\"]/div/input")).getLocation());
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.className("mb-5")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"field1\"]/div/input")).isDisplayed());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"field1\"]/div/input")).isEnabled());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"field1\"]/div/input")).isSelected());
		
		

	}

}
