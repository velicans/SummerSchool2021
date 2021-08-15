package tema12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WineTest2 extends BaseTest {

    WineyardPO2 winePage = new WineyardPO2(driver);

    public static final String GRAPE_NAME;

    static {
        GRAPE_NAME = "Nebbiolo";
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

