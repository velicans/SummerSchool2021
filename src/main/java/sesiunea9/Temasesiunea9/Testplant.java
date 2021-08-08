package sesiunea9.Temasesiunea9;

public class Testplant {
        public static void main(String args[]){
                Orange orangeTree = new Orange(10, 5, 50);
                Pinidae cedarTree = new Cedar(200);

                System.out.println("Orange Time to Harvest: " + orangeTree.getTimeToHarvest());
                System.out.println("Orange Oxygen Production: " + orangeTree.oxygenProduction);
                System.out.println("Orange Color: " + orangeTree.color);
                System.out.println("Orange Is Eatable: " + orangeTree.isEatable());
                System.out.println("");

                System.out.println("Pinidae Number of Species: " + cedarTree.numberOfSpecies);
                System.out.println("Pinidae Growth Environment: " + cedarTree.getGrowthEnvironment());
                System.out.println("Pinidae Oxygen Production: " + cedarTree.oxygenProduction);
                System.out.println("Pinidae Is Eatable: " + cedarTree.isEatable());
        }
}
