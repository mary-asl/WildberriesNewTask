package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ItemPage extends AbstractPage {

    private static final By READ_ALL_INFORM_BTN_LOCATOR = By.xpath("//span//div[@class='for-link']/div[@class='c-link-in3-v1']");
    private static final By ACTUAL_ITEM_CATEGORY = By.xpath("//div[@class='params']//div//span[b='Жанры/тематика']/following::span[1]");
    private static final By ITEMS_SIZE_LOCATOR = By.xpath("//label[@data-size-name]");
    private static final By ITEMS_SKU_LOCATOR = By.xpath("//span[@class='j-article']");

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    public String getCategory() {
        return getText(ACTUAL_ITEM_CATEGORY);
    }

    public void selectSize() {
        getWebElement(ITEMS_SIZE_LOCATOR).click();
    }

    public ItemPage readAllInformation() {
        int elementPosition = getWebElement(READ_ALL_INFORM_BTN_LOCATOR).getLocation().getY();
        String js = String.format("window.scroll(0, %s)", elementPosition);
        ((JavascriptExecutor) getDriver()).executeScript(js);
        click(READ_ALL_INFORM_BTN_LOCATOR);
        return this;
    }

    public String getItemsNumber() {
        return getText(ITEMS_SKU_LOCATOR);
    }

}
