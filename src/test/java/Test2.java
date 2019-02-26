import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Test2 {

    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments("start-maximized");
        driver=new ChromeDriver(options);
        driver.get("http://localhost/litecart/");

    }
    @After
    public void teardown(){
        driver.quit();
    }

    @Test
    public void test(){


        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element;
        for (int i=0; i<3; i++) {
            element = driver.findElement(By.cssSelector("a img[title='My Store']"));
            element.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li#rslides1_s0")));
            element = driver.findElement(By.cssSelector("a[title='Yellow Duck']"));

            element.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select[required='required']")));
            element = driver.findElement(By.cssSelector("select[required='required']"));
            element.click();

            Select select = new Select(driver.findElements(By.cssSelector("select[required='required']")).get(0));
            select.selectByVisibleText("Small");

            element = driver.findElement(By.cssSelector("td.quantity button[type='submit']"));
            element.click();
            String value=(i+1)+"";
            wait.until(ExpectedConditions.attributeContains(By.cssSelector("a.content span.quantity"), "textContent", value));
        }
        element = driver.findElement(By.cssSelector("div#cart a.link"));
        element.click();

        for (int i=2; i>0; i--) {
            element = driver.findElement(By.cssSelector("input[type='number']"));
            element.clear();
            String value=i+"";
            element.sendKeys(value);
            element = driver.findElement(By.cssSelector("button[name='update_cart_item']"));
            element.click();

            wait.until(ExpectedConditions.attributeContains(By.cssSelector("table[class='dataTable rounded-corners'] tbody tr:nth-child(2) td:first-child"), "textContent", value));
        }
        element = driver.findElement(By.cssSelector("input[type='number']"));
        element.clear();
        String value="0";
        element.sendKeys(value);
        element = driver.findElement(By.cssSelector("button[name='update_cart_item']"));
        element.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//em[contains(text(), 'There are no items in your cart.')]")));
        element = driver.findElement(By.xpath("//a[contains(text(), '<< Back')]"));
        element.click();
        wait.until(ExpectedConditions.attributeContains(By.cssSelector("a.content span.quantity"), "textContent", "0"));
    }

}
