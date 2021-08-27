package Sesiunea15.PageObject;

import Sesiunea15.enums.MenuOptions;
import Sesiunea15.helpers.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuPO extends BasePO {
    By homePage = By.cssSelector("a:nth-child(1)");
    By grapePage = By.cssSelector("a:nth-child(2)");
    By mustPage = By.cssSelector("a:nth-child(3)");
    By winePage = By.cssSelector("a:nth-child(4)");

    public MenuPO() {
        driver = DriverFactory.getDriver();
        wait = new WebDriverWait(driver, 10);
    }

    public void open(MenuOptions option) {
        switch (option) {
            case HOME:
                wait.until(ExpectedConditions.presenceOfElementLocated(homePage)).click();
                break;
            case GRAPE:
                wait.until(ExpectedConditions.presenceOfElementLocated(grapePage)).click();
                break;
            case MUST:
                wait.until(ExpectedConditions.presenceOfElementLocated(mustPage)).click();
                break;
            case WINES:
                wait.until(ExpectedConditions.presenceOfElementLocated(winePage)).click();
                break;
        }
    }
}
