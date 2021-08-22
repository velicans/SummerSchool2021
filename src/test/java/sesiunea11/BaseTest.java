package sesiunea11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class BaseTest {
    public static final String GRAPE_NAME = "Cabernet5";

    @Test
    public void test() throws InterruptedException {

        WineyardPO page = new WineyardPO();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://wineappui.azurewebsites.net/");

        Thread.sleep(2000);
        // clicks on Add grapes button
        driver.findElement(By.cssSelector(page.addGrapesBtn)).click();

        Thread.sleep(2000);

        driver.findElement(By.cssSelector(page.name)).clear();
        driver.findElement(By.cssSelector(page.name)).sendKeys(GRAPE_NAME);
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(page.age)).clear();
        driver.findElement(By.cssSelector(page.age)).sendKeys("40");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(page.ripeness)).clear();
        driver.findElement(By.cssSelector(page.ripeness)).sendKeys("95");
        Thread.sleep(2000);

        // define select for quantity dropdown
        Select quantitySelect = new Select(driver.findElement(By.cssSelector(page.quantity)));
        quantitySelect.selectByVisibleText("48");
        Thread.sleep(2000);

        // define select for unit dropdown
        Select unitSelect = new Select(driver.findElement(By.cssSelector(page.unit)));
        unitSelect.selectByVisibleText("barrows");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector(page.submitBtn)).click();

        Thread.sleep(2000);

        // Pressing the button for our grape- Pick & crush grapes

        List<WebElement> rows = driver.findElements(By.cssSelector(page.tableRows));

        for (WebElement row : rows) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)) {
                row.findElement(By.tagName("button")).click();
                break;
            }
        }
        Thread.sleep(2000);
        //  Select Checkbox for our created grape in "MUST" page

        List<WebElement> rows1 = driver.findElements(By.cssSelector(page.tableRows1));

        for (WebElement row : rows1) {
            if (row.findElements(By.tagName("td")).get(1).getText().equals(GRAPE_NAME)) {
                row.findElement(By.cssSelector("input[type=checkbox]")).click();
                break;
            }
        }

        Thread.sleep(2000);

        // Pressing the button for ferment our  selected wine

        driver.findElement(By.cssSelector(page.fermentBtn)).click();

        Thread.sleep(2000);

        // Pressing the button for setting the name of the wine

        List<WebElement> rows2 = driver.findElements(By.cssSelector(page.tableRows2));

        for (WebElement row : rows2) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)) {
                row.findElements(By.tagName("button")).get(0).click();

                Thread.sleep(2000);

                // Setting the name of our wine

                row.findElement(By.cssSelector(page.wineName)).clear();
                row.findElement(By.cssSelector(page.wineName)).sendKeys("ServeWine");

                // Pressing the button for saving the name of the wine
                row.findElement(By.xpath(page.okBtn)).click();
                Thread.sleep(2000);
                break;
            }
        }
        // Pressing the button for setting bottling volume

        rows2 = driver.findElements(By.cssSelector(page.tableRows2));

        for (WebElement row : rows2) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)) {
                row.findElements(By.tagName("button")).get(0).click();

                Thread.sleep(2000);

                // Setting the bottling volume
                row.findElement(By.cssSelector(page.bottlingVolume)).clear();
                row.findElement(By.cssSelector(page.bottlingVolume)).sendKeys("750 ml");

                // Pressing the button for saving the bottling volume
                row.findElement(By.xpath(page.okBtn)).click();
                Thread.sleep(2000);
                break;
            }
        }
        Thread.sleep(3000);

        driver.quit();
    }

}
