package sesiunea12.tema;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
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
    public String grapesRows = "table > tbody >tr";


    public WineyardPO(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public boolean checkIfHomePageIsOpened() {
        boolean isHomePageOpened = true;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(addGrapesBtn)));
        if (!driver.findElement(By.cssSelector(addGrapesBtn)).isDisplayed())
            isHomePageOpened = false;
        return isHomePageOpened;
    }

    public int countTableRows(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(grapesRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(grapesRows));
        return list.size();
    }

    public int getFirstCountNumber(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li")));
        List<WebElement> list = driver.findElements(By.cssSelector("li"));
        String extractedText = list.get(0).getText();

        return Integer.parseInt(extractedText.replaceAll("[^0-9]", ""));
    }

    public int getSecondCountNumber(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li")));
        List<WebElement> list = driver.findElements(By.cssSelector("li"));
        String extractedText = list.get(1).getText();

        return Integer.parseInt(extractedText.replaceAll("[^0-9]", ""));
    }

    public int sumUpGrapeRows(){
        int totalRows = 0;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(grapesRows)));
        List<WebElement> rows = driver.findElements(By.cssSelector(grapesRows));
        for(WebElement row : rows) {
            if(row.findElements(By.tagName("td")).get(1).getText() != null){
                String extractedText = row.findElements(By.tagName("td")).get(1).getText();
                totalRows += Integer.parseInt(extractedText.replaceAll("[^0-9]", ""));
            }
        }
        return totalRows;
    }

    public int sumUpVolume(){
        int totalVolume = 0;

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustRows)));
        List<WebElement> rows = driver.findElements(By.cssSelector(grapesRows));
        for(WebElement row : rows) {
            if(row.findElements(By.tagName("td")).get(2).getText() != null){
                String extractedText = row.findElements(By.tagName("td")).get(2).getText();
                totalVolume += Integer.parseInt(extractedText.replaceAll("[^0-9]", ""));
            }
        }
        return totalVolume;
    }

    public void addNewGrape(String GRAPE_NAME, String GRAPE_AGE, String GRAPE_RIPENESS, String GRAPE_QUANTITY, String GRAPE_UNIT){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(addGrapesBtn))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).sendKeys(GRAPE_NAME);

        // define select for quantity dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(quantity)));
        Select quantitySelect = new Select(driver.findElement(By.cssSelector(quantity)));
        quantitySelect.selectByVisibleText(GRAPE_QUANTITY);


        // define select for unit dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(unit)));
        Select unitSelect = new Select(driver.findElement(By.cssSelector(unit)));
        unitSelect.selectByVisibleText(GRAPE_UNIT);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(age))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(age))).sendKeys(GRAPE_AGE);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).sendKeys(GRAPE_RIPENESS);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(submitBtn))).click();
    }

    public void pickAndCrush(String GRAPE_NAME) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> rows = driver.findElements(By.cssSelector(tableRows));
        for(WebElement row : rows) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)){
                System.out.println(row.findElements(By.tagName("td")).get(0).getText());
                row.findElement(By.tagName("button")).click();
                break;
            }
        }
    }

    public void selectMustByName(String GRAPE_NAME){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> rows = driver.findElements(By.cssSelector(tableRows));
        for(WebElement row : rows) {
            if(row.findElements(By.tagName("td")).get(1).getText().equals(GRAPE_NAME)){
                System.out.println(row.findElements(By.tagName("td")).get(1).getText());
                row.findElement(By.xpath(".//input")).click();
                break;
            }
        }
    }

    public void clickFermentButton(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button")));
        driver.findElement(By.xpath(".//button")).click();
    }

    public void setWineInputFields(String GRAPE_NAME, String WINE_NAME, String WINE_VOLUME){
        List<WebElement> rowsCellar = driver.findElements(By.cssSelector(tableRows));
        for(WebElement row : rowsCellar) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)){
                row.findElement(By.xpath(".//button[.='Set name']")).click();
                row.findElement(By.tagName("input")).sendKeys(WINE_NAME);
                row.findElement (By.xpath (".//button[.='OK']")).click();
                break;
            }

        }

        //find must, click set bottling volume -> set volume -> click ok
        List<WebElement> rowsCellar2 = driver.findElements(By.cssSelector(tableRows));
        for(WebElement row : rowsCellar2) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)){
                row.findElement (By.xpath (".//button[.='Set bottlingVolume']")).click();
                row.findElement(By.tagName("input")).sendKeys(WINE_VOLUME);
                row.findElement (By.xpath (".//button[.='OK']")).click();
                break;
            }
        }
    }

    public boolean verifyIfMissingFromMustPage(String GRAPE_NAME){

        boolean found = false;

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/header/nav/a[3]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> rows = driver.findElements(By.cssSelector(tableRows));
        for(WebElement row : rows) {
            if(row.findElements(By.tagName("td")).get(1).getText().equals(GRAPE_NAME)){
                System.out.println(row.findElements(By.tagName("td")).get(1).getText());
                found = true;
                break;
            }
        }

        if(found == false) {
            int count = getFirstCountNumber();
            int volume = getSecondCountNumber();
            int tableRows = countTableRows();
            int tableVolume = sumUpVolume();

            if((count == tableRows) && (volume == tableVolume))
                return true;
        }

      return false;
    }

    public boolean verifyIfMissingFromGrapesPage(String GRAPE_NAME){
        boolean found = false;

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/header/nav/a[2]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> rows = driver.findElements(By.cssSelector(tableRows));
        for(WebElement row : rows) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)){
                System.out.println(row.findElements(By.tagName("td")).get(1).getText());
                found = true;
                break;
            }
        }

        if(found == false) {
            int count = getFirstCountNumber();
            int rowsTotal = getSecondCountNumber();
            int tableGrapes = countTableRows();
            int tableRows =  sumUpGrapeRows();

            if((count == tableGrapes) && (rowsTotal == tableRows))
                return true;
        }
        return false;
    }

}


