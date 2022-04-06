package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Exercise_BrowserElement {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver ();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_GetCurrentURL ()
	{
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
	
	}
	
	@Test 
	public void TC_02_GetTitle()
	{
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}
	
	@Test
	public void TC_03_BackForward()
	{
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		
		// verify URL of register page
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		
		//back lai trang login
		driver.navigate().back();
		
		//Verify URL of login page
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		//forward to register page
		driver.navigate().forward();
		
		//Verify title of register page
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
		
	}
	@Test 
	public void TC_04_GetPageSource ()
	{
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();

		
		//verify pagesource contain text Login or Create an Account
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		
		//verify pagesource contain text Create an Account
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	//giugiggugiugygiug
}
