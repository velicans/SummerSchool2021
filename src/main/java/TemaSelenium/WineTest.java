package TemaSelenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class WineTest extends BaseTest {

    WineyardPO winePage = new WineyardPO(driver);
    @BeforeEach
    public void openPage(){
        driver.get("https://wineappui.azurewebsites.net");
    }

    @Test
    public void isHomePageOpened(){
        boolean isOpened = winePage.amOnHomePage();
        Assertions.assertTrue(isOpened, "Home page did not open seccessfully!");
    }

    @Test
    public void areTenWines(){
        Assertions.assertTrue(winePage.countWines()==13, "There is a different number of wines in list"+ winePage.countWines());
    }

    @Test
    public void addNewGrape(){
        int initialValue= winePage.countWines();
        winePage.addNewGrape();
        int finalValue = winePage.countWines();
        Assertions.assertTrue(finalValue-initialValue==1, "New grape was NOT added successfully!");
    }

    @Test
    public void typesOfGrapes(){
        System.out.println(winePage.typesOfGrapesAreMatching());
        Assertions.assertTrue(winePage.typesOfGrapesAreMatching() , "There is a different number of grapes in list "+winePage.countWines());
    }

    @Test
    public void totalRows(){
        System.out.println(winePage.totalRows());
        Assertions.assertTrue(winePage.totalRows(), "There is a different number of rows in list ");
    }

    @Test
    public void pickUpAndCrush(){
        winePage.pickUpAndCrush();
    }

    @Test
    public void mustCount(){
        System.out.println(winePage.mustCountsAreMatching());
        Assertions.assertTrue(winePage.mustCountsAreMatching() , "There is a different number of musts in list "+winePage.countWines());
    }

    @Test
    public void mustVolume(){
        System.out.println(winePage.mustTotalVolume());
        Assertions.assertTrue(winePage.mustTotalVolume(), "There is a different number of rows in list ");
    }

    @Test
    public void ferment(){
        winePage.fermentMust();
    }

/*    public void verificationMustPage(){
        driver.findElement(By.xpath("//a[contains(text(),'Must')]")).click();

    }*/


}
