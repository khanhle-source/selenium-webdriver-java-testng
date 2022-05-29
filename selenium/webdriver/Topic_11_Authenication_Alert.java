package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

public class Topic_11_Authenication_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	WebDriverWait explixitWait;
	
	

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
	public int random () {
		Random rnd = new Random();
		return rnd.nextInt(999);
	}
	
	/* @Test 
	public void TC_Authenication_Alert throws IOExpception {
		String username = "admin";
		String password = "admin";
		
		// before open url
		// execute file exe to wait alert is opened
		Runtime.getRuntime().exec(new String[] { autoITFireFox, username, password });
		
		driver.get("url");
		sleepInSecond(5);
		
		
		
	} */ 
	
	@Test 
	public void TC_05_Authenication_Alert_Trick_Navigate_From_Other_Page () {
		String username = "admin";
		String password = "admin"; 
		
		driver.get("http://the-internet.herokuapp.com/");
		
		// khong nen click vao link de show dialog ra
		// get url cua link
		
		String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		// split 
		String [] authenArray = basicAuthenLink.split("//");
		// http:
		// the-internet.herokuapp.com/
		
		// add string
		String newAuthenLink = authenArray[0] + "//" + username + ":" + password + "@" + authenArray[1];
		driver.get(newAuthenLink);
		
		// verify 
		String contentText = driver.findElement(By.cssSelector("div#content p")).getText();
		Assert.assertTrue(contentText.contains("Congratulations! You must have the proper credentials."));
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	} 
}
