package com.cio.rough;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DragAndDrop {
	
	public static WebDriver driver;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/droppable/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		WebElement frame = driver.findElement(By.xpath("//*[@id=\"content\"]/iframe"));
		driver.switchTo().frame(frame);
		WebElement drag = driver.findElement(By.xpath("//*[@class='ui-widget-content ui-draggable ui-draggable-handle']"));
		WebElement drop = driver.findElement(By.xpath("//*[@class='ui-widget-header ui-droppable']"));
		Actions action = new Actions(driver);
		
		//action.moveToElement(drag).perform();
		action.dragAndDrop(drag, drop).perform();
		

	}

}
