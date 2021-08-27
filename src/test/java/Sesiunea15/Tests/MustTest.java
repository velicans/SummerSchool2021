package Sesiunea15.Tests;

import Sesiunea15.Api.MustApi;
import Sesiunea15.Api.WinesApi;
import Sesiunea15.enums.MenuOptions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static Sesiunea15.helpers.Utils.sleep;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MustTest extends BaseTest {

    private static final String MUST_NAME = "MUST " + Instant.now();
    private static final int MUST_VOLUME = 50;
    private static final String MUST_UNIT = "liters";
    private static final String MUST_TYPE = "sweet";

    MustApi mustApi = new MustApi();

    @AfterAll
    public static void cleanUp() {
        WinesApi winesApi = new WinesApi();
        winesApi.getWines();
        winesApi.deleteWine(MUST_NAME);

    }

    @Test @Disabled
    public void test() {

        menu.open(MenuOptions.MUST);
        sleep(1);
        int beforeValue = winesPO.countWines();
        mustPO.selectAndFerment(MUST_NAME);
        sleep(1);
        menu.open(MenuOptions.MUST);
        sleep(1);
        int afterValue = winesPO.countWines();

        assertThat(afterValue, is(beforeValue - 1));
    }


    //TODO: Tema sesiunea 15
    @Test
    public void checkMustTotalVolume(){

        menu.open(MenuOptions.MUST);

        int initialRowsNumber = mustPO.sumUpTableVolume();
        int initialRowsCounter = mustPO.getCounterValue();

        sleep(2);

        mustApi.addMust(MUST_NAME, MUST_VOLUME, MUST_UNIT, MUST_TYPE);

        sleep(1);
        menu.open(MenuOptions.HOME);
        sleep(1);
        menu.open(MenuOptions.MUST);

        int finalRowsNumber = mustPO.sumUpTableVolume();
        int finalRowsCounter = mustPO.getCounterValue();

        assertThat(grapesPO.checkIfEqual(initialRowsCounter, initialRowsNumber, finalRowsCounter, finalRowsNumber, MUST_VOLUME), is(true));
    }

}
