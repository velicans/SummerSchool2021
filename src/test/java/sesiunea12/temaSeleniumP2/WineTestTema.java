package sesiunea12.temaSeleniumP2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WineTestTema extends BaseTestTema {

    WineyardPO2 winePage = new WineyardPO2 (driver);

    public static final String GRAPE_NAME;

    static {
        GRAPE_NAME = "Chianti";
    }

    @BeforeEach
    public void openPage ( ) {
        driver.get ("https://wineappui.azurewebsites.net/");
    }

    @Test
    public void isHomePageOpened ( ) {
        boolean isOpened = winePage.amOnHomePage ();
        Assertions.assertTrue (isOpened, "Home page did not open successfully!");
    }

    @Test
    public void WinesNumber ( ) {
        Assertions.assertTrue (winePage.countWines () == 11, "There is a different number of wines in list: " + winePage.countWines ());
    }


    @Test
    public void addNewGrape ( ) {
        int initialValue = winePage.countWines ();
        winePage.addNewGrape ();
        int finalValue = winePage.countWines ();
        Assertions.assertTrue (finalValue - initialValue == 1, "New grape was NOT added successfully!");
    }


    //Check that total rows value and type of grapes value are matching the values in table.

    @Test
    public void checkRows ( ) {
        boolean checkRows = winePage.checkRows ();
        Assertions.assertTrue (winePage.checkRows (), "Total rows value and type of grapes are NOT matching the values in the table!");

    }

    //Add a new type of grape with ripeness of 97%.
    @Test
    public void newGrape ( ) {
        int firstValue = winePage.countWines ();
        winePage.addNewGrape();

        int finalValue = winePage.countWines ();
        Assertions.assertTrue (finalValue - firstValue == 1, "The grape wasn't successfully added");

        //Check that total rows value and type of grapes value are updated    (firstValue and finalValue)
        Assertions.assertTrue (winePage.checkRows (), "Total rows value and type of grapes value were NOT successfully updated !");
    }

    //Pick up and crush your newly added grape.
    @Test
    public boolean PickUpAndCrush ( ) {
        winePage.PickUpAndCrush (GRAPE_NAME);

        //Check that must count value and must total volume matches the values from the table.
        Assertions.assertTrue (PickUpAndCrush (), "Must count value and must total volume does not match the values from the table");
        return false;
    }

    /*Select your must and Ferment it.
    Check that wines value and volume value matches the data displayed in table*/
    @Test
    public void SelectFermentMust ( ) {
        winePage.selectAndFerment (GRAPE_NAME);
        Assertions.assertTrue (winePage.checkWinesCountVolume (), "Wines count value and wines total volume don't match the values displayed in the table!");
    }

    //Go back to Must page and check that your must is missing and that the values have been updated.
    @Test
    public void checkMustPage ( ) {

        Assertions.assertTrue (winePage.isMyMustMissing (GRAPE_NAME), "Must was not deleted from the table!");
        Assertions.assertTrue (winePage.checkMustCountVolume (), "Must count value or must total volume has not been updated!");
    }


    //Go back to Grapes page and check that your grape is missing and that the values have been updated.

    @Test

    public void checkGrapePage ( ) {
        Assertions.assertTrue (winePage.IsMyGrapeMissing (GRAPE_NAME), "The grape has not been deleted from the table!");
        Assertions.assertTrue (winePage.checkGrapeType () && winePage.checkRows (), "Grapes count or rows values have not been updated");


    }

}




