package sesiunea15.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class GrapesPO extends BasePO {
    private String addGrapesBtn = "button.animated-button";
    private String name = "input#name";
    private String quantity = "select#quantity";
    private String unit = "select#unit";
    private String age = "input#age";
    private String ripeness = "input#ripeness";
    private String submitBtn = "input[value='Submit']";
    private String typeOfGrapes = "li:nth-child(1)";
    private String totalRows = "li:nth-child(2)";
    private String grapeQuantity = "td:nth-child(2)";

    //Add a new type of grape with ripeness of 97%.
    public void addNewGrapeV2(String grapeNameHelp, String grapeQuantityHelp, String grapeUnitHelp, String grapeAgeHelp, String grapeRipenessHelp) {
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

    //Pick up and crush your newly added grape.
    public void pickAndCrush(String myGrape) {

        wait.until(ExpectedConditions.presenceOfElementLocated(tableRows));
        List<WebElement> list = driver.findElements(tableRows);

        for (WebElement row : list) {

            if (row.findElements(By.tagName("td")).get(0).getText().equals(myGrape)) {
                row.findElement(By.tagName("button")).click();
                break;
            }


        }

    }

    //Check that your grape is missing
    public boolean checkGrapeMissing(String myGrape) {

        boolean grapeIsMissing = true;

        wait.until(ExpectedConditions.presenceOfElementLocated(tableRows));
        List<WebElement> list = driver.findElements(tableRows);

        for (WebElement row : list) {

            if (row.findElements(By.tagName("td")).get(0).getText().equals(myGrape)) {
                grapeIsMissing = false;
                break;
            }

        }

        return grapeIsMissing;
    }

    //Check that total rows value and type of grapes value are matching the values in table.
    public boolean checkRowsTypes() {

        boolean rowsTypesMatch = true;
        int numberOfGrapes = 0;
        int numberOfRows = 0;
        int numberOfRowsTable = 0;

        numberOfGrapes = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(typeOfGrapes))).getText().replace("Types of grapes: ", ""));

        if (numberOfGrapes != countWines()) {
            rowsTypesMatch = false;
        }

        if (rowsTypesMatch == true) {

            wait.until(ExpectedConditions.presenceOfElementLocated(tableRows));
            List<WebElement> list = driver.findElements(tableRows);

            numberOfRows = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(totalRows))).getText().replace("Total rows: ", ""));

            for (WebElement row : list) {

                numberOfRowsTable = numberOfRowsTable + Integer.parseInt(row.findElement(By.cssSelector(grapeQuantity)).getText().replace(" rows", "").replace(" barrows", "").replace(" cases", ""));

            }

            if (numberOfRows != numberOfRowsTable) {
                rowsTypesMatch = false;
            }

        }
        return rowsTypesMatch;
    }


}
