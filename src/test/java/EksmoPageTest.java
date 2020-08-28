import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import businessObject.Item;
import pageObject.CategoryPage;
import pageObject.EksmoPage;
import pageObject.HomePage;
import pageObject.ItemPage;

import java.util.*;

public class EksmoPageTest extends BaseForAllTests {

    private static final String EKSMO_PAGE_LINK = "https://www.wildberries.kz/brands/eksmo";

    public EksmoPageTest() throws Exception {
        super();
    }

    @Test(description = "verify that items filtered by discount")
    @Parameters({"category"})
    public void filterByDiscount(@Optional(value = "Psychology") String category) throws Exception {
        CategoryPage categoryPage = new HomePage(driver).clickBrandLogo().selectCategory(category).filterByDiscount();
        List<Double> doubleDiscounts = categoryPage.getItemsDiscount();
        List<Double> sortedDoubleDiscounts = new ArrayList<>(doubleDiscounts);
        Collections.sort(sortedDoubleDiscounts);
        Assert.assertEquals(doubleDiscounts, sortedDoubleDiscounts, "filter by discount does not sort items correctly");
    }

    @Test(description = "verify that items filtered by rate")
    public void filterByRate() throws Exception {
        CategoryPage categoryPage = new CategoryPage(driver).filterByRate();
        List<Integer> integerRates = categoryPage.getItemsRate();
        List<Integer> sortedIntegerRates = new ArrayList<>(integerRates);
        Collections.sort(sortedIntegerRates, Collections.reverseOrder());
        Assert.assertEquals(integerRates, sortedIntegerRates, "filter by rate does not sort items correctly");
    }

    @Test(description = "verify that items filtered by price")
    public void filterByPrice() throws Exception {
        CategoryPage categoryPage = new CategoryPage(driver).filterByPrice();
        List<Integer> integerPrices = categoryPage.getItemsPrice();
        List<Integer> sortedIntegerPrices = new ArrayList<>(integerPrices);
        Collections.sort(sortedIntegerPrices);
        Assert.assertEquals(integerPrices, sortedIntegerPrices, "filter by price does not sort items correctly");
    }

    @Test(description = "Verify that categories are displayed on the page")
    public void verifyDisplayedCategory() throws Exception {
        driver.navigate().to(EKSMO_PAGE_LINK);
        EksmoPage eksmoPage = new EksmoPage(driver);
        Assert.assertTrue(eksmoPage.findCategoryBanners().isDisplayed(), "there are no categories on the shop's page");
    }

    @Test(description = "verify that displayed item corresponds to the selected category",
            dataProvider = "bookCategories")
    public void isCategoryCorrect(String category, String expected) throws Exception {
        Item item = new Item();
        item.setCategory(expected);
        driver.navigate().to(EKSMO_PAGE_LINK);
        CategoryPage categoryPage = new EksmoPage(driver).selectCategory(category);
        ItemPage itemPage = categoryPage.selectItem();
        itemPage.readAllInformation();
        Assert.assertEquals(itemPage.getCategory(), item.getCategory(), "the item category does not match the selected category");
    }

    @DataProvider(name = "bookCategories", parallel = false)
    public Object[][] bookCategories() {
        return new Object[][]{
                {"Psychology", "Психология"},
                {"Cooking", "Кулинария"}
        };
    }
}