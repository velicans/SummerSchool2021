package sesiunea11_tema;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class BaseTest {

    public static final String GRAPE_NAME = "Tuta Vinery";

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

        Thread.sleep(2000);

        driver.findElement(By.cssSelector(page.name)).clear();
        driver.findElement(By.cssSelector(page.name)).sendKeys(GRAPE_NAME);
        Thread.sleep(1000);

        // define select for quantity dropdown
        Select quantitySelect = new Select(driver.findElement(By.cssSelector(page.quantity)));
        quantitySelect.selectByVisibleText("36");
        Thread.sleep(1000);

        // define select for unit dropdown
        Select unitSelect = new Select(driver.findElement(By.cssSelector(page.unit)));
        unitSelect.selectByVisibleText("cases");
        Thread.sleep(1000);

        driver.findElement(By.cssSelector(page.age)).clear();
        driver.findElement(By.cssSelector(page.age)).sendKeys("20");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(page.ripeness)).clear();
        driver.findElement(By.cssSelector(page.ripeness)).sendKeys("99");
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

        Thread.sleep(1000);


        //homework
        List<WebElement> rows2 = driver.findElements(By.cssSelector(page.tableRows2));

        for(WebElement row : rows2) {
            if(row.findElements(By.tagName("td")).get(1).getText().equals(GRAPE_NAME)){
                row.findElement(By.cssSelector("input[type=checkbox]")).click();
                break;
            }
        }

        Thread.sleep(1000);

        driver.findElement(By.cssSelector(page.fermentButton)).click();

        Thread.sleep(1000);

        // wines

        List<WebElement> rows3 = driver.findElements(By.cssSelector(page.tableRows3));

        for(WebElement row : rows3) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)){
                row.findElements(By.tagName("button")).get(0).click();

                Thread.sleep(1000);

                row.findElement(By.cssSelector(page.WName)).clear();
                row.findElement(By.cssSelector(page.WName)).sendKeys("Rosu de Tuta");

                row.findElement(By.xpath(page.okButton)).click();
                Thread.sleep(1000);

                //row.findElements(By.tagName("button")).get(0).click();
                //Thread.sleep(1000);
                //row.findElement(By.cssSelector(page.WBottlingVolume)).clear();
                //row.findElement(By.cssSelector(page.WBottlingVolume)).sendKeys("5L");
                //row.findElement(By.xpath(page.okButton)).click();
                //Thread.sleep(1000);

                break;
            }
        }

        //List<WebElement> rows3 = driver.findElements(By.cssSelector(page.tableRows3));
        rows3 = driver.findElements(By.cssSelector(page.tableRows3));

        for(WebElement row : rows3) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)){
                row.findElements(By.tagName("button")).get(0).click();

                Thread.sleep(1000);

                row.findElement(By.cssSelector(page.WBottlingVolume)).clear();
                row.findElement(By.cssSelector(page.WBottlingVolume)).sendKeys("5L");

                row.findElement(By.xpath(page.okButton)).click();
                Thread.sleep(1000);
                break;
            }
        }
        Thread.sleep(2000);
        driver.quit();
    }
}
