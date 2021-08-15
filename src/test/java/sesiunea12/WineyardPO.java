package sesiunea12;

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
    public String typeOfGrapesNr = "//body/div[@id='root']/div[1]/section[1]/ul[1]/li[1]";
    public String totalRows = "//body/div[@id='root']/div[1]/section[1]/ul[1]/li[2]";
    public String mustCount = "//body/div[@id='root']/div[1]/section[1]/ul[1]/li[1]";
    public String mustTotalVolume="//body[1]/div[1]/div[1]/section[1]/ul[1]/li[2]";
    public String winesValue="//body/div[@id='root']/div[1]/section[1]/ul[1]/li[1]";
    public String winesVolume="//body[1]/div[1]/div[1]/section[1]/ul[1]/li[2]";
    public static final String GRAPE_NAME = "Grapy10";

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

    public int countWines() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(wineRows));
        return list.size();
    }

    public void addNewGrape() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(addGrapesBtn))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))).sendKeys(GRAPE_NAME);;

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
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).sendKeys("97");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(submitBtn))).click();
    }

    public void pickUpAndCrush() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> rows = driver.findElements(By.cssSelector(tableRows));
        for (WebElement row : rows) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)) {
                row.findElement(By.tagName("button")).click();
                wait.until(ExpectedConditions.urlContains("/must"));
                break;
            }
        }


    }

    public void selectAndFerment(){

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> rows = driver.findElements(By.cssSelector(tableRows));
        System.out.println("Number of rows: " + rows.size());
        for(WebElement row : rows) {
            System.out.println("Grape name: " + row.findElements(By.tagName("td")).get(1).getText());
            if(row.findElements(By.tagName("td")).get(1).getText().equals(GRAPE_NAME)){
                WebElement checkbox = row.findElement(By.tagName("input"));
                System.out.println("Is selected:" + checkbox.isSelected());
                if(!checkbox.isSelected())
                {
                    checkbox.click();
                    System.out.println("Checkbox clicked");
                }
                break;
            }
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(fermentBtn))).click();
        System.out.println("Ferment clicked");
        wait.until(ExpectedConditions.urlContains("/wines"));
    }

    public int getSumRows()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> rows = driver.findElements(By.cssSelector(tableRows));
        int sum = 0;
        for (WebElement row : rows) {
            WebElement rowQuantity= row.findElements(By.tagName("td")).get(1);
            sum = sum + Integer.parseInt(rowQuantity.getText().replaceAll("[\\D]", ""));
        }
        return sum;
    }

    public int getTotalRows()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(totalRows)));
        String typeOfGrapesValue= driver.findElement(By.xpath(totalRows)).getText();
        return Integer.parseInt(typeOfGrapesValue.replaceAll("[\\D]", ""));
    }

    public int getNumberOfTypes()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(typeOfGrapesNr)));
        String typeOfGrapesValue=driver.findElement(By.xpath(typeOfGrapesNr)).getText();
        return Integer.parseInt(typeOfGrapesValue.replaceAll("[\\D]", ""));
    }

    public int getMustCount(){

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        String mustCountValue=driver.findElement(By.xpath(mustCount)).getText();
        return Integer.parseInt(mustCountValue.replaceAll("[\\D]", ""));

    }
    public int getMustVolume(){

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        String mustTotalVolumeValue=driver.findElement(By.xpath(mustTotalVolume)).getText();
        return Integer.parseInt(mustTotalVolumeValue.replaceAll("[\\D]", ""));

    }

    public int getSumMustRows()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> rows = driver.findElements(By.cssSelector(tableRows));
        int sum = 0;
        for (WebElement row : rows) {
            WebElement rowQuantity= row.findElements(By.tagName("td")).get(2);
            sum = sum + Integer.parseInt(rowQuantity.getText().replaceAll("[\\D]", ""));
        }
        return sum;
    }
    public int getWinesNr(){

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        String winesNumber=driver.findElement(By.xpath(winesValue)).getText();
        return Integer.parseInt(winesNumber.replaceAll("[\\D]", ""));

    }

    public int getWinesVolumeNr(){

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        String winesVolumeNumber=driver.findElement(By.xpath(winesVolume)).getText();
        return Integer.parseInt(winesVolumeNumber.replaceAll("[\\D]", ""));

    }

    public boolean mustIsmissing(){
        boolean isMustMissing = true;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> rows = driver.findElements(By.cssSelector(tableRows));
        for (WebElement row : rows) {
            if (row.findElements(By.tagName("td")).get(1).getText().equals(GRAPE_NAME)) {
                isMustMissing=false;
                break;
            }
        }
    return isMustMissing;

    }

    public boolean grapeIsMissing(){
        boolean isGrapeMissing = true;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> rows = driver.findElements(By.cssSelector(tableRows));
        for (WebElement row : rows) {
            if (row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)) {
                isGrapeMissing=false;
                break;
            }
        }
        return isGrapeMissing;

    }

    public void goToMust()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Must')]"))).click();
        wait.until(ExpectedConditions.urlContains("/must"));
    }

    public void goToWines()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Wines')]"))).click();
        wait.until(ExpectedConditions.urlContains("/wines"));
    }

    public void goToGrapes()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Grapes')]"))).click();
        wait.until(ExpectedConditions.urlContains("/grapes"));
    }



 }
