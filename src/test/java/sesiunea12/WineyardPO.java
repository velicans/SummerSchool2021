package sesiunea12;

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
    public String fermentBtn = "button";
    public String wineRows = "table > tbody >tr";
    public String mustPage = "a:nth-child(3)";

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

    public int countWines() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));
        return list.size();
    }

    public void addNewGrape() {
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

    //Homework session 12 part II
    //1.Add a new type of grape with ripeness of 97%.

    public void addNewGrape2(String grapeName, String grapeQuantity, String grapeUnit, String grapeAge, String grapeRipeness) {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(addGrapesBtn))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).sendKeys(grapeName);

        // define select for quantity dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(quantity)));
        Select quantitySelect = new Select(driver.findElement(By.cssSelector(quantity)));
        quantitySelect.selectByVisibleText(grapeQuantity);

        // define select for unit dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(unit)));
        Select unitSelect = new Select(driver.findElement(By.cssSelector(unit)));
        unitSelect.selectByVisibleText(grapeUnit);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(age))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(age))).sendKeys(grapeAge);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).sendKeys(grapeRipeness);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(submitBtn))).click();
    }

    //2.Pick up and crush your newly added grape.
    public void pickAndCrush(String addedGrape) {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

        for (WebElement row : list) {

            if (row.findElements(By.tagName("td")).get(0).getText().equals(addedGrape)) {
                row.findElement(By.tagName("button")).click();
                System.out.println("Pick and crush has been executed successfully !");
                break;
            }
        }
    }

    //3.Select your must and Ferment it.
    public void selectAndFerment(String addedGrape) {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustPage))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

        for (WebElement row : list) {
            if (row.findElements(By.tagName("td")).get(1).getText().equals(addedGrape)) {
                row.findElement(By.cssSelector("input[type=checkbox]")).click();
                break;
            }
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(fermentBtn))).click();
        System.out.println("Select and ferment has been executed successfully !");
    }

    //4.Go back to Grapes page and check that your grape is missing
    public boolean checkGrapeMissing(String addedGrape) {

        boolean grapeMissing = false;

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

        for (WebElement row : list) {

            if (row.findElements(By.tagName("td")).get(0).getText().equals(addedGrape)) {
                grapeMissing = true;
                System.out.println("Your grape is NOT missing from Grapes page");
                break;
            }
        }
        System.out.println("Your grape is missing from Grapes page");
        return grapeMissing;
    }

    //5.Go back to Must page and check that your must is missing
    public boolean checkMustMissing(String addedGrape) {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustPage))).click();

        boolean mustMissing = true;

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

        for (WebElement row : list) {

            if (row.findElements(By.tagName("td")).get(1).getText().equals(addedGrape)) {
                mustMissing = false;
                System.out.println("Your must is NOT missing from Must page");
                break;
            }

        }
        System.out.println("Your must is missing from Must page");
        return mustMissing;
    }
//Check that total rows value and type of grapes value are matching the values in table.
//Check that total rows value and type of grapes value are updated.
//Check that must count value and must total volume matches the values from the table
//Check that wines value and volume value matches the data displayed in table.

}