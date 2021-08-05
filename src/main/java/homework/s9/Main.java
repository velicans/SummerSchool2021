package homework.s9;


public class Main {
    public static void main(String[] args) {

        Orange orange = new Orange(50, 10, 5);
        System.out.println(orange);
        System.out.println("Is this orange eatable? " + orange.isEatable());

        Pinidae cedar = new Cedar(200);
        System.out.println("The number of species is " + cedar.getNumberOfSpecies());
        System.out.println("Cedar grows in " + cedar.getGrowthEnvironment());
        /* Nu pot afisa rezultatul metodei isEatable() pentru cedar de tip Pinidae,
         deoarece clasa Pinidae nu implementeaza interfata Eatable
         System.out.println("Is this cedar eatable? " + cedar.isEatable());  */

        Cedar cedar1 = new Cedar(200);
        System.out.println("Is this cedar1 eatable? " + cedar1.isEatable());

        Rose rose = new Rose(10, "red");
        System.out.println(rose);
    }
}
