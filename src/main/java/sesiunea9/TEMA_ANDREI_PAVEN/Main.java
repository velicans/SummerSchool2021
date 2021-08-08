package sesiunea9.TEMA_ANDREI_PAVEN;

public class Main {

    public static void main(String[] args) {

        // creating an Orange tree object
        Orange orangeTree = new Orange (10, 5,50);
        System.out.println(orangeTree.age);
        System.out.println(orangeTree.height);
        System.out.println(orangeTree.isEatable());
        System.out.println(orangeTree.getTimeToHarvest());

        // creating an Cedar tree object
        Cedar cedarTree = new Cedar(200);
        System.out.println(cedarTree.numberOfSpecies);
        System.out.println(cedarTree.isEatable());
        System.out.println(cedarTree.getGrowthEnvironment());

        // creating a Rose object
        Rose rose = new Rose(150, "white", "Asia");
        System.out.println(rose.color);
        System.out.println(rose.origin);
        System.out.println(rose.isEatable());
        System.out.println(rose.getGrowthEnvironment());

    }

}
