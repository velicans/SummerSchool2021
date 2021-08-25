package Sesiunea15.Tests;

import Sesiunea15.Api.WinesApi;
import Sesiunea15.enums.MenuOptions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static Sesiunea15.helpers.Utils.sleep;

public class End2EndTest extends BaseTest {

    public static final String GRAPE_NAME = "Cabernet Sauvignon";


    @AfterAll
    public static void cleanUp() {
        WinesApi winesApi = new WinesApi();
        winesApi.deleteWine(GRAPE_NAME);

    }

    @Test
    public void test() {

        menu.open(MenuOptions.GRAPE);
        grapesPO.addNewGrapeV2(GRAPE_NAME, "36", "rows", "80", "97");

        sleep(1);
        grapesPO.pickAndCrush(GRAPE_NAME);
        sleep(1);
        mustPO.selectAndFerment(GRAPE_NAME);
        sleep(1);
        winesPO.setLabel(GRAPE_NAME, "ce vrem noi");
        sleep(2);
        winesPO.setBottlingVol(GRAPE_NAME, "0.7");
        sleep(3);
    }
}
