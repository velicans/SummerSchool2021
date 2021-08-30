package Sesiunea15.Tests;

import Sesiunea15.Api.MustApi;
import Sesiunea15.Api.WinesApi;
import Sesiunea15.enums.MenuOptions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Arrays;

import static Sesiunea15.helpers.Utils.sleep;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WinesTest extends BaseTest {

    public static final String GRAPE_NAME = "Cabernet Sauvignon " + Instant.now();


    @BeforeAll
    public static void setUp() {
        WinesApi winesApi = new WinesApi();
        winesApi.addWine(GRAPE_NAME,"0.75",1,"liters", Arrays.asList(new String[]{"grapes"}), "merlot");
        sleep(3);
    }

    @AfterAll
    public static void cleanUp() {
        WinesApi winesApi = new WinesApi();
        winesApi.getWines();
        winesApi.deleteWine(GRAPE_NAME);

    }

    @Test
    //homework session 15
    public void testMustTotalVolume() {

        menu.open(MenuOptions.WINES);
        sleep(1);
        boolean isVolumeTheSame = winesPO.checkWinesCountVolume();
        assertThat(isVolumeTheSame, is(true));

    }
}
