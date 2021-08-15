package sesiunea12.Tema;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


import java.util.*;
import java.util.stream.Collectors;


public class WineyardPOTema extends sesiunea12.WineyardPO {

    public final String grapeType = "li:nth-child(1)";
    public final String rowTotal = "li:nth-child(2)";
    public final String quantity = "td:nth-child(2)";
    public final String page_grapes = "a:first-child";
    public final String mustCountInit = "li:first-child";
    public final String mustVolumeInit = "li:nth-child(2)";
    public final String page_must = "a:nth-child(3)";

    public final String tableRows = "table > tbody >tr";
    public final String volume = "td:nth-child(3)";

    public final String wineCountInit = "li:first-child";
    public final String wineVolumeInit = "li:nth-child(2)";

    public final String wineRow = "tbody tr";
    public final String mustRow = "tbody tr";
    public final String page_wine = "a:nth-child(4)";


    public String fermentBtn = "button";


    public String grapeRows = "table > tbody >tr";


    public WineyardPOTema(WebDriver driver) {
        super(driver);
    }

    public int grapeTotal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(grapeType)));
        String grapeTypes = driver.findElement(By.cssSelector(grapeType)).getText().replace("Grape Types: ", "");
        int grapeTypesInt = Integer.parseInt(grapeTypes);
        return grapeTypesInt;
    }

    public int rowTotal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(rowTotal)));
        String rows = driver.findElement(By.cssSelector(rowTotal)).getText().replace("Rows: ", "");
        int rowsInt = Integer.parseInt(rows);
        return rowsInt;
    }

    public int countGrapes() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(grapeRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(grapeRows));
        return list.size();
    }

    public int countRows() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(quantity)));
        List<WebElement> list = driver.findElements(By.cssSelector(quantity));

        List<Integer> newList = list.stream()
                .map(s -> Integer.parseInt(String.valueOf(s)))
                .collect(Collectors.toList());
        Integer sum = newList.stream()
                .mapToInt(Integer::intValue)
                .sum();


        return sum;

    }


    public void clickPickAndCrush(String grapeName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> rowsforPickAndCrush = driver.findElements(By.cssSelector(tableRows));
        for (WebElement row : rowsforPickAndCrush) {
            if (row.findElements(By.tagName("td")).get(1).getText().equals(grapeName)) {
                row.findElement(By.cssSelector("input")).click();

                break;
            }
        }

    }


    public int mustCountInit() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(page_must))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(mustCountInit)));

        String mustCount = driver.findElement(By.cssSelector(mustCountInit)).getText().replace("Must count: ", "");
        int mustCountInt = Integer.parseInt(mustCount);
        return mustCountInt;
    }

    public int mustVolumeInit() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(page_must))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(mustVolumeInit)));
        int mustVolume = Integer.parseInt(driver.findElement(By.cssSelector(mustVolumeInit)).getText().replace("Must count: ", ""));
        return mustVolume;
    }

    public int countRowsMust() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(tableRows));
        return list.size();
    }

    public int countVolume() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(volume)));
        List<WebElement> list = driver.findElements(By.cssSelector(volume));

        List<Integer> newList = list.stream()
                .map(s -> Integer.parseInt(String.valueOf(s)))
                .collect(Collectors.toList());
        Integer sum = newList.stream()
                .mapToInt(Integer::intValue)
                .sum();


        return sum;

    }

    public void fermentButton(String must_name) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(fermentBtn))).click();
    }


    public int wineCountInit() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(page_wine))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(wineCountInit)));
        return Integer.parseInt(driver.findElement(By.cssSelector(wineCountInit)).getText().replace("Wine count: ", ""));

    }

    public int wineVolumeInit() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(page_wine))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(wineVolumeInit)));
        return Integer.parseInt(driver.findElement(By.cssSelector(wineVolumeInit)).getText().replace("Wine count: ", ""));

    }

    public int countRowsWine() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRow)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRow));
        return list.size();
    }

    public void mustCheck(String mustName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(page_must))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustRow)));
        List<WebElement> rows = driver.findElements(By.cssSelector(tableRows));
        for (WebElement row : rows) {
            if (row.findElements(By.tagName("td")).get(1).getText().equals(mustName))
                System.out.println("Must was still found");
            else System.out.println("It wasnt found");
            break;
        }
    }
    public void grapesCheck(String grapeNmae){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(page_grapes))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(grapeRows)));
        List<WebElement> rows = driver.findElements(By.cssSelector(tableRows));
        for (WebElement row : rows) {
            if (row.findElements(By.tagName("td")).get(1).getText().equals(grapeNmae))
                System.out.println("Grape was found");
            else System.out.println("It wasnt found");
            break;
        }
    }


    public void addNewGrape(String grapeName, int ripenessValue, int quantityValue) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(addGrapesBtn))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).sendKeys(grapeName);

        // define select for quantity dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(quantity)));
        Select quantitySelect = new Select(driver.findElement(By.cssSelector(quantity)));
        quantitySelect.selectByVisibleText(String.valueOf(quantityValue));


        // define select for unit dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(unit)));
        Select unitSelect = new Select(driver.findElement(By.cssSelector(unit)));
        unitSelect.selectByVisibleText("cases");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(age))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(age))).sendKeys("3");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).sendKeys(String.valueOf(ripenessValue));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(submitBtn))).click();

    }


}


