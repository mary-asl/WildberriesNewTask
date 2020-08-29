import framework.driver.Driver;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseForAllTests {

    private static final String BASE_URL = "https://www.wildberries.kz";
    private Logger logger = LogManager.getLogger();

    WebDriver driver;

    public BaseForAllTests() throws Exception {
        this.driver = Driver.getWebDriver();
    }

    @BeforeClass
    public void openWebDriver() {
        try {
            driver.get(BASE_URL);
            RestAssured.baseURI = BASE_URL;
        } catch (WebDriverException e) {
            logger.error("WebDriverException occured");
        }
    }

    @AfterClass
    public void quit() {
        Driver.closeBrowser(driver);
    }

}
