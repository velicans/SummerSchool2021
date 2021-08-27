package Sesiunea15.Tests;

import Sesiunea15.Api.GrapeApi;
import Sesiunea15.Api.MustApi;
import Sesiunea15.enums.MenuOptions;
import groovy.grape.Grape;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static Sesiunea15.helpers.Utils.sleep;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GrapeTest extends BaseTest {

    private static final String GRAPE_NAME = "GRAPE " + Instant.now();
    private static final String GRAPE_QUANTITY = "50";
    private static final String GRAPE_AGE = "90";
    private static final String GRAPE_RIPENESS = "97";

    private GrapeApi grapeApi = new GrapeApi();

    @AfterAll
    public static void cleanUp() {
        MustApi mustApi = new MustApi();
        mustApi.getMusts();
        String id = mustApi.getMustId(GRAPE_NAME);
        mustApi.deleteMust(id);

        GrapeApi grapeApi = new GrapeApi();
        String grapeId = grapeApi.getGrapeId(GRAPE_NAME);
        grapeApi.deleteGrape(grapeId);
    }

    @Test @Disabled
    public void test() {

        menu.open(MenuOptions.GRAPE);
        grapesPO.addNewGrapeV2(GRAPE_NAME, "36", "rows", "80", "97");
        sleep(1);
        grapesPO.pickAndCrush(GRAPE_NAME);
        sleep(2);
        assertThat(mustPO.isMustAvailable(GRAPE_NAME), is(true));
    }

    @Test
    public void checkGrapesTotalRows(){

        menu.open(MenuOptions.GRAPE);

        int initialRowsNumber = grapesPO.sumUpGrapeRows();
        int initialRowsCounter = grapesPO.getCounterValue();

        sleep(2);

        grapeApi.addGrape(GRAPE_NAME, Float.parseFloat(GRAPE_QUANTITY), Integer.parseInt(GRAPE_AGE), Float.parseFloat(GRAPE_RIPENESS));

        sleep(2);

        menu.open(MenuOptions.HOME);

        int finalRowsNumber = grapesPO.sumUpGrapeRows();
        int finalRowsCounter = grapesPO.getCounterValue();

        assertThat(grapesPO.checkIfEqual(initialRowsCounter, initialRowsNumber, finalRowsCounter, finalRowsNumber, Integer.parseInt(GRAPE_QUANTITY)), is(true));
    }

}
