import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.CategoryPage;
import pageObject.HomePage;
import pageObject.ItemPage;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TextSearchTest extends BaseForAllTests {

    public TextSearchTest() throws Exception {
        super();
    }

    @Test(description = "verify that found item's name contains words from text search",
            dataProvider = "searchingItems")
    public void isItemFound(String searchingItem) throws Exception {
        CategoryPage categoryPage = new HomePage(driver).cleanInputSearch().searchForItem(searchingItem);
        String searchingItemActualName = categoryPage.getSearchingItemName();
        List<String> keyWords = Arrays.asList(categoryPage.getInputValue().split("\\s"));
        boolean asMatch = keyWords.stream().allMatch(keyWord -> searchingItemActualName.isEmpty() ?
                false : StringUtils.containsIgnoreCase(searchingItemActualName, keyWord));

        Assert.assertTrue(asMatch, "expected item is not found");
    }

    @Test(description = "verify that page title changed to searching item's name",
            dataProvider = "searchingItems")
    @Parameters({"searchingItem"})
    public void verifyPageTitle(String searchingItem) throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        new HomePage(driver).cleanInputSearch().searchForItem(searchingItem);
        Assert.assertEquals(driver.getTitle(), searchingItem.toLowerCase(), "incorrect page title");
    }

    @Test(description = "Write in the text search six numbers - find a product with the matching SKU")
    public void searchingSixRandomNumbers() throws Exception {
        ItemPage itemPage = new ItemPage(driver);
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        String numberAsString = String.format("%06d", number);
        new HomePage(driver).cleanInputSearch().searchForItem(numberAsString);
        String actualItemsNumber = itemPage.getItemsNumber();
        Assert.assertEquals(actualItemsNumber, numberAsString, "the item number doesn't match the number which was requested in the search");
    }

    @DataProvider(name = "searchingItems", parallel = false)
    public Object[][] serchingItems() {
        return new Object[][]{
                {"funko pop star wars"},
                {"POLO SHIRT"}
        };

    }
}
