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

public class Topic_08_Execise_WebElement {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	//khong the khai bao bien cuc bo element (nhu ben duoi) duoc vi bien driver van chua duoc khoi tao. (no duoc khoi tao o doan code driver = new FirefoxDriver()
	//Do do, khi run test se bi loi la bien chua duoc khai bao
	/*WebElement element = driver.findElement(By.xpath("")); */
	
	//chi co the khai bao bien By nhu ben duoi
	By fullnameTextBox = By.id("txtFirstName");
	By emailTextBox = By.id("txtEmail");
	By confirmEmailTextBox = By.id("txtcEmail");
	By passwordTextBox = By.id("txtPassword");
	By confirmPasswordTextBox = By.id("txtcPassword");
	By phoneTextBox = By.id("txtPhone");
	By SubmitButton = By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']");
	
	By errorFirstname = By.xpath("//label[@id='txtFirstname-error']");
	
	String fullname, email, cEmail, password, phone;
	
	// ham beforeClass se chay truoc TAT CA (ALL) test
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver ();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//chi co the set gia tri cho fullname/email/password/phone o trong class beforeClass boi vi
		//chi co the khai bao o global va khoi tao trong beforeClass 
		// hoac khai bao + khoi tao luon o global Eg. String fullname = "le nhu khanh" 
		// chu khong chap nhan viec khai bao xong roi cham phay ; roi khoi tao o global Eg. String fullname; fullname = "le nhu khanh";
		// xem them o phut 1h43:  https://www.youtube.com/watch?v=tzeK78lSSEQ&list=PLo1QA-RK2zyryB4iZO_cXYF9OAW5SPUlt&index=19&ab_channel=AutomationFC
		fullname = "le nhu khanh";
		email = "nhukhanhle@gmail.com";
		cEmail = "nhukhanhle@gmail.com";
		password = "12345678";
		phone = "0123456789";
		
	}

	// ham beforeMethod se chay truoc MOI (EACH) test
	@BeforeMethod 
	public void beforeMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}
	
	@Test
	public void TC_01() {
		driver.findElement(fullnameTextBox).sendKeys("");
		driver.findElement(emailTextBox).sendKeys("");
		driver.findElement(confirmEmailTextBox).sendKeys("");
		driver.findElement(confirmPasswordTextBox).sendKeys("");
		driver.findElement(phoneTextBox).sendKeys("");
		driver.findElement(SubmitButton).click();
		
		Assert.assertEquals(driver.findElement(errorFirstname).getText(), "Vui lòng nhập email");
		
		
		
		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
