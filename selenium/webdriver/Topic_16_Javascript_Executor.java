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

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_16_Javascript_Executor {
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

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public String getDomain (String url) {
		return (String) jsExecutor.executeScript("return document.domain");
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}


	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}




	public void TC_01_techPanda() {
		String url = "http://live.techpanda.org";
		//Step 1: Get url
		navigateToUrlByJS("http://live.techpanda.org");
		sleepInSecond(3);
		//Step 2: Get domain
		Assert.assertEquals(getDomain(url), "live.techpanda.org");

		// get url
		String jsURL = (String) jsExecutor.executeScript("return document.URL");
		System.out.println("url " + jsURL);
		Assert.assertEquals(jsURL, "http://live.techpanda.org/");

		//click on Mobile menu
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(2);
		clickToElementByJS("//a[@title='Samsung Galaxy']");
		sleepInSecond(3);
		clickToElementByJS("//span[text()='Add to Cart']");
		sleepInSecond(3);
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

		//click on customer service
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(3);
		scrollToElementOnDown("//input[@id='newsletter']");
		sleepInSecond(2);

		//input value into newsletter
		sendkeyToElementByJS("//input[@id='newsletter']", "nhukhanh@yahoo.com");
		sleepInSecond(3);
		clickToElementByJS("//span[text()='Subscribe']");
		sleepInSecond(5);
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));


		//navigate to domain http://demo.guru99.com/v4/
		sleepInSecond(3);
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(3);
		Assert.assertEquals(getDomain("http://demo.guru99.com/v4/"), "demo.guru99.com");
		sleepInSecond(3);

	}


	public void TC_02_HTML5 () {
		navigateToUrlByJS("https://login.ubuntu.com/");
		sleepInSecond(3);
		//clickToElementByJS("//button[@id='cookie-policy-button-accept']");
		sleepInSecond(2);
		sendkeyToElementByJS("//input[@class='textType']", "abc");
		clickToElementByJS("//button[@data-qa-id='login_button']");
		sleepInSecond(3);
		getElementValidationMessage("//input[@class='textType']");

	}

	@Test
	public void TC_03_RemoveAtribute () {
		driver.get("http://demo.guru99.com/v4");
		sleepInSecond(3);
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr419809");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("qubYsEd");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		sleepInSecond(2);

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		sleepInSecond(2);
		removeAttributeInDOM("//input[@id='dob']", "type");
		driver.findElement(By.xpath("//input[@id='dob']")).sendKeys("12/12/2022");
		sleepInSecond(3);


	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
