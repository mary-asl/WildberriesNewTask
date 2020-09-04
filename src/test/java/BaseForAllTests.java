import framework.driver.Driver;
import framework.util.MyLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseForAllTests {

    private static final String BASE_URL = "https://www.wildberries.kz";

    WebDriver driver;

    public BaseForAllTests() throws Exception {
        this.driver = Driver.getWebDriver();
    }

    @BeforeClass
    public void openWebDriver() {
        try {
            driver.get(BASE_URL);
            MyLogger.info("driver was opened with URL: " + BASE_URL);
        } catch (WebDriverException e) {
            MyLogger.error("WebDriverException occured");
        }
    }

    @AfterClass
    public void quit() {
        Driver.closeBrowser(driver);
        MyLogger.info("driver was closed");
    }

}
