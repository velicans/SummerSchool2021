package sesiunea11.tema;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WineyardPO {

    private WebDriver webDriver;
    private static final long TIMEOUT = 10;

    public String addGrapesBtn = "button.animated-button";
    public String name = "input#name";
    public String quantity = "select#quantity";
    public String unit = "select#unit";
    public String age = "input#age";
    public String ripeness = "input#ripeness";
    public String submitBtn = "input[value='Submit']";
    public String tableRows = "table > tbody >tr";
    public String tableCellName = "//table//tbody//td[contains(text(), '%s')]";
    public String tableCellPickAndCrushButton = "//table//tbody//td[contains(text(), '%s')]/../td[5]/button";

    public WineyardPO() {
    }

    public WineyardPO(WebDriver driver) {
        this.webDriver = driver;
    }

    public void addGrapes(String grapeName) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, TIMEOUT);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(addGrapesBtn)));
        webDriver.findElement(By.cssSelector(addGrapesBtn)).click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(name)));
        webDriver.findElement(By.cssSelector(name)).clear();
        webDriver.findElement(By.cssSelector(name)).sendKeys(grapeName);

        webDriver.findElement(By.cssSelector(age)).clear();
        webDriver.findElement(By.cssSelector(age)).sendKeys("15");

        webDriver.findElement(By.cssSelector(ripeness)).clear();
        webDriver.findElement(By.cssSelector(ripeness)).sendKeys("99");

        // define select for quantity dropdown
        Select quantitySelect = new Select(webDriver.findElement(By.cssSelector(quantity)));
        quantitySelect.selectByVisibleText("48");

        webDriver.findElement(By.cssSelector(submitBtn)).click();
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(name)));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(tableCellName, grapeName))));
    }

    public void clickPickAndCrush(String grapeName) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, TIMEOUT);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(tableCellPickAndCrushButton, grapeName))));
        webDriver.findElement(By.xpath(String.format(tableCellPickAndCrushButton, grapeName))).click();
    }
}
