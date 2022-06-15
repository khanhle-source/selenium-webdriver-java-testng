package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_14_Frame_Iframe {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver ();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
	}
	
	@BeforeMethod 
	public void beforeMethod () {
	}
	
	public int random () {
		Random rnd = new Random();
		return rnd.nextInt(999);
	}

	public void sleepInSecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void TC_01_() {
		driver.get("https://kyna.vn/");
		sleepInSecond(3);
		By facebook = By.cssSelector("iframe[src*='//www.facebook.com']");
		WebElement iframe = driver.findElement(facebook);

		//switch to facebook iframe
		driver.switchTo().frame(iframe);

		// verify like facebook
		By like = By.cssSelector("div.lfloat div._1drq");
		Assert.assertEquals(driver.findElement(like).getText(), "166K likes");

		//back to main page
		driver.switchTo().defaultContent();

		//switch to support
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#cs_chat_iframe")));

		//click on support
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();

		//input name/phone
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("le nhu khanh");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0123456789");

		//switch to main page
		driver.switchTo().defaultContent();

		// search
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
		sleepInSecond(3);


	}

	@Test
	public void TC_02_banking () {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		sleepInSecond(3);
		//switch to frame
		driver.switchTo().frame("login_page");

		//input username
		By byUsername = By.xpath("//div[text()='Customer ID/ User ID']/following-sibling::div/input");
		WebElement usrname = driver.findElement(byUsername);
		usrname.sendKeys("khanhle");
		sleepInSecond(2);

		//click on continue button
		driver.findElement(By.cssSelector("a.btn-primary.login-btn")).click();

		//verify password field is display
		Assert.assertTrue(driver.findElement(By.id("fldPasswordDispId")).isDisplayed());
		sleepInSecond(2);

		//switch to main screen
		driver.switchTo().defaultContent();
	}


	public void TC_03_RandomPopup () {

	}


	public void TC_04_Random_Not_In_DOM_Popup ()
	{

	}



	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
