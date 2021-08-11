package sesiunea11.tema;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import sesiunea11.BaseTest;
import sesiunea11.WineyardPO;



import java.util.List;

public class WineyardTest {

    public static final String GRAPE_NAME = "AGG_WINE_TEST";

    @Test
    public void test() throws InterruptedException {
        WineyardPO page = new WineyardPO();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://wineappui.azurewebsites.net/");

        Thread.sleep(3000);
        // clicks on Add grapes button
        driver.findElement(By.cssSelector(page.addGrapesBtn)).click();

        Thread.sleep(2000);

        driver.findElement(By.cssSelector(page.name)).clear();
        driver.findElement(By.cssSelector(page.name)).sendKeys(GRAPE_NAME);
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(page.age)).clear();
        driver.findElement(By.cssSelector(page.age)).sendKeys("15");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(page.ripeness)).clear();
        driver.findElement(By.cssSelector(page.ripeness)).sendKeys("99");
        Thread.sleep(2000);

        // define select for quantity dropdown
        Select quantitySelect = new Select(driver.findElement(By.cssSelector(page.quantity)));
        quantitySelect.selectByVisibleText("36");
        Thread.sleep(2000);

        // define select for unit dropdown
        Select unitSelect = new Select(driver.findElement(By.cssSelector(page.unit)));
        unitSelect.selectByVisibleText("cases");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector(page.submitBtn)).click();

        Thread.sleep(2000);

        // click on 'pick & crush grapes
        List<WebElement> rows = driver.findElements(By.cssSelector(page.tableRows));
        for(WebElement row : rows) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)){
                row.findElement(By.tagName("button")).click();
                break;
            }
        }

        Thread.sleep(2000);

        //find and select must to ferment
        List<WebElement> rowsMust = driver.findElements(By.cssSelector(page.tableRows));
        for(WebElement row : rowsMust) {
            if(row.findElements(By.tagName("td")).get(1).getText().equals(GRAPE_NAME)){
                row.findElement(By.tagName("input")).click();
                break;
            }
        }

        Thread.sleep(2000);

        //click on button ferment must
        driver.findElement(By.tagName("button")).click();
        Thread.sleep(2000);

        //find must, click set name -> set name -> click ok

        List<WebElement> rowsCellar = driver.findElements(By.cssSelector(page.tableRows));
        for(WebElement row : rowsCellar) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)){
                //*[@id="root"]/div/section/table/tbody/tr[69]/td[4]/button
                row.findElement(By.xpath(".//button[.='Set name']")).click();
                Thread.sleep(2000);
                row.findElement(By.tagName("input")).sendKeys("AG_Wine");
                Thread.sleep(2000);
                row.findElement (By.xpath (".//button[.='OK']")).click();
                Thread.sleep(2000);
                break;
            }

        }

        //find must, click set bottling volume -> set volume -> click ok
        List<WebElement> rowsCellar2 = driver.findElements(By.cssSelector(page.tableRows));
        for(WebElement row : rowsCellar2) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)){
                //*[@id="root"]/div/section/table/tbody/tr[69]/td[4]/button
                row.findElement (By.xpath (".//button[.='Set bottlingVolume']")).click();
                Thread.sleep(2000);
                row.findElement(By.tagName("input")).sendKeys("1L");
                Thread.sleep(2000);
                row.findElement (By.xpath (".//button[.='OK']")).click();
                break;
            }
        }

        Thread.sleep(3000);

        driver.quit();
    }

}
