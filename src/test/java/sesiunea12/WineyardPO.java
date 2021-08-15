package sesiunea12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WineyardPO {

    public WebDriver driver;
    public WebDriverWait wait;

    public String addGrapesBtn = "button.animated-button";
    public String name = "input#name";
    public String quantity = "select#quantity";
    public String unit = "select#unit";
    public String age = "input#age";
    public String ripeness = "input#ripeness";
    public String submitBtn = "input[value='Submit']";
    public String tableRows = "table > tbody >tr";
    public String mustRows = "table > tbody >tr";
//    public String fermentBtn = "button";
    public String wineRows = "table > tbody >tr";
    public String wineRows2 = "table > tbody >tr";
    public String check="table>tbody>tr>td>input";
    public String pick="//*[@id=\"root\"]/div/section/table/tbody/tr[50]/td[5]/button";
    public static final String ALL_ROWS = "//ul/li[2]";
    public static final String TYPE_GRAPE = "//ul/li[1]";
    public static final String MUST_COUNT = "//ul/li[1]";
    public static final String MUST_TOTAL = "//ul/li[2]";
    public static final String WINE_COUNT = "//ul/li[1]";
    public static final String VOLUME = "//ul/li[2]";
    
    
    public WineyardPO(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public boolean amOnHomePage() {
        boolean isHomePageOpened = true;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(addGrapesBtn)));
        if (!driver.findElement(By.cssSelector(addGrapesBtn)).isDisplayed())
            isHomePageOpened = false;
        return isHomePageOpened;
    }

    public int countWines(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));
        return list.size();
    }
    
    
    
    public int Rows() {
    	
    	  wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
    	  
    	  int nr=0;
    	  
    	  String s;
    	  
    	  List<WebElement> list=driver.findElements(By.cssSelector(tableRows));
    	  
    	  for(WebElement row:list) {
    		  
    		  s=row.findElements(By.tagName("td")).get(1).getText();
    		  nr=nr+Integer.parseInt(s.replaceAll("[^0-9]", ""));
    		  
    	  }
    	  
    	 
    	
    	
    	
    	
    	return nr;
    	
    }
    public int getRows() {
        String s = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ALL_ROWS))).getText();
        return Integer.parseInt(s.replaceAll("[^0-9]", ""));
    }
    
    public int getTypes() {
        String s = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TYPE_GRAPE))).getText();
        return Integer.parseInt(s.replaceAll("[^0-9]", ""));
    }
    
    public int grapeTypes() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(tableRows));
        return list.size();
    }

    public void addNewGrape(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(addGrapesBtn))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).sendKeys("asd");

        // define select for quantity dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(quantity)));
        Select quantitySelect = new Select(driver.findElement(By.cssSelector(quantity)));
        quantitySelect.selectByVisibleText("36");


        // define select for unit dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(unit)));
        Select unitSelect = new Select(driver.findElement(By.cssSelector(unit)));
        unitSelect.selectByVisibleText("cases");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(age))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(age))).sendKeys("3");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).sendKeys("97");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(submitBtn))).click();
   
    }
    public void pickUpAndCrush()
    
    {
    	
    	 
    	 
    	
    }
    
    public int Must() {

        String s = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(MUST_COUNT))).getText();
        return Integer.parseInt(s.replaceAll("[^0-9]", ""));
    }

    public int totalVolume() {
        String s = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(MUST_TOTAL))).getText();
        return Integer.parseInt(s.replaceAll("[^0-9]", ""));
    }

    public int Types() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(mustRows));
        return list.size();
    }

    public int mustVolume() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustRows)));
        int v = 0;
        String s;
        List<WebElement> listOfRows = driver.findElements(By.cssSelector(mustRows));
        for (WebElement row : listOfRows) {
            s = row.findElements(By.tagName("td")).get(2).getText();
            v = v + Integer.parseInt(s.replaceAll("[^0-9]", ""));
        }
        return v;
    }
    
    
    public void ferment() {
    	
    	
    }
    
    public int Wine() {

        String s = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(WINE_COUNT))).getText();
        return Integer.parseInt(s.replaceAll("[^0-9]", ""));
    }
    public int VolumeWine() {
        String s = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(VOLUME))).getText();
        return Integer.parseInt(s.replaceAll("[^0-9]", ""));
    }
    
    
    
    
   
}
