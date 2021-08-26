package Sesiunea15.PageObject;

import Sesiunea15.helpers.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePO {

    protected WebDriver driver;
    protected WebDriverWait wait;

    By tableRows = By.cssSelector("table > tbody >tr");

    public BasePO() {
        driver = DriverFactory.getDriver();
        wait = new WebDriverWait(driver, 10);
    }

    public int countWines() {
        wait.until(ExpectedConditions.presenceOfElementLocated(tableRows));
        List<WebElement> list = driver.findElements(tableRows);
        return list.size();
    }
}
