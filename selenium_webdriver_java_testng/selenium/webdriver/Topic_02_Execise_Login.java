package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_02_Execise_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By username = By.name("uid");
	

	public int random () {
		Random rnd = new Random();
		return rnd.nextInt();
	}
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver ();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://demo.guru99.com/v4/");
	}
	

	@Test
	public void TC_01() {
		
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(), "This is a required field.");
		
	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {	
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		String email = random() + "@" + random();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pass")).sendKeys("123456789");
		driver.findElement(By.cssSelector("button#send2")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		
		
	}
	@Test
	public void TC_03_LoginWithPassLessThan6() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.id("email")).sendKeys("nhukhanh94@yahoo.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.cssSelector("button#send2")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_04_LoginWithIncorrectEmailPass() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.id("email")).sendKeys("nhukhanh94@yahoo.com");
		driver.findElement(By.id("pass")).sendKeys("1234567");
		driver.findElement(By.cssSelector("button#send2")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg li")).getText(), "Invalid login or password.");
		}

	@Test
	public void TC_05_CreateNewAccount() {
		}
	
	@Test
	public void TC_06_LoginSuccessful() {
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
