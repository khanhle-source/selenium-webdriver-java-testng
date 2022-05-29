package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_11_CustomRadio_CustomCheckBox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	WebDriverWait explixitWait;
	String osName = System.getProperty("os.name");
	

	@BeforeClass
	public void beforeClass() {
		if (osName.startsWith("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} 
		else {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		
		driver = new FirefoxDriver ();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@BeforeMethod 
	public void beforeMethod () {
	
	}
	
	public void sleepInSecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public int random () {
		Random rnd = new Random();
		return rnd.nextInt(999);
	}

	public void CheckAllCheckbox (List <WebElement> checkboxes) {
		for (WebElement checkbox : checkboxes) {
			if (!checkbox.isSelected()) {
				checkbox.click();
			}
			Assert.assertTrue(checkbox.isSelected());
		}
	}
	
	public void UnCheckAllCheckbox (List <WebElement> checkboxes) {
		for (WebElement checkbox : checkboxes) {
			if (checkbox.isSelected()) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", checkbox);
				checkbox.click();
				sleepInSecond(2);
			}
			Assert.assertFalse(checkbox.isSelected());
		}
	}
	
	public boolean isElementSelected (By by) {
		if (driver.findElement(by).isSelected()) {
		return true;
		}
		else
		return false;
	}
	
	public void clickByJavascript (By by) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
	}

	@Test
	public void TC_01_Custom_Radio () {
		// the input khong co kich thuoc thi duoc goi la custom 
		driver.get("https://material.angular.io/components/radio/examples");
		
		By winterCheckboxInput = By.cssSelector("input[value='Winter']");
		By winterCheckboSpan = By.cssSelector("input#mat-radio-2-input");
		
		// Case 1: Dung the input
		// Selenium click(); -> ElementNotInteractableException
		// isSelected() -> Work
		
		// Case 2: Dung the span
		// Selenium click(); -> Work
		// isSelected() -> Ko work (do no khong phai la the input nen khong select duoc va default tra ve false
		
		// Case 3: Dung the span - click ()
		// Dung2 the input -> isSelected()
		
		//driver.findElement(winterCheckboSpan).click();
		//Assert.assertTrue(driver.findElement(winterCheckboxInput).isSelected());
		
		// Case 4: Dung the input 
		// Javascript - click (quan tam element an/hien)
		// isSelected - verify
		clickByJavascript(winterCheckboxInput);
		Assert.assertTrue(driver.findElement(winterCheckboxInput).isSelected());
	}
	

	
	
	public void TC_02_Custom_Checkbox () {
		By check = By.cssSelector("input#mat-checkbox-1-input");
		By dedeterminate = By.cssSelector("input#mat-checkbox-2-input");
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		//click checkbox and verify
		clickByJavascript(check);
		Assert.assertTrue(driver.findElement(check).isSelected());
		sleepInSecond(1);
		
		//click checkbox and verify 
		clickByJavascript(dedeterminate);
		assertTrue(driver.findElement(dedeterminate).isSelected());
		sleepInSecond(1);
		
		//uncheck checkbox and verify
		clickByJavascript(check);
		Assert.assertFalse(driver.findElement(check).isSelected());
		sleepInSecond(1);
		
		//uncheck checkbox and verify
		clickByJavascript(dedeterminate);
		Assert.assertFalse(driver.findElement(dedeterminate).isSelected());
		sleepInSecond(1);
		
	}
	
	 
	public void TC_03_CustomRadio () {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		By myfamilyRadio = By.xpath("");
		
		clickByJavascript(myfamilyRadio);
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='registerFullname']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='refisterPhoneNumner']")).isDisplayed());
		
		clickByJavascript(myfamilyRadio);
		sleepInSecond(2);
		
		// Verify khi mot element khong hien thi khong the dung isDisplay dc vi thu tu dau tien la find element, sau do moi isDisplay va cuoi cung la so sanh assert True False
		// find element khong ton tai se false ngay
		// Vi the, ta dung size = 0
		Assert.assertEquals(driver.findElements(By.xpath("//input[@formcontrolname='registerFullname']")).size(), 0);
		Assert.assertEquals(driver.findElements(By.xpath("//input[@formcontrolname='refisterPhoneNumner']")).size(), 0);
	}
	
	@Test
	public void TC_04_CustomRadio_Google ()
	{
		//dac thu cua google la khong co the input, chi co the div
		
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		By HaiPhong = By.cssSelector("div[aria-label='Hải Phòng']");
		
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(HaiPhong));
		driver.findElement(HaiPhong).click();
		Assert.assertEquals(driver.findElement(HaiPhong).getAttribute("aria-checked"), "true");
	}
	
	@Test
	public void TC_05_CustomCheckbox_Google () {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		By MyQuang = By.cssSelector("div[data-answer-value=\'Mì Quảng\']");
		
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(MyQuang));
		driver.findElement(MyQuang).click();
		Assert.assertEquals(driver.findElement(MyQuang).getAttribute("aria-checked"), "true");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	} 
}
