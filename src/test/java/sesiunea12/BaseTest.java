package sesiunea12;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;


public class BaseTest {

    public static WebDriver driver;

    @BeforeAll
    public static void setUpAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    public static void takeScreenShot(WebDriver driver, String pathToFile) throws Exception {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(pathToFile);
        FileUtils.copyFile(srcFile, destFile);
    }

    @AfterEach
    public void takeScreen(TestInfo testInfo) throws Exception {
        takeScreenShot(driver, "target/screenshots/" + this.getClass().getName() + "/" + testInfo.getDisplayName() + ".png");
    }
}
