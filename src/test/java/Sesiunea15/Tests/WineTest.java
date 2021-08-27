package Sesiunea15.Tests;

import Sesiunea15.Api.MustApi;
import Sesiunea15.Api.WinesApi;
import Sesiunea15.enums.MenuOptions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static Sesiunea15.helpers.Utils.sleep;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WineTest extends BaseTest{

    private static final String WINE_NAME = "vin de masa";
    private static final String BOTTLING_VOLUME = "ABC";
    private static final int WINE_VOLUME = 50;
    private static final String WINE_UNIT = "liters";
    private static List<String> COMPONENTS = new LinkedList<>(Arrays.asList("must1", "must2", "must3"));
    private String[] types = {"sweet", "dry", "semi sweet"};

    WinesApi winesApi = new WinesApi();

    @Test
    public void test(){

        menu.open(MenuOptions.WINES);

        int initialRowsNumber = winesPO.sumUpTableVolume();
        int initialRowsCounter = mustPO.getCounterValue();

        sleep(2);

        winesApi.addWine(WINE_NAME, BOTTLING_VOLUME, WINE_VOLUME, WINE_UNIT, COMPONENTS, winesPO.randomType(types) );

        sleep(1);
        menu.open(MenuOptions.HOME);
        sleep(1);
        menu.open(MenuOptions.MUST);

        int finalRowsNumber = mustPO.sumUpTableVolume();
        int finalRowsCounter = mustPO.getCounterValue();

        assertThat(grapesPO.checkIfEqual(initialRowsCounter, initialRowsNumber, finalRowsCounter, finalRowsNumber, WINE_VOLUME), is(true));

    }
}
