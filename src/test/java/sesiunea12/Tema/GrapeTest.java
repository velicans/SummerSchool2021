package sesiunea12.Tema;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sesiunea12.BaseTest;

public class GrapeTest extends BaseTest {


    @BeforeEach
    public void openPage() {
        driver.get("https://wineappui.azurewebsites.net/");
    }

    private final WineyardPOTema wineyardPO = new WineyardPOTema(driver); //package ses12.tema

    final int grapeTotal = wineyardPO.grapeTotal();
    final int rowTotal = wineyardPO.rowTotal();

    final int mustCountInit = wineyardPO.mustCountInit();
    final int mustVolumeInit = wineyardPO.mustVolumeInit();

    final int wineCountInit = wineyardPO.wineCountInit();
    final int wineVolumeInit = wineyardPO.wineVolumeInit();


    @Test
    public void tasks() {

        //task 1

        Assertions.assertEquals(grapeTotal, wineyardPO.countGrapes());
        Assertions.assertEquals(rowTotal, wineyardPO.countRows());


        //task2
        wineyardPO.addNewGrape("my_final", 97, 12);


        // taSK3
        Assertions.assertEquals(rowTotal + 12, wineyardPO.rowTotal());
        Assertions.assertEquals(grapeTotal + 1, wineyardPO.grapeTotal());


        // task 4
        wineyardPO.clickPickAndCrush("my_final");

        //task 5

        Assertions.assertEquals(mustCountInit, wineyardPO.countRowsMust());
        Assertions.assertEquals(mustVolumeInit, wineyardPO.countVolume());

        // task6

        wineyardPO.fermentButton("my_test");

        //task7
        Assertions.assertEquals(wineCountInit, wineyardPO.countRowsWine());
        Assertions.assertEquals(wineVolumeInit, wineyardPO.countVolume());

        //task 8
        wineyardPO.mustCheck("my_test");
        Assertions.assertEquals(mustCountInit, wineyardPO.countRowsMust());
        Assertions.assertEquals(mustVolumeInit, wineyardPO.countVolume());



        //task 9
        wineyardPO.grapesCheck("my_test");
        Assertions.assertEquals(grapeTotal, wineyardPO.countGrapes());
        Assertions.assertEquals(rowTotal, wineyardPO.countRows());

    }
}