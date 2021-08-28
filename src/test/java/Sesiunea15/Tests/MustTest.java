package Sesiunea15.Tests;

import Sesiunea15.Api.MustApi;
import Sesiunea15.Api.WinesApi;
import Sesiunea15.enums.MenuOptions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static Sesiunea15.helpers.Utils.sleep;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MustTest extends BaseTest {

    public static final String MUST_NAME = "Must " + Instant.now();
    MustApi mustApi = new MustApi();

    @BeforeAll
    public static void setUp() {
        MustApi mustApi = new MustApi();
        mustApi.addMust(MUST_NAME, 300, "liters", "savignon");
        sleep(3);
    }

    @AfterAll
    public static void cleanUp() {
        WinesApi winesApi = new WinesApi();
        winesApi.getWines();
        winesApi.deleteWine(MUST_NAME);

    }

    @Test
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
    @Test
    public void checkMustTotalVolume(){
        menu.open(MenuOptions.MUST);
        sleep(2);
        assertThat(mustPO.checkMustVolume(),is(true));


    }
}
