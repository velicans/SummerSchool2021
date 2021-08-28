package Sesiunea15.tema.pageobject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import sesiunea12.WineyardPO;

import java.util.Arrays;

public class HomeworkWineyardPO extends WineyardPO {

    public final String TYPES_OF_GRAPES = ".App-page ul li:first-child";
    public final String TOTAL_ROWS = ".App-page ul li:nth-child(2)";
    public final String QUANTITY_TABLE_CELLS = ".App-table tr td:nth-child(2)";
    public final String PICKUP_CRUSH_BUTTON = "//tbody//tr/td[contains(text(), '%s')]/..//td/button";
    public final String GRAPE_TABLE_CELL = "//tbody//tr/td[contains(text(), '%s')]";
    public final String HOME_LINK = ".App-header a:first-child";

    public HomeworkWineyardPO(WebDriver driver) {
        super(driver);
    }

    public int getTypesOfGrapes() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(TYPES_OF_GRAPES)));
        final String typesOfGrapes = driver.findElement(By.cssSelector(TYPES_OF_GRAPES)).getText();
        return Integer.parseInt(typesOfGrapes.split(" ")[typesOfGrapes.split(" ").length - 1]);
    }

    public int getTotalRows() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(TOTAL_ROWS)));
        final String typesOfGrapes = driver.findElement(By.cssSelector(TOTAL_ROWS)).getText();
        return Integer.parseInt(typesOfGrapes.split(" ")[typesOfGrapes.split(" ").length - 1]);
    }

    public int countRows() {
        return driver.findElements(By.cssSelector(QUANTITY_TABLE_CELLS))
                .stream()
                .map(webElement -> Arrays.stream(webElement.getText().split(" ")).findFirst())
                .map(s -> Integer.parseInt(s.get()))
                .reduce(0, Integer::sum);
    }

    public void addNewGrape(String grapeName, int ripenessValue, int quantityValue) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(addGrapesBtn))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).sendKeys(grapeName);

        // define select for quantity dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(quantity)));
        Select quantitySelect = new Select(driver.findElement(By.cssSelector(quantity)));
        quantitySelect.selectByVisibleText(String.valueOf(quantityValue));


        // define select for unit dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(unit)));
        Select unitSelect = new Select(driver.findElement(By.cssSelector(unit)));
        unitSelect.selectByVisibleText("cases");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(age))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(age))).sendKeys("3");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).sendKeys(String.valueOf(ripenessValue));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(submitBtn))).click();

    }

    public void clickPickAndCrush(String grapeName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(PICKUP_CRUSH_BUTTON, grapeName)))).click();
    }

    public void navigate() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(HOME_LINK))).click();
    }

    public void assertGrapeIsMissing(String grapeName) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(String.format(GRAPE_TABLE_CELL, grapeName))));
    }
}
