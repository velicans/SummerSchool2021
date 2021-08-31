package Sesiunea15.Tests;

import Sesiunea15.Api.WinesApi;
import Sesiunea15.enums.MenuOptions;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static Sesiunea15.helpers.Utils.sleep;


public class WineTest extends BaseTest {
    public static final String GRAPE_NAME = "Chianti " + Instant.now();

    @BeforeAll
    public static void setUp() {
        WinesApi winesApi = new WinesApi();
        winesApi.addWine(GRAPE_NAME, "1", 1, "liters", List.of("grapes"), "sweet");
        sleep(2);

    }

    @AfterAll
    public static void cleanUp() {
        WinesApi winesApi = new WinesApi();
        winesApi.getWines();
        winesApi.deleteWine(GRAPE_NAME);

    }

    @Test
    public  void test(){
        menu.open(MenuOptions.WINES);
        sleep(1);
        MatcherAssert.assertThat(winesPO.checkWinesCountVolume(), (true));
    }



}


