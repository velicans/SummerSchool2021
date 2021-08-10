package sesiunea11.tema;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MustTest {

    private final long TIMEOUT = 10;
    public WebDriver driver = null;

    @BeforeAll
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://wineappui.azurewebsites.net");
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        WineyardPO wineyardPO = new WineyardPO();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(wineyardPO.addGrapesBtn)));
    }

    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void adaugaMust() {
        WineyardPO wineyardPO = new WineyardPO(driver);
        String grapeName = Utils.generateRandom();
        wineyardPO.addGrapes(grapeName);
        wineyardPO.clickPickAndCrush(grapeName);
    }
}
