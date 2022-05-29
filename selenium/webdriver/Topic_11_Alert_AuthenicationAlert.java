package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_11_Alert_AuthenicationAlert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	WebDriverWait explixitWait;
	
	Alert alert; 

	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver ();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@BeforeMethod 
	public void beforeMethod () {
	
	}
	
	public void sleepInSecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@Test 
	public void TC_01_Accept_Alert () {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()=\"Click for JS Alert\"]")).click();
		sleepInSecond(3);
		
		// switch vao alert sau luc alert nay bat len
		alert = driver.switchTo().alert();
		//accept vao alert nay
		alert.accept();
		//verify accept alert successful
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
		
		
		
	}
	
	//@Test 
	public void TC_02_Confirm_Alert () {
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text()=\"Click for JS Confirm\"]")).click();
		sleepInSecond(3);
		
		//switch vao alert sau luc alert nay bat len
		alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		// cancel alert 
		alert.dismiss();
		
		// verify cancel 
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
	}
	
	//@Test 
	public void TC_03_Prompt_Alert () {
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text()=\"Click for JS Prompt\"]")).click();
		sleepInSecond(3);
		
		//switch vao alert sau khi alert nay duoc bat len
		alert = driver.switchTo().alert();
		
		// verify text in alert 
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		// input text into alert prompt
		alert.sendKeys("Le Nhu Khanh");
		//click ok
		alert.accept();
		
		// verify alert sendkeys correctly
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: Le Nhu Khanh");
		
		
	}
	
	@Test 
	public void TC_04_Authenication_Alert () {
		// Selenium cho pass User/Pass directly into Url before website is opened
		// Format: http://Username:Password@domain 
		// Khong bat alert len (vi da by pass qua url roi)
		
		// All cac OS/ Browser 
		
		String username = "admin";
		String password = "admin";
		String url = "http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth";
		
		driver.get(url);
		
		
	}


	/* @AfterClass
	public void afterClass() {
		driver.quit();
	} */
}
