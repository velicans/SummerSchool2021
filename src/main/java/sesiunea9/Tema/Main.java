package sesiunea9.Tema;

public class Main {

    public static void main(String[] args) {

        Orange orangeTree = new Orange(50);
        orangeTree.setAge(10);
        orangeTree.setHeight(5);
        orangeTree.setHarvestTime("August");

        Pinidae cedarTree = new Cedar(100);

        System.out.println(cedarTree.numberOfSpecies);
        System.out.println(cedarTree.getGrowthEnvironment());

        Rose roseAsClassEntity = new Rose(10);
        Plant roseAsAnonymous = new Plant(10) {
            @Override
            public String toString() {
                return "I am a rose";
            }
        };
    }


}
