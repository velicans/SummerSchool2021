package sesiunea12.TEMA_ANDREI_PAVEN;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WineTest extends BaseTest {

    WineyardPO winePage = new WineyardPO(driver);
    public final String NEW_GRAPE = "Southern_sample";

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
        Assertions.assertEquals(11, winePage.countWines(), "There is a different number of wines in list: " + winePage.countWines());
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
    public void checkGrapeQuantityMatch() {

        Assertions.assertTrue(winePage.checkGrapeTotalQuantity(),
                "The total rows value displayed on the grapes page does NOT match the total quantity value in the table");

    }

    @Test
    public void checkGrapeTypesMatch() {

        Assertions.assertTrue(winePage.checkGrapeTypes(),
                "The total number of types of grapes does NOT match the total number of table rows.");

    }

    //2. Add a new grape of 97% ripeness & and check that both total rows and types of grapes values are updated

    @Test
    public void addNewGrapeType() {

        int initialValue = winePage.countWines();
        winePage.addNewGrape(NEW_GRAPE);
        int finalValue = winePage.countWines();
        Assertions.assertEquals(1, finalValue - initialValue, "New grape was NOT added successfully!");
        // Once table updates successfully =>

        Assertions.assertTrue(winePage.checkGrapeTotalQuantity(),
                "Table updates while the total rows value displayed on the grapes page does NOT get updated.");

        Assertions.assertTrue(winePage.checkGrapeTypes(),
                "Table updates while the total number of types of grapes on the grapes page does NOT get updated.");
    }

    //3. Pick up and crush your newly added grape & check that must count value and must total volume matches the values from the table.

    @Test
    public void pickAndCrushGrape() {

        // assuming Pick & crush grapes button has any effect on the must page, otherwise, button malfunctions
        winePage.pickAndCrushGrape(NEW_GRAPE);
        winePage.navigateToMustPage();

        Assertions.assertTrue(winePage.checkMustCount(),
                "The must count fails to display the correct value or the table fails to update properly");

        Assertions.assertTrue(winePage.checkMustVolume(),
                "The must total volume count fails to display the correct value or the table fails to update properly");

    }

    //4. Select your must and ferment it. Check that wines value and volume value match the data displayed in the table.

    @Test
    public void selectAndFermentGrape() {

        winePage.navigateToMustPage();
        // assuming checkbox and ferment button have any effect, otherwise, buttons malfunction
        winePage.selectAndFermentGrape(NEW_GRAPE);
        winePage.navigateToWinePage();

        Assertions.assertTrue(winePage.checkWineCount(),
                "The wine count fails to display the correct value or the table fails to update properly");

        Assertions.assertTrue(winePage.checkWineVolume(),
                "The wine total volume count fails to display the correct value or the table fails to update properly");
    }

    //5. Go back to Must page and check that your must is missing and that the values have been updated.

    @Test
    public void checkMustPage() {

        winePage.navigateToMustPage();
        Assertions.assertTrue(winePage.isMustDeleted(NEW_GRAPE),
                "The removal of Must from the table has failed.");

        // if the Must is missing from the table and this test fails => Must is still part of the must count value.
        Assertions.assertTrue(winePage.checkMustCount(),
                "The must count fails to display the correct value or the table fails to update properly " +
                        "(Must is not being removed from the table)");

        // if the Must is missing from the table and this test fails => Must is still part of the must total volume count value.
        Assertions.assertTrue(winePage.checkMustVolume(),
                "The must total volume count fails to display the correct value or the table fails to update properly " +
                        "(Must is not being removed from the table)");

    }

    //6. Go back to Must page and check that your must is missing and that the values have been updated.

    @Test
    public void checkGrapePage(){

        winePage.navigateToGrapePage();
        Assertions.assertTrue(winePage.isGrapeDeleted(NEW_GRAPE),
                "The removal of the newly added grape from the table has failed.");

        // if the newly added grape is missing from the table and this test fails => newly added grape is still part of the must count value.
        Assertions.assertTrue(winePage.checkGrapeTotalQuantity(),
                "The total rows value displayed on the grapes page does NOT match the total quantity value in the table");

        // if the newly added grape is missing from the table and this test fails => newly added grape is still part of the Grape Types count value.
        Assertions.assertTrue(winePage.checkGrapeTypes(),
                "The total number of types of grapes does NOT match the total number of table rows.");

    }

}

