package Sesiunea15.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MustPO extends BasePO {

    private String name = "input#name";
    private String unit = "select#unit";
    private String age = "input#age";
    private String ripeness = "input#ripeness";
    private String fermentBtn = "button";
    private String wineRows = "table > tbody >tr";
    private String mustCount = "li:nth-child(1)";
    private String mustTotalVolume = "li:nth-child(2)";
    private String mustVolume = "td:nth-child(3)";

    //Check that must count value and must total volume matches the values from the table.
    public boolean checkMustCountVolume() {

        boolean mustCountVolumeMatch = true;
        int mustNumber = 0;
        int volumeNumber = 0;
        int volumeNumberTable = 0;

        mustNumber = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustCount))).getText().replace("Must count: ", ""));

        if (mustNumber != countWines()) {
            mustCountVolumeMatch = false;
        }

        if (mustCountVolumeMatch == true) {

            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
            List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

            volumeNumber = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustTotalVolume))).getText().replace("Must total volume: ", "").replace(" liters", ""));

            for (WebElement row : list) {

                volumeNumberTable = volumeNumberTable + Integer.parseInt(row.findElement(By.cssSelector(mustVolume)).getText().replace(" liters", ""));

            }

            if (volumeNumber != volumeNumberTable) {
                mustCountVolumeMatch = false;
            }

        }

        return mustCountVolumeMatch;
    }

    //Select your must and Ferment it.
    public void selectAndFerment(String myGrape) {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

        for (WebElement row : list) {

            if (row.findElements(By.tagName("td")).get(1).getText().equals(myGrape)) {
                row.findElement(By.cssSelector("input[type=checkbox]")).click();
                break;
            }
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(fermentBtn))).click();

    }

    //Check that your must is missing
    public boolean isMustAvailable(String myGrape) {


        boolean isAvailable = false;

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

        for (WebElement row : list) {

            if (row.findElements(By.tagName("td")).get(1).getText().equals(myGrape)) {
                isAvailable = true;
                break;
            }
        }

        return isAvailable;
    }

    // Session 15 assignment: Check that the total must volume displayed number matches the total volume value in the table.
    public boolean checkMustTotalVolume() {

        boolean mustTotalVolumeMatch = true;
        int volumeNumber = 0;
        int volumeNumberTable = 0;

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

        volumeNumber = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustTotalVolume))).getText().replace("Must total volume: ", "").replace(" liters", ""));

        for (WebElement row : list) {

            volumeNumberTable = volumeNumberTable + Integer.parseInt(row.findElement(By.cssSelector(mustVolume)).getText().replace(" liters", ""));

        }

        if (volumeNumber != volumeNumberTable) {
            mustTotalVolumeMatch = false;
        }

        return mustTotalVolumeMatch;

    }

}