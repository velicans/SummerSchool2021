package sesiunea12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

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
    public String totalRowsCalculated = "ul > li:nth-child(2)";
    public String typesOfGrapes = "ul > li:nth-child(1)";
    public String mustMenu = "a[href*=\"must\"]";
    public String wineTable = "App-table";
    public String wineMenu = "a[href*=\"wines\"]";
    public String totalMustVolumeCalculated = "ul > li:nth-child(2)";
    public String totalMustCountCalculated = "ul > li:nth-child(1)";
    public String totalWinesVolumeCalculated = "ul > li:nth-child(2)";
    public String totalWinesCountCalculated = "ul > li:nth-child(1)";

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

    public int countWines(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));
        return list.size();
    }

    public void addNewGrape(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(addGrapesBtn))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).sendKeys("testName");

        // define select for quantity dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(quantity)));
        Select quantitySelect = new Select(driver.findElement(By.cssSelector(quantity)));
        quantitySelect.selectByVisibleText("36");


        // define select for unit dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(unit)));
        Select unitSelect = new Select(driver.findElement(By.cssSelector(unit)));
        unitSelect.selectByVisibleText("cases");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(age))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(age))).sendKeys("3");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).sendKeys("98");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(submitBtn))).click();
    }

    public void addNewGrape_Updated(String addName, String addQuantity, String addUnit, String addAge, String addRipness){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(addGrapesBtn))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).sendKeys(addName);

        // define select for quantity dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(quantity)));
        Select quantitySelect = new Select(driver.findElement(By.cssSelector(quantity)));
        quantitySelect.selectByVisibleText(addQuantity);


        // define select for unit dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(unit)));
        Select unitSelect = new Select(driver.findElement(By.cssSelector(unit)));
        unitSelect.selectByVisibleText(addUnit);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(age))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(age))).sendKeys(addAge);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).sendKeys(addRipness);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(submitBtn))).click();
    }

    public int getTotalRowsCalculated(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(totalRowsCalculated)));
        WebElement totalRows = driver.findElement(By.cssSelector(totalRowsCalculated));
        String numar = totalRows.getText();
        return Integer.parseInt(numar.substring(numar.lastIndexOf(":") + 2));
    }

    public int getTypesOfGrapesFromPage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        WebElement typesOfGrapes1 = driver.findElement(By.cssSelector(typesOfGrapes));
        String nrTypesCalculated = typesOfGrapes1.getText();

        return Integer.parseInt(nrTypesCalculated.substring(nrTypesCalculated.lastIndexOf(":") + 2));
    }

    public int getTypesOfGrapesCalculated(){
        List<WebElement> listWithDuplicates = driver.findElements(By.cssSelector(wineRows));
        List<String> listGrapeNames = new ArrayList<String>();

        for (WebElement row : listWithDuplicates) {
            listGrapeNames.add(row.findElements(By.tagName("td")).get(0).getText());
        }

        //remove duplicate values
        Set<String> set = new HashSet<>(listGrapeNames);
        listGrapeNames.clear();
        listGrapeNames.addAll(set);

        return listGrapeNames.size();
    }

    public void pickAndCrush(String GRAPE_NAME) throws InterruptedException {
        int i=1;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> rows = driver.findElements(By.cssSelector(tableRows));
        for (WebElement row : rows) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)) {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("table > tbody > tr:nth-child(" + i + ")" + " > td > button"))).click();
                //nu am reusit sa apas butonul acesta fara sa ii pun sleep. nu am gasit o alta metoda functionala pana acum.
                Thread.sleep(1000);
                break;
            }
            else
            {
                i+=1;
            }

        }

    }

    public void openMust(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustMenu))).click();
    }

    public int getMustCountFromPage(){

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(totalMustCountCalculated)));
        WebElement totalRows = driver.findElement(By.cssSelector(totalMustCountCalculated));
        String numar = totalRows.getText();
        return Integer.parseInt(numar.substring(numar.lastIndexOf(":") + 2));
    }

    public int getMustVolumeFromPage(){

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(totalMustVolumeCalculated)));
        WebElement totalVolume = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(totalMustVolumeCalculated)));
        String totalVolumeCalculatedOnPageString = totalVolume.getText();

        totalVolumeCalculatedOnPageString = totalVolumeCalculatedOnPageString.substring(totalVolumeCalculatedOnPageString.lastIndexOf(":") + 2);
        totalVolumeCalculatedOnPageString = totalVolumeCalculatedOnPageString.substring(0,totalVolumeCalculatedOnPageString.indexOf(" "));

        int totalVolumeCalculatedOnPage;
        return totalVolumeCalculatedOnPage = Integer.parseInt(totalVolumeCalculatedOnPageString);
    }

    public int calculateSumVolume(){

        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

        int sumVolumeFromTableRow = 0;

        //Thread.sleep(2000);

        for (WebElement row : list) {
            String volumeFromTableRow = row.findElements(By.tagName("td")).get(2).getText();
            sumVolumeFromTableRow = sumVolumeFromTableRow + Integer.parseInt(volumeFromTableRow.substring(0,volumeFromTableRow.indexOf(" ")));
        }
        return sumVolumeFromTableRow;
    }

    public void fermentMust(String GRAPE_NAME) throws InterruptedException {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> rowsMust = driver.findElements(By.cssSelector(wineRows));

        int i = 1;

        for (WebElement row : rowsMust) {
            if (row.findElements(By.tagName("td")).get(1).getText().equals(GRAPE_NAME)) {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[" + i + "]//input[@type='checkbox']"))).click();
                Thread.sleep(1000);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Ferment')]"))).click();
                break;
            } else {
                i += 1;
            }
        }
    }

    public void openWines(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineMenu))).click();
    }

    public int getWinesCountFromPage(){

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(totalWinesCountCalculated)));
        WebElement winesCount = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(totalWinesCountCalculated)));
        String totalWinesCalculatedOnPageString = winesCount.getText();

        totalWinesCalculatedOnPageString = totalWinesCalculatedOnPageString.substring(totalWinesCalculatedOnPageString.lastIndexOf(":") + 2);
        totalWinesCalculatedOnPageString = totalWinesCalculatedOnPageString.substring(0,totalWinesCalculatedOnPageString.indexOf(" "));

        int totalWinesCalculatedOnPage;
        return totalWinesCalculatedOnPage = Integer.parseInt(totalWinesCalculatedOnPageString);
    }

    public int getWinesVolumeFromPage(){

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(totalWinesVolumeCalculated)));
        WebElement totalVolume = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(totalWinesVolumeCalculated)));
        String totalVolumeCalculatedOnPageString = totalVolume.getText();

        totalVolumeCalculatedOnPageString = totalVolumeCalculatedOnPageString.substring(totalVolumeCalculatedOnPageString.lastIndexOf(":") + 2);
        totalVolumeCalculatedOnPageString = totalVolumeCalculatedOnPageString.substring(0,totalVolumeCalculatedOnPageString.indexOf(" "));

        int totalVolumeCalculatedOnPage;
        return totalVolumeCalculatedOnPage = Integer.parseInt(totalVolumeCalculatedOnPageString);
    }

    public int calculateWinesSumVolume(){

        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));

        int sumVolumeFromTableRow = 0;

        for (WebElement row : list) {
            String volumeFromTableRow = row.findElements(By.tagName("td")).get(2).getText();
            sumVolumeFromTableRow = sumVolumeFromTableRow + Integer.parseInt(volumeFromTableRow.substring(0,volumeFromTableRow.indexOf(" ")));
        }
        return sumVolumeFromTableRow;
    }

    public boolean isTheMustMissing(String GRAPE_NAME){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> rowsMust = driver.findElements(By.cssSelector(wineRows));

        int i = 1;
        boolean isMissing = true;

        for (WebElement row : rowsMust) {
            if (row.findElements(By.tagName("td")).get(1).getText().equals(GRAPE_NAME)) {
                isMissing = false;
                break;
            }
        }
        return isMissing;
    }

    public boolean isTheGrapeMissing(String GRAPE_NAME){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> rowsMust = driver.findElements(By.cssSelector(wineRows));

        int i = 1;
        boolean isMissing = true;

        for (WebElement row : rowsMust) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)) {
                isMissing = false;
                break;
            }
        }
        return isMissing;
    }

}
