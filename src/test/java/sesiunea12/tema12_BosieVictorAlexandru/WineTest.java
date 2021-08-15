package sesiunea12.tema12_BosieVictorAlexandru;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class WineTest extends BaseTest {

    WineyardPO winePage = new WineyardPO(driver);

    @BeforeEach
    public void openPage() {
        driver.get("https://wineappui.azurewebsites.net/");
    }

    @AfterEach
    public void waitAfter() throws InterruptedException{
        Thread.sleep(1000);
    }


    /*
    @Test
    public void isHomePageOpened() {
        boolean isOpened = winePage.amOnHomePage();
        Assertions.assertTrue(isOpened, "Home page did NOT open successfully!");
    }

    @Test
    public void areTenWines() {
        Assertions.assertTrue(winePage.countWines() == 11, "There is a different number of wines in list: " + winePage.countWines());
    }

    @Test
    public void addNewGrape() {
        int initialValue = winePage.countWines();
        winePage.addNewGrape();
        int finalValue = winePage.countWines();
        Assertions.assertTrue(finalValue - initialValue == 1, "New grape was NOT added successfully!");
    }
    */


    @Test
    public void Homework() throws InterruptedException{

        String wineText, rowText, grapeName = "test16", mustText, mustVolume;

        int tableWines = winePage.countWines();
        wineText = winePage.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li"))).getText();
        int statedWines = Integer.parseInt(wineText.substring( wineText.lastIndexOf(' ') + 1 ));
        //System.out.println(statedWines);

        boolean winesIsEqual = tableWines == statedWines;
        Assertions.assertTrue(winesIsEqual, "The number of grapes is NOT equal.");

        rowText = winePage.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li:nth-child(2)"))).getText();
        int statedRows = Integer.parseInt(rowText.substring(12));
        //System.out.println(statedRows);
        int totRows = 0;
        List<WebElement> rows = driver.findElements(By.cssSelector(winePage.wineRows));
        for(WebElement row : rows) {
            String textRow = row.findElements(By.tagName("td")).get(1).getText();
            int numRow = Integer.parseInt(textRow.substring(0, textRow.indexOf(" ")));
            //System.out.println(numRow);
            totRows+=numRow;
        }
        //System.out.println(totRows);

        boolean rowsIsEqual = statedRows == totRows;
        Assertions.assertTrue(rowsIsEqual, "The number of rows is NOT equal.");

        System.out.println("checked first");

        winePage.addNewGrape(grapeName, 30, 97);

        System.out.println("added grape");

        String wineTextUpd = winePage.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li"))).getText();

        boolean winesIsEqualUpd = wineText != wineTextUpd;
        Assertions.assertTrue(winesIsEqualUpd, "The number of grapes did NOT update.");

        String rowTextUpd = winePage.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li:nth-child(2)"))).getText();

        boolean rowsIsEqualUpd = rowText != rowTextUpd;
        Assertions.assertTrue(rowsIsEqualUpd, "The number of rows did NOT update.");

        System.out.println("checked update");

        List<WebElement> seekRows = winePage.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(winePage.wineRows)));
        //System.out.println(seekRows.size());
        for(WebElement rowMust : seekRows) {
            if(rowMust.findElements(By.tagName("td")).get(0).getText().equals(grapeName)) {
                rowMust.findElement(By.tagName("button")).click();
                break;
            }
        }

        Thread.sleep(1000);
        System.out.println("went to must");

        mustText = winePage.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li"))).getText();
        int statedMust = Integer.parseInt(mustText.substring( mustText.lastIndexOf(' ') + 1 ));
        int tableMust = winePage.countWines();
        //System.out.println(statedMust);

        boolean mustIsEqual = tableMust == statedMust;
        Assertions.assertTrue(mustIsEqual, "The number of must is NOT equal.");

        mustVolume = winePage.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li:nth-child(2)"))).getText();
        String tableVolumeTR = mustVolume.substring(0, mustVolume.lastIndexOf(' '));
        int tableVolume = Integer.parseInt(tableVolumeTR.substring(tableVolumeTR.lastIndexOf(' ') + 1));
        //System.out.println(tableVolume);

        int totVol = 0;
        List<WebElement> volRows = driver.findElements(By.cssSelector(winePage.wineRows));
        for(WebElement row : volRows) {
            String textRow = row.findElements(By.tagName("td")).get(2).getText();
            int numRow = Integer.parseInt(textRow.substring(0, textRow.indexOf(" ")));
            //System.out.println(numRow);
            totVol+=numRow;
        }
        //System.out.println(totVol);

        boolean volIsEqual = totVol == tableVolume;
        Assertions.assertTrue(volIsEqual, "The volume of must is NOT equal.");

        System.out.println("checked must");

        WebElement rowToUse = null;
        List<WebElement> mustRows = driver.findElements(By.cssSelector(winePage.tableRows));
        for(WebElement mustRow : mustRows) {
            if(mustRow.findElements(By.tagName("td")).get(1).getText().equals(grapeName)){
                rowToUse = mustRow;
                break;
            }
        }

        rowToUse.findElement(By.tagName("input")).click();
        Thread.sleep(200);
        driver.findElement(By.tagName("button")).click();
        Thread.sleep(2000);

        System.out.println("went to wine page");

        String statedWineText = winePage.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li"))).getText();
        String statedWineTR = statedWineText.substring(0, statedWineText.lastIndexOf(' '));
        int statedWine = Integer.parseInt(statedWineTR.substring(statedWineTR.lastIndexOf(' ') + 1));
        //System.out.println(statedWine);

        int tableWine = winePage.countWines();

        boolean wineIsEqual = tableWine == statedWine;
        Assertions.assertTrue(wineIsEqual, "The number of wine is NOT equal.");


        String statedWineVolText = winePage.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li:nth-child(2)"))).getText();
        String statedWineVolTR = statedWineVolText.substring(0, statedWineVolText.lastIndexOf(' '));
        int statedWineVol = Integer.parseInt(statedWineVolTR.substring(statedWineVolTR.lastIndexOf(' ') + 1));
        //System.out.println(statedWineVol);

        int wineVol = 0;
        List<WebElement> wineRows = driver.findElements(By.cssSelector(winePage.wineRows));
        for(WebElement row : wineRows) {
            String textRow = row.findElements(By.tagName("td")).get(2).getText();
            int numRow = Integer.parseInt(textRow.substring(0, textRow.indexOf(" ")));
            //System.out.println(numRow);
            wineVol+=numRow;
        }
        //System.out.println(wineVol);

        boolean wineVolIsEqual = wineVol == statedWineVol;
        Assertions.assertTrue(wineVolIsEqual, "The volume of wine is NOT equal.");

        System.out.println("checked the wine");

        driver.findElement(By.cssSelector("a:nth-child(3)")).click();

        boolean foundMust = false;

        List<WebElement> findMust = winePage.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(winePage.wineRows)));
        //System.out.println(seekRows.size());
        for(WebElement row : findMust) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(grapeName)) {
                foundMust = true;
                break;
            }
        }

        String mustTextLast = winePage.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li"))).getText();

        boolean foundTextMust = mustTextLast.equals(mustText);

        Assertions.assertFalse(foundMust || foundTextMust, "The must did NOT disappear from Must");

        Thread.sleep(1000);

        System.out.println("checked if grape is in must");

        driver.findElement(By.cssSelector("a:nth-child(2)")).click();

        boolean foundGrape = false;

        List<WebElement> findGrape = winePage.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(winePage.wineRows)));
        //System.out.println(seekRows.size());
        for(WebElement row : findGrape) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(grapeName)) {
                foundGrape = true;
                break;
            }
        }

        String winesTextLast = winePage.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li"))).getText();

        boolean foundTextGrapes = winesTextLast.equals(wineTextUpd);


        Assertions.assertFalse(foundGrape || foundTextGrapes, "The grape did NOT disappear from Grapes");


        Thread.sleep(1000);

        System.out.println("checked if grape is in grape");

    }

}

