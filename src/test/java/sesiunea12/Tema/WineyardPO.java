package sesiunea12.Tema;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WineyardPO {
    public WebDriver driver;
    public WebDriverWait asteapta;

    public String addGrapesBtn = "button.animated-button";
    public String name = "input#name";
    public String quantity = "select#quantity";
    public String unit = "select#unit";
    public String age = "input#age";
    public String ripeness = "input#ripeness";
    public String submitBtn = "input[value='Submit']";
    public String tableRows = "table > tbody >tr";
    public String mustRows = "table > tbody >tr";
    public String fermentBtn = "button";
    public String wineRows = "table > tbody >tr";
    public String nrRows = "<ul>";

    public WineyardPO(WebDriver driver){
        this.driver = driver;
        asteapta = new WebDriverWait(driver, 10);
    }

}
