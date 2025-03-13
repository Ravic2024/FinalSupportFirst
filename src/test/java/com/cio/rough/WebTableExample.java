package com.cio.rough;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTableExample {
	
	public static WebDriver driver;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.w3schools.com/html/html_tables.asp");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//Step1 - Check Inside Frame or not
		//Step2 - Count number of rows in the Table
		//Step3 - Count number of columns in the table
		//Step4 - Loop and get data of table
		
		////*[@id="customers"]/tbody/tr[2]/td[1]
		
		//  //*[@id="customers"]/tbody/tr[2]/td - column
		//  //*[@id="customers"]/tbody/tr - rows
		
	List<WebElement> rows_count = driver.findElements(By.xpath("//*[@id=\"customers\"]/tbody/tr"));
	System.out.println(rows_count.size());
	List<WebElement> column_count = driver.findElements(By.xpath("//*[@id=\"customers\"]/tbody/tr[2]/td"));
	System.out.println(column_count.size());
		
	for(int i=2;i<=rows_count.size();i++) {
		for(int j=1;j<=column_count.size();j++) {
			
		String data = driver.findElement(By.xpath("//*[@id='customers']/tbody/tr["+ i +"]/td["+ j +"]")).getText();
		System.out.print(data+" | ");	
			
		}
		System.out.println(" ");
	}	
	}

}
