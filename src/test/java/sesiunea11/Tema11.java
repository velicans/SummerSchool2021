package sesiunea11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Tema11 {
    public static final String GRAPE_NAME = "Grapes";

    @Test
    public void test() throws InterruptedException {
        WineyardPO page = new WineyardPO();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        //driver.manage().window().maximize();
        driver.get("https://wineappui.azurewebsites.net/");

        Thread.sleep(1000);
        // clicks on Add grapes button

        driver.findElement(By.cssSelector(page.addGrapesBtn)).click();

        Thread.sleep(1000);

        driver.findElement(By.cssSelector(page.name)).clear();
        driver.findElement(By.cssSelector(page.name)).sendKeys(GRAPE_NAME);
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(page.age)).clear();
        driver.findElement(By.cssSelector(page.age)).sendKeys("10");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(page.ripeness)).clear();
        driver.findElement(By.cssSelector(page.ripeness)).sendKeys("90");
        Thread.sleep(1000);

        // define select for quantity dropdown
        Select quantitySelect = new Select(driver.findElement(By.cssSelector(page.quantity)));
        quantitySelect.selectByVisibleText("24");
        Thread.sleep(1000);

        // define select for unit dropdown
        Select unitSelect = new Select(driver.findElement(By.cssSelector(page.unit)));
        unitSelect.selectByVisibleText("barrows");
        Thread.sleep(1000);

        driver.findElement(By.cssSelector(page.submitBtn)).click();

        Thread.sleep(3000);

        List<WebElement> rows = driver.findElements(By.cssSelector(page.tableRows));
        for (WebElement row : rows) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)) {
                row.findElement(By.tagName("button")).click();
                Thread.sleep(3000); break;

            }
        }
        List<WebElement> rowsMust = driver.findElements(By.cssSelector(page.tableRows));
        for (WebElement row : rowsMust) {
            if (row.findElements(By.tagName("td")).get(1).getText().equals(GRAPE_NAME)) {
                row.findElement(By.cssSelector(page.checkbox)).click();
                break;
            }
        }
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(page.fermentMustBtn)).click();

        List<WebElement> rowsWine = driver.findElements(By.cssSelector(page.tableRows));
        for (WebElement row : rowsWine) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)) {
                row.findElements(By.tagName("td")).get(3).findElement(By.cssSelector("button")).click();
                row.findElement(By.cssSelector("input")).click();
                row.findElement(By.cssSelector("input")).sendKeys("Grapes SC");
                row.findElements(By.tagName("td")).get(3).findElement(By.cssSelector("button")).click();
                break;
            }
        }
            Thread.sleep(1000);

            }
            //driver.quit();


        }