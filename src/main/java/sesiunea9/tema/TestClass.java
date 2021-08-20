package sesiunea9.tema;

import org.junit.jupiter.api.Test;

public class TestClass {

    @Test
    public void test() {

        //TODO: Create an object: An orange tree, aged 10, of height 5, that is eatable, the time to harvest is August and an oxygen production of 50
        Orange orange = new Orange(10, 5);
        orange.setOxygenProduction(50);
        System.out.println(orange);

        //TODO: Create an object: A Cedar tree having a reference type of Pinidae, with an oxygen production of 200,
        // not eatable. Print the field number of species and the result of the getGrowthEnvironment method.
        Cedar cedar = new Cedar();
        cedar.setOxygenProduction(200);
        System.out.println(cedar);

        //TODO: Implement a way to create a Rose object
        Rose rose = new Rose();
        rose.setOxygenProduction(10);
        System.out.println(rose);

    }

}
