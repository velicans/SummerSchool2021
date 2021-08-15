package sesiunea12.tema;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class HomeworkWinePO {

    public WebDriver driver;
    public WebDriverWait wait;
    public final int TIMEOUT_IN_SECONDS = 10;

    public final String WINES_COUNT = ".App-page ul li:first-child";
    public final String WINE_TOTAL_VOLUME = ".App-page ul li:nth-child(2)";
    public final String WINE_ROW = "tbody tr";
    public final String VOLUME_TABLE_CELLS = "tbody tr td:nth-child(3)";
    public final String HEADER = "//*[contains(text(), 'Wine cellar contents:')]";

    public HomeworkWinePO(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT_IN_SECONDS);
    }

    public int getWineCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HEADER)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(WINES_COUNT)));
        final String typesOfGrapes = driver.findElement(By.cssSelector(WINES_COUNT)).getText();
        return Integer.parseInt(typesOfGrapes.split(" ")[typesOfGrapes.split(" ").length - 2]);
    }

    public int getWineTotalVolume() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HEADER)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(WINE_TOTAL_VOLUME)));
        final String typesOfGrapes = driver.findElement(By.cssSelector(WINE_TOTAL_VOLUME)).getText();
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
}
