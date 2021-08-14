package sesiunea12.tema;

import org.junit.jupiter.api.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test extends WineSetup {

    WineyardPO wineyardPO = new WineyardPO(driver);


    public static String GRAPE_NAME = "AGG_WINE";
    public static String GRAPE_AGE = "3";
    public static String GRAPE_RIPENESS = "97";
    public static String GRAPE_QUANTITY = "36";
    public static String GRAPE_UNIT = "cases";
    public static String WINE_NAME = "AGG_WINE";
    public static String WINE_VOLUME = "1L";


    @org.junit.jupiter.api.Test
    @Order(1)
    public void isHomePageOpened() throws InterruptedException{

        boolean isOpened = wineyardPO.checkIfHomePageIsOpened();
        Assertions.assertTrue(isOpened, "Home page couldn't be opened.");
    }


    @org.junit.jupiter.api.Test
    @Order(2)
    public void checkIfGrapesCountInfoEqualsWithTheAmountPresentInTable() throws InterruptedException{

       int listSize = wineyardPO.countTableRows();
       int numberOfGrapes = wineyardPO.getFirstCountNumber();
       Assertions.assertTrue(listSize == numberOfGrapes, "Number of grape types NOT matching the number present in table");
    }

    @org.junit.jupiter.api.Test
    @Order(3)
    public void checkIfNumberOfGrapeRowsIsEqualWithTheSumPresentInTable() throws InterruptedException{

        int totalGrapeRows = wineyardPO.getSecondCountNumber();
        int rowsSum = wineyardPO.sumUpGrapeRows();
        Assertions.assertTrue(totalGrapeRows == rowsSum, "Number of total grape rows NOT matching the amount present in table");
    }

    @org.junit.jupiter.api.Test
    @Order(4)
    public void addGrapeFlow()  throws InterruptedException{
        int initialValue_Types = wineyardPO.getFirstCountNumber();
        int initialValue_Rows = wineyardPO.getSecondCountNumber();

        wineyardPO.addNewGrape(GRAPE_NAME,GRAPE_AGE, GRAPE_RIPENESS,GRAPE_QUANTITY,GRAPE_UNIT);

        int finalValue_Types = wineyardPO.getFirstCountNumber();
        int finalValue_Rows = wineyardPO.getSecondCountNumber();

        Assertions.assertTrue(finalValue_Types - initialValue_Types == 1, "Total grape types NOT updated.");
        Assertions.assertTrue(finalValue_Rows - initialValue_Rows == Integer.parseInt(GRAPE_QUANTITY), "Total rows number was NOT updated.");
    }



    @org.junit.jupiter.api.Test
    @Order(5)
    public void pickAndCrushAndCheckMustInformationWithTable() throws InterruptedException {
        wineyardPO.pickAndCrush(GRAPE_NAME);
    }


    @org.junit.jupiter.api.Test
    @Order(6)
    public void checkMustCountWithTable() {
        int mustCount = wineyardPO.getFirstCountNumber();
        int listSize = wineyardPO.countTableRows();
        System.out.println("Must count: " + mustCount + " Table number: " + listSize);
        Assertions.assertTrue(listSize == mustCount, "Must count NOT matching the amount present in table");
    }

    @org.junit.jupiter.api.Test
    @Order(7)
    public void checkMustVolumeWithTable(){
        int totalMustVolume = wineyardPO.getSecondCountNumber();
        int mustSum = wineyardPO.sumUpVolume();
        System.out.println("Total volume: " + totalMustVolume + " Table sum: " +mustSum);
        Assertions.assertTrue(totalMustVolume == mustSum, "Must total volume is NOT matching the amount present in table");
    }

    @org.junit.jupiter.api.Test
    @Order(8)
    public void fermentMust() throws InterruptedException{

        wineyardPO.selectMustByName(GRAPE_NAME);
        wineyardPO.clickFermentButton();
    }

    @org.junit.jupiter.api.Test
    @Order(9)
    public void cellarSetWineData() throws InterruptedException{

        wineyardPO.setWineInputFields(GRAPE_NAME,WINE_NAME, WINE_VOLUME);

    }

    @org.junit.jupiter.api.Test
    @Order(10)
    public void checkWineCountWithTable(){

        int wines = wineyardPO.getFirstCountNumber();
        int tableWines = wineyardPO.countTableRows();

        System.out.println("Wine count: " + wines + " Table wines: " + tableWines);
        Assertions.assertTrue(wines == tableWines, "Wine count is NOT matching the number present in table");
    }

    @org.junit.jupiter.api.Test
    @Order(11)
    public void checkWineVolumeWithTable(){
        int volume = wineyardPO.getSecondCountNumber();
        int tableVolume = wineyardPO.sumUpVolume();

        System.out.println("Total volume: " + volume + " Table volume: " + tableVolume);
        Assertions.assertTrue(volume == tableVolume, "Wine count is NOT matching the number present in table");
    }

    @org.junit.jupiter.api.Test
    @Order(12)
    public void checkMustPage(){
        boolean missing = wineyardPO.verifyIfMissingFromMustPage(GRAPE_NAME);
        Assertions.assertTrue(missing, "Must was not removed from Must list");
    }

    @org.junit.jupiter.api.Test
    @Order(13)
    public void checkGrapesPage(){
        boolean missing =  wineyardPO.verifyIfMissingFromGrapesPage(GRAPE_NAME);
        Assertions.assertTrue(missing, "Grape was not removed from Must list");
    }


}
