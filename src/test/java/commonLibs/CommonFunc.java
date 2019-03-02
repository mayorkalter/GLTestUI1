package commonLibs;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CommonFunc {
    WebElement element;

    public void addItems(String name, int count, WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        for (int i=0; i<count; i++) {
            element = driver.findElement(By.cssSelector("a img[title='My Store']"));
            element.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li#rslides1_s0")));
            driver.findElement(By.cssSelector("a[title='"+name+"']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1[itemprop='name']")));
            int Acount= Integer.parseInt(driver.findElement(By.cssSelector("a.content span.quantity")).getText());
            element = driver.findElement(By.cssSelector("td.quantity button[type='submit']"));
            element.click();
            String value=(Acount+1)+"";
            wait.until(ExpectedConditions.attributeContains(By.cssSelector("a.content span.quantity"), "textContent", value));
            Assert.assertEquals(value,driver.findElement(By.cssSelector("a.content span.quantity")).getAttribute("textContent"));
        }
    }

    public void deleteAllItems(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(By.cssSelector("div#cart a.link")).click();
        List<WebElement> list = driver.findElements(By.cssSelector("li.shortcut"));
        int sizeOfList=list.size();
        if(sizeOfList==0){
            sizeOfList=1;
        }else {
            driver.findElement(By.cssSelector("li.shortcut:first-child a")).click();
        }
        for(int j=0; j<sizeOfList; j++) {

            int inputValue = Integer.parseInt(driver.findElement(By.cssSelector("input[name='quantity']")).getAttribute("valueAsNumber"));
            String name=driver.findElement(By.cssSelector("table[class='dataTable rounded-corners'] tbody tr:nth-child(2) td:nth-child(2)")).getAttribute("textContent");
            String nextName;
            if(j<sizeOfList-1) {
                nextName = driver.findElement(By.cssSelector("table[class='dataTable rounded-corners'] tbody tr:nth-child(3) td:nth-child(2)")).getAttribute("textContent");
            }else {
                nextName="";
            }
            for (int i = inputValue; i > 0; i--) {
                int Acount = Integer.parseInt(driver.findElement(By.cssSelector("input[name='quantity']")).getAttribute("value"));
                driver.findElement(By.cssSelector("input[type='number']")).clear();
                Acount--;
                String value = Acount+"";
                driver.findElement(By.cssSelector("input[type='number']")).sendKeys(value);
                driver.findElement(By.cssSelector("button[name='update_cart_item']")).click();
                if(Acount>0) {
                    wait.until(ExpectedConditions.attributeContains(By.cssSelector("table[class='dataTable rounded-corners'] tbody tr:nth-child(2) td:nth-child(2)"), "textContent", name));
                    wait.until(ExpectedConditions.attributeContains(By.cssSelector("table[class='dataTable rounded-corners'] tbody tr:nth-child(2) td:first-child"), "textContent", value));
                    Assert.assertEquals(driver.findElement(By.cssSelector("table[class='dataTable rounded-corners'] tbody tr:nth-child(2) td:first-child")).getAttribute("textContent"),value);
                }
                else if(Acount==0&&j<sizeOfList-1){
                    wait.until(ExpectedConditions.attributeContains(By.cssSelector
                            ("table[class='dataTable rounded-corners'] tbody tr:nth-child(2) td:nth-child(2)"),
                            "textContent", nextName));
                    Assert.assertNotEquals(name, nextName);
                }
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//em[contains(text(), 'There are no items in your cart.')]")));
        element = driver.findElement(By.xpath("//a[contains(text(), '<< Back')]"));
        element.click();
        wait.until(ExpectedConditions.attributeContains(By.cssSelector("a.content span.quantity"), "textContent", "0"));
        Assert.assertEquals("0",driver.findElement(By.cssSelector("a.content span.quantity")).getAttribute("textContent"));

    }

    public void addCategory(String name, String code, String photo, String amount , WebDriver driver){
        ConfigReader configReader=new ConfigReader();
        element = driver.findElement(By.cssSelector("a[href='http://localhost/litecart/admin/?app=catalog&doc=catalog']"));
        element.click();
        element = driver.findElement(By.cssSelector("a[href='http://localhost/litecart/admin/?category_id=0&app=catalog&doc=edit_product']"));
        element.click();
        element = driver.findElement(By.cssSelector("input[name='status'][value='1']"));
        element.click();
        element = driver.findElement(By.cssSelector("input[name='name[en]']"));
        element.sendKeys(name);
        element = driver.findElement(By.cssSelector("input[name='code']"));
        element.sendKeys(code);
        element = driver.findElement(By.cssSelector("input[name='categories[]'][value='0']"));
        element.click();
        element = driver.findElement(By.cssSelector("input[name='categories[]'][value='1']"));
        element.click();

//        element = driver.findElement(By.cssSelector("input[name='product_groups[]'][value='1-3']"));
//        element.click();
        element = driver.findElement(By.cssSelector("input[name='quantity']"));
        element.clear();
        element.sendKeys(amount);

        element = driver.findElement(By.cssSelector("input[name='new_images[]']"));
        element.clear();
        element.sendKeys(configReader.photo(photo));

        element = driver.findElement(By.cssSelector("input[name='date_valid_from']"));
        element.clear();
        element.sendKeys("10.02.2019");
        element = driver.findElement(By.cssSelector("input[name='date_valid_to']"));
        element.clear();
        element.sendKeys("20.02.2020");
        element = driver.findElement(By.cssSelector("button[name='save']"));
        element.click();
        String text = driver.findElement(By.xpath(".//tr/td/a[contains(text(), 'Real Duck')]")).getText();
        Assert.assertEquals(text, name);
    }

}
