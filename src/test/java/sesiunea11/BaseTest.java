package sesiunea11;

import com.google.gson.internal.$Gson$Preconditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class BaseTest {

    public static final String GRAPE_NAME = "MariusTereu1";

    @Test
    public void test() throws InterruptedException {
        WineyardPO page = new WineyardPO();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://wineappui.azurewebsites.net/");

        Thread.sleep(1000);
        // clicks on Add grapes button
       driver.findElement(By.cssSelector(page.addGrapesBtn)).click();

        Thread.sleep(1000);

        driver.findElement(By.cssSelector(page.name)).clear();
        driver.findElement(By.cssSelector(page.name)).sendKeys(GRAPE_NAME);
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(page.age)).clear();
        driver.findElement(By.cssSelector(page.age)).sendKeys("15");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(page.ripeness)).clear();
        driver.findElement(By.cssSelector(page.ripeness)).sendKeys("99");
        Thread.sleep(1000);

        // define select for quantity dropdown
        Select quantitySelect = new Select(driver.findElement(By.cssSelector(page.quantity)));
        quantitySelect.selectByVisibleText("36");
        Thread.sleep(1000);

        // define select for unit dropdown
        Select unitSelect = new Select(driver.findElement(By.cssSelector(page.unit)));
        unitSelect.selectByVisibleText("cases");
        Thread.sleep(1000);

        driver.findElement(By.cssSelector(page.submitBtn)).click();



        Thread.sleep(1000);

        List<WebElement> rows = driver.findElements(By.cssSelector(page.tableRows));
        for(WebElement row : rows) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)){
                row.findElement(By.tagName("button")).click();
                break;
            }
        }

        Thread.sleep(1000);


        List<WebElement> mustRows = driver.findElements(By.cssSelector(page.tableRows));
        for(WebElement row : mustRows) {
            if(row.findElements(By.tagName("td")).get(1).getText().equals(GRAPE_NAME)){
                row.findElement(By.cssSelector(page.mustCheckbox)).click();
                break;
            }
        }

        Thread.sleep(1000);

        driver.findElement(By.cssSelector(page.fermentBtn)).click();

        Thread.sleep(1000);
        List<WebElement> wineRows = driver.findElements(By.cssSelector(page.tableRows));
        for(WebElement row : wineRows) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)){
                row.findElements(By.tagName("td")).get(3).findElement(By.cssSelector("button")).click();
                Thread.sleep(1000);
                row.findElement(By.cssSelector("input")).click();
                row.findElement(By.cssSelector("input")).sendKeys("struguri bio");
                row.findElements(By.tagName("td")).get(3).findElement(By.cssSelector("button")).click();
                 break;
            }
        }
        Thread.sleep(2000);

        List<WebElement> wineBottles = driver.findElements(By.cssSelector(page.tableRows));
        for (WebElement row :wineBottles) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME) ) {
                row.findElement(By.cssSelector("button")).click();
                Thread.sleep(2000);
                row.findElement(By.cssSelector("input")).click();
                Thread.sleep(1000);
                row.findElement(By.cssSelector("input")).sendKeys("0.85");
                Thread.sleep(1000);
               row.findElement(By.cssSelector("button")).click();
                break;
            }
        }

        Thread.sleep(3000);
        driver.quit();


    }
}
