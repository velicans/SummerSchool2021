package sesiunea15.Tests;


import sesiunea15.PageObject.GrapesPO;
import sesiunea15.PageObject.MenuPO;
import sesiunea15.PageObject.MustPO;
import sesiunea15.PageObject.WinesPO;
import sesiunea15.helpers.DriverFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.RemoteWebDriver;

import static sesiunea15.helpers.Utils.sleep;

public class BaseTest {

    private static RemoteWebDriver driver;
    MenuPO menu = new MenuPO();
    GrapesPO grapesPO = new GrapesPO();
    MustPO mustPO = new MustPO();
    WinesPO winesPO = new WinesPO();

    @BeforeAll
    public static void before() {

        DriverFactory.setDriver();
        driver = DriverFactory.getDriver();
        driver.get("https://wineappui.azurewebsites.net/");
        sleep(2);

    }

    @AfterAll
    public static void tearDown() {
        sleep(3);
        driver.quit();
    }
}