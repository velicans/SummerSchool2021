package sesiunea9.tema;

public class Test {

    @org.junit.Test
    public void Test() {

        /* 1.Create an object: An orange tree, aged 10, of height 5, that is eatable,
         the time to harvest is August and an oxygen production of 50*/
        Orange orangeTree = new Orange(10, 5, true);
        orangeTree.setOxygenProduction(50);
        orangeTree.isEatable();
        orangeTree.getTimeToHarvest();

        /*2.Create an object: A Cedar tree having a reference type of Pinidae, with an oxygen production of 200,
        not eatable. Print the field number of species and the result of the getGrowthEnvironment method. */

        Cedar cedarTree = new Cedar(200, false);
        cedarTree.getNumberOfSpecies();
        System.out.println("The growth environment for the cedar tree is : " + cedarTree.getGrowthEnvironment());

        /*3.Implement a way to create a Rose object*/
        Rose rose1 = new Rose(5, "Yellow", false);
        Rose rose2 = new Rose();
        rose2.numberOfSpecies = 10;
        rose2.setColor("Red");
        rose2.setEatable(true);
        rose2.isEatable();

    }

}
