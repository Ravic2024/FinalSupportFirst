package com.cio.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = LogManager.getLogger(TestBase.class);
	public static WebDriverWait wait;
	public static ExtentSparkReporter spark = new ExtentSparkReporter(
			System.getProperty("user.dir") + "\\target\\Spark.html");
	public static ExtentReports extent = new ExtentReports();
	public static ExtentTest test;

	@BeforeSuite
	public void setUp() {

		if (driver == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				config.load(fis);
				log.debug("Config file loaded successfully");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				OR.load(fis);
				log.debug("OR File Loaded Successfully");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (config.getProperty("Execution_env").equalsIgnoreCase("remote")) {
				spark.config().setTheme(Theme.STANDARD);
				spark.config().setDocumentTitle("Automation Report");
				extent.attachReporter(spark);
				/*
				 * System.setProperty("otel.traces.exporter", "jaeger");
				 * System.setProperty("otel.exporter.jaeger.endpoint",
				 * "http://localhost:14250"); System.setProperty("otel.resource.attributes",
				 * "service.name=selenium-java-client");
				 */
				//ImmutableCapabilities capabilities = new ImmutableCapabilities("browserName", "chrome");
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setPlatform(Platform.LINUX);
				capabilities.setBrowserName("chrome");
				/*
				 * ChromeOptions options = new ChromeOptions(); options.addArguments
				 * ("user-data-dir=~/.config/google-chrome"); options.addArguments
				 * ("disable-gpu"); options.addArguments ("disable-impl-side-painting");
				 * options.addArguments ("disable-dev-shm-usage"); options.addArguments
				 * ("disable-infobars"); options.addArguments ("disable-gpu-sandbox");
				 * options.addArguments ("no-sandbox"); options.addArguments
				 * ("disable-accelerated-2d-canvas"); options.addArguments
				 * ("disable-accelerated-jpeg-decoding"); options.addArguments ("test-type=ui");
				 * options.addArguments ("no-proxy-server");
				 * options.addArguments("--incognito");
				 */


				/*
				 * ChromeOptions options = new ChromeOptions();
				 * //options.setBinary(/path/to/chrome); driver = new ChromeDriver(options);
				 */
				String hubURI = "http://10.20.246.140:4444/wd/hub";

				try {
					driver = new RemoteWebDriver(new URI(hubURI).toURL(),capabilities);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}else if (config.getProperty("Execution_env").equalsIgnoreCase("local")) {

				if (config.getProperty("Browser").equalsIgnoreCase("chrome")) {
					spark.config().setTheme(Theme.STANDARD);
					spark.config().setDocumentTitle("Automation Report");
					extent.attachReporter(spark);
					WebDriverManager.chromedriver().setup();
					//ChromeOptions options=new ChromeOptions();
					//options.addArguments("headless");
					//driver=new ChromeDriver(options);
					driver = new ChromeDriver();
					log.debug("Chrome Browser Launched Successfully");
					System.out.println("Chrome Browser Launched Successfully");

				} else if (config.getProperty("Browser").equalsIgnoreCase("edge")) {
					WebDriverManager.edgedriver().setup();
					driver = new EdgeDriver();
					log.debug("Edge Browser Launched Successfully");
				} else {
					log.debug("Invalid Browser");
				}


			}else {
				test.log(Status.FAIL, "Invalid Execution Environment");
			}

			driver.manage().deleteAllCookies();
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Browser Navigated to the application " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		}
	}

	public void Click(String locator) {

		if (locator.endsWith("_XPATH")) {

			driver.findElement(By.xpath(OR.getProperty(locator))).click();
			test.log(Status.PASS, "Clicked on Element: " + locator);
		}

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
			test.log(Status.PASS, "Clicked on Element: " + locator);

		}

		if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
			test.log(Status.PASS, "Clicked on Element: " + locator);
		}
	}

	public void Type(String locator, String value) {

		if (locator.endsWith("_XPATH")) {

			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
			test.log(Status.PASS, "Typed in " + locator + " and entered values is " + value);
		}

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
			test.log(Status.PASS, "Typed in " + locator + " and entered value is " + value);

		}

		if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
			test.log(Status.PASS, "Typed in " + locator + " and entered value is " + value);
		}
	}

	public boolean isElementPresent(By by) {

		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}

	}

	public void selectByVisibleText(String locator, String text) {

		if (locator.endsWith("_XPATH")) {

			WebElement ddown = driver.findElement(By.xpath(OR.getProperty(locator)));
			Select select = new Select(ddown);
			select.selectByVisibleText(text);
			test.log(Status.PASS, "Drop down in " + locator + " and Selected value is " + text);
		}

		if (locator.endsWith("_CSS")) {

			WebElement ddown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
			Select select = new Select(ddown);
			select.selectByVisibleText(text);
			test.log(Status.PASS, "Drop down in " + locator + " and Selected value is " + text);
		}

		if (locator.endsWith("_ID")) {
			WebElement ddown = driver.findElement(By.id(OR.getProperty(locator)));
			Select select = new Select(ddown);
			select.selectByVisibleText(text);
			test.log(Status.PASS, "Drop down in " + locator + " and Selected value is " + text);
		}

	}

	public void autoSuggestiveDropDown(String locator, String text) {

		if (locator.endsWith("_XPATH")) {

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			WebElement ddown = driver.findElement(By.xpath(OR.getProperty(locator)));
			Actions action = new Actions(driver);
			action.moveToElement(ddown).perform();
			action.click(ddown).perform();
			action.keyDown(Keys.SHIFT).sendKeys(text).build().perform();
			action.keyDown(Keys.ARROW_DOWN).perform();
			action.keyDown(Keys.ENTER).perform();
			test.log(Status.PASS, "Drop down in " + locator + " and Selected value is " + text);
		}

		if (locator.endsWith("_CSS")) {

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			WebElement ddown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
			Actions action = new Actions(driver);
			action.moveToElement(ddown).perform();
			action.click(ddown).perform();
			action.keyDown(Keys.SHIFT).sendKeys(text).build().perform();
			action.keyDown(Keys.ARROW_DOWN).perform();
			action.keyDown(Keys.ENTER).perform();
			test.log(Status.PASS, "Drop down in " + locator + " and Selected value is " + text);
		}

		if (locator.endsWith("_ID")) {

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			WebElement ddown = driver.findElement(By.id(OR.getProperty(locator)));
			Actions action = new Actions(driver);
			action.moveToElement(ddown).perform();
			action.click(ddown).perform();
			action.keyDown(Keys.SHIFT).sendKeys(text).build().perform();
			action.keyDown(Keys.ARROW_DOWN).perform();
			action.keyDown(Keys.ENTER).perform();
			test.log(Status.PASS, "Drop down in " + locator + " and Selected value is " + text);

		}

	}

	public void searchBoxDropDown(String searchbox, String text) {

		if (searchbox.endsWith("_XPATH")) {

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			WebElement searchtextbox = driver.findElement(By.xpath(OR.getProperty(searchbox)));
			Actions action = new Actions(driver);
			action.sendKeys(text).perform();
			action.keyDown(Keys.ARROW_DOWN).perform();
			action.keyDown(Keys.ENTER).perform();
			test.log(Status.PASS, "Drop down in " + searchbox + " and Selected value is " + text);
		}

		if (searchbox.endsWith("_CSS")) {

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			WebElement searchtextbox = driver.findElement(By.cssSelector(OR.getProperty(searchbox)));
			Actions action = new Actions(driver);
			action.sendKeys(text).perform();
			action.keyDown(Keys.ARROW_DOWN).perform();
			action.keyDown(Keys.ENTER).perform();
			test.log(Status.PASS, "Drop down in " + searchbox + " and Selected value is " + text);
		}

		if (searchbox.endsWith("_ID")) {

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			WebElement searchtextbox = driver.findElement(By.id(OR.getProperty(searchbox)));
			Actions action = new Actions(driver);
			action.sendKeys(text).perform();
			action.keyDown(Keys.ARROW_DOWN).perform();
			action.keyDown(Keys.ENTER).perform();
			test.log(Status.PASS, "Drop down in " + searchbox + " and Selected value is " + text);

		}

	}
		
	public void windowHandles() {
			Set<String> windowHandle =driver.getWindowHandles();
			Iterator<String> iterator = windowHandle.iterator();
			while(iterator.hasNext()){
				String childWindow = iterator.next();
				driver.switchTo().window(childWindow);
			}
		}
		
	public void waitForElement(String locator) {

		if (locator.endsWith("_XPATH")) {

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(locator))));
			test.log(Status.PASS, "Explicit wait 10 seconds statement executed");
		}
		if (locator.endsWith("_CSS")) {

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(locator))));
			test.log(Status.PASS, "Explicit wait 10 seconds statement executed");
		}
		if (locator.endsWith("_ID")) {

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(locator))));
			test.log(Status.PASS, "Explicit wait 10 seconds statement executed");
		}
	}

	public void HighlightWebElement(String locator) {

		if (locator.endsWith("_XPATH")) {

			WebElement ele = driver.findElement(By.xpath(OR.getProperty(locator)));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", ele);
			test.log(Status.PASS, "Highlighted element is " + locator);
		}
		if (locator.endsWith("_CSS")) {

			WebElement ele = driver.findElement(By.cssSelector(OR.getProperty(locator)));
			// Create object of a JavascriptExecutor interface
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", ele);
			test.log(Status.PASS, "Highlighted element is " + locator);
		}
		if (locator.endsWith("_ID")) {

			WebElement ele = driver.findElement(By.id(OR.getProperty(locator)));
			// Create object of a JavascriptExecutor interface
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", ele);
			test.log(Status.PASS, "Highlighted element is " + locator);
		}
	}
	
	public void selectEmailAddress(String searchbox, String userEmail) {

        if(searchbox.endsWith("_XPATH")) {

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            WebElement searchtextbox = driver.findElement(By.xpath(OR.getProperty("Searchbox_XPATH")));
            String regexx = "[\\.]";
            String[] myArray = userEmail.split(regexx);
            for (String s : myArray) {
                Actions acti = new Actions(driver);
                acti.sendKeys(s.toLowerCase()).perform();
                acti.keyDown(Keys.DECIMAL).perform();
            }
            Actions act = new Actions(driver);
            act.keyDown(Keys.BACK_SPACE).perform();
            act.keyDown(Keys.ARROW_DOWN).perform();
            act.keyDown(Keys.ENTER).perform();
            test.log(Status.PASS, "User Drop down "+ searchbox +" selected value is " + userEmail);
        }

    }
	
	public void selectDropdownWithoutSearch(String AssignRoleDDown, String AssignTicketRole) {

        if (AssignRoleDDown.endsWith("_XPATH")) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            WebElement Role = driver.findElement(By.xpath(OR.getProperty(AssignRoleDDown)));
            Actions action = new Actions(driver);
            action.moveToElement(Role).perform();
            action.click(Role).perform();
            action.keyDown(Keys.SHIFT).sendKeys(AssignTicketRole).build().perform();
           // action.keyDown(Keys.ARROW_DOWN).perform();
            action.keyDown(Keys.ENTER).perform();
            test.log(Status.PASS, "Drop down in " + AssignRoleDDown + " and Selected value is " + AssignTicketRole);

        }
        if (AssignRoleDDown.endsWith("_CSS")) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            WebElement Role = driver.findElement(By.cssSelector(OR.getProperty(AssignRoleDDown)));
            Actions action = new Actions(driver);
            action.moveToElement(Role).perform();
            action.click(Role).perform();
            action.keyDown(Keys.SHIFT).sendKeys(AssignTicketRole).build().perform();
            action.keyDown(Keys.ARROW_DOWN).perform();
            action.keyDown(Keys.ENTER).perform();
            test.log(Status.PASS, "Drop down in " + AssignRoleDDown + " and Selected value is " + AssignTicketRole);

        }
        if (AssignRoleDDown.endsWith("_ID")) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            WebElement Role = driver.findElement(By.id(OR.getProperty(AssignRoleDDown)));
            Actions action = new Actions(driver);
            action.moveToElement(Role).perform();
            action.click(Role).perform();
            action.keyDown(Keys.SHIFT).sendKeys(AssignTicketRole).build().perform();
            action.keyDown(Keys.ARROW_DOWN).perform();
            action.keyDown(Keys.ENTER).perform();
            test.log(Status.PASS, "Drop down in " + AssignRoleDDown + " and Selected value is " + AssignTicketRole);
        }
    }

	@AfterSuite
	public void tearDown() {

		if (driver != null) {
			driver.quit();
			// SendEmailWithReport();
			log.debug("Test Execution Completed Successfully");
		}
	}

	public static void SendEmailWithReport() {

		// Set up the SMTP server settings
		Properties props = new Properties();

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		/*
		 * props.put("mail.smtp.user","ravichandran.t@iopex.com");
		 * props.put("mail.smtp.host", "smtp.gmail.com"); props.put("mail.smtp.port",
		 * "587"); props.put("mail.smtp.starttls.enable", "true");
		 * props.put("mail.smtp.auth", "true");
		 * props.put("mail.smtp.socketFactory.port", "465");
		 * props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		 * props.put("mail.smtp.socketFactory.fallback", "false");
		 */

		// props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		// Authenticate the email account
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ravichandran.t@iopex.com", "P@ssw0rd@900");
			}
		});

		try {
			// Compose the email
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ravichandran.t@iopex.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ravichandran.t@iopex.com"));
			message.setSubject("Automated Test Report");

			// Email body text
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Please find the attached test report.");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// Attach the report
			messageBodyPart = new MimeBodyPart();
			String filename = System.getProperty("user.dir") + "\\target\\Spark.html";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);

			// Combine message parts
			message.setContent(multipart);

			// Send the email
			Transport.send(message);

			System.out.println("Email sent successfully.");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}





