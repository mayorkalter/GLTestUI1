import commonLibs.CommonFunc;
import commonLibs.ConfigReader;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestHelp extends BeforeAfterTest2 {
    @Test
    public void test2(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        ConfigReader reader = new ConfigReader();
        CommonFunc commonFunc= new CommonFunc();
        String url = reader.getProp("QAresources", "shopUrl");
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='Real Duck']")));

        commonFunc.addItems("Real Duck",1,driver);
        commonFunc.addItems("Green Duck",2,driver);
        //commonFunc.addItems("Blue Duck",1,driver);
        commonFunc.deleteAllItems(driver);

    }
}
