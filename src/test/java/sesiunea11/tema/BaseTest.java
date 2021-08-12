package sesiunea11.tema;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class BaseTest {

    public static final String GRAPE_NAME = "Chianti";

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
        driver.findElement(By.cssSelector(page.age)).sendKeys("20");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(page.ripeness)).clear();
        driver.findElement(By.cssSelector(page.ripeness)).sendKeys("98");
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

        //click on Pick & Crush Button

        List<WebElement> rows = driver.findElements(By.cssSelector(page.tableRows));
        for(WebElement row : rows)
        {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)){
                row.findElement(By.tagName("button")).click();
                break;
            }
        }

        Thread.sleep(1000);

        //find and select must

        List<WebElement> rowsMust = driver.findElements(By.cssSelector(page.tableRows));
        for(WebElement row : rowsMust) {
            if(row.findElements(By.tagName("td")).get(1).getText().equals(GRAPE_NAME)){
                row.findElement(By.tagName("input")).click();
                break;
            }
        }

        Thread.sleep(2000);

        // click on Ferment must button

        driver.findElement(By.tagName("button")).click();
        Thread.sleep(2000);

       // driver.findElement(By.cssSelector(page.FermentButton)).click();

        //find the must that was made, click set the name, set the name, press ok

        rows = driver.findElements (By.cssSelector (page.tableRows));
        for(WebElement row : rows)
        {
            //find the must by name, Chianti
            if(row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME))
            {
                row.findElements(By.tagName("button")).get(0).click();

                Thread.sleep(1000);
            //insert must name
                row.findElement(By.tagName("input")).sendKeys("ChiantiMust");

                Thread.sleep(1000);

                //press ok

                row.findElement(By.xpath(page.OkButton)).click();

                Thread.sleep(1000);

                break;
            }

        }

        //find the must
        List<WebElement> rows2 = driver.findElements(By.cssSelector(page.tableRows));
        for(WebElement row : rows2) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)){

                row.findElement(By.xpath(page.SetBottlingVolume)).click();

                Thread.sleep(1000);



            //set bottling volume
                row.findElement(By.tagName("input")).sendKeys("1000ml");

                Thread.sleep(1000);

                row.findElement(By.xpath(page.OkButton2)).click();
                break;
            }
        }



        Thread.sleep(2000);

        driver.quit();
    }
}
