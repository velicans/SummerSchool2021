package sesiunea15.helpers;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    private static RemoteWebDriver driver;

    private DriverFactory() {
    }

    public static void setDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    public static RemoteWebDriver getDriver() {
        return driver;
    }

    protected static ChromeOptions getChromeOption() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");

        if (!SystemUtils.IS_OS_WINDOWS && !SystemUtils.IS_OS_MAC) {
            chromeOptions.addArguments("--no-sandbox"); // needed for Ubuntu
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("window-size=1920,1080");
        }

        return chromeOptions;

    }
}