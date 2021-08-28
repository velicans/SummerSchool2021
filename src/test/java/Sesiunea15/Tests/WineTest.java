package Sesiunea15.Tests;

import Sesiunea15.enums.MenuOptions;
import org.junit.jupiter.api.Test;
import static Sesiunea15.helpers.Utils.sleep;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WineTest extends BaseTest{


    @Test
    public void checkWineVolume(){
        menu.open(MenuOptions.WINES);
        sleep(2);
        assertThat(winesPO.checkWineVolume(),is(true));


    }
}
