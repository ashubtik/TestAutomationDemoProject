package test.framework.pages;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Function;
import io.cucumber.java.AfterAll;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.xpath.operations.Bool;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.log4testng.Logger;
import test.framework.config.Configuration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class BasePage {
    protected WebDriverWait wait;
    private final WebDriver driver;
    private final FluentWait<WebDriver> fluentWait;
    private final JavascriptExecutor jsExecutor;
    protected final static String PAGE_HEADER_XPATH = "//h1";
    protected final static String SUBMIT_BUTTON_XPATH = "//button[@id='submit']";
    protected final static String ADD_CONTACT_BUTTON_XPATH = "//button[@id='add-contact']";


    public BasePage(WebDriver webDriver) {
        this.driver = webDriver;
        this.jsExecutor = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, 10);
        this.fluentWait = (new FluentWait<>(driver))
                .withTimeout(Configuration.getConfig().getFluentWait(), TimeUnit.SECONDS)
                .pollingEvery(Configuration.getConfig().getPollingInterval(), TimeUnit.MILLISECONDS)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotVisibleException.class)
                .ignoring(ElementNotFoundException.class);
    }

    protected void getUrl(String url) {
        log.info("Getting " + url);
        driver.get(url);
    }

    protected void navigateTo(String url) {
        log.info("Navigating to " + url);
        driver.navigate().to(url);
    }

    protected List<WebElement> getElements(String locator) {
        return driver.findElements(By.xpath(locator));
    }

    protected List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    protected WebElement getElement(String locator) {
        return fluentWait.until((Function<? super WebDriver, WebElement>) (driver1) ->
                driver.findElement(By.xpath(locator)));
    }

    protected WebElement getElement(By locator) {
        return fluentWait.until((Function<? super WebDriver, WebElement>) (driver1) -> 
                driver.findElement(locator));
    }
    
    protected boolean isElementDisplayed(String locator) {
        return !getElements(locator).isEmpty();
    }

    protected boolean isElementDisplayed(By locator) {
        return !getElements(locator).isEmpty();
    }

    protected void clickElement(String locator) {
        getElement(locator).click();
    }

    protected void setInputValue(String locator, String value) {
        var element = getElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    protected WebElement waitUntilClickable(String locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
    }

    protected WebElement waitUntilVisible(String locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    protected void waitForPageToLoad() {
        wait.until((ExpectedCondition<Boolean>) input ->
                ((JavascriptExecutor) input).executeScript("return document.readyState").equals("complete"));
    }

    @SneakyThrows
    protected void blackMagic(long seconds) {
        Thread.sleep(seconds * 1000);
    }

    public void closeBrowser() {
        driver.quit();
    }

    public String getToken() {
        blackMagic(1);
        return driver
                .manage()
                .getCookieNamed("token")
                .getValue();
    }
}
