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

    // Add grapes page
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

    public String mustRows = "table > tbody >tr";
    public String fermentBtn = "button";
    public String wineRows = "table > tbody >tr";
    public String wineRows2 = "table > tbody >tr";

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
        unitSelect.selectByVisibleText ("cases");

        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (age))).clear ();
        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (age))).sendKeys ("3");

        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (ripeness))).clear ();
        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (ripeness))).sendKeys ("97");

        wait.until (ExpectedConditions.presenceOfElementLocated (By.cssSelector (submitBtn))).click ();
    }

    public boolean checkGrapeTotalQuantity() {

        // total grape quantities from the table
        int grapeRowsTableTotalNumber = 0;

        // total rows displayed value
        int totalRowsNumber = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(TotalRows))).getText().replace("Total rows: ",""));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));

        System.out.println(totalRowsNumber);

        // defining a list of grapes consisting of table rows to iterate through
        List<WebElement> grapes = driver.findElements(By.cssSelector(tableRows));

        // obtaining the total number of grape rows by iterating through the list above
        for(WebElement row : grapes) {
            grapeRowsTableTotalNumber += Integer.parseInt(row.findElement(By.cssSelector(grapeQuantity)).getText().replace(" rows","").replace(" barrows","").replace(" cases",""));
        }
        System.out.println(grapeRowsTableTotalNumber);

        // comparing the total rows displayed value to the total sum of grape quantities and returning the boolean
        // if true => the value displayed on the page is the sum of the values in the grapes table
        return grapeRowsTableTotalNumber == totalRowsNumber;
    }

    public boolean checkGrapeTypes(){

        // displayed types of grapes total value
        int grapeTypes = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(grapeType))).getText().replace("Types of grapes: ",""));

        // comparing the types of grapes total value to the total number of realised wines and returning the boolean
        // if true => each separate type of grape has been used for a distinct wine
        return grapeTypes == countWines();
    }

}