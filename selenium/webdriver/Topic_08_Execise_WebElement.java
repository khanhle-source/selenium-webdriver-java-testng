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
	By email = By.id("mail");
	By under18 = By.id("under_18");
	By education = By.cssSelector("#edu");
	By job1 = By.id("job1");
	By job2 = By.id("job2");
	By interest = By.id("development");
	By slider1 = By.id("slider-1");
	By password = By.id("disable_password");
	By ageDisable = By.id("radio-disabled");
	By languageJava = By.id("java");
	
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
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
	}
	
	@Test
	public void TC_01() {
		Assert.assertTrue(driver.findElement(email).isDisplayed());
		if (driver.findElement(email).isDisplayed()) {
			driver.findElement(email).sendKeys("Automation Testing");
			System.out.println("Email field is displayed");			
		}
		else 
		{
			System.out.println("Email field is not displayed");	
		}
		
		if (driver.findElement(under18).isDisplayed()) {
			driver.findElement(under18).click();
			System.out.println("under 18 is selected");			
		}
		else 
		{
			System.out.println("under 18 is not selected");	
		}
	}
	
	@Test
	public void TC_02() {
		//email
		Assert.assertTrue(driver.findElement(email).isEnabled());
		if (driver.findElement(email).isEnabled()) {
			System.out.println ("email is enable");
		}
		else 
		{
			System.out.println ("email is disable");
		}
		
		//age
		if (driver.findElement(under18).isEnabled()) {
			System.out.println ("Age under 18 is enable");
		}
		else 
		{
			System.out.println ("Age under 18 is disable");
		}
		
		//edu
		if (driver.findElement(education).isEnabled()) {
			System.out.println ("education is enable");
		}
		else 
		{
			System.out.println ("education is disable");
		}
		
		//job1
		if (driver.findElement(job1).isEnabled()) {
			System.out.println ("job1 is enable");
		}
		else 
		{
			System.out.println ("job1 is disable");
		}
		
		//job2
		if (driver.findElement(job2).isEnabled()) {
			System.out.println ("job2 is enable");
		}
		else 
		{
			System.out.println ("job2 is disable");
		}
		
		//interest 
		if (driver.findElement(interest).isEnabled()) {
			System.out.println ("interest development is enable");
		}
		else 
		{
			System.out.println ("interest development is disable");
		}
		
		//slider 1
		if (driver.findElement(slider1).isEnabled()) {
			System.out.println ("slider1 is enable");
		}
		else 
		{
			System.out.println ("slider1 is disable");
		}
		
		//password
		if (driver.findElement(password).isEnabled()) {
			System.out.println ("password is enable");
		}
		else 
		{
			System.out.println ("password is disable");
		}
		
		//age disable 
		if (driver.findElement(ageDisable).isEnabled()) {
			System.out.println ("radio button Age is enable");
		}
		else 
		{
			System.out.println ("Radio button Age is disable");
		}
		
		
	}
	
	@Test
	public void TC_03() {
		driver.findElement(under18).click();
		driver.findElement(languageJava).click();
		if (driver.findElement(under18).isSelected()) {
			System.out.println (" Radio button under 18 is selected");
			
		}
		else {
			System.out.println (" Radio button under 18 is de-selected");
		}
		
		
	}
	
	@Test
	public void TC_04() {
		
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
