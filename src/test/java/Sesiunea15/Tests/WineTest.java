package Sesiunea15.Tests;

import Sesiunea15.enums.MenuOptions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WineTest extends BaseTest {
    @Test
    public void testWineVolume() {

        menu.open(MenuOptions.WINES);
        assertThat(winesPO.checkWinesCountVolume(), is(true));

    }


}
