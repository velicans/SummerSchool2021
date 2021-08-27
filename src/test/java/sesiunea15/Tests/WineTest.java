package sesiunea15.Tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sesiunea15.Api.WinesApi;

import java.time.Instant;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static sesiunea15.helpers.Utils.sleep;

public class WineTest extends BaseTest {

    public static final String GRAPE_NAME = "My wine " + Instant.now();

    @BeforeAll
    public static void setUp() {
        WinesApi wineApi = new WinesApi();
        wineApi.addWine(GRAPE_NAME, "dry",300,"Merlot","cases");
        sleep(3);
    }

    @AfterAll
    public static void cleanUp() {
        WinesApi wineApi = new WinesApi();
        wineApi.getWines();
        String id = wineApi.getWineId(GRAPE_NAME);
        wineApi.deleteWine(id);

    }

    //Homework-total wine volume
    @Test
    public void checkTotalWineVolume(){

        assertThat(winesPO.checkWinesCountVolume(), is(true));
    }

}
