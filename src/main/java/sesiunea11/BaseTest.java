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


    public static final String RIESLING = "Testttt";

    @Test
    public void test() throws InterruptedException {
        WineyardPO page = new WineyardPO();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://wineappui.azurewebsites.net");

        Thread.sleep(2000);
        //clicks on Add grapes button
        driver.findElement(By.cssSelector(page.addGrapesBtn)).click();

        Thread.sleep(2000);

        //
        driver.findElement(By.cssSelector(page.name)).clear();
        driver.findElement(By.cssSelector(page.name)).sendKeys(RIESLING);
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(page.age)).clear();
        
        driver.findElement(By.cssSelector(page.age)).sendKeys("13");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(page.ripeness)).clear();
        driver.findElement(By.cssSelector(page.ripeness)).sendKeys("97");
        Thread.sleep(2000);

        // define select for quantity dropdown
        Select quantitySelect = new Select(driver.findElement(By.cssSelector(page.quantity)));
        quantitySelect.selectByVisibleText("24");
        Thread.sleep(2000);

        // define select for unit dropdown
        Select unitSelect = new Select(driver.findElement(By.cssSelector(page.unit)));
        unitSelect.selectByVisibleText("barrows");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector(page.submitBtn)).click();

        Thread.sleep(5000);

        List<WebElement> rows = driver.findElements(By.cssSelector(page.tableRows));
        for (WebElement row : rows) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(RIESLING)) {
                row.findElement(By.tagName("button")).click();
                Thread.sleep(1000);
                break;
            }
        }

        driver.findElement(By.xpath("//td[contains(text(),'Testttt')]/preceding-sibling::td/input[@type='checkbox']")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector(page.fermentMust)).click();
        Thread.sleep(3000);

        List<WebElement> winerows = driver.findElements(By.cssSelector(page.wineRows));
        for (WebElement winerow : winerows) {
            if (winerow.findElements(By.tagName("td")).get(0).getText().equals(RIESLING)) {
                winerow.findElement(By.tagName("button")).click();
                Thread.sleep(1000);

                driver.findElement(By.cssSelector(page.setName)).sendKeys("Testttt");
                Thread.sleep(2000);

                winerow.findElement(By.tagName("button")).click();
                Thread.sleep(1000);
                break;
                    }
                }

        List<WebElement> winerows2 = driver.findElements(By.cssSelector(page.wineRows));
        for (WebElement winerow2 : winerows2) {
            if (winerow2.findElements(By.tagName("td")).get(0).getText().equals(RIESLING)) {
                winerow2.findElement(By.tagName("button")).click();
                Thread.sleep(1000);

                driver.findElement(By.cssSelector(page.setName)).sendKeys("1.5L");
                Thread.sleep(2000);

                winerow2.findElement(By.tagName("button")).click();
                Thread.sleep(1000);
                break;
            }
        }
            driver.quit();
        }
    }
