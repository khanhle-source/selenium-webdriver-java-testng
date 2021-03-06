package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_10_Execise_CustomDropdown2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String employeeID;
	WebDriverWait explixitWait;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver ();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@BeforeMethod 
	public void beforeMethod () {
		driver.get("https://opensource-demo.orangehrmlive.com/");
	}
	
	public int random () {
		Random rnd = new Random();
		return rnd.nextInt(999);
	}

	@Test
	public void TC_01_() {
		// neu attribute khong xuat hien, vao tab Properties de view all attribute 
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
