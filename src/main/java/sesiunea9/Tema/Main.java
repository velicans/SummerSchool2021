package sesiunea9.Tema;

public class Main {

    public static void main(String[] args){

        //G
        Orange orangeTree = new Orange(50);
        orangeTree.setAge(10);
        orangeTree.setHeight(5);
        orangeTree.setHarvestTime("August");

        //H
        Pinidae cedarTree = new Cedar(200);
        System.out.println(cedarTree.numberOfSpecies);
        System.out.println(cedarTree.getGrowthEnvironment());

        //I
        Rose rose = new Rose(10);

    }

}
