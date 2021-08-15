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
    public String fermentBtn = "button";
    public String wineRows = "table > tbody >tr";
    public String wineRows2 = "table > tbody >tr";
    public String grapePage = "a:nth-child(2)";
    public String GrapesType = "li:nth-child(1)";
    public String totalRows = "li:nth-child(2)";
    public String grapeQuantity = "td:nth-child(2)";
    public String mustCount = "li:nth-child(1)";
    public String mustVolumeTotal ="li:nth-child(2)";
    public String mustVolume = "td:nth-child(3)";
    public String mustPage = "a:nth-child(3)";
    public String winePage = "a:nth-child(4)";
    public String wineCount = "li:nth-child(1)";
    public String wineVolumeTotal = "li:nth-child(2)";
    public String wineVolume = "td:nth-child(3)";

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

    public int countWinery(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));
        return list.size();
    }

    public void addNewGrape(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(addGrapesBtn))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).sendKeys("testName");

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
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).sendKeys("98");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(submitBtn))).click();
    }

    //swich to Grapes page
    public void swichTograpePage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(grapePage))).click();
    }

    //Check that total rows value and type of grapes value are matching the values in table.
    public boolean checkRowsTypes(){

        boolean rowsTypesMatch = true;
        int numOfGrapes = 0;
        int numOfRows = 0;
        int numOfRowsTable = 0;

        numOfGrapes = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(GrapesType))).getText().replace("Types of grapes: ",""));

        if(numOfGrapes != countWinery()){
            rowsTypesMatch = false;
        }

        if (rowsTypesMatch == true) {

            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
            List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

            numOfRows = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(totalRows))).getText().replace("Total rows: ",""));

            for(WebElement row : list) {

                numOfRowsTable = numOfRowsTable + Integer.parseInt(row.findElement(By.cssSelector(grapeQuantity)).getText().replace(" rows","").replace(" barrows","").replace(" cases",""));

            }

            if(numOfRows != numOfRowsTable){
                rowsTypesMatch = false;
            }

        }
        return rowsTypesMatch;
    }

    //Ex 2) Add a new type of grape with ripeness of 97%.
    public void addNewGrapeV2(String grapeNameHelp, String grapeQuantityHelp, String grapeUnitHelp, String grapeAgeHelp, String grapeRipenessHelp){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(addGrapesBtn))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).sendKeys(grapeNameHelp);

        // define select for quantity dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(quantity)));
        Select quantitySelect = new Select(driver.findElement(By.cssSelector(quantity)));
        quantitySelect.selectByVisibleText(grapeQuantityHelp);


        // define select for unit dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(unit)));
        Select unitSelect = new Select(driver.findElement(By.cssSelector(unit)));
        unitSelect.selectByVisibleText(grapeUnitHelp);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(age))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(age))).sendKeys(grapeAgeHelp);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).sendKeys(grapeRipenessHelp);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(submitBtn))).click();
    }

    //EX 4 )Pick up and crush your newly added grape.
    public void pickAndCrush(String myGrape){

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

        for(WebElement row : list) {

            if(row.findElements(By.tagName("td")).get(0).getText().equals(myGrape)){
                row.findElement(By.tagName("button")).click();
                break;
            }


        }

    }

    //swich to Must page
    public void moveToMustPage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustPage))).click();
    }

    //Ex 5)Check that must count value and must total volume matches the values from the table.
    public boolean checkMustCountVolume(){

        moveToMustPage();

        boolean mustCountVolumeMatch = true;
        int mustNumber = 0;
        int volumeNumber = 0;
        int volumeNumberTable = 0;

        mustNumber = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustCount))).getText().replace("Must count: ",""));

        if (mustNumber != countWinery()){
            mustCountVolumeMatch = false;
        }

        if (mustCountVolumeMatch == true){

            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
            List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

            volumeNumber = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustVolumeTotal))).getText().replace("Must total volume: ","").replace(" liters",""));

            for(WebElement row : list) {

                volumeNumberTable = volumeNumberTable + Integer.parseInt(row.findElement(By.cssSelector(mustVolume)).getText().replace(" liters",""));

            }

            if(volumeNumber != volumeNumberTable){
                mustCountVolumeMatch = false;
            }

        }

        return mustCountVolumeMatch;
    }

    //Ex 6)Select your must and Ferment it.
    public void selectAndFerment(String myGrape){

        moveToMustPage();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

        for(WebElement row : list) {

            if (row.findElements(By.tagName("td")).get(1).getText().equals(myGrape)) {
                row.findElement(By.cssSelector("input[type=checkbox]")).click();
                break;
            }
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(fermentBtn))).click();

    }

    //swich to Wines page
    public void swichToWinePage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(winePage))).click();
    }

    //Ex 7)Check that wines value and volume value matches the data displayed in table.
    public boolean checkwineCountVolume(){

        swichToWinePage();

        boolean wineCountVolumeMatch = true;
        int numberOfWines = 0;
        int volumeNumber = 0;
        int volumeNumberTable = 0;

        numberOfWines = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineCount))).getText().replace("Wines: ","").replace(" wines",""));

        if (numberOfWines != countWinery()){
            wineCountVolumeMatch = false;
        }

        if (wineCountVolumeMatch == true){

            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
            List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

            volumeNumber = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineVolumeTotal))).getText().replace("Volume: ","").replace(" liters",""));

            for(WebElement row : list) {

                volumeNumberTable = volumeNumberTable + Integer.parseInt(row.findElement(By.cssSelector(wineVolume)).getText().replace(" liters",""));

            }

            if(volumeNumber != volumeNumberTable){
                wineCountVolumeMatch = false;
            }

        }

        return wineCountVolumeMatch;
    }

    //Ex 8)Check that your must is missing
    public boolean checkMustMissing(String myGrape){

        moveToMustPage();

        boolean mustIsMissing = true;

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

        for(WebElement row : list) {

            if(row.findElements(By.tagName("td")).get(1).getText().equals(myGrape)){
                mustIsMissing = false;
                break;
            }

        }

        return mustIsMissing;
    }

    //Ex 9)Check that your grape is missing
    public boolean checkGrapeMissing(String myGrape){

        boolean grapeIsMissing = true;

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

        for(WebElement row : list) {

            if(row.findElements(By.tagName("td")).get(0).getText().equals(myGrape)){
                grapeIsMissing = false;
                break;
            }

        }

        return grapeIsMissing;
    }

}

