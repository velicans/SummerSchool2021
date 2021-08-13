package sesiunea12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WineTest extends BaseTest {

    WineyardPO winePage = new WineyardPO(driver);
    public static final String GRAPE_NAME = "DanielGrapeV6";

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


    //Check that total rows value and type of grapes value are matching the values in table.
    @Test
    public void checkRowsTypes(){

        Assertions.assertTrue(winePage.checkRowsTypes(),"Total rows value and/or type of grapes value do NOT match the values from the table!");

    }


    //Add a new type of grape with ripeness of 97%.
    //Check that total rows value and type of grapes value are updated.
    @Test
    public void addNewGrapeV2(){

        int initialValue = winePage.countWines();
        winePage.addNewGrapeV2(GRAPE_NAME,"36","rows","80","97");
        int finalValue = winePage.countWines();
        Assertions.assertTrue(finalValue - initialValue == 1, "New grape was NOT added successfully!");
        Assertions.assertTrue(winePage.checkRowsTypes(),"Total rows value and/or type of grapes value have NOT been updated!");

    }


    //Pick up and crush your newly added grape.
    //Check that must count value and must total volume matches the values from the table.
    @Test
    public void pickAndCrush(){

        winePage.pickAndCrush(GRAPE_NAME);
        Assertions.assertTrue(winePage.checkMustCountVolume(),"Must count value and/or must total volume do NOT match the values from the table!");

    }


    //Select your must and Ferment it.
    //Check that wines value and volume value matches the data displayed in table
    @Test
    public void selectAndFerment(){

        winePage.selectAndFerment(GRAPE_NAME);
        Assertions.assertTrue(winePage.checkWinesCountVolume(),"Wines count value and/or wines total volume do NOT match the values from the table!");

    }


    //Go back to Must page and check that your must is missing and that the values have been updated.
    @Test
    public void checkMustPage(){

        Assertions.assertTrue(winePage.checkMustMissing(GRAPE_NAME),"Must has NOT been removed from the table!");
        Assertions.assertTrue(winePage.checkMustCountVolume(),"Must count value and/or must total volume have NOT been updated!");

    }


    //Go back to Grapes page and check that your grape is missing and that the values have been updated.
    @Test
    public void checkGrapePage(){

        Assertions.assertTrue(winePage.checkGrapeMissing(GRAPE_NAME),"Grape has NOT been removed from the table!");
        Assertions.assertTrue(winePage.checkRowsTypes(),"Grapes count and/or rows values have NOT been updated");

    }


}
