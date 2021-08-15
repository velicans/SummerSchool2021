package homeworkS12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sesiunea12.BaseTest;
import sesiunea12.WineyardPO;

import java.util.List;

public class HomeworkWineyardTest extends BaseTest {
    public static final String GRAPE_NAME = "Adi";
    WineyardPO wineyardPage = new WineyardPO(driver);

    @BeforeEach
    public void openPage() {

        driver.get("https://wineappui.azurewebsites.net/");
    }

    // Check that total rows value and type of grapes value are matching the values in table.

    @Test
    public void checkGrapeTotalRowsTest() {
        int sumOfRows = wineyardPage.sumGrapeRows();
        int totalGrapeRows = wineyardPage.getTotalGrapeRows();
        Assertions.assertEquals(totalGrapeRows, sumOfRows, "Total rows does not match sum of rows");
    }

    @Test
    public void checkGrapeTotalTypesTest() {
        int sumOfGrapeTypes = wineyardPage.countGrapeTypes();
        int totalGrapeTypes = wineyardPage.getTotalGrapeTypes();
        Assertions.assertEquals(totalGrapeTypes, sumOfGrapeTypes, "Total types does not match sum of types");
    }

    //Add a new type of grape with ripeness of 97%.
    @Test
    public void addGrapeTest() {
        wineyardPage.addNewGrape(GRAPE_NAME, "12", "rows", "10", "97");
        List<WebElement> list = driver.findElements(By.cssSelector(wineyardPage.tableRows));
        boolean isGrapeAdded = false;
        for (WebElement row : list) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME))
                isGrapeAdded = true;
            break;
        }
        Assertions.assertTrue(isGrapeAdded);
    }


    // Check that total rows value and type of grapes value are updated.


    @Test
    public void checkUpdatedGrapesTable() {
        String grapeQty = "12";
        int rows = Integer.parseInt(grapeQty);

        int totalGrapeTypesBefore = wineyardPage.getTotalGrapeTypes();
        int totalGrapeRowsBefore = wineyardPage.getTotalGrapeRows();

        wineyardPage.addNewGrape(GRAPE_NAME, grapeQty, "rows", "10", "97");

        int totalGrapeTypesAfter = wineyardPage.getTotalGrapeTypes();
        int totalGrapeRowsAfter = wineyardPage.getTotalGrapeRows();

        Assertions.assertEquals(totalGrapeTypesBefore + 1, totalGrapeTypesAfter);
        Assertions.assertEquals(totalGrapeRowsBefore + rows, totalGrapeRowsAfter);
    }

    //Pick up and crush your newly added grape. + Go back to Grapes page and check that your grape is missing
    @Test
    public void pickAndCrushGrapeTest() {
        wineyardPage.pickAndCrushGrape(GRAPE_NAME);
        List<WebElement> list = driver.findElements(By.cssSelector(wineyardPage.tableRows));
        boolean isGrapePickedAndCrushed = true;
        for (WebElement row : list) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME))
                isGrapePickedAndCrushed = false;
        }
        Assertions.assertTrue(isGrapePickedAndCrushed);
    }

    //Check that must count value and must total volume matches the values from the table.

    @Test
    public void checkMustTotalVolumeTest() {
        driver.findElement(By.linkText("Must")).click();
        int sumOfMustVolume = wineyardPage.sumMustVolume();
        int mustTotalVolume = wineyardPage.getMustTotalVolume();
        Assertions.assertEquals(mustTotalVolume, sumOfMustVolume, "Total must volume does not match sum volume");
    }

    @Test
    public void checkMustCount() {
        driver.findElement(By.linkText("Must")).click();
        int sumOfMustTypes = wineyardPage.countMustTypes();
        int mustCount = wineyardPage.getMustCount();
        Assertions.assertEquals(mustCount, sumOfMustTypes, "Must count does not match must sum in table");
    }

    // Select your must and Ferment it.
    @Test
    public void fermentMustTest() {
        driver.findElement(By.linkText("Must")).click();
        wineyardPage.fermentMust(GRAPE_NAME);
        List<WebElement> list = driver.findElements(By.cssSelector(wineyardPage.mustRows));
        boolean isMustFermented = true;
        for (WebElement row : list) {
            if (row.findElements(By.tagName("td")).get(1).getText().equals(GRAPE_NAME))
                isMustFermented = false;
        }
        Assertions.assertTrue(isMustFermented);
    }

    //  Check that wines value and volume value matches the data displayed in table.
    @Test
    public void checkWinesCount() {
        driver.findElement(By.linkText("Wines")).click();
        int countOfWineTypes = wineyardPage.countWines();
        int winesCount = wineyardPage.getWinesCount();
        Assertions.assertEquals(winesCount, countOfWineTypes, "Wine count does not match count of wine types");
    }

    @Test
    public void checkWinesVolume() {
        driver.findElement(By.linkText("Wines")).click();
        int sumOfWineVolume = wineyardPage.sumWinesVolume();
        int winesVolume = wineyardPage.getWinesVolume();
        Assertions.assertEquals(winesVolume, sumOfWineVolume, "Sum of wine does not match wines volume");
    }


    // Go back to Must page and check that your must is missing and that the values have been updated..

    @Test
    public void checkUpdatedMustTable() {

        int totalMustTypesBefore = wineyardPage.getMustCount();
        int totalMustVolumeBefore = wineyardPage.getMustTotalVolume();

        wineyardPage.fermentMust(GRAPE_NAME);

        int totalMustTypesAfter = wineyardPage.getMustCount();
        int totalMustVolumeAfter = wineyardPage.getMustTotalVolume();

        Assertions.assertEquals(totalMustTypesBefore, totalMustTypesAfter);
        Assertions.assertEquals(totalMustVolumeBefore, totalMustVolumeAfter);
    }

    //  Go back to Grapes page and check that your grape is missing and that the values have been updated

    @Test
    public void checkIfGrapesTableIsUpdated() {
        String grapeQty = "12";
        int rows = Integer.parseInt(grapeQty);

        int totalGrapeTypesBefore = wineyardPage.getTotalGrapeTypes();
        int totalGrapeRowsBefore = wineyardPage.getTotalGrapeRows();

        wineyardPage.addNewGrape(GRAPE_NAME, grapeQty, "rows", "10", "97");

        int totalGrapeTypesAfter = wineyardPage.getTotalGrapeTypes();
        int totalGrapeRowsAfter = wineyardPage.getTotalGrapeRows();

        Assertions.assertEquals(totalGrapeTypesBefore + 1, totalGrapeTypesAfter);
        Assertions.assertEquals(totalGrapeRowsBefore + rows, totalGrapeRowsAfter);
    }


}




