package sesiunea11;

import com.google.gson.internal.$Gson$Preconditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class BaseTest {

    public static final String GRAPE_NAME = "vinulmeu7";

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

        Thread.sleep(3000);

        List<WebElement> rows = driver.findElements(By.cssSelector(page.tableRows));
        for (WebElement row : rows) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)) {
                row.findElement(By.tagName("button")).click();
                break;
            }

        }
        Thread.sleep(3000);

        driver.findElement(By.cssSelector(page.mustMenu)).click();

        Thread.sleep(3000);

        List<WebElement> rowsMust = driver.findElements(By.cssSelector(page.tableRows));

        int i = 1, j = 1;

        for (WebElement row : rowsMust) {
            if (row.findElements(By.tagName("td")).get(1).getText().equals(GRAPE_NAME)) {
                row.findElement(By.xpath("//tr[" + i + "]//input[@type='checkbox']")).click();
                Thread.sleep(1000);
                row.findElement(By.xpath("//button[contains(text(),'Ferment')]")).click();

                Thread.sleep(2000);

                WebElement tableWInes = driver.findElement(By.className(page.wineTable));
                List<WebElement> rowsWines = tableWInes.findElements(By.cssSelector(page.tableRows));

                for(WebElement row1 : rowsWines){
                    if(row1.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)){

                        row1.findElement(By.cssSelector("button")).click();
                        row1.findElement(By.xpath("//tr["+ j +"]//td[4]//input[@type='text']")).clear();
                        row1.findElement(By.xpath("//tr["+ j +"]//td[4]//input[@type='text']")).sendKeys("numevinulmeu");
                        Thread.sleep(1000);
                        row1.findElement(By.cssSelector("button")).click();


                        /*Thread.sleep(1000);
                        row1.findElement(By.xpath("//button[contains(text(),'Set')]")).click();
                        row1.findElement(By.xpath("//tr["+ j +"]//td[5]//input[@type='text']")).clear();
                        row1.findElement(By.xpath("//tr["+ j +"]//td[5]//input[@type='text']")).sendKeys("volumvinulmeu");
                        Thread.sleep(1000);
                        row1.findElement(By.cssSelector("button")).click();*/

                        Thread.sleep(1000);

                        break;
                    }
                    else
                    {
                        j+=1;
                    }
                }
                break;
            } else {
                i += 1;
            }

        }

        Thread.sleep(1000);

        driver.quit();

    }
}

