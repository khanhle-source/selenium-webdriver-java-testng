package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_10_Execise_Textbox_TextArea_Dropdown_CustomDropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	By email = By.name("uid");
	By password = By.name("password");
	By loginBtn = By.name("btnLogin");
	By createCustomerBtn = By.xpath("//a[text()='New Customer']");
	By customerName = By.name("name");
	By sex = By.xpath("//input[@value='f']");
	By addr = By.name("addr");
	By city = By.name("city");
	By state = By.name("state");
	By PIN =By.name("pinno");
	By phone = By.name("telephoneno");
	By emailAdd = By.name("emailid");
	By passWord = By.name("password");
	By submitBtn = By.name("sub");
	
	By customerID = By.xpath("//td[text()='Customer ID']/following-sibling::td");
	By successMessage = By.cssSelector("p.heading3");
	
	By editCustomerBtn = By.xpath("//a[text()='Edit Customer']");
	
	String CustomerID;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver ();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@BeforeMethod 
	public void beforeMethod () {
		driver.get("https://demo.guru99.com/v4/");
	}
	
	public int random () {
		Random rnd = new Random();
		return rnd.nextInt(999);
	}

	@Test
	public void TC_01_() {
		driver.findElement(email).sendKeys("mngr400749");
		driver.findElement(password).sendKeys("udyvygA");
		driver.findElement(loginBtn).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://demo.guru99.com/v4/manager/Managerhomepage.php");
		
		WebElement a = driver.findElement(createCustomerBtn);
		a.click();
		driver.findElement(customerName).sendKeys("Khanh Le");
		driver.findElement(sex).click();
		
		JavascriptExecutor jsExecutor;
		jsExecutor = (JavascriptExecutor) driver;
		WebElement dob = driver.findElement(By.name("dob"));
		jsExecutor.executeScript("arguments[0].removeAttribute('type')", dob);
		dob.sendKeys("09/03/1994");
		driver.findElement(addr).sendKeys("20 Nhat Chi Mai\n Tan Binh \n TPHCM ");
		driver.findElement(city).sendKeys("HCM");
		driver.findElement(state).sendKeys("HCM");
		driver.findElement(PIN).sendKeys("123456");
		driver.findElement(phone).sendKeys("0355249588");
		driver.findElement(emailAdd).sendKeys("nhukhanh" + random() + "@yahoo.com");
		driver.findElement(passWord).sendKeys("45473589");
		driver.findElement(submitBtn).click();
		
		Assert.assertEquals(driver.findElement(successMessage).getText(), "Customer Registered Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), "Khanh Le");
		
		CustomerID = driver.findElement(customerID).getText();
		
		
		
	}
	
	@Test
	public void TC_02_() {	
		driver.findElement(email).sendKeys("mngr400749");
		driver.findElement(password).sendKeys("udyvygA");
		driver.findElement(loginBtn).click();
		
		driver.findElement(editCustomerBtn).click();
		driver.findElement(By.name("cusid")).sendKeys(CustomerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
