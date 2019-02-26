import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Test1 extends BeforeAfterTest1 {

    @Test
    public void test(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        List<WebElement> list = driver.findElements(By.cssSelector("span.name"));
        for (int i=1; i<=list.size(); i++){
            WebElement element = driver.findElement(By.cssSelector("li#app-:nth-child("+i+") a"));
            element.click();
            List<WebElement> elements = driver.findElements(By.cssSelector("ul.docs li span.name"));
            if(elements.size()==0){
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));            }
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
