package sesiunea12.TEMA_ANDREI_PAVEN;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    public String TotalRows = "li:nth-child(2)";
    public String grapeQuantity = "td:nth-child(2)";
    public String grapeType = "li:nth-child(1)";

    public String tableRows = "table > tbody >tr";

    public String fermentBtn = "button";

    public String mustPage = "a:nth-child(3)";
    public String mustCount = "li:nth-child(1)";
    public String mustTotalVolume ="li:nth-child(2)";
    public String mustVolume = "td:nth-child(3)";

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

    public void addNewGrape (String grapeName) {
        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (addGrapesBtn))).click ();

        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (name))).clear ();
        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (name))).sendKeys(grapeName);

        // define select for quantity dropdown
        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (quantity)));
        Select quantitySelect = new Select (driver.findElement (By.cssSelector (quantity)));
        quantitySelect.selectByVisibleText ("36");

        // define select for unit dropdown
        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (unit)));
        Select unitSelect = new Select (driver.findElement (By.cssSelector (unit)));
        unitSelect.selectByVisibleText ("rows");

        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (age))).clear ();
        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (age))).sendKeys ("3");

        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (ripeness))).clear ();
        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (ripeness))).sendKeys ("97");

        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (submitBtn))).click ();
    }

    public boolean checkGrapeTotalQuantity() {

        // total grape quantities from the table
        int grapeRowsTotalTableNumber = 0;

        // total rows displayed value on the page
        int totalRowsNumber = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(TotalRows))).getText().replace("Total rows: ",""));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));

        System.out.println(totalRowsNumber);

        // defining a list of grapes consisting of table rows to iterate through
        List<WebElement> grapes = driver.findElements(By.cssSelector(tableRows));

        // obtaining the total number of grape rows by iterating through the list above and incrementing (barrows and cases get counted as rows on the page)
        for(WebElement row : grapes) {
            grapeRowsTotalTableNumber += Integer.parseInt(row.findElement(By.cssSelector(grapeQuantity)).getText().replace(" rows","").replace(" barrows","").replace(" cases",""));
        }
        System.out.println(grapeRowsTotalTableNumber);

        // comparing the total rows displayed value to the total sum of grape quantities and returning the boolean
        // if true => the value displayed on the page is the sum of the values in the grapes table
        return grapeRowsTotalTableNumber == totalRowsNumber;
    }

    public boolean checkGrapeTypes(){

        // displayed types of grapes total value
        int grapeTypes = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(grapeType))).getText().replace("Types of grapes: ",""));

        // comparing the types of grapes total value to the total number of rows in the table
        return grapeTypes == countWines();
    }

    public void pickAndCrushGrape(String grapeName){

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> grapeList = driver.findElements(By.cssSelector(tableRows));

        for(WebElement row : grapeList) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(tableRows))
                // find the only possible button on the row and click it
                row.findElement(By.tagName("button")).click();
                break;
            }
        }


    public void navigateToMustPage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustPage))).click();
    }


    public boolean checkMustCount(){

        // localizing must count number element on must page and extracting value
        int mustDisplayedNumber = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustCount))).getText().replace("Must count: ",""));

        // if must count number does not match the number of rows in the current table there is a mismatch
        return mustDisplayedNumber == countWines();

        }


    public boolean checkMustVolume(){

        // localizing must total volume number element on page and extracting value
        int volumeDisplayedNumber = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustTotalVolume))).getText().replace("Must total volume: ","").replace(" liters",""));

        int volumeTableValue = 0;

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));

        // creating o list of table rows from the current page, iterating through it, incrementing volumeTableValue
        List<WebElement> mustList = driver.findElements(By.cssSelector(tableRows));
        for(WebElement row : mustList) {
            volumeTableValue += Integer.parseInt(row.findElement(By.cssSelector(mustVolume)).getText().replace(" liters",""));
        }

        // if the incremented volumeTableValue does not match the volumeDisplayedNumber there is a mismatch
        return volumeDisplayedNumber == volumeTableValue;

    }

}