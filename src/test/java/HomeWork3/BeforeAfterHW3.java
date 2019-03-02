package HomeWork3;

import commonLibs.CommonFunc;
import commonLibs.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BeforeAfterHW3 {


    ConfigReader reader = new ConfigReader();
    CommonFunc commonFunc= new CommonFunc();
    WebDriver dr;
    EventFiringWebDriver driver;
    WebEventListener eventListener;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        dr=new ChromeDriver();
        driver=new EventFiringWebDriver(dr);
        eventListener=new WebEventListener();
        driver.register(eventListener);
        String url = reader.getProp("QAresources", "adminUrl");
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, 10);
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
    public void tearDown(){
        driver.quit();
    }

}
