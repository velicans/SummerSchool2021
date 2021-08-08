package sesiunea9.TemaSimonaMakkos;

import org.junit.jupiter.api.Test;

public class test {

    @Test
    public void testClass (){

        Orange tree = new Orange(10,5,"August",50);
        System.out.println("age: " + tree.age + " /height: " + tree.height + " /is eatable: " + tree.isEatable() + " /time to harves: " + tree.timeToHarvest + " /oxigen production: " + tree.oxygenProduction);

        Pinidae cedarTree = new Cedar();
        cedarTree.setOxygenProduction(200);
        System.out.println("number of cedar species: " + cedarTree.getNumberOfSpecies() + "/ is eatable: " + ((Cedar) cedarTree).isEatable() + "/ growth envinroment: " + cedarTree.getGrowthEnvironment() + " /oxigen production: " + cedarTree.getOxygenProduction());

        Rose rose = new Rose("Red");
        System.out.println("the rose color is: " + rose.getColor());

    }

}
