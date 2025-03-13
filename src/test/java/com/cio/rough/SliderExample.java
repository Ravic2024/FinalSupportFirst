package com.cio.rough;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SliderExample {
	
	public static WebDriver driver;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/slider/#colorpicker");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		WebElement frame = driver.findElement(By.xpath("//*[@id=\"content\"]/iframe"));
		driver.switchTo().frame(frame);
		
		WebElement drag = driver.findElement(By.xpath("//*[@id=\"green\"]/span"));
		Actions action = new Actions(driver);
		action.dragAndDropBy(drag, -100, 10).perform();
		
	}

}
