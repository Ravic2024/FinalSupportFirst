package com.cio.testcases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cio.base.TestBase;
import com.cio.utilities.TestUtils;

public class EIBSecurityTesting extends TestBase {

	public int count;
	public int counter = 1;
	public String Text404;

	@Test(priority=1,dataProviderClass = TestUtils.class, dataProvider = "dp")
	public void SecurityTesting(String SNo, String Execution, String User, String Conditions, String Useremail, String Password,
			String XPATHName, String URL, String URLSecured, String ConfigResult, String URLResult)
					throws InterruptedException, IOException {
		
		System.out.println("Serial Number Iteration is "+SNo);
		
		if (Execution.equalsIgnoreCase("Y")) {
			try {

				System.out.println("Application Launched");
				driver.manage().deleteAllCookies();
				driver.get("chrome://settings/clearBrowserData");
				Thread.sleep(1000);
				driver.get(URL);
				// log.debug("Browser Navigated to the application " +
				// config.getProperty("testsiteurl"));
				driver.manage().window().maximize();
				Thread.sleep(2000);
				Type("EIBLoginEmail_XPATH", Useremail);
				Thread.sleep(2000);
				Type("EIBLoginPassword_XPATH", Password);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
				Click("EIBLoginButton_XPATH");
				Thread.sleep(3000);
				count = Integer.parseInt(SNo);
				boolean config_Exist = isElementPresent(By.xpath(OR.getProperty("EIBConfigIcon_XPATH")));
				System.out.println("Conditions Value is " + Conditions);
				if (Conditions.equalsIgnoreCase("No Config")) {
					if (config_Exist == false) {
						setConfigResult("PASSED:No configuration as expected", count);
						test.log(Status.PASS, "" + User + " :No configuration as expected");
					} else {
						setConfigResult("Failed: Because Configuration Exist", count);
						test.log(Status.FAIL, "" + User + " :Because Configuration Exist");
					}
				}
				if (Conditions.equalsIgnoreCase("Yes Config")) {
					if (config_Exist == true) {
						setConfigResult("PASSED:Configuration Exist", count);
						test.log(Status.PASS, "" + User + " :Configuration Exist");
					} else {
						setConfigResult("Failed: Configuration doesn't Exist", count);
						test.log(Status.FAIL, "" + User + " :Because Configuration doesn't Exist");
					}
				}
				if (Conditions.equalsIgnoreCase("TeamDashboard")) {
					if (config_Exist == false) {
						// Click("EIBDashboard_XPATH");
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
						WebElement Dashb = driver.findElement(By.xpath(OR.getProperty("EIBDashboard_XPATH")));
						WebElement TeamDashb = driver.findElement(By.xpath(OR.getProperty("EIBTeamDashboard_XPATH")));
						Actions action = new Actions(driver);
						action.moveToElement(Dashb).click(Dashb).build().perform();
						action.moveToElement(TeamDashb).perform();
						HighlightWebElement("EIBTeamDashboard_XPATH");
						Thread.sleep(1000);
						setConfigResult("PASSED:No Configuration but Team Dashboard Exist", count);
						test.log(Status.PASS, "" + User + " :No Configuration but Team Dashboard Exist");
					} else {
						setConfigResult("Failed:Conditions not exist in testData", count);
						test.log(Status.FAIL, "" + User + " :Conditions not exist in testData");
					}
				}

			
				driver.navigate().to(URLSecured);
				Thread.sleep(3000);
				if (URLSecured.endsWith("my_profile") || URLSecured.endsWith("apply_or_revoke")
						|| URLSecured.endsWith("manager_dashboard") || URLSecured.endsWith("hra_dashboard")
						|| URLSecured.endsWith("attrition_dashboard") || URLSecured.endsWith("allemplist")
						|| URLSecured.endsWith("exit/reports") || URLSecured.endsWith("empApprovals")
						|| URLSecured.endsWith("empApprovals") || URLSecured.endsWith("transfer_process")
						|| URLSecured.endsWith("transfer/reports") || URLSecured.endsWith("salarymass_upload")
						|| URLSecured.endsWith("masterData") || URLSecured.endsWith("userconfig")
						|| URLSecured.endsWith("app_config") || URLSecured.endsWith("createEmployee")
						|| URLSecured.endsWith("emp_detail/approve_empstatus")
						|| URLSecured.endsWith("emp_details/approve_myprofileupdate")
						|| URLSecured.endsWith("hr/othermass_upload") || URLSecured.endsWith("exit/exit_approvals")
						|| URLSecured.endsWith("exit/interviewReportView") || URLSecured.endsWith("exit/change_status")
						|| URLSecured.endsWith("transfer/empList")) {
					
					
				
					Thread.sleep(3000);
					Text404 = driver.getTitle();
					System.out.println(Text404);
					
					
					
					if(Text404.startsWith("404")==true || (Text404=="")) {
						//driver.close();
						System.out.println("404 Found");
						setURLResult("PASSED : 404 Found", count);
						test.log(Status.PASS, "404 Found Page is displayed");
						//driver.close();
					}else if(isElementPresent(By.xpath(OR.getProperty("EIBPermissionDenied_XPATH")))== true) {
						System.out.println("Inside permission If");
						setURLResult("PASSED : PERMISSION DENIED OR 404", count);
						test.log(Status.PASS, "PERMISSION DENIED OR 404 is displayed");								
					}else
						waitForElement(XPATHName);
					}	
				boolean ObjectExist = isElementPresent(By.xpath(OR.getProperty(XPATHName)));
				System.out.println("Object Exist is "+ObjectExist);
				test.log(Status.PASS, "Boolean value is " + ObjectExist);
					if (ObjectExist == true) {
						test.log(Status.PASS, "Boolean value is " + ObjectExist);
						String TextValue = driver.findElement(By.xpath(OR.getProperty(XPATHName))).getText();
						System.out.println("Inside If Else "+TextValue);
						setURLResult("PASSED :" + TextValue + " Page is displayed", count);
						test.log(Status.PASS, "" + TextValue + " :Page is displayed");
					}
					
					if(ObjectExist == false && Text404.startsWith("404")==false && (Text404 =="") && (isElementPresent(By.xpath(OR.getProperty("EIBPermissionDenied_XPATH")))== false)) {
						System.out.println("Inside Failed If");
						String TextValue = driver.findElement(By.xpath(OR.getProperty(XPATHName))).getText();
						setURLResult("FAILED :" + TextValue + " Page is NOT displayed", count);
						test.log(Status.FAIL, "" + TextValue + " :Page is NOT displayed");			
				}
				
				if (URLSecured.endsWith("dashboard/manager_team_dashboard")) {
					
					System.out.println("Manager Team Dashboard Opened");
					Thread.sleep(3000);			
					if(Text404.startsWith("404")|| (Text404.startsWith("cio"))) {
						setURLResult("PASSED : 404 Found", count);
						test.log(Status.PASS, "404 Found Page is displayed");
						//driver.close();
					}else if(isElementPresent(By.xpath(OR.getProperty("EIBPermissionDenied_XPATH")))== true) {
						System.out.println("Inside permission If");
						setURLResult("PASSED : PERMISSION DENIED OR 404", count);
						test.log(Status.PASS, "PERMISSION DENIED OR 404 is displayed");								
					}else {
						waitForElement("EIBDashboard_XPATH");
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
						WebElement Dashb = driver.findElement(By.xpath(OR.getProperty("EIBDashboard_XPATH")));
						WebElement TeamDashb = driver.findElement(By.xpath(OR.getProperty("EIBTeamDashboard_XPATH")));
						Actions action = new Actions(driver);
						action.moveToElement(Dashb).click(Dashb).build().perform();
						action.moveToElement(TeamDashb).perform();
						HighlightWebElement("EIBTeamDashboard_XPATH");
					}	
					/*
					 * WebElement Dashb =
					 * driver.findElement(By.xpath(OR.getProperty("EIBDashboard_XPATH")));
					 * JavascriptExecutor js = (JavascriptExecutor) driver;
					 * js.executeScript("arguments[0].click();", Dashb);
					 */
					// Click("EIBDashboard_XPATH");
					
					boolean tDashboard = isElementPresent(By.xpath(OR.getProperty("EIBTeamDashboard_XPATH")));
					System.out.println(tDashboard);
					if (tDashboard == true) {
						setURLResult("PASSED:Team Dashboard Displayed", count);
						test.log(Status.PASS, "" + Conditions + " :Page is displayed");
					} 
					
					if(ObjectExist == false && Text404.startsWith("404")==false && (Text404.startsWith("cio"))==false && (isElementPresent(By.xpath(OR.getProperty("EIBPermissionDenied_XPATH")))== false)) {
						String TextValue = driver.findElement(By.xpath(OR.getProperty(XPATHName))).getText();
						System.out.println("Inside Failed If "+TextValue);
						setURLResult("FAILED :" + TextValue + " Page is NOT displayed", count);
						test.log(Status.FAIL, "" + TextValue + " :Page is NOT displayed");
					}

				}
				
				/*
				 * String path = System.getProperty("user.dir") +
				 * "\\src\\test\\resources\\excel\\testdata.xlsx"; FileInputStream fs = new
				 * FileInputStream(path); Workbook wb = new XSSFWorkbook(fs); Sheet sheet1 =
				 * wb.getSheet("SecurityTesting"); String cellData =
				 * sheet1.getRow(7).getCell(7).getStringCellValue();
				 * System.out.println(cellData); FileOutputStream fos = new
				 * FileOutputStream(path); wb.write(fos); fos.close();
				 */
				
				//count = count + 1;
				
				System.out.println("Execution Yes "+count);

				// driver.quit();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				String error = driver.getTitle();
				System.out.println("Inside catch block "+error);
				e.printStackTrace();
			}
		}
	}

	public static void setURLResult(String URLResult, int i) throws IOException, InterruptedException {

		
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx";
		FileInputStream fs = new FileInputStream(path);
		Workbook wb = new XSSFWorkbook(fs);
		Sheet sheet1 = wb.getSheet("SecurityTesting");
		//int lastRow = sheet1.getLastRowNum();
		// for(int i=0; i<=lastRow; i++){
		Row row = sheet1.getRow(i);
		Cell cell1 = row.createCell(10);
		Thread.sleep(2000);
		cell1.setCellValue(URLResult);
		FileOutputStream fos = new FileOutputStream(path);
		wb.write(fos);
		fos.close();

	}

	public static void setConfigResult(String ConfigResult, int i) throws IOException, InterruptedException {

		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx";
		FileInputStream fs = new FileInputStream(path);
		Workbook wb = new XSSFWorkbook(fs);
		Sheet sheet1 = wb.getSheet("SecurityTesting");
		// int lastRow = sheet1.getLastRowNum();
		// for(int i=0; i<=lastRow; i++){
		Row row = sheet1.getRow(i);
		Cell cell = row.createCell(9);
		Thread.sleep(2000);
		cell.setCellValue(ConfigResult);

		// }

		FileOutputStream fos = new FileOutputStream(path);
		wb.write(fos);
		// fos.close();

	}

	//@Test(priority=2,dataProviderClass = TestUtils.class, dataProvider = "dp")
	public void EIBSecurityTestingFlow(String Execution, String User, String Useremail, String Password,String URL,String URLExtention,String URLResult) throws InterruptedException, IOException, ArrayIndexOutOfBoundsException {

		if(Execution.equalsIgnoreCase("Y")) {

			try {
				System.out.println("Application Launched");
				driver.manage().deleteAllCookies();
				driver.get(URL);
				//log.debug("Browser Navigated to the application " + config.getProperty("testsiteurl"));
				driver.manage().window().maximize();
				Type("EIBLoginEmail_XPATH", Useremail);
				Thread.sleep(2000);
				Type("EIBLoginPassword_XPATH", Password);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
				Click("EIBLoginButton_XPATH");
				Thread.sleep(3000);
				driver.navigate().to(URL+URLExtention);
				System.out.println(URL+URLExtention);
				Thread.sleep(8000);


				if(URLExtention.equalsIgnoreCase("emp_detail/allemplist")) {
					Thread.sleep(2000);
					List<WebElement> oList = driver.findElements(By.tagName("a"));
					System.out.println("List of All Links "+oList.size());

					for(int j=0;j<oList.size();j++) {
						String getText = oList.get(j).getText();
						System.out.println(getText);
						System.out.println(j);
						if(j>6) {
							char[] c;
							try {
								c = getText.toCharArray();
								System.out.println(c);
								boolean b = Character.isDigit(c[0]);					
								
								System.out.println(b);
								if (b==true) {

									System.out.println("Inside If Else");

									try {
										Actions action = new Actions(driver);
										action.click(oList.get(j)).perform();
										test.log(Status.PASS, "Employee id :"+getText+" is clicked");
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}	
									waitForElement("EIBAllEmpReportApprovalListTransSalMass_XPATH");
									HighlightWebElement("EIBAllEmpReportApprovalListTransSalMass_XPATH");

									boolean ObjectExist= isElementPresent(By.xpath(OR.getProperty("EIBAllEmpReportApprovalListTransSalMass_XPATH"))); 
									String TextValue = driver.findElement(By.xpath(OR.getProperty("EIBAllEmpReportApprovalListTransSalMass_XPATH"))).getText();
									if(ObjectExist==true) {
										setResult("PASSED :"+TextValue+" Page is displayed",counter); 
										test.log(Status.PASS, ""+TextValue+" :Page is displayed"); 
									}else {
										setResult("FAILED :"+TextValue+" Page is NOT displayed",counter);
										test.log(Status.FAIL, ""+TextValue+" :Page is NOT displayed"); 	  
									}
									
									break;
								}
							} catch (Exception e1) {
								System.out.println("Inside Catch Block");
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}		
					}
				}

				counter = counter+1;
				System.out.println("Counter value is "+counter);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}else if(Execution.equalsIgnoreCase("N")){
			counter = counter+1;
			//driver.quit();
		}


	}

	public static void setResult(String URLResult, int i) throws IOException, InterruptedException {

		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx";
		FileInputStream fs = new FileInputStream(path);
		Workbook wb = new XSSFWorkbook(fs);
		Sheet sheet1 = wb.getSheet("EIBSecurityTestingFlow");
		// int lastRow = sheet1.getLastRowNum();
		// for(int i=0; i<=lastRow; i++){
		Row row = sheet1.getRow(i);
		Cell cell2 = row.createCell(6);
		Thread.sleep(2000);
		System.out.println(URLResult);
		cell2.setCellValue(URLResult);
		FileOutputStream fos1 = new FileOutputStream(path);
		wb.write(fos1);
		fos1.close();

	}

}
