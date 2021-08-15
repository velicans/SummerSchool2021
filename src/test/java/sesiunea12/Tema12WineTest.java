package sesiunea12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class Tema12WineTest extends Tema12BaseTest {

       Tema12WineyardPO winePage = new Tema12WineyardPO(driver);

       public String GRAPE_NAME = "Grapes";


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
    public void checkTotalGrapesRows() {
            Assertions.assertTrue(winePage.countWines() == winePage.totalRows(), "There is a different number of wines in list: " + winePage.countWines());
    }

    @Test
    public void checkTotalGrapesType() {
        Assertions.assertTrue(winePage.typesOfGrapes() == winePage.countTypeGrapes(), "There is a different number of wines in list: " + winePage.countWines());
    }

    // Add a new type of grape with ripeness of 97%.
    //Check that total rows value and type of grapes value are updated.
    @Test
    public void addNewGrapeRipeness() {

        int initialValue = winePage.countWines();
        winePage.addNewGrape(GRAPE_NAME);
        int finalValue = winePage.countWines();
        Assertions.assertTrue(finalValue - initialValue == 1, "New grape was NOT added successfully!");
        Assertions.assertTrue(winePage.countWines() == winePage.totalRows(),"There is a different number of wines in list: " + winePage.countWines());
        Assertions.assertTrue(winePage.typesOfGrapes() == winePage.countTypeGrapes(), "There is a different number of wines in list: " + winePage.countWines());
    }

    //Pick up and crush your newly added grape.
    @Test
    public void Crush() {
        winePage.Crush(GRAPE_NAME);
    }

    //Check that must count value and must total volume matches the values from the table.
    @Test
    public void checkMustValuesAndVolume() {
        Assertions.assertTrue(winePage.countMustRows() == winePage.MustCount(), "There is a different number of musts in list: " + winePage.MustCount());
        Assertions.assertTrue(winePage.MustVolumeSum() == winePage.MustVolumeIndex(), "There is a different number of musts in list: " + winePage.MustVolumeIndex());

    }

    //Select your must and Ferment it.
    @Test
    public void FermentMust() {
        winePage.FermentMust(GRAPE_NAME);
    }

    //Check that wines value and volume value matches the data displayed in table.
    @Test
    public void checkWinesValuesAndVolume() {
        Assertions.assertTrue(winePage.FermetedMustWinesIndex() == winePage.FermetedMustWinesCount(), "There is a different number of wines in list: " + winePage.FermetedMustWinesIndex());
    }

}




