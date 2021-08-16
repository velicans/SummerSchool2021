package sesiunea12;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class WineTest extends BaseTest {

    WineyardPO winePage = new WineyardPO(driver);
    public static final String GRAPE_NAME = "FeteascaNeagraPavel3";

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

    //Homework session 12 part II
    //1.Add a new type of grape with ripeness of 97%.
    @Test
    public void addNewGrape2(){

        int initialValue = winePage.countWines();
        winePage.addNewGrape2(GRAPE_NAME,"48","cases","100","97");
        int finalValue = winePage.countWines();
        Assertions.assertTrue(finalValue - initialValue == 1, "New grape was NOT added successfully!");
    }

    //2.Pick up and crush your newly added grape.
    @Test
    public void pickAndCrush(){
        winePage.pickAndCrush(GRAPE_NAME);
    }

    //3.Select your must and Ferment it.
    @Test
    public void selectAndFerment(){
        winePage.selectAndFerment(GRAPE_NAME);
    }
    //4.Go back to Grapes page and check that your grape is missing
    @Test
    public void checkGrapePage(){

        Assertions.assertTrue(winePage.checkGrapeMissing(GRAPE_NAME));

    }

    //5.Go back to Must page and check that your must is missing
    @Test
    public void checkMustPage(){
        Assertions.assertTrue(winePage.checkMustMissing(GRAPE_NAME));
    }




}