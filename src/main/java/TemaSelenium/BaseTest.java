package TemaSelenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import sesiunea11.WineyardPO;

import java.io.File;
import java.io.IOException;

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

    public static void takeScreenShot(WebDriver driver, String PathToFile) throws IOException {
        TakesScreenshot screenshot= ((TakesScreenshot) driver);
        File scrFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(PathToFile);
        FileUtils.copyFile(scrFile,destFile);
    }

    @AfterEach
    public void takeScreen(TestInfo testInfo) throws IOException {
        takeScreenShot(driver, "target/screenshots/" + this.getClass().getName()+"/"+testInfo.getDisplayName()+".png");
    }

}
