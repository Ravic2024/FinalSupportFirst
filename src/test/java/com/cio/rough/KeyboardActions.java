package com.cio.rough;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class KeyboardActions {
	
	public static WebDriver driver;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://extendsclass.com/text-compare.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement SourceTarget = driver.findElement(By.xpath("//*[@id=\"dropZone\"]/div[2]/div/div[6]/div[1]"));
		WebElement DestinationTarget = driver.findElement(By.xpath("//*[@id=\"dropZone2\"]/div[2]/div/div[6]"));
		Actions action = new Actions(driver);
		action.keyDown(SourceTarget, Keys.CONTROL).sendKeys("a").sendKeys("c").build().perform();
		action.keyDown(DestinationTarget, Keys.CONTROL).sendKeys("a").sendKeys("v").build().perform();
	}
	

}
