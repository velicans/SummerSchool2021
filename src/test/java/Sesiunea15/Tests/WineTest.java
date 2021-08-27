package Sesiunea15.Tests;

import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WineTest extends BaseTest {


    //TODO: Tema sesiunea 15
    @Test
    public void checkTotalWineVolume(){
        assertThat(winesPO.checkWinesCountVolume(), is(true));
    }

}
