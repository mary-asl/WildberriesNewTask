package framework.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class Screenshoter {

    private static final String SCREENSHOTS_NAME_TPL = "./screenshots/";

    public static void makeScreenshot(WebDriver driver) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File copy = new File(SCREENSHOTS_NAME_TPL + System.nanoTime() + ".png");
            FileUtils.copyFile(screenshot, copy);
        } catch (IOException e) {
            MyLogger.error("Failed to make screenshot");
        }
    }

    public static void makeFullPageScreenshot(WebDriver driver, String testName) {
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        try {
            Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1500)).takeScreenshot(augmentedDriver);
            ImageIO.write(screenshot.getImage(),"PNG",new File(SCREENSHOTS_NAME_TPL + testName + System.nanoTime() + ".png"));
            MyLogger.info("full page screenshot: " + SCREENSHOTS_NAME_TPL + testName + System.nanoTime() + " was made");
        }
        catch(IOException e) {
            MyLogger.error("Failed to make screenshot");
        }
    }
}
