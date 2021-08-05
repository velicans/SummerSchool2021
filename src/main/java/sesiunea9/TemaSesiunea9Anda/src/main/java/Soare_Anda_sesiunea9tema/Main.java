package Soare_Anda_sesiunea9tema;

//G
public class Main {
    public static void main ( String[] args ) {
        Orange orangeTree = new Orange (10, 5, 50, "August") {
            @Override
            public boolean isEatable ( ) {
                return false;
            }
        };


        Pinidae cedarTree = new Cedar (200, false);
        System.out.println (cedarTree.numberOfSpecies);
        System.out.println (cedarTree.getGrowthEnvironment ());

        //I
        Rose rose = new Rose ();
    }
}
