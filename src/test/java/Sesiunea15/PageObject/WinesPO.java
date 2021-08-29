package Sesiunea15.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static Sesiunea15.helpers.Utils.sleep;

public class WinesPO extends BasePO {

    private String name = "input#name";
    private String unit = "select#unit";
    private String age = "input#age";
    private String wineRows = "table > tbody >tr";
    private String winesCount = "li:nth-child(1)";
    private String winesTotalVolume = "li:nth-child(2)";
    private String winesVolume = "td:nth-child(3)";

    //Check that wines value and volume value matches the data displayed in table.
    public boolean checkWinesCountVolume() {

        boolean winesCountVolumeMatch = true;
        int numberOfWines = 0;
        int volumeNumber = 0;
        int volumeNumberTable = 0;

        numberOfWines = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(winesCount))).getText().replace("Wines: ", "").replace(" wines", ""));

        if (numberOfWines != countWines()) {
            winesCountVolumeMatch = false;
        }

        if (winesCountVolumeMatch == true) {

            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
            List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

            volumeNumber = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(winesTotalVolume))).getText().replace("Volume: ", "").replace(" liters", ""));

            for (WebElement row : list) {

                volumeNumberTable = volumeNumberTable + Integer.parseInt(row.findElement(By.cssSelector(winesVolume)).getText().replace(" liters", ""));

            }

            if (volumeNumber != volumeNumberTable) {
                winesCountVolumeMatch = false;
            }

        }

        return winesCountVolumeMatch;
    }

    public void setLabel(String grapeName, String name) {
        List<WebElement> winerows = driver.findElements(tableRows);
        for (WebElement row : winerows) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(grapeName)) {
                row.findElement(By.cssSelector("button")).click();
                sleep(2);
                row.findElement(By.cssSelector("input")).click();
                row.findElement(By.cssSelector("input")).sendKeys(name);
                row.findElement(By.cssSelector("button")).click();
                break;
            }
        }
    }

    public void setBottlingVol(String grapeName, String volume) {
        List<WebElement> bottles = driver.findElements(tableRows);
        for (WebElement row : bottles) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(grapeName)) {
                row.findElement(By.cssSelector("button")).click();
                sleep(2);
                row.findElement(By.cssSelector("input")).click();
                sleep(1);
                row.findElement(By.cssSelector("input")).sendKeys(volume);
                sleep(1);
                row.findElement(By.cssSelector("button")).click();
                break;
            }
        }
    }

    public int getWineVolumeTotal(){
        int totVol = 0;
        List<WebElement> volRows = driver.findElements(By.cssSelector(wineRows));
        for(WebElement row : volRows) {
            String textRow = row.findElements(By.tagName("td")).get(2).getText();
            int numRow = Integer.parseInt(textRow.substring(0, textRow.indexOf(" ")));
            //System.out.println(numRow);
            totVol+=numRow;
        }
        return totVol;
    }


}
