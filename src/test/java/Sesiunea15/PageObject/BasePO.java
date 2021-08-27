package Sesiunea15.PageObject;

import Sesiunea15.helpers.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

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

    public int getCounterValue(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li")));
        List<WebElement> list = driver.findElements(By.cssSelector("li"));
        String extractedText = list.get(1).getText();

        return Integer.parseInt(extractedText.replaceAll("[^0-9]", ""));
    }

    public boolean checkIfEqual(int initialFirstValue, int initialSecondValue, int finalFirstValue, int finalSecondValue, int valueAdded ){
        boolean check = false;

        if(((initialFirstValue + valueAdded) == finalFirstValue) && ((initialSecondValue + valueAdded) == finalSecondValue)){
            check = true;
        }

        return check;
    }

    // get the total volume value from column "Volume"
    public int sumUpTableVolume(){
        int totalVolume = 0;

        wait.until(ExpectedConditions.presenceOfElementLocated(tableRows));
        List<WebElement> rows = driver.findElements(tableRows);
        for(WebElement row : rows) {
            if(row.findElements(By.tagName("td")).get(2).getText() != null){
                String extractedText = row.findElements(By.tagName("td")).get(2).getText();
                totalVolume += Integer.parseInt(extractedText.replaceAll("[^0-9]", ""));
            }
        }
        return totalVolume;
    }

    public String randomType(String[] list){
        Random random = new Random();

        return list[random.nextInt(list.length)];
    }

}
