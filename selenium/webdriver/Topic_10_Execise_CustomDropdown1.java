package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_10_Execise_CustomDropdown1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explixitWait;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		//vi day la class nen phai new
		driver = new FirefoxDriver ();
		
		// wait cho cac trang thai cua element: visible/presence/ invisbible/ staleness
		// vi WebDriverWait class nen fai new (hover chuot vao se thay chu C)
		explixitWait = new WebDriverWait(driver, 30);
		
		//ep kieu WebDriver thanh JavascriptExecutor
		jsExecutor = (JavascriptExecutor) driver ;
		
		// wait cho viec tim element, neu ko tim dc thi cu 0.5s goi lai ham nay cho toi khi het 30s
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
	
	//ham select item 
	public void selectItemInDropdown (String parentLocator, String itemLocator, String expectedItem)
	{
		// 1 - Click vao the cha de xo ra het tat ca cac item trong dropdown ra
		explixitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(parentLocator)));
		driver.findElement(By.cssSelector(parentLocator)).click();
		
		// 2 - Cho cho tat ca item trong dropdown hien len
		explixitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(itemLocator)));
		
		// 3 - Lay het tat ca cac item nay dua vao 1 List Element
		List<WebElement> allItems = driver.findElements(By.cssSelector(itemLocator));
		
		// 4 - Duyet qua tung item
		for (WebElement item : allItems)
		{
			// 5 - Moi lan duyet kiem tra xem text cua item do co bang voi item minh can click hay ko
			String actualItem = item.getText();

			// Neu bang thi click vao va thoat khoi trinh duyet
			// Neu nhu khong bang thi duyet tiep cho den het cac item
			if (actualItem.equals(expectedItem)) {
				// truoc khi click thi scroll den element 
				// scrollIntoView (true) : scroll toi mep tren cua element
				// scrollIntoView (false): scroll toi mep duoi cua element
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(2);
				
				// wait cho element clickable 
				explixitWait.until(ExpectedConditions.elementToBeClickable(item));
				item.click();
				sleepInSecond(2);
				break;
				// phai co break vi neu chay cho toi cuoi thi cung vo nghia, va do ton tai nguyen
				// tuy business: sau khi click xong cac item khac thuong se disable, neu khong break ma chay tiep se bi tra ve null > vo nghia
			}
			
		}
		sleepInSecond(3);
	}
	
	//ham enter keyword in dropdownlist
		public void enterItemDropdownList (String parentLocator, String itemLocator, String expectedItem)
		{
			// 1 - Click vao the cha de xo ra het tat ca cac item trong dropdown ra
			explixitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(parentLocator)));
			driver.findElement(By.cssSelector(parentLocator)).sendKeys(expectedItem);
			
			// 2 - Cho cho tat ca item trong dropdown hien len
			explixitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(itemLocator)));
			
			// 3 - Lay het tat ca cac item nay dua vao 1 List Element
			List<WebElement> allItems = driver.findElements(By.cssSelector(itemLocator));
			
			// 4 - Duyet qua tung item
			for (WebElement item : allItems)
			{
				// 5 - Moi lan duyet kiem tra xem text cua item do co bang voi item minh can click hay ko
				String actualItem = item.getText();

				// Neu bang thi click vao va thoat khoi trinh duyet
				// Neu nhu khong bang thi duyet tiep cho den het cac item
				if (actualItem.equals(expectedItem)) {
					// truoc khi click thi scroll den element 
					// scrollIntoView (true) : scroll toi mep tren cua element
					// scrollIntoView (false): scroll toi mep duoi cua element
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					sleepInSecond(2);
					
					// wait cho element clickable 
					explixitWait.until(ExpectedConditions.elementToBeClickable(item));
					item.click();
					sleepInSecond(2);
					break;
					// phai co break vi neu chay cho toi cuoi thi cung vo nghia, va do ton tai nguyen
					// tuy business: sau khi click xong cac item khac thuong se disable, neu khong break ma chay tiep se bi tra ve null > vo nghia
				}
				
			}
			sleepInSecond(3);
		}

	public void sleepInSecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void TC_01_JQuery() {
		// open url
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		//click and select speed
		String parent = "span#speed-button > span.ui-selectmenu-icon";
		//Chu y 1: locator chua het tat ca cac items
		//Chu y 2: locator phai den node cuoi cung chua text (vi de get text)
		String itemLocator = "ul#speed-menu li div";
		String expectedItem = "Slow";
		selectItemInDropdown (parent, itemLocator, expectedItem);
		Assert.assertEquals(driver.findElement(By.cssSelector("#speed-button span.ui-selectmenu-text")).getText(), "Slow");
		
		//click and select number
		selectItemInDropdown ("span#number-button span.ui-selectmenu-icon", "ul#number-menu li.ui-menu-item div", "19");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "19");
		
	}
	@Test
	public void TC_02_ReactJS ()
	{
		//open URL 
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		//click and select name
		String parent1 = "i.dropdown.icon"; //element nay co class la dropdown icon (co dau cach), de chuyen thanh css thi thay dau cach thanh dau .
		String itemLocator1 = "div.visible.menu.transition div span.text";
		String expectedItem1 = "Matt";
		
		selectItemInDropdown (parent1, itemLocator1, expectedItem1);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");
	}
	
	@Test 
	public void TC_03_Vue () 
	{
		//open URL
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		// click and select name 
		String parent2 = "span.caret";
		String itemLocator2 = "ul.dropdown-menu li a";
		String expectedItem2 = "Second Option";
		selectItemInDropdown (parent2, itemLocator2, expectedItem2);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");

		
	} 
	
	@Test 
	public void TC_04_Angular ()
	{
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		
		String parent3 = "ng-select[bindvalue='provinceCode'] span.ng-arrow-wrapper";
		String itemLocator3 = "div.ng-dropdown-panel-items.scroll-host div span.ng-option-label.ng-star-inserted";
		String expectedItem3 = "Tỉnh Quảng Nam";
		
		selectItemInDropdown (parent3, itemLocator3, expectedItem3);
		
		//Text khong nam trong HTML (view more attribute o tab Properties
		//1. Use javascript
		String actualText = (String) jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='provineCode'] span.ng-value-label\").innerText");
		Assert.assertEquals(actualText, "Tỉnh Quảng Nam");

		//2. Use getText()
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getText(), "Tỉnh Quảng Nam");

		//3. Use getAttribute()
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getAttribute("innerText"), "Tỉnh Quảng Nam");

		
	}

	@Test 
	public void TC_05_EnterDropdown_react ()
	{
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		enterItemDropdownList ("input.search" , "div.visible.menu.transition span", "Algeria");
	}
	
	@Test 
	public void TC_06_DefaultDropdown ()
	{
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		selectItemInDropdown ("select[name='DateOfBirthDay']", "select[name='DateOfBirthDay'] option ", "10");
		Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']/option[text()='10']")).isSelected());
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	} 
}
