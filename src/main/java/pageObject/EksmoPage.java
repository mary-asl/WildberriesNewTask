package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EksmoPage extends AbstractPage {

    private static final By CATEGORIES_LOCATOR = By.id("newsSmallBanners");
    private static final By PSYCHOLOGY_CATEGORY_LOCATOR = By.xpath("//div[@class='small-banners']/div[@class='number-5']");
    private static final By COOKING_CATEGORY_LOCATOR = By.xpath("//div[@class='small-banners']/div[@class='number-4']");

    public EksmoPage(WebDriver driver) {
        super(driver);
    }

    public WebElement findCategoryBanners() {
        return getWebElement(CATEGORIES_LOCATOR);
    }

    public CategoryPage selectCategory(String category) throws Exception {
        switch (category) {
            case ("Psychology"):
                getWebElement(PSYCHOLOGY_CATEGORY_LOCATOR).click();
                break;
            case ("Cooking"):
                getWebElement(COOKING_CATEGORY_LOCATOR).click();
                break;
        }
        return new CategoryPage(getDriver());
    }
}
