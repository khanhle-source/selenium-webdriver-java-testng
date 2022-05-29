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

public class Topic_10_Execise_Textbox_TextArea2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String employeeID;
	
	
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
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		
		//open add employee link
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
		//input firstname and lastname
		driver.findElement(By.id("firstName")).sendKeys("Khanh");
		driver.findElement(By.id("lastName")).sendKeys("Le");
		employeeID = driver.findElement(By.id("employeeId")).getText();
		
		//click Save button
		driver.findElement(By.id("btnSave")).click();
		
		//verify disable field
		Assert.assertFalse(driver.findElement(By.id("personal_txtEmpFirstName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.id("personal_txtEmpLastName")).isEnabled());	
		Assert.assertFalse(driver.findElement(By.id("personal_txtEmployeeId")).isEnabled());
		
		//Verify firstname/lastname/employeeID is display correct
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmpFirstName")).getAttribute("value"), "Khanh");
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmpLastName")).getAttribute("value"), "Le");
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmployeeId")).getText(), employeeID);
		
		//click edit button
		driver.findElement(By.id("btnSave")).click();
		
		//edit firstname/ lastname
		driver.findElement(By.id("personal_txtEmpFirstName")).clear();
		driver.findElement(By.id("personal_txtEmpLastName")).clear();
		driver.findElement(By.id("personal_txtEmpFirstName")).sendKeys("Khanh QA");
		driver.findElement(By.id("personal_txtEmpLastName")).sendKeys("Le QA");
		driver.findElement(By.id("btnSave")).click();
		
		//Verify value
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmpFirstName")).getAttribute("value"), "Khanh QA");
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmpLastName")).getAttribute("value"), "Le QA");
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
