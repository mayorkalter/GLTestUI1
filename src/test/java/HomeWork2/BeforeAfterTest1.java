package HomeWork2;

import commonLibs.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BeforeAfterTest1 {
WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments("start-maximized");
        driver=new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        ConfigReader reader = new ConfigReader();
        String url = reader.getProp("QAresources", "adminUrl");
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='text']")));
        WebElement login = driver.findElement(By.cssSelector("input[type='text']"));
        WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
        WebElement enter = driver.findElement(By.cssSelector("button[type='submit']"));
        login.sendKeys("admin");
        password.sendKeys("admin");
        enter.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.logotype")));


    }
    @After
    public void teardown(){
        driver.quit();
    }


}
