package sesiunea12;

import org.testng.annotations.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WineTest extends BaseTest {

    WineyardPO winePage = new WineyardPO(driver);
    public static final String GRAPE_NAME = "vinulmeu7";
    public WebDriverWait wait;
    //Add a new type of grape with ripeness of 97%
    //@BeforeSuite
    @Test
    public void addNewGrape1(){
        int initialValue = winePage.countWines();
        //I've created a new method with parameters, in case other grapes need to be added, it will be easier with parameters
        winePage.addNewGrape_Updated(GRAPE_NAME,"12","rows","10","97");
        int finalValue = winePage.countWines();
        Assertions.assertTrue(finalValue - initialValue == 1, "New grape was NOT added successfully!");
    }

    @BeforeEach
    public void openPage() {
        driver.get("https://wineappui.azurewebsites.net/");
    }

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

    //Check that total rows value is matching the value in table.
    @Test
    public void areSameTotalRows() {
        Assertions.assertTrue(winePage.countWines() == winePage.getTotalRowsCalculated(), "There is a different number of calculated rows than actual rows : actual rows:" + winePage.countWines() + "/from page rows value:" + winePage.getTotalRowsCalculated());
    }

    //Check that type of grapes value is matching the value in table.
    @Test
    public void areSameTypesOfGrapes() {
        Assertions.assertTrue(winePage.getTypesOfGrapesFromPage() == winePage.getTypesOfGrapesCalculated(), "There is a different number of calculated types of grapes than the actual number of types of grapes: actual number: " + winePage.getTypesOfGrapesCalculated() + "/from page number:" + winePage.getTypesOfGrapesFromPage());
    }

    //Pick up and crush your newly added grape.
    @Test
    public void pickAndCrush() throws InterruptedException {
       int initialValue = winePage.countWines();
        winePage.pickAndCrush(GRAPE_NAME);
        openPage();
        int finalValue = winePage.countWines();
        Assertions.assertTrue(initialValue - finalValue == 1, "New grape was NOT picked up successfully! initial value: " + initialValue + " final value: "+ finalValue);
    }

    //Check that must count value matches the values from the table.
    @Test
    public void isSameTotalMustCount() {
        winePage.openMust();
        Assertions.assertTrue(winePage.countWines() == winePage.getMustCountFromPage(), "There is a different number of calculated rows than actual rows : actual rows:" + winePage.countWines() + "/ rows from page:" + winePage.getMustCountFromPage());
    }

    //Check that must total volume matches the values from the table.
    @Test
    public void isSameMustVolume() throws InterruptedException {
        winePage.openMust();
        Assertions.assertTrue(winePage.getMustVolumeFromPage() == winePage.calculateSumVolume(), "There is a different number of calculated volume than actual volume : actual volume:" + winePage.calculateSumVolume() + "/ rows value from page:" + winePage.getMustVolumeFromPage());

    }

    //Select your must and Ferment it.
    @Test
    public void fermentMyMust() throws InterruptedException {
        winePage.openMust();
        int initialValue = winePage.countWines();
        winePage.fermentMust(GRAPE_NAME);
        winePage.openMust();
        int finalValue = winePage.countWines();
        Assertions.assertTrue(initialValue - finalValue == 1, "New must was NOT fermented successfully! initial value: " + initialValue + " final value: "+ finalValue);
    }

    //Check that wines value matches the data displayed in table.
    @Test
    public void isSameTotalWinesCount() {
        winePage.openWines();
        Assertions.assertTrue(winePage.countWines() == winePage.getWinesCountFromPage(), "There is a different number of calculated rows than actual rows : actual rows:" + winePage.countWines() + "/ rows from page:" + winePage.getWinesCountFromPage());
    }

    //Check that volume value matches the data displayed in table.
    @Test
    public void isSameWinesVolume() throws InterruptedException {
        winePage.openWines();
        Assertions.assertTrue(winePage.getWinesVolumeFromPage() == winePage.calculateWinesSumVolume(), "There is a different number of calculated volume than actual volume : actual volume:" + winePage.calculateWinesSumVolume() + "/ rows value from page:" + winePage.getWinesVolumeFromPage());
    }

    //Go back to Must page and check that your must is missing. If this test passes, it's enough to run the above 2 tests again in order to check
    //if the values were updated. If this test doesn't pass, there is no need to check if the values are updated.
    @Test
    public void isMyMustMissing() throws InterruptedException {
        winePage.openMust();
        Assertions.assertTrue(winePage.isTheMustMissing(GRAPE_NAME)==true, "The must was not removed from Must table");
    }

    //Go back to Must page and check that your must is missing. If this test passes, it's enough to run the above 2 tests (areSameTotalRows and areSameTypesOfGrapes) again in order to check
    //if the values were updated. If this test doesn't pass, there is no need to check if the values are updated.
    @Test
    public void isMyGrapeMissing() throws InterruptedException {
        openPage();
        Assertions.assertTrue(winePage.isTheGrapeMissing(GRAPE_NAME)==true, "My grape was not removed from Grapes table");
    }
}
