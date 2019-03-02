package HomeWork3;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.io.File;
import java.io.IOException;

public class WebEventListener extends HW3_event implements WebDriverEventListener {


    @Override
    public void beforeAlertAccept(WebDriver driver) {
        System.out.println("Before navigating to: '" + driver + "'");
    }

    @Override
    public void afterAlertAccept(WebDriver driver) {
        System.out.println("Before navigating to: '" + driver + "'");
    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {
        System.out.println("Before navigating to: '" + driver + "'");
    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {
        System.out.println("Before navigating to: '" + driver + "'");
    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        System.out.println("Before navigating to: '" + url + "'");

    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        System.out.println("Navigated to:'" + url + "'");

    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
        System.out.println("Navigating back to previous page");

    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        System.out.println("Navigated back to previous page");

    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
        System.out.println("Navigating forward to next page");
    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
        System.out.println("Navigated forward to next page");

    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("Trying to find Element By : " + by.toString());

    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("Found Element By : " + by.toString());

    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        System.out.println("Trying to click on: " + element.toString());

    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        System.out.println("Clicked on: " + element.toString());

    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        System.out.println("Value of the:" + element.toString()
                + " before any changes made");
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        System.out.println("Element value changed to: " + element.toString());

    }

    @Override
    public void beforeScript(String script, WebDriver driver) {

    }

    @Override
    public void afterScript(String script, WebDriver driver) {

    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {

            try {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File("target/surefire-reports/screenshot.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }


    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {

    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {

    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {

    }
}
