package sesiunea12.temaSeleniumP2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

class WineyardPO2 {

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
    public String fermentBtn = "button";
    public String wineRows = "table > tbody >tr";
    public String typesOfGrapes = "li:nth-child(1)";
    public String totalRows = "li:nth-child(2)";
    public String grapeQuantity = "td:nth-child(2)";
    public String mustCount = "li:nth-child(1)";
    public String mustTotalVolume = "li:nth-child(2)";
    public String mustVolume = "td:nth-child(3)";
    public String mustPage = "a:nth-child(3)";
    public String winePage = "a:nth-child(4)";
    public String winesCount = "li:nth-child(1)";
    public String winesTotalVolume = "li:nth-child(2)";
    public String winesVolume = "td:nth-child(3)";
    public String mustCheckBox = "input[type=checkbox]";

    public WineyardPO2 ( WebDriver driver ) {
        this.driver = driver;
        wait = new WebDriverWait (driver, 10);
    }

    public boolean amOnHomePage ( ) {
        boolean isHomePageOpened = true;
        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (addGrapesBtn)));
        if (!driver.findElement (By.cssSelector (addGrapesBtn)).isDisplayed ())
            isHomePageOpened = false;
        return isHomePageOpened;
    }

    public int countWines ( ) {
        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (wineRows)));
        List<WebElement> list = driver.findElements (By.cssSelector (wineRows));
        return list.size ();
    }

    public void addNewGrape ( ) {
        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (addGrapesBtn))).click ();

        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (name))).clear ();
        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (name))).sendKeys ("Chianti");

        // define select for quantity dropdown
        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (quantity)));
        Select quantitySelect = new Select (driver.findElement (By.cssSelector (quantity)));
        quantitySelect.selectByVisibleText ("36");


        // define select for unit dropdown
        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (unit)));
        Select unitSelect = new Select (driver.findElement (By.cssSelector (unit)));
        unitSelect.selectByVisibleText ("cases");

        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (age))).clear ();
        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (age))).sendKeys ("3");

        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (ripeness))).clear ();
        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (ripeness))).sendKeys ("97");

        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (submitBtn))).click ();
    }

    //tema

    public boolean checkRows ( ) {

        int numberOfRows = 0;
        int numberOfRowsInTotal = Integer.parseInt (wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (totalRows))).getText ().replace ("Total rows: ", ""));
        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (tableRows)));
        List<WebElement> grapeList = driver.findElements (By.cssSelector (tableRows));

        for (WebElement row : grapeList) {
            numberOfRows += Integer.parseInt (row.findElement (By.cssSelector (grapeQuantity)).getText ().replace (" rows", "").replace (" barrows", "").replace (" cases", ""));
        }
        return numberOfRows == numberOfRowsInTotal;
    }
    //check the grapes type
    public boolean checkGrapeType ( ) {

        int grapeTypes = Integer.parseInt (wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (typesOfGrapes))).getText ().replace ("Types of grapes: ", ""));

        return grapeTypes == countWines ();
    }

    //pick up and crush the newly added grape

    public void PickUpAndCrush (String newGrape) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

        for(WebElement row : list) {

            if(row.findElements(By.tagName("td")).get(0).getText().equals(newGrape)){
                row.findElement(By.tagName("button")).click();
                break;
            }


        }

    }

    //now we have to move to must page

    public void MoveToMustPage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustPage))).click();
    }

    //

    public boolean checkMustCountVolume(){

        MoveToMustPage();

        boolean mustCountVolumeMatch = true;
        int mustNumber;
        int volumeNumber;
        int volumeNumberTable = 0;

        mustNumber = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustCount))).getText().replace("Must count: ",""));

        if (mustNumber != countWines())

        {
            mustCountVolumeMatch = false;
        }

        if (mustCountVolumeMatch){

            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
            List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

            volumeNumber = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustTotalVolume))).getText().replace("Must total volume: ","").replace(" liters",""));

            for(WebElement row : list) {

                volumeNumberTable = volumeNumberTable + Integer.parseInt(row.findElement(By.cssSelector(mustVolume)).getText().replace(" liters",""));

            }

            if(volumeNumber != volumeNumberTable){
                mustCountVolumeMatch = false;
            }

        }

        return mustCountVolumeMatch;
    }

    //Select your must and ferment it

    public void selectAndFerment(String newGrape){

        MoveToMustPage ();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

        for(WebElement row : list) {

            if (row.findElements(By.tagName("td")).get(1).getText().equals(newGrape)) {
                row.findElement(By.cssSelector(mustCheckBox)).click();
                break;
            }
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(fermentBtn))).click();

    }

    //Moving on to wines page

    public void moveToWinePage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(winePage))).click();
    }

    //Check that wines value and volume value matches the data displayed in table.

    public boolean checkWinesCountVolume(){

        moveToWinePage();

        boolean winesCountVolumeMatch = true;
        int numberOfWines;
        int volumeNumber;
        int volumeNumberTable = 0;

        numberOfWines = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(winesCount))).getText().replace("Wines: ","").replace(" wines",""));

        if (numberOfWines != countWines()){
            winesCountVolumeMatch = false;
        }

        if (winesCountVolumeMatch){

            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
            List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

            volumeNumber = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(winesTotalVolume))).getText().replace("Volume: ","").replace(" liters",""));

            for(WebElement row : list) {

                volumeNumberTable = volumeNumberTable + Integer.parseInt(row.findElement(By.cssSelector(winesVolume)).getText().replace(" liters",""));

            }

            if(volumeNumber != volumeNumberTable){
                winesCountVolumeMatch = false;
            }

        }

        return winesCountVolumeMatch;
    }

    //Check that your mst is missing
    public boolean isMyMustMissing(String newGrape){
        MoveToMustPage ();
        wait.until(ExpectedConditions.presenceOfElementLocated (By.cssSelector (wineRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

        for(WebElement row : list) {

            if(row.findElements(By.tagName("td")).get(1).getText().equals(newGrape)){
                break;
            }

        }

        return false;

    }

    //Check that my grape is missing
    public boolean IsMyGrapeMissing(String newGrape){
        boolean mygrapeIsMissing = true;

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

        for(WebElement row : list) {

            if(row.findElements(By.tagName("td")).get(0).getText().equals(newGrape)){
                mygrapeIsMissing = false;
                break;
            }

        }

        return mygrapeIsMissing;
    }



}