package sesiunea12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Tema12WineyardPO {

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
    public String mustPage = "a:nth-child(3)";
    public String mustVolume = "td:nth-child(3)";
    public String mustCheckbox = "input[type=checkbox]";
    public String winePage = "a:nth-child(4)";


    public Tema12WineyardPO(WebDriver driver) {
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

    public int totalRows() {
        int totalRows = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"root\"]/div/section/ul/li[2]")).getText().split("")[3]);
        return totalRows;
            }

            public int typesOfGrapes() {
                int typesOfGrapes = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"root\"]/div/section/ul/li[1]")).getText().split("")[4]);
                return typesOfGrapes;
            }
            public int countTypeGrapes() {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wineRows)));
                List<WebElement> list = driver.findElements(By.cssSelector(wineRows));
                int s = 0;
                int countTypeGrapes = 0;
                for (int i=0; i< list.size(); i++) {
                    for (int j=i+1; j< list.size(); j++) {
                        if (driver.findElements(By.tagName("td")).get(i).getText().equals(driver.findElements(By.tagName("td")).get(j).getText()));
                        s ++;}
                    }
                countTypeGrapes = list.size()-s;
                return (countTypeGrapes);
                }

    public void addNewGrape(String GRAPE_NAME) {
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
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ripeness))).sendKeys("97");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(submitBtn))).click();
    }

    public void Crush(String GRAPE_NAME){

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(tableRows));

        for(WebElement row : list) {
            if(row.findElements(By.tagName("td")).get(0).getText().equals(GRAPE_NAME)){
                row.findElement(By.tagName("button")).click();
                break;
            }
        }

    }

    public void mustPage () {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mustPage))).click();
    }

    public int countMustRows() {
        mustPage();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(mustRows));
        return list.size();
    }

    public int MustCount() {
        mustPage();
        int MustCountIndex = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"root\"]/div/section/ul/li[1]")).getText().split("")[4]);
        return MustCountIndex;
    }

    public int MustVolumeIndex() {
        mustPage();
        int MustVolumeIndex = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"root\"]/div/section/ul/li[2]/text()[2]")).getText().split("")[5]);
        return MustVolumeIndex;
    }

    public int MustVolumeSum() {
        mustPage();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> volumeMust = driver.findElements(By.cssSelector(tableRows));

        int TotalMustVolume = 0;
        for (WebElement row : volumeMust) {
            TotalMustVolume += Integer.parseInt(row.findElement(By.cssSelector(mustVolume)).getText().replace(" liters",""));
                break;
            }
        return TotalMustVolume;
    }

    public void FermentMust(String GRAPE_NAME){

        mustPage();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> list = driver.findElements(By.cssSelector(tableRows));

        for(WebElement row : list) {
            if (row.findElements(By.tagName("td")).get(1).getText().equals(GRAPE_NAME)) {
                row.findElement(By.cssSelector(mustCheckbox)).click();
                break;
            }
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(fermentBtn))).click();

    }

    public int FermetedMustWinesIndex() {
        //go to wines page
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(winePage))).click();

        int FermetedMustWinesIndex = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"root\"]/div/section/ul/li[1]/text()[2]")).getText().split("")[3]);
        return FermetedMustWinesIndex;
    }

    public int FermetedMustWinesCount() {
        //go to wines page
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(winePage))).click();

        int FermetedMustWinesCount =0;

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tableRows)));
        List<WebElement> wineList = driver.findElements(By.cssSelector(tableRows));
        FermetedMustWinesCount = wineList.size();
        return FermetedMustWinesCount;
        }


}

