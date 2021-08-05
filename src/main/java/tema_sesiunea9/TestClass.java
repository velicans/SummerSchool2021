package tema_sesiunea9;

import org.junit.jupiter.api.Test;

public class TestClass {

    @Test

    public void test1(){

        Orange orangeTree = new Orange();
        Pinidae cedarTree = new Cedar();

        orangeTree.setAge(10);
        orangeTree.setHeight(5);
        orangeTree.itIsEatable = true;
        orangeTree.timeToHarvest = "August";
        orangeTree.oxygenProduction = 50;

        System.out.println(orangeTree.getAge());
        System.out.println(orangeTree.getHeight());
        orangeTree.isEatable();
        orangeTree.getTimeToHarvest();
        System.out.println(orangeTree.oxygenProduction);

        cedarTree.oxygenProduction = 200;
        cedarTree.itIsEatable = false;
        System.out.println(cedarTree.numberOfSpecies);
        cedarTree.getGrowthEnvironment();

        ((Cedar) cedarTree).isEatable();
        System.out.println(cedarTree.oxygenProduction);

        Rose rose = new Rose();
        rose.oxygenProduction = 40;
        rose.itIsEatable = true;
        rose.timeToHarvest = "spring or summer";
        rose.getTimeToHarvest();
        rose.setColor("red");
        System.out.println(rose.getColor());

    }


}
