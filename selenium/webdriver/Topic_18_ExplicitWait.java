package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_18_ExplicitWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	String file1 = projectPath + File.separator + "browserDrivers" + File.separator +"FileUpload" + File.separator + "Screenshot.png";
	String file2 = projectPath + File.separator + "browserDrivers" + File.separator +"FileUpload" + File.separator + "AutomationText";

	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		//driver = new FirefoxDriver ();

		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();

		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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

	public void TC_04_05 () {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		// wait cho loading invisibility
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

		//wait cho text Hello World visibility
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div#finish>h4")));

		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

	}


	public void TC_06 () {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		// wait cho calendar display
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.calendarContainer")));

		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),"No Selected Dates to display.");

		//wait cho date pick successful and click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='20']"))).click();

		//wait cho date loading icon bien mat
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar']>div.raDiv")));

		Assert.assertEquals(driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='20']")).getText(), "20");
	}

	@Test
	public void TC_07 () {
		explicitWait = new WebDriverWait(driver, 90);
		driver.get("https://gofile.io/?t=uploadFiles");

		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(file1);
		//wait cho progress bar mat di
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));
		//wait cho message success load len thanh cong

		WebElement uploadedText = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		Assert.assertTrue(uploadedText.isDisplayed());

	}



	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
