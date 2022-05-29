package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_08_Execise_WebElement2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	//khong the khai bao bien cuc bo element (nhu ben duoi) duoc vi bien driver van chua duoc khoi tao. (no duoc khoi tao o doan code driver = new FirefoxDriver()
	//Do do, khi run test se bi loi la bien chua duoc khai bao
	/*WebElement element = driver.findElement(By.xpath("")); */
	
	//chi co the khai bao bien By nhu ben duoi
	By email = By.id("email");
	By username = By.id("new_username");
	By password = By.id("new_password");
	By SignUpbutton = By.id("create-account");
	By marketingLetter = By.id("marketing_newsletter");
	// ham beforeClass se chay truoc TAT CA (ALL) test
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver ();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		
	}

	// ham beforeMethod se chay truoc MOI (EACH) test
	@BeforeMethod 
	public void beforeMethod() {
		driver.get("https://login.mailchimp.com/signup/");
		
	}
	
	
	@Test
	public void TC_04() {
		driver.findElement(email).sendKeys("nhukhanhle@gmail.com");
		driver.findElement(username).sendKeys("nhukhanhle");
		driver.findElement(password).sendKeys("123456");
		
		//verify button Sign Up disable if password is number only
		Assert.assertFalse(driver.findElement(SignUpbutton).isEnabled());
		
		//verify checkbox marketing letter is selected or not
		Assert.assertFalse(driver.findElement(marketingLetter).isSelected());
		driver.findElement(marketingLetter).click();
		Assert.assertTrue(driver.findElement(marketingLetter).isSelected());
		
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
