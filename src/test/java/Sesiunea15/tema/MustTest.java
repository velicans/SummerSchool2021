package Sesiunea15.tema;

import Sesiunea15.Tests.BaseTest;
import Sesiunea15.enums.MenuOptions;
import Sesiunea15.tema.pageobject.HomeworkMustPO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class MustTest extends BaseTest {

    public static final String GRAPE_NAME = "Cabernet Sauvignon " + Instant.now();
    private HomeworkMustPO homeworkMustPO = new HomeworkMustPO(driver);

    @Test
    public void test() {
        menu.open(MenuOptions.MUST);
        Assertions.assertEquals(homeworkMustPO.getMustTotalVolume(), homeworkMustPO.countLiters());
    }
}
