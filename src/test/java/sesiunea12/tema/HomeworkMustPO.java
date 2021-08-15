package sesiunea12.tema;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class HomeworkMustPO {

    public WebDriver driver;
    public WebDriverWait wait;
    public final int TIMEOUT_IN_SECONDS = 10;

    public final String MUST_COUNT = ".App-page ul li:first-child";
    public final String MUST_TOTAL_VOLUME = ".App-page ul li:nth-child(2)";
    public final String WINE_ROW = "tbody tr";
    public final String VOLUME_TABLE_CELLS = "tbody tr td:nth-child(3)";
    public final String HEADER = "//*[contains(text(), 'Must:')]";
    public final String FERMENT_BUTTON = "//tbody//tr/td[contains(text(), '%s')]/..//td/input";
    public final String FERMENT_ALL_BUTTON = "button";
    public final String MUST_LINK = ".App-header a:nth-child(3)";
    public final String MUST_TABLE_CELL = "//tbody//tr/td[contains(text(), '%s')]";

    public HomeworkMustPO(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT_IN_SECONDS);
    }

    public int getMustCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HEADER)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(MUST_COUNT)));
        final String typesOfGrapes = driver.findElement(By.cssSelector(MUST_COUNT)).getText();
        return Integer.parseInt(typesOfGrapes.split(" ")[typesOfGrapes.split(" ").length - 1]);
    }

    public int getMustTotalVolume() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HEADER)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(MUST_TOTAL_VOLUME)));
        final String typesOfGrapes = driver.findElement(By.cssSelector(MUST_TOTAL_VOLUME)).getText();
        return Integer.parseInt(typesOfGrapes.split(" ")[typesOfGrapes.split(" ").length - 2]);
    }

    public int countRows() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(WINE_ROW)));
        List<WebElement> list = driver.findElements(By.cssSelector(WINE_ROW));
        return list.size();
    }

    public int countLiters() {
        return driver.findElements(By.cssSelector(VOLUME_TABLE_CELLS))
                .stream()
                .map(webElement -> Arrays.stream(webElement.getText().split(" ")).findFirst())
                .map(s -> Integer.parseInt(s.get()))
                .reduce(0, Integer::sum);
    }

    public void clickFerment(String grapeName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(FERMENT_BUTTON, grapeName)))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(FERMENT_ALL_BUTTON))).click();
    }

    public void navigate() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(MUST_LINK))).click();
    }

    public void assertMustIsMissing(String grapeName) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(String.format(MUST_TABLE_CELL, grapeName))));
    }
}
