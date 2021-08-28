package Sesiunea15.Tests;

import Sesiunea15.Api.MustApi;
import Sesiunea15.Api.WinesApi;
import Sesiunea15.enums.MenuOptions;
import org.jsoup.Connection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Collections;

import static Sesiunea15.helpers.Utils.sleep;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WinesTest extends BaseTest {

    public static final String GRAPE_NAME = "Cabernet Sauvignon " + Instant.now();


    @BeforeAll
    public static void setUp() {
        WinesApi winesAPI = new WinesApi();
        winesAPI.addWine("numeVin","volume",1,"L", Collections.singletonList(GRAPE_NAME),"tipVIn");
        sleep(1);
    }

    @AfterAll
    public static void cleanUp() {
        WinesApi winesAPI = new WinesApi();
        winesAPI.getWines();
        winesAPI.deleteWine(GRAPE_NAME);
        sleep(1);
    }

    @Test
    public  void test(){
        menu.open(MenuOptions.WINES);
        sleep(1);
        assertThat(winesPO.checkWinesCountVolume(), is(true));
    }
}
