import framework.driver.Driver;
import framework.util.MyLogger;
import framework.util.Screenshoter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
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

    @AfterMethod
    public void screenshotFailedCases(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                Screenshoter.makeFullPageScreenshot(driver, result.getName());
            } catch (Exception e) {
                MyLogger.error("Exception while taking screenshot " + e.getMessage());
            }
        }
    }

    @AfterClass
    public void quit() {
        Driver.closeBrowser(driver);
        MyLogger.info("driver was closed");
    }

}
