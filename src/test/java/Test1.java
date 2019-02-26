import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Test1 {
    WebDriver driver;
    //WebDriverWait wait = new WebDriverWait(driver, 10);

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments("start-maximized");
        driver=new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("http://localhost/litecart/admin/");
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

    @Test
    public void test(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        List<WebElement> list = driver.findElements(By.cssSelector("span.name"));
        for (int i=1; i<=list.size(); i++){
            WebElement element = driver.findElement(By.cssSelector("li#app-:nth-child("+i+") a"));
            element.click();
            List<WebElement> elements = driver.findElements(By.cssSelector("ul.docs li span.name"));
            if(elements.size()==0){
                List<WebElement> listt = driver.findElements(By.cssSelector("span.name"));
                String title=listt.get(i-1).getText();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), '"+title+"')]")));
            }
            else {
                for (int j=1; j<=elements.size(); j++){
                    element=driver.findElement(By.cssSelector("ul.docs li:nth-child("+j+") span.name"));
                    element.click();
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

                }
            }
        }

    }

}
