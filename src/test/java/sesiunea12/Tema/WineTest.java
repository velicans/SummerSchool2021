package sesiunea12.Tema;

import org.jsoup.Connection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WineTest extends BaseTest {

    WineyardPO grapesPage = new WineyardPO(driver);

    @BeforeEach
    public void OpenPage(){
        driver.get("https://wineappui.azurewebsites.net/");
    }

    @Test
    public void equalNumberOfRows(){
        WebElement rows = driver.findElements(By.cssSelector(grapesPage.nrRows));
        String numberOfRows = rows.findElement(By.tagName("li")).get(2).getText().toString();

        Assertions.assertTrue(numberOfRows.equals("57"), "The number of rows doesn't match the number from the table.");
    }



}
