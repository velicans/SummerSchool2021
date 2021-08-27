package sesiunea15.Tests;

import sesiunea15.Api.MustApi;
import sesiunea15.Api.WinesApi;
import sesiunea15.enums.MenuOptions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static sesiunea15.helpers.Utils.sleep;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MustTest extends BaseTest {


    public static final String GRAPE_NAME = "Cabernet Sauvignon " + Instant.now();


    @BeforeAll
    public static void setUp() {
        MustApi mustApi = new MustApi();
        mustApi.addMust(GRAPE_NAME, 300, "liters", "savignon");
        sleep(3);
    }

    @AfterAll
    public static void cleanUp() {
        WinesApi winesApi = new WinesApi();
        winesApi.getWines();
        winesApi.deleteWine(GRAPE_NAME);

    }

    @Test
    public void test() {
        menu.open(MenuOptions.MUST);
        sleep(1);
        int beforeValue = winesPO.countWines();
        mustPO.selectAndFerment(GRAPE_NAME);
        sleep(1);
        menu.open(MenuOptions.MUST);
        sleep(1);
        int afterValue = winesPO.countWines();
        assertThat(afterValue, is(beforeValue - 1));
    }

    //Homework-Must total volume
    @Test
    public void checkMustTotalVolume() {

        assertThat(mustPO.checkMustCountVolume(), is(true));
    }
}
