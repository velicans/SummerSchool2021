package Sesiunea15.Tests;

import Sesiunea15.PageObject.GrapesPO;
import Sesiunea15.PageObject.MenuPO;
import Sesiunea15.PageObject.MustPO;
import Sesiunea15.PageObject.WinesPO;
import Sesiunea15.helpers.DriverFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.RemoteWebDriver;

import static Sesiunea15.helpers.Utils.sleep;

public class BaseTest {

    protected static RemoteWebDriver driver;
    protected MenuPO menu = new MenuPO();
    protected GrapesPO grapesPO = new GrapesPO();
    protected MustPO mustPO = new MustPO();
    protected WinesPO winesPO = new WinesPO();

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
