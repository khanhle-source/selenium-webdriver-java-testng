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
import org.testng.annotations.Test;

public class Topic_07_WebElement {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver ();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01() {
		WebElement element = driver.findElement(By.xpath(""));
		
		//clear textbox, text area, editable dropdown
		element.clear();
		
		//input data into textbox, text area, editable dropdown
		element.sendKeys("");
		
		//click button, radio button, checkbox, link, image
		element.click();
		
		//get attribute
		element.getAttribute("placeholder");
		
		//get text 
		element.getText();
		
		//get css value
		element.getCssValue("");
		
		//screenshot element 
		String base64Image = element.getScreenshotAs(OutputType.BASE64);
		element.getScreenshotAs(OutputType.BYTES);
		element.getScreenshotAs(OutputType.FILE);
		
		//get tag name of element
		element = driver.findElement(By.xpath("//input[@id='email']"));
		element = driver.findElement(By.cssSelector("input[id='email']"));
		String elementTagname = element.getTagName();
		
		//element is display or not
		Assert.assertTrue(element.isDisplayed());
		Assert.assertFalse(element.isDisplayed());
		
		//element is enable or not
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());
		
		//element is selected or not
		Assert.assertTrue(element.isSelected());
		Assert.assertFalse(element.isSelected());
		
		//ENTER in keyboard 
		// Only use for form
		element.submit();
		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
