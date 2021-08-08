package sesiunea9.tema;

public class Main {

    public static void main(String[] args)
    {
        Orange orangeTree = new Orange(50);
        orangeTree.setAge(10);
        orangeTree.setHeight(5);
        orangeTree.isEatable(true);
        orangeTree.setTimeToHarvest("August");

        Pinidae cedarTree = new Cedar(200);
        ((Cedar) cedarTree).isEatable(false);

        System.out.println(cedarTree.numberOfSpecies);
        String[] cedarTreeGrowthEnvironments = cedarTree.getGrowthEnvironment();

        for (String environment: cedarTreeGrowthEnvironments)
        {
            System.out.println(environment);
        }

        Rose rose = new Rose(10);
        rose.isEatable(false);
    }
}
