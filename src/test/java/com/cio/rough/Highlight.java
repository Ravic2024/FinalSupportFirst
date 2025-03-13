package com.cio.rough;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Highlight {
	
	public static WebDriver driver;
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.gmail.com");
		Thread.sleep(2000);
		WebElement ele = driver.findElement(By.xpath("//*[@id=\"headingText\"]/span"));
        //Create object of a JavascriptExecutor interface
	JavascriptExecutor js = (JavascriptExecutor) driver;
        
	js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", ele);

	}

}
