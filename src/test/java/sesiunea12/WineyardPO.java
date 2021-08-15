package sesiunea12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WineyardPO {

    public WebDriver driver;
    public WebDriverWait wait;

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
    public String wineRows2 = "table > tbody >tr";
    public static final String TYPES_OF_GRAPES = "//ul/li[1]";
    public static final String TOTAL_GRAPES_ROWS = "//ul/li[2]";
    public static final String MUST_COUNT = "//ul/li[1]";
    public static final String MUST_TOTAL_VOLUME = "//ul/li[2]";
    public static final String WINE_COUNT = "//ul/li[1]";
    public static final String WINE_VOLUME = "//ul/li[2]";

    public WineyardPO(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public boolean amOnHomePage() {
        boolean isHomePageOpened = true;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(addGrapesBtn)));
        if (!driver.findElement(By.cssSelector(addGrapesBtn)).isDisplayed())
            isHomePageOpened = false;
        return isHomePageOpened;
    }

    //Grapes page
    public int sumGrapeRows() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        int noOfRows = 0;
        String qty;
        List<WebElement> listOfRows = driver.findElements(By.cssSelector(tableRows));
        for (WebElement row : listOfRows) {
            qty = row.findElements(By.tagName("td")).get(1).getText();
            noOfRows = noOfRows + Integer.parseInt(qty.replaceAll("[^0-9]", ""));
        }
        return noOfRows;
    }

    public int getTotalGrapeRows() {
        String s = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TOTAL_GRAPES_ROWS))).getText();
        return Integer.parseInt(s.replaceAll("[^0-9]", ""));
    }

    public int getTotalGrapeTypes() {
        String s = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TYPES_OF_GRAPES))).getText();
        return Integer.parseInt(s.replaceAll("[^0-9]", ""));
    }

    public int countGrapeTypes() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(tableRows));
        return list.size();
    }

    public void addNewGrape(String grapeName, String grapeQty, String grapeUnit, String grapeAge, String grapeRipeness) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(addGrapesBtn))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).sendKeys(grapeName);

        // define select for quantity dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(quantity)));
        Select quantitySelect = new Select(driver.findElement(By.cssSelector(quantity)));
        quantitySelect.selectByVisibleText(grapeQty);


        // define select for unit dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(unit)));
        Select unitSelect = new Select(driver.findElement(By.cssSelector(unit)));
        unitSelect.selectByVisibleText(grapeUnit);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(age))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(age))).sendKeys(grapeAge);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).sendKeys(grapeRipeness);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(submitBtn))).click();
    }

    public void pickAndCrushGrape(String grapeName) {
        List<WebElement> rows = driver.findElements(By.cssSelector(tableRows));
        for (WebElement row : rows) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(grapeName)) {
                wait.until(ExpectedConditions.elementToBeClickable(row.findElement(By.tagName("button")))).click();
                break;
            }
        }
    }

    // Must page
    public int getMustCount() {

        String s = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(MUST_COUNT))).getText();
        return Integer.parseInt(s.replaceAll("[^0-9]", ""));
    }

    public int getMustTotalVolume() {
        String s = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(MUST_TOTAL_VOLUME))).getText();
        return Integer.parseInt(s.replaceAll("[^0-9]", ""));
    }

    public int countMustTypes() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(mustRows));
        return list.size();
    }

    public int sumMustVolume() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustRows)));
        int volume = 0;
        String qty;
        List<WebElement> listOfRows = driver.findElements(By.cssSelector(mustRows));
        for (WebElement row : listOfRows) {
            qty = row.findElements(By.tagName("td")).get(2).getText();
            volume = volume + Integer.parseInt(qty.replaceAll("[^0-9]", ""));
        }
        return volume;
    }

    public void fermentMust(String grapeName) {
        driver.findElement(By.linkText("Must")).click();
        List<WebElement> rows = driver.findElements(By.cssSelector(mustRows));

        for (WebElement row : rows) {
            if (row.findElements(By.tagName("td")).get(1).getText().equals(grapeName)) {
//                row.findElement(By.tagName("input")).click();
//                wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("input"))).click();
                row.findElement(By.xpath(".//input[1]")).click();
                sleep();
                break;
            }
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("button"))).click();
    }

    //Wines page
    public int countWines() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));
        return list.size();
    }

    public int sumWinesVolume() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        int volume = 0;
        String qty;
        List<WebElement> rows = driver.findElements(By.cssSelector(wineRows));
        for (WebElement row : rows) {
            qty = row.findElements(By.tagName("td")).get(2).getText();
            volume = volume + Integer.parseInt(qty.replaceAll("[^0-9]", ""));
        }
        return volume;
    }

    public int getWinesCount() {
        String s = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(WINE_COUNT))).getText();
        return Integer.parseInt(s.replaceAll("[^0-9]", ""));
    }

    public int getWinesVolume() {
        String s = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(WINE_VOLUME))).getText();
        return Integer.parseInt(s.replaceAll("[^0-9]", ""));
    }


    public void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
