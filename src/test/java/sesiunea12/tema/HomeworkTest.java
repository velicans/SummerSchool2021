package sesiunea12.tema;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sesiunea12.BaseTest;

public class HomeworkTest extends BaseTest {

    @BeforeEach
    public void openPage() {
        driver.get("https://wineappui.azurewebsites.net/");
    }

    private final HomeworkWineyardPO homeworkWineyardPO = new HomeworkWineyardPO(driver);
    private final HomeworkMustPO homeworkMustPO = new HomeworkMustPO(driver);
    private final HomeworkWinePO homeworkWinePO = new HomeworkWinePO(driver);

    @Test
    public void homework() {
        final int typesOfGrapes = homeworkWineyardPO.getTypesOfGrapes();
        final int totalRows = homeworkWineyardPO.getTotalRows();
        final int quantityValue = 12;
        final String grapeName = Utils.generateRandomString();

        // Check that total rows value and type of grapes value are matching the values in table.
        Assertions.assertEquals(typesOfGrapes, homeworkWineyardPO.countWines());
        Assertions.assertEquals(totalRows, homeworkWineyardPO.countRows());

        // Add a new type of grape with ripeness of 97%.
        homeworkWineyardPO.addNewGrape(grapeName, 97, quantityValue);

        // Check that total rows value and type of grapes value are updated.
        Assertions.assertEquals(totalRows + quantityValue,homeworkWineyardPO.getTotalRows());
        Assertions.assertEquals(typesOfGrapes + 1,homeworkWineyardPO.getTypesOfGrapes());

        // Pick up and crush your newly added grape.
        homeworkWineyardPO.clickPickAndCrush(grapeName);

        final int mustCount = homeworkMustPO.getMustCount();
        final int mustTotalVolume = homeworkMustPO.getMustTotalVolume();

        // Check that must count value and must total volume matches the values from the table.
        Assertions.assertEquals(homeworkMustPO.countRows(), mustCount);
        Assertions.assertEquals(homeworkMustPO.countLiters(), mustTotalVolume);

        // Select your must and Ferment it.
        homeworkMustPO.clickFerment(grapeName);

        final int wineCount = homeworkWinePO.getWineCount();
        final int wineTotalVolume = homeworkWinePO.getWineTotalVolume();

        // Check that wines value and volume value matches the data displayed in table.
        Assertions.assertEquals(wineCount, homeworkWinePO.countRows());
        Assertions.assertEquals(wineTotalVolume, homeworkWinePO.countLiters());

        // Go back to Must page and check that your must is missing and that the values have been updated.
        homeworkWineyardPO.navigate();
        Assertions.assertEquals(typesOfGrapes, homeworkWineyardPO.countWines());
        Assertions.assertEquals(totalRows, homeworkWineyardPO.countRows());
        homeworkWineyardPO.assertGrapeIsMissing(grapeName);

        // Go back to Grapes page and check that your grape is missing and that the values have been updated.
        homeworkMustPO.navigate();
        Assertions.assertEquals(mustCount - 1, homeworkMustPO.countRows());
        Assertions.assertNotEquals(mustTotalVolume, homeworkMustPO.countLiters());
        homeworkMustPO.assertMustIsMissing(grapeName);
    }
}
