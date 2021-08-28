package Sesiunea15.tema;

import Sesiunea15.Tests.BaseTest;
import Sesiunea15.enums.MenuOptions;
import Sesiunea15.tema.pageobject.HomeworkWinePO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WineTest extends BaseTest {

    private HomeworkWinePO homeworkWinePO = new HomeworkWinePO(driver);

    @Test
    public void test() {
        menu.open(MenuOptions.WINES);
        Assertions.assertEquals(homeworkWinePO.getWineTotalVolume(), homeworkWinePO.countLiters());
    }
}
