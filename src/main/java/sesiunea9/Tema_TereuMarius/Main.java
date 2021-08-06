package sesiunea9.Tema_TereuMarius;

public class Main {
    public static void main(String[] args) {
        Orange orangeTree = new Orange(50, 10, 5, "August", true);

        Pinidae cedarTree = new Cedar(200, false);

        System.out.println("Number of species is " + cedarTree.numberOfSpecies
                + " and the growth environment is " + cedarTree.getGrowthEnvironment());

        Rose trandafir = new Rose(20, true, "for jam");

        System.out.println("Type of rose is " + trandafir.getRoseType() + " and it is " +
                trandafir.isEatable() + " that rose is good to eat");


    }
}
