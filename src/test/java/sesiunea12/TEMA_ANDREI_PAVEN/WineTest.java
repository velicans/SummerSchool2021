package sesiunea12.TEMA_ANDREI_PAVEN;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WineTest extends BaseTest {

    WineyardPO winePage = new WineyardPO(driver);
    public final String NEW_GRAPE = "Southern";

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

//    @Test
//    public void addNewGrape() {
//        int initialValue = winePage.countWines();
//        winePage.addNewGrape();
//        int finalValue = winePage.countWines();
//        Assertions.assertTrue(finalValue - initialValue == 1, "New grape was NOT added successfully!");
//    }

    //1. Check that total rows value and type of grapes value are matching the values in table.

    @Test
    public void checkGrapeQuantityMatch(){

        Assertions.assertTrue(winePage.checkGrapeTotalQuantity(),
                "The total rows value displayed on the grapes page does NOT match the total quantity value in the table");

    }

    @Test
    public void checkGrapeTypesMatch(){

        Assertions.assertTrue(winePage.checkGrapeTypes(),
                "The total number of types of grapes does NOT match the number of distinct wines produced.");

    }

    //2. Add a new grape of 97% ripeness & and check that both total rows and types of grapes values are updated

    @Test

    public void addNewGrapeType() {

        int initialValue = winePage.countWines();
        winePage.addNewGrape(NEW_GRAPE);
        int finalValue = winePage.countWines();
        Assertions.assertEquals(1, finalValue - initialValue, "New grape was NOT added successfully!");

        Assertions.assertTrue(winePage.checkGrapeTotalQuantity(),
                "Table updates while the total rows value displayed on the grapes page does NOT get updated.");

        Assertions.assertTrue(winePage.checkGrapeTypes(),
                "Table updates while the total number of types of grapes on the grapes page does NOT get updated.");
    }

}

    //3. Pick up and crush your newly added grape & check that must count value and must total volume matches the values from the table.
