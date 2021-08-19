package sesiunea9.TemaPaltaneaMonica;

import org.junit.jupiter.api.Test;


//Create an object: An orange tree, aged 10, of height 5, that is eatable, the time to harvest is August and an oxygen production of 50

public class TestClass {

    @Test
    public void testOrange() {
        Orange tree = new Orange(50);
        tree.setAge(10);
        tree.setHeight(5);
        System.out.println("Orange tree:");
        System.out.println(tree.isEatable());
        System.out.println(tree.getTimeToHarvest());
    }

    @Test
    public void testCedar() {
        Cedar tree = new Cedar(200);
        System.out.println("Cedar tree:");
        System.out.println(tree.isEatable());
        System.out.println(tree.numberOfSpecies);
    }

    @Test
    public void testRose() {
        Rose rose = new Rose(100);
        System.out.println("Rose flower:");
        System.out.println(rose.isEatable());
        System.out.println(rose.color);

    }
}
