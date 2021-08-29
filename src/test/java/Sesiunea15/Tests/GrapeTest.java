package Sesiunea15.Tests;

import Sesiunea15.Api.MustApi;
import Sesiunea15.enums.MenuOptions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static Sesiunea15.helpers.Utils.sleep;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GrapeTest extends BaseTest {

    public static final String GRAPE_NAME = "Cabernet Sauvignon";


    @AfterAll
    public static void cleanUp() {
        MustApi mustApi = new MustApi();
        mustApi.getMusts();
        String id = mustApi.getMustId(GRAPE_NAME);
        mustApi.deleteMust(id);

    }

    @Test
    public void test() {

        menu.open(MenuOptions.GRAPE);
        grapesPO.addNewGrapeV2(GRAPE_NAME, "36", "rows", "80", "97");
        sleep(1);
        grapesPO.pickAndCrush(GRAPE_NAME);
        sleep(2);
        assertThat(mustPO.isMustAvailable(GRAPE_NAME), is(true));
    }

    @Test
    public void testTotalRowsGrapes() {

        menu.open(MenuOptions.GRAPE);
        assertThat(grapesPO.checkRowsTypes(), is(true));
    }
}
