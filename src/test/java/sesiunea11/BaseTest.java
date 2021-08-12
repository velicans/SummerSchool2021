package sesiunea11;

import com.google.gson.internal.$Gson$Preconditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;


import java.util.List;


public class BaseTest {

    public static final String GRAPE_NAME = "VinDuPlec";

    @Test
    public void test() throws InterruptedException {
        WineyardPO page = new WineyardPO();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://wineappui.azurewebsites.net/");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        Thread.sleep(1000);
        // clicks on Add grapes button
        driver.findElement(By.cssSelector(page.addGrapesBtn)).click();

        Thread.sleep(1000);

        driver.findElement(By.cssSelector(page.name)).clear();
        driver.findElement(By.cssSelector(page.name)).sendKeys(GRAPE_NAME);
        driver.findElement(By.cssSelector(page.age)).clear();
        driver.findElement(By.cssSelector(page.age)).sendKeys("15");
        driver.findElement(By.cssSelector(page.ripeness)).clear();
        driver.findElement(By.cssSelector(page.ripeness)).sendKeys("95");

        // define select for quantity dropdown
        Select quantitySelect = new Select(driver.findElement(By.cssSelector(page.quantity)));
        quantitySelect.selectByVisibleText("36");

        // define select for unit dropdown
        Select unitSelect = new Select(driver.findElement(By.cssSelector(page.unit)));
        unitSelect.selectByVisibleText("cases");

        driver.findElement(By.cssSelector(page.submitBtn)).click();

        Thread.sleep(200);

        List<WebElement> rows = driver.findElements(By.cssSelector(page.tableRows));
        for(WebElement row : rows) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)){
                row.findElement(By.tagName("button")).click();
                break;
            }
        }

        Thread.sleep(1000);

        WebElement rowToUse = null;
        List<WebElement> mustRows = driver.findElements(By.cssSelector(page.tableRows));
        for(WebElement mustRow : mustRows) {
            if(mustRow.findElements(By.tagName("td")).get(1).getText().equals(GRAPE_NAME)){
                rowToUse = mustRow;
                break;
            }
        }

        rowToUse.findElement(By.tagName("input")).click();

        Thread.sleep(200);

        driver.findElement(By.tagName("button")).click();

        Thread.sleep(1000);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        List<WebElement> rowsName = driver.findElements(By.cssSelector(page.tableRows));
        for(WebElement rowLast : rowsName) {
            if(rowLast.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)){
                rowLast.findElement(By.tagName("button")).click();
                rowLast.findElement(By.tagName("input")).sendKeys("name");
                rowLast.findElement(By.tagName("button")).click();
                break;
            }
        }

        List<WebElement> rowsVol = driver.findElements(By.cssSelector(page.tableRows));
        for(WebElement rowLast : rowsVol) {
            if(rowLast.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)){
                rowLast.findElement(By.tagName("button")).click();
                rowLast.findElement(By.tagName("input")).sendKeys("vol");
                rowLast.findElement(By.tagName("button")).click();
                break;
            }
        }

        Thread.sleep(2000);
        driver.quit();
    }
}
