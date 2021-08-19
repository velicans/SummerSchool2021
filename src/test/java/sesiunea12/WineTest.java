package sesiunea12;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WineTest extends BaseTest {

    WineyardPO winePage = new WineyardPO(driver);
    public WebDriverWait wait = new WebDriverWait(driver, 10);

    static int initialNumberOfTypes;
    static int initialGrapeRows;
    static int initialTotalQuantity;
    static int initialRowsQuantitySum;

    @BeforeEach
    public void openPage() {
        driver.get("https://wineappui.azurewebsites.net/");
    }

    /*@Test
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
    }*/

    @Test
    @Order(1)
    //Check that total rows value and type of grapes value are matching the values in table.
    public void typeOfGrapesEqualsValue() {
        initialNumberOfTypes = winePage.getNumberOfTypes();
        System.out.println("Initial number of types: " + initialNumberOfTypes);
        initialGrapeRows = winePage.countWines();
        System.out.println("Initial number of grape rows: " + initialGrapeRows);
        Assertions.assertTrue(initialGrapeRows == initialNumberOfTypes, "The type of grapes value does not match the values from the table");
    }

    @Test
    @Order(2)
    //Check that total rows value and type of grapes value are matching the values in table.
    public void totalRowsEqualsSumRows() {
        initialTotalQuantity = winePage.getTotalRows();
        System.out.println("Total rows: " + initialTotalQuantity);
        initialRowsQuantitySum = winePage.getSumRows();
        System.out.println("Rows sum: " + initialRowsQuantitySum);
        Assertions.assertTrue(initialTotalQuantity == initialRowsQuantitySum, "The total sum does not match the row sum");

    }


    //Add a new type of grape with ripeness of 97%.
    //Check that total rows value and type of grapes value are updated.
    @Test
    @Order(3)
    public void addNewGrapeTest() {
        winePage.addNewGrape();
        int finalTotalRows = winePage.getTotalRows();
        System.out.println("Final total rows: " + finalTotalRows);
        int finalNumberOfTypes =winePage.getNumberOfTypes();
        System.out.println("Final number of types: " + finalNumberOfTypes);
        Assertions.assertTrue((finalTotalRows - initialTotalQuantity == 36) && (finalNumberOfTypes - initialNumberOfTypes == 1) , "New grape was NOT added successfully!");
    }

        //Pick up and crush your newly added grape.
        //Check that must count value and must total volume matches the values from the table.
    @Test
    @Order(4)
    public void mustValuesTest() {
        winePage.pickUpAndCrush();
        int mustCountNr = winePage.getMustCount();
        System.out.println("Must count value " + mustCountNr);
        int mustTotalVolumeNr = winePage.getMustVolume();
        System.out.println("Must total volume number " + mustTotalVolumeNr);
        int mustRows= winePage.countWines();
        System.out.println("Must total rows " + mustRows);
        int sumVolume= winePage.getSumMustRows();
        System.out.println("Sum must volume " + sumVolume);
        Assertions.assertTrue((mustCountNr == mustRows)&&(mustTotalVolumeNr==sumVolume), "must count value or must total volume matches the values from the table");


    }


//Select your must and Ferment it.
//Check that wines value and volume value matches the data displayed in table.
@Test
@Order(5)
public void winesValuesTest() {
    winePage.goToMust();
    winePage.selectAndFerment();
    int winesCountNr = winePage.getWinesNr();
    System.out.println("Wines count value " + winesCountNr);
    int winesTotalVolumeNr = winePage.getWinesVolumeNr();
    System.out.println("Wines total volume number " + winesTotalVolumeNr);
    int winesRows= winePage.countWines();
    System.out.println("Wines total rows " + winesRows);
    int sumWinesVolume= winePage.getSumMustRows();
    System.out.println("Sum must volume " + sumWinesVolume);
    Assertions.assertTrue((winesCountNr == winesRows)&&(winesTotalVolumeNr==sumWinesVolume), "wines count value or must total volume matches the values from the table");


}
//Go back to Must page and check that your must is missing and that the values have been updated.

    @Test
    @Order(6)
    public void isMustMissing() {
        winePage.goToMust();
        boolean isMissing = winePage.mustIsmissing();
        Assertions.assertTrue(isMissing, "The must is not missing from the table");
    }

    //Go back to Grapes page and check that your grape is missing and that the values have been updated.
    @Test
    @Order(7)


    public void isGrapeMissing() {
        winePage.goToGrapes();
        boolean grapeIsMissing = winePage.grapeIsMissing();
        Assertions.assertTrue(grapeIsMissing, "The grape is not missing from the table");
    }

    @Test
    @Order(8)

    public void grapesValueUpdated(){

        int finalTotalRows = winePage.getTotalRows();
        System.out.println("Final total rows: " + finalTotalRows);
        int finalNumberOfTypes =winePage.getNumberOfTypes();
        System.out.println("Final number of types: " + finalNumberOfTypes);
        Assertions.assertTrue((finalTotalRows==initialTotalQuantity) && (finalNumberOfTypes==initialNumberOfTypes) , "New grape was NOT removed from the values of the table!");

    }




}