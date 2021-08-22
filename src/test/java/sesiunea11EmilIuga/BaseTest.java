package sesiunea11EmilIuga;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;



import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static final String GRAPE_NAME="EmilIuga";
	public static final String BOTTLE_NAME="EmilIugaBottle";
	
	
	
	
	@Test
	public void test()throws InterruptedException{
		
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
		
	        

	        driver.findElement(By.cssSelector(page.name)).clear();
	        driver.findElement(By.cssSelector(page.name)).sendKeys(GRAPE_NAME);
	        Thread.sleep(1000);
	        driver.findElement(By.cssSelector(page.age)).clear();
	        driver.findElement(By.cssSelector(page.age)).sendKeys("5");
	        Thread.sleep(1000);
	        driver.findElement(By.cssSelector(page.ripeness)).clear();
	        driver.findElement(By.cssSelector(page.ripeness)).sendKeys("99");
	        Thread.sleep(1000);
		
	        
	      
	        Select quantitySelect = new Select(driver.findElement(By.cssSelector(page.quantity)));
	        quantitySelect.selectByVisibleText("24");
	        Thread.sleep(1000);

	    
	        Select unitSelect = new Select(driver.findElement(By.cssSelector(page.unit)));
	        unitSelect.selectByVisibleText("rows");
	        Thread.sleep(1000);
	        
	        driver.findElement(By.cssSelector(page.submitBtn)).click();
	        Thread.sleep(2000);
	        
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
	                row.findElement(By.tagName("input")).sendKeys("0.7");
	                Thread.sleep(2000);
	                button = row.findElement(By.tagName("button"));
	                button.click();
	                break;
	            }
	        }

	        driver.quit();
	}
	
	
}
