package testng.tech;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Data_Provider {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;
    Actions action;

   @BeforeClass
    public void beforeClass() {
       System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
       driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
       driver.manage().window().maximize();
   }
   @Test(dataProvider ="user_pass")
    public void TC_01_Login (String username, String password) throws InterruptedException {
       driver.get("http://live.techpanda.org/index.php/customer/account/login/");
       driver.findElement(By.id("email")).sendKeys(username);
       driver.findElement(By.id("pass")).sendKeys(password);
       driver.findElement(By.id("send2")).click();

   }

   @DataProvider(name="user_pass")
    public Object [] [] UserAndPasswordData () {
       return new Object [] []{
               {"abc@gmail.com", "1235"},
               {"def@gmail.com", "1238"},
               {"hjk@gmail.com", "1234"}
       };
   }

@AfterClass
    public void afterClass () {
       driver.quit();
}
}

