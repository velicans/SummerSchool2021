package homeworkS11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Test {

    public static final String GRAPE_NAME = "Adrian's Grape4";

    static WebDriver driver;
    static WineryPO page;

    @BeforeAll
    public static void beforeAll() {
        page = new WineryPO();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @org.junit.jupiter.api.Test
    public void testGrape() throws InterruptedException {

        driver.get("https://wineappui.azurewebsites.net/");
        Thread.sleep(1000);

        // clicks on Add grapes button
        driver.findElement(By.cssSelector(page.addGrapesBtn)).click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector(page.name)).clear();
        driver.findElement(By.cssSelector(page.name)).sendKeys(GRAPE_NAME);
        Thread.sleep(1000);

        // define select for quantity dropdown
        Select quantitySelect = new Select(driver.findElement(By.cssSelector(page.quantity)));
        quantitySelect.selectByVisibleText("12");
        Thread.sleep(1000);

        // define select for unit dropdown
        Select unitSelect = new Select(driver.findElement(By.cssSelector(page.unit)));
        unitSelect.selectByVisibleText("rows");
        Thread.sleep(1000);

        driver.findElement(By.cssSelector(page.age)).clear();
        driver.findElement(By.cssSelector(page.age)).sendKeys("15");
        Thread.sleep(1000);

        driver.findElement(By.cssSelector(page.ripeness)).clear();
        driver.findElement(By.cssSelector(page.ripeness)).sendKeys("96");
        Thread.sleep(1000);

        driver.findElement(By.cssSelector(page.submitBtn)).click();
        Thread.sleep(1000);

        List<WebElement> rows = driver.findElements(By.cssSelector(page.tableRows));
        for (WebElement row : rows) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)) {
                row.findElement(By.tagName("button")).click();
                break;
            }
        }
        Thread.sleep(500);
        driver.quit();
    }

    @org.junit.jupiter.api.Test
    public void testMust() throws InterruptedException {
        driver.get("https://wineappui.azurewebsites.net/");
        driver.findElement(By.linkText("Must")).click();
        Thread.sleep(1000);

        List<WebElement> rows = driver.findElements(By.cssSelector(page.tableRowsMust));
        for (WebElement row : rows) {
            if (row.findElements(By.tagName("td")).get(1).getText().equals(GRAPE_NAME)) {
                row.findElement(By.tagName("input")).click();
                Thread.sleep(5000);
                break;
            }
        }
        driver.findElement(By.tagName("button")).click();
        Thread.sleep(2000);
        driver.quit();
    }

    @org.junit.jupiter.api.Test
    public void testWine() throws InterruptedException {

        driver.get("https://wineappui.azurewebsites.net/");
        driver.findElement(By.linkText("Wines")).click();
        Thread.sleep(1000);

        List<WebElement> rows = driver.findElements(By.cssSelector(page.tableRowsWine));
        for (WebElement row : rows) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)) {
                row.findElement(By.cssSelector(page.setNameBtn)).click();
                Thread.sleep(1000);
                row.findElement(By.cssSelector(page.inputName)).sendKeys("My Best Wine Ever");
                Thread.sleep(1000);
                row.findElement(By.cssSelector(page.setNameOkBtn)).click();
                break;
            }
        }

        rows = driver.findElements(By.cssSelector(page.tableRowsWine2));
        for (WebElement row1 : rows) {
            if (row1.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)) {
                row1.findElement(By.cssSelector(page.setBottlingVolume)).click();
                Thread.sleep(1000);
                row1.findElement(By.cssSelector(page.inputBottlingVolume)).sendKeys("1l");
                Thread.sleep(1000);
                row1.findElement(By.cssSelector(page.setBottlingVolumeOkBtn)).click();
                Thread.sleep(1000);
                break;
            }
        }
        Thread.sleep(2000);
        driver.quit();
    }

}



