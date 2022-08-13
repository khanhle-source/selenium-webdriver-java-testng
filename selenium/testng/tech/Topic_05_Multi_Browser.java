package testng.tech;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Topic_05_Multi_Browser {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;
    Actions action;
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        switch (browserName) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Brower name is not valid");
                break;
        }

       driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
       driver.manage().window().maximize();
   }
   @Test
    public void TC_01_Login() {
       driver.get("http://live.techpanda.org/index.php/customer/account/login/");
       driver.findElement(By.id("email")).sendKeys("khanh@gmail.com");
       driver.findElement(By.id("pass")).sendKeys("12345678");
       driver.findElement(By.id("send2")).click();
   }

@AfterClass
    public void afterClass () {
       driver.quit();
}
}

