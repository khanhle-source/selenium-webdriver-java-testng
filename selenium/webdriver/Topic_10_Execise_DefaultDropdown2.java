package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_10_Execise_DefaultDropdown2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	By firstName = By.id("FirstName");
	By lastName = By.id("LastName");
	By dateofBirthday = By.name("DateOfBirthDay");
	By monthofBirthday = By.name("DateOfBirthMonth");
	By yearofBirthday = By.name("DateOfBirthYear");
	By email = By.id("Email");
	
	//khoi tao
	Select select;
	WebDriverWait explixitWait;
	Actions action;
	
	//khoi tao
	String Email, password, confirmPassword;
	
	public int random () {
		Random rnd = new Random();
		return rnd.nextInt(999);
	} 
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver ();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//khai bao
		action = new Actions(driver);
		explixitWait = new WebDriverWait(driver, 2);
		
		Email = "nhukhanh" + random() + "@yahoo.com";
		password = "123456789";
		confirmPassword = "123456789";
		
	}
	
	@BeforeMethod 
	public void beforeMethod () {
		driver.get("https://demo.nopcommerce.com/register");
		
	}
	
	

	@Test
	public void TC_01() {
		//input Firstname and Last name
		driver.findElement(firstName).sendKeys("Khanh");
		driver.findElement(lastName).sendKeys("Le");
		//khai bao new Select cho element date 
		select = new Select(driver.findElement(dateofBirthday));
		select.selectByVisibleText("9");
		//Verify dropdown is selected correct value
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "9");
		
		//khai bao new Select cho element month
		select = new Select(driver.findElement(monthofBirthday));
		select.selectByVisibleText("March");
		//Verify dropdown is selected correct value
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "March");
		
		//khai bao new Select cho element year
		select = new Select (driver.findElement(yearofBirthday));
		select.selectByVisibleText("1994");
		//Verify dropdown is selected correct value
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1994");
		
		//input email/ pass/ confirm pass and click Submit button
		driver.findElement(email).sendKeys(Email);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(confirmPassword);
		driver.findElement(By.name("register-button")).click();
		
		// Verify output
		Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");
		driver.findElement(By.xpath("//a[@class='button-1 register-continue-button']")).click();
		driver.findElement(By.className("ico-account")).click();
		
		// khai bao lai select ( vi load lai trang nen gia tri select truoc do bi mat, can phai new lai
		// select cua field date
		select = new Select (driver.findElement(dateofBirthday));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "9");
		//select cua field month
		select = new Select (driver.findElement(monthofBirthday));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "March");
		//select cua field year
		select = new Select (driver.findElement(yearofBirthday));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1994");
		
		
		
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
