package sesiunea12.TereuMarius_Tema;

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
    public String mustCheckbox="input[type=checkbox]";
    public String fermentBtn = "button";
    public String typeOfGrapes = "li:nth-child(1)";
    public String vineyardTotalRows = "li:nth-child(2)";
    public String grapeQuantity = "td:nth-child(2)";
    public String mustCount = "li:nth-child(1)";
    public String mustTotalVolume ="li:nth-child(2)";
    public String mustVolume = "td:nth-child(3)";
    public String mustPage = "a:nth-child(3)";
    public String winePage = "a:nth-child(4)";
    public String winesCount = "li:nth-child(1)";
    public String winesTotalVolume = "li:nth-child(2)";
    public String winesVolume = "td:nth-child(3)";


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
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(tableRows));
        return list.size();
    }

    public void addNewGrape(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(addGrapesBtn))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).sendKeys("newGrapeName");

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

    public void addNewGrape(String grapeName){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(addGrapesBtn))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).sendKeys(grapeName);

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

    public boolean checkGrapeTotalRows(){

        int rowsNumber = 0;
        int totalRowsNumber = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(vineyardTotalRows))).getText().replace("Total rows: ",""));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> grapeList = driver.findElements(By.cssSelector(tableRows));

        for(WebElement row : grapeList) {
            rowsNumber += Integer.parseInt(row.findElement(By.cssSelector(grapeQuantity)).getText().replace(" rows","").replace(" barrows","").replace(" cases",""));
        }
        if(rowsNumber != totalRowsNumber)
            return false;

        return true;
    }

    public boolean checkGrapesType(){
        int grapeTypes = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(typeOfGrapes))).getText().replace("Types of grapes: ",""));

        if (grapeTypes != countWines())
          return false;

        return true;
    }

    //Pick up and crush your newly added grape.
    public void pickAndCrush(String addedGrape){

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(tableRows));

        for(WebElement row : list) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(addedGrape)){
                row.findElement(By.tagName("button")).click();
                break;
            }
        }

    }

    //move to Must page
    public void moveToMustPage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustPage))).click();
    }

    public boolean checkMustCount(){
        moveToMustPage();
        int mustNumber = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustCount))).getText().replace("Must count: ",""));

        if (mustNumber != countWines())
          return false;

        return true;

    }

    public boolean checkMustVolume(){
        moveToMustPage();
        int volumeNumber = 0;
        int volumeNumberTable = 0;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> mustList = driver.findElements(By.cssSelector(tableRows));

        volumeNumber = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustTotalVolume))).getText().replace("Must total volume: ","").replace(" liters",""));

        for(WebElement row : mustList) {
            volumeNumberTable += Integer.parseInt(row.findElement(By.cssSelector(mustVolume)).getText().replace(" liters",""));
        }

        if(volumeNumber != volumeNumberTable)
           return false;
        return true;

    }

    //Select your must and Ferment it.
    public void selectAndFerment(String chosenGrape){

        moveToMustPage();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(tableRows));

        for(WebElement row : list) {
            if (row.findElements(By.tagName("td")).get(1).getText().equals(chosenGrape)) {
                row.findElement(By.cssSelector(mustCheckbox)).click();
                break;
            }
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(fermentBtn))).click();

    }

    //move to Wines page
    public void moveToWinePage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(winePage))).click();
    }

    public boolean checkWineCount(){
        moveToWinePage();
        int numberOfWines = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(winesCount))).getText().replace("Wines: ","").replace(" wines",""));
        if (numberOfWines != countWines())
             return false;

        return true;
    }

    public boolean checkWineVolume(){
        moveToWinePage();
        int volumeNumber =0;
        int volumeNumberTable = 0;

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> wineList = driver.findElements(By.cssSelector(tableRows));

        volumeNumber = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(winesTotalVolume))).getText().replace("Volume: ","").replace(" liters",""));

        for(WebElement row : wineList) {
            volumeNumberTable = volumeNumberTable + Integer.parseInt(row.findElement(By.cssSelector(winesVolume)).getText().replace(" liters",""));
        }

        if(volumeNumber != volumeNumberTable)
          return false;

        return true;
    }


    //Check that your must is missing
    public boolean isMustDeleted(String myGrape){

        moveToMustPage();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> mustList = driver.findElements(By.cssSelector(tableRows));

        for(WebElement row : mustList) {
            if(row.findElements(By.tagName("td")).get(1).getText().equals(myGrape)){
               return false;
            }
        }
        return true;
    }

    //Check that your grape is missing
    public boolean isGrapeDeleted(String myGrape){

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> grapeList = driver.findElements(By.cssSelector(tableRows));

        for(WebElement row : grapeList) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(myGrape)){
             return false;
            }
        }
        return true;
    }

}