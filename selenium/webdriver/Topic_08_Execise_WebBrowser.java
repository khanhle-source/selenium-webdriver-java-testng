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

public class Topic_08_Execise_WebBrowser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	// khong the khai bao bien cuc bo element (nhu ben duoi) duoc vi bien driver van
	// chua duoc khoi tao. (no duoc khoi tao o doan code driver = new
	// FirefoxDriver()
	// Do do, khi run test se bi loi la bien chua duoc khai bao
	/* WebElement element = driver.findElement(By.xpath("")); */

	// chi co the khai bao bien By nhu ben duoi
	By myAccount = By.xpath("//div[@class='footer']//a[@title='My Account']");
	By createAccountBtn = By.xpath("//a[@title='Create an Account']");

	String fullname, email, cEmail, password, phone;

	// ham beforeClass se chay truoc TAT CA (ALL) test
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// command + shift + F de chinh code dep hon

		// chi co the set gia tri cho fullname/email/password/phone o trong class
		// beforeClass boi vi
		// chi co the khai bao o global va khoi tao trong beforeClass
		// hoac khai bao + khoi tao luon o global Eg. String fullname = "le nhu khanh"
		// chu khong chap nhan viec khai bao xong roi cham phay ; roi khoi tao o global
		// Eg. String fullname; fullname = "le nhu khanh";
		// xem them o phut 1h43:
		// https://www.youtube.com/watch?v=tzeK78lSSEQ&list=PLo1QA-RK2zyryB4iZO_cXYF9OAW5SPUlt&index=19&ab_channel=AutomationFC
		fullname = "le nhu khanh";
		email = "nhukhanhle@gmail.com";
		cEmail = "nhukhanhle@gmail.com";
		password = "12345678";
		phone = "0123456789";

	}

	// ham beforeMethod se chay truoc MOI (EACH) test
	@BeforeMethod
	public void beforeMethod() {
		driver.get("http://live.techpanda.org/");

	}

	@Test
	public void TC_01() {
		driver.findElement(myAccount).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");

		driver.findElement(createAccountBtn).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");

	}

	@Test
	public void TC_02() {
		driver.findElement(myAccount).click();
		Assert.assertEquals(driver.getTitle(), "Customer Login");

		driver.findElement(createAccountBtn).click();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}

	@Test
	public void TC_03() {
		driver.findElement(myAccount).click();
		driver.findElement(createAccountBtn).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");

		driver.navigate().back();

		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");

		driver.navigate().forward();

		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}

	@Test
	public void TC_04() {
		driver.findElement(myAccount).click();
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));

		driver.findElement(createAccountBtn).click();
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
