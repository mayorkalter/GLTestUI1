package HomeWork3;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HW3_event extends BeforeAfterHW3{


    @Test
    public void test(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        commonFunc.addCategory("Real Duck", "rd123", "testDuck.jpg", "15",driver);
        String url = reader.getProp("QAresources", "shopUrl");
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='Real Duck']")));
        commonFunc.addItems("Real Duck",1,driver);
        commonFunc.addItems("Green Duck",2,driver);
        commonFunc.addItems("Blue Duck",1,driver);
        commonFunc.deleteAllItems(driver);
    }

}
