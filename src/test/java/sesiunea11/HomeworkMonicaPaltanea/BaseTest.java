package sesiunea11.HomeworkMonicaPaltanea;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import sesiunea11.HomeworkMonicaPaltanea.WineyardPO;

import java.util.List;


public class BaseTest {

    public static final String GRAPE_NAME = "portoTest14";
    public static final String BOTTLE_NAME = "porto name";

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
        driver.findElement(By.cssSelector(page.age)).sendKeys("18");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(page.ripeness)).clear();
        driver.findElement(By.cssSelector(page.ripeness)).sendKeys("95");
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
        for(WebElement row : rows) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)){
                row.findElement(By.tagName("button")).click();
                break;
            }
        }

        Thread.sleep(3000);

        rows = driver.findElements(By.cssSelector(page.tableRows));

        for(WebElement row : rows) {
            if(row.findElements(By.tagName("td")).get(1).getText().equals(GRAPE_NAME)){
                WebElement checkbox = row.findElement(By.tagName("input"));
                System.out.println("Is selected:" + checkbox.isSelected());
                if(!checkbox.isSelected())
                {
                    checkbox.click();
                }
                break;
            }
        }

        Thread.sleep(3000);

        // click on ferment 1 must button
        driver.findElement(By.cssSelector(page.fermentBtn)).click();

        Thread.sleep(3000);

        rows = driver.findElements(By.cssSelector(page.tableRows));
        for(WebElement row : rows) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)){

                WebElement button = row.findElement(By.tagName("button"));
                button.click();
                Thread.sleep(2000);
                row.findElement(By.tagName("input")).sendKeys(BOTTLE_NAME);
                Thread.sleep(2000);
                button = row.findElement(By.tagName("button"));
                button.click();
                Thread.sleep(2000);
                break;
            }
        }

        rows = driver.findElements(By.cssSelector(page.tableRows));
        for(WebElement row : rows) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)){
                WebElement button = row.findElement(By.tagName("button"));
                button.click();
                Thread.sleep(2000);
                row.findElement(By.tagName("input")).sendKeys("0.5");
                Thread.sleep(2000);
                button = row.findElement(By.tagName("button"));
                button.click();
                break;
            }
        }

        driver.quit();
    }
}
