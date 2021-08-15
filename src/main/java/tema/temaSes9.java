package tema;


class Main {

    public static void main(String[] args) {
        Orange tree = new Orange(10, 5, "August", 50);
        Pinidae cedarTree = new Cedar(200, false);
        System.out.println(cedarTree.numberOfSpecies);
        System.out.println(cedarTree.getGrowthEnvironment());
        Rose rose = new Rose(8, "red", "3 months ", 30);

    }
}