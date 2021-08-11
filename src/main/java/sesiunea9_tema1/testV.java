package sesiunea9_tema1;

import org.junit.jupiter.api.Test;

import javax.sql.rowset.RowSetFactory;
import java.util.ArrayList;
import java.util.HashMap;


public class testV {

    @Test
    public void testg() {
        Orange Tree1 = new Orange();
        Tree1.setAge(10);
        Tree1.setHeight(5);
        Tree1.isItEatable = true;
        Tree1.timeToHarvest = "August";
        Tree1.setOxygenProduction(50);

        System.out.println("EX g)");
        //Create an object: An orange tree, aged 10, of height 5, that is eatable, the time to harvest is August and an oxygen production of 50
        System.out.println("An "+Fructiferous.color+" tree,aged "+Tree1.getAge()+", of height "+Tree1.getHeight()+" that " + Tree1.IsEatable()+", the time to harvest is "+Tree1.getTimeToHarvest()+" and oxygen production of "+Tree1.getOxygenProduction());


        //Create an object: A Cedar tree having a reference type of Pinidae, with an oxygen production of 200, not eatable. Print the field number of species and the result of the getGrowthEnvironment method.
        Pinidae Tree2 = new Cedar();

        Tree2.setOxygenProduction(200);
        ((Cedar) Tree2).isItEatable = false;

        System.out.println("EX h)");
        System.out.println("A Cedar tree, with an oxygen production of "+Tree2.getOxygenProduction()+",that "+ ((Cedar) Tree2).IsEatable());
        System.out.println("Number of species ="+Tree2.numberOfSpecies);
        System.out.println("GrowthEnvironment ="+Tree2.getGrowthEnvironment());

        System.out.println("EX i)");
        Rose Rose = new Rose();
        Rose.setOxygenProduction(70);
        Rose.setAge(5);
        Rose.setHeight(30);
        Rose.setRoseType("Imperial");
        Rose.setRoseColor("White");
        Rose.setRosePrice(7);
        System.out.println("Rose with:");
        System.out.println("oxygen production ="+Rose.getOxygenProduction());
        System.out.println("Age ="+Rose.getAge()+" and height ="+Rose.getHeight());
        System.out.println("Type ="+Rose.getRoseType()+" on color "+Rose.getRoseColor());
        System.out.println("Price = "+Rose.getRosePrice()+ "$");


    }

}
