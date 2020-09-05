package pageObject;

import framework.util.MyLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class AbstractPage {
    private static final int WAIT_FOR_ELEMENT_SECONDS = 10;
    private WebDriver driver;
    private  WebElement lastClick = null;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    protected WebDriver getDriver() {
        return this.driver;
    }

    public boolean isElementPresent(By locator) {
        return !getWebElements(locator).isEmpty();
    }

    public void waitForElementPresent(By locator) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void waitForElementVisible(By locator) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String getText(By by) {
        MyLogger.info("get text from element located by - " + by);
        return getWebElement(by).getText();
    }

    public WebElement getWebElement(By by) {
        waitForElementVisible(by);
        MyLogger.info("wait for element located by - " + by);
        return driver.findElement(by);
    }

    public List<WebElement> getWebElements(By by) {
        waitForElementVisible(by);
        return driver.findElements(by);
    }

    public void refresh() {
        driver.navigate().refresh();
        MyLogger.info("refreshed current page");
    }

    public void click(By locator) {
        getWebElement(locator).click();
        MyLogger.info("click to element located by: " + locator);
    }

    public void waitForElementEnabled(By locator) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS).until(ExpectedConditions.elementToBeClickable(locator));
        MyLogger.info("wait for element by - " + locator);
    }

    public void redrawElement(By locator) {
        new  WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS).until(ExpectedConditions.
                refreshed(ExpectedConditions.stalenessOf(getWebElement(locator))));
    }

    public void highlightElement(By locator){
        unHighlightElement(getWebElement(locator));
        lastClick = getWebElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor = '" + "yellow" + "'", lastClick);
    }

    public void unHighlightElement(WebElement element){
        if (lastClick!=null){
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor = '" + "none" + "'",  element);
            } catch (StaleElementReferenceException ignored) {
            } finally {
                lastClick = null;
            }
        }
    }

}
