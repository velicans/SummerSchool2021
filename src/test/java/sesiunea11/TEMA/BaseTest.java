package sesiunea11.TEMA;

// ANDREI PAVEN - Tema sesiunea 11 - Selenium WebDriver

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class BaseTest {

    public static final String GRAPE_NAME = "Italian_northern";

    @Test
    public void test() throws InterruptedException {


        // Instantiate page object and Selenium Chrome Webdriver

        WineyardPO page = new WineyardPO();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://wineappui.azurewebsites.net/");
        Thread.sleep(2000);


        // Find Add Grapes Button and click it

        driver.findElement(By.cssSelector(page.addGrapes_Btn)).click();
        Thread.sleep(2000);


        // Clear the name field and fill it with required text

        driver.findElement(By.cssSelector(page.name)).clear();
        driver.findElement(By.cssSelector(page.name)).sendKeys(GRAPE_NAME);
        Thread.sleep(2000);


        // Define Selector for quantity dropdown menu and select value

        Select quantitySelect = new Select(driver.findElement(By.cssSelector(page.quantity)));
        quantitySelect.selectByVisibleText("48");
        Thread.sleep(2000);


        // Define Selector for unit dropdown menu and select value

        Select unitSelect = new Select(driver.findElement(By.cssSelector(page.unit)));
        unitSelect.selectByVisibleText("cases");
        Thread.sleep(2000);


        // Clear the age field and fill it with required text

        driver.findElement(By.cssSelector(page.age)).clear();
        driver.findElement(By.cssSelector(page.age)).sendKeys("40");
        Thread.sleep(2000);


        // Clear the ripeness field and fill it with required text

        driver.findElement(By.cssSelector(page.ripeness)).clear();
        driver.findElement(By.cssSelector(page.ripeness)).sendKeys("98");
        Thread.sleep(2000);


        // Find Submit Button and click it

        driver.findElement(By.cssSelector(page.submit_Btn)).click();
        Thread.sleep(2000);


        // Iterate through a list of found table rows on the Grape page, check to see if a row contains our GRAPE_NAME,
        // find and click its corresponding Pick & crush grapes button

        List<WebElement> rows = driver.findElements(By.cssSelector(page.table_Rows));
        for (WebElement row : rows) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)) {
                row.findElement(By.tagName("button")).click();
                break;
            }
        }
        Thread.sleep(2000);


        // Iterate through a list of found table rows on the Must page, find our GRAPE_NAME on column 1,
        // tick corresponding checkbox on column 0

        List<WebElement> rows_must = driver.findElements(By.cssSelector(page.tableRows_Must));

        for (WebElement row : rows_must) {
            if (row.findElements(By.tagName("td")).get(1).getText().equals(GRAPE_NAME)) {
                row.findElement(By.cssSelector(page.checkBox_Btn)).click();
                break;
            }
        }
        Thread.sleep(2000);


        // Find and click the "Ferment 1 must" button

        driver.findElement(By.cssSelector(page.ferment_Btn)).click();
        Thread.sleep(2000);


        // Iterate through a list of found table rows on the Wine cellar page, find our GRAPE_NAME on column 0,
        // find corresponding "Set name" button and click it and insert required text

        List<WebElement> rows_Wine = driver.findElements(By.cssSelector(page.tableRows_Wine));

        for (WebElement row : rows_Wine) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)) {
                row.findElements(By.tagName("button")).get(0).click();

                Thread.sleep(2000);

                // Set up name
                row.findElement(By.cssSelector(page.wine_Name)).clear();
                row.findElement(By.cssSelector(page.wine_Name)).sendKeys("Tempranillo_Red");

                // Press the OK button for saving name
                row.findElement(By.cssSelector(page.ok_Btn_1)).click();
                Thread.sleep(2000);
                break;
            }
        }


        // Iterate through a list of found table rows on the Wine cellar page, find our GRAPE_NAME on column 0,
        // find corresponding "Set bottlingVolume" button, click it and insert required value

        rows_Wine = driver.findElements(By.cssSelector(page.tableRows_Wine));

        for (WebElement row : rows_Wine) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)) {
                row.findElements(By.tagName("button")).get(0).click();

                Thread.sleep(2000);

                // Set the bottling volume
                row.findElement(By.cssSelector(page.bottling_Volume)).clear();
                row.findElement(By.cssSelector(page.bottling_Volume)).sendKeys("0,75L");

                // Press the OK button for saving the bottling volume
                row.findElement(By.cssSelector(page.ok_Btn_2)).click();
                Thread.sleep(2000);
                break;
            }
        }
        Thread.sleep(2500);

        driver.quit();
    }
}