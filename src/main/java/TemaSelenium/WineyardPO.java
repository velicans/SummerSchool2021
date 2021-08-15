package TemaSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
    public String tableRows = "table > tbody > tr";
    public String fermentMust = "button";
    public String wineRows = "table > tbody > tr";
    public String setName = "input";
    public String typesOfGrapes = "ul > li:nth-child(1)";
    public String totalRows = "ul > li:nth-child(2)";
    public String must = "#root > div > header > nav > a:nth-child(3)";

    public WineyardPO(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
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



    public void addNewGrape(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(addGrapesBtn))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).sendKeys("Glera");


        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(age))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(age))).sendKeys("13");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).sendKeys("97");

        // define select for quantity dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(quantity)));
        Select quantitySelect = new Select(driver.findElement(By.cssSelector(quantity)));
        quantitySelect.selectByVisibleText("24");


        // define select for unit dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(unit)));
        Select unitSelect = new Select(driver.findElement(By.cssSelector(unit)));
        unitSelect.selectByVisibleText("rows");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(submitBtn))).click();

    }

    public boolean typesOfGrapesAreMatching() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(typesOfGrapes)));
        String typeOfGrapesLabel = driver.findElement(By.cssSelector(typesOfGrapes)).getText();
        String[] splittedString = typeOfGrapesLabel.split(" ");

        int numberOfGrapes = Integer.parseInt(splittedString[splittedString.length - 1]);

        int count = countWines();
        return numberOfGrapes == count;
    }

    public boolean totalRows() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(totalRows)));
        String nOfRows = driver.findElement(By.cssSelector(totalRows)).getText();
        String[] splittedString = nOfRows.split(" ");

        int numberOfTotalRows = Integer.parseInt(splittedString[splittedString.length-1]);

        int totalRows = 0;
        List<WebElement> rows = driver.findElements(By.cssSelector(tableRows));
        for (WebElement row : rows) {
            String quantity = row.findElements(By.tagName("td")).get(1).getText();
            String[] parts = quantity.split(" ");
            totalRows += Integer.parseInt(parts[0]);
        }

        return totalRows == numberOfTotalRows;
    }


    public void pickUpAndCrush(){

        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("button"))).click();
        List<WebElement> rows = driver.findElements(By.cssSelector(tableRows));
        for (WebElement row : rows) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals("Glera")) {
                row.findElement(By.tagName("button")).click();
                break;
            }
        }
    }

    public boolean mustCountsAreMatching() {
        driver.findElement(By.xpath("//a[contains(text(),'Must')]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(typesOfGrapes)));
        String mustCountLabel = driver.findElement(By.cssSelector(typesOfGrapes)).getText();
        String[] splittedString = mustCountLabel.split(" ");

        int numberOfMusts = Integer.parseInt(splittedString[splittedString.length - 1]);

        int count = countWines();
        return numberOfMusts == count;
    }

    public boolean mustTotalVolume() {
        driver.findElement(By.xpath("//a[contains(text(),'Must')]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(totalRows)));
        String nOfRows = driver.findElement(By.cssSelector(totalRows)).getText();
        String[] splittedString = nOfRows.split(" ");

        int numberOfTotalRows = Integer.parseInt(splittedString[3]);

        int totalRows = 0;
        List<WebElement> rows = driver.findElements(By.cssSelector(tableRows));
        for (WebElement row : rows) {
            String quantity = row.findElements(By.tagName("td")).get(2).getText();
            String[] parts = quantity.split(" ");
            totalRows += Integer.parseInt(parts[0]);
        }

        return totalRows == numberOfTotalRows;
    }

    public void fermentMust(){
        driver.findElement(By.xpath("//a[contains(text(),'Must')]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(fermentMust))).click();
        driver.findElement(By.xpath("//td[contains(text(),'Glera')]/preceding-sibling::td/input[@type='checkbox']")).click();
        driver.findElement(By.cssSelector(fermentMust)).click();
    }


}
