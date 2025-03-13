package com.cio.rough;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DepartmentDropdown {
	
	public static WebDriver driver;
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://supportfirst-demo.growipx.com/#/login");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("mohanraj.a@iopex.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Iopex@123");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='LOGIN']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//div[@class='list-icon'])[2]")).click();
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("//*[@id='pn_id_18']/span")).click();
		WebElement searchtextbox = driver.findElement(By.xpath("//input[@type='text' and @role='searchbox']"));
		Actions action = new Actions(driver);
		action.click(searchtextbox).perform();
		action.sendKeys("cio").perform();
		//action.keyDown(Keys.SHIFT).sendKeys(text).build().perform();
		action.keyDown(Keys.ARROW_DOWN).perform();
		action.keyDown(Keys.ENTER).perform();
		
		
		
		
	}

}
