package sesiunea12.Tema;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseTest {

    public static WebDriver driver;

    @BeforeAll
    public static void setUpAll(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }



    @AfterAll
    public static void tearDown(){
        driver.quit();
    }

    public static void takeScreenShot(WebDriver driver, String pathToFile) throws Exception{
        TakesScreenshot printscreen = ((TakesScreenshot) driver);
        File srcPoza = printscreen.getScreenshotAs(OutputType.FILE);
        File destPoza = new File(pathToFile);
        FileUtils.copyFile(srcPoza, destPoza);
    }

    @AfterEach
    public void takeScreenShot(TestInfo testInfo) throws Exception{
        takeScreenShot(driver, "Target/printscreens/" + this.getClass().getName() + "/" + testInfo.getDisplayName() + ".png");
    }
}
