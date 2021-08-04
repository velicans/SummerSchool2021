package sesiunea9.Tema;

public class Main {

    public static void main(String[] args ){

        Orange tree = new Orange(50);
        tree.setAge(10);
        tree.setHeight(5);
        tree.setHarvestTime("August");

        Pinidae cedarTree = new Cedar(200);
        System.out.println(cedarTree.numberOfSpecies);
        System.out.println(cedarTree.getGrowthEnvironment());

        Rose rose = new Rose(10);
    }



}
