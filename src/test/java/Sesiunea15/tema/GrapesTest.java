package Sesiunea15.tema;

import Sesiunea15.Api.GrapeApi;
import Sesiunea15.Tests.BaseTest;
import Sesiunea15.enums.MenuOptions;
import Sesiunea15.tema.pageobject.HomeworkWineyardPO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GrapesTest extends BaseTest {

    public static final String GRAPE_NAME = "struguri";
    private final HomeworkWineyardPO homeworkWineyardPO = new HomeworkWineyardPO(driver);

    @AfterEach
    public void cleanup() {
        GrapeApi grapeApi = new GrapeApi();
        grapeApi.getGrapes();
        String id = grapeApi.getGrapeId(GRAPE_NAME);
        grapeApi.deleteGrape(id);
    }

    @Test
    public void testTotalRows() {
        menu.open(MenuOptions.GRAPE);
        grapesPO.addNewGrapeV2(GRAPE_NAME, "36", "rows", "80", "97");
        Assertions.assertEquals(homeworkWineyardPO.countRows(), homeworkWineyardPO.getTotalRows());
    }
}
