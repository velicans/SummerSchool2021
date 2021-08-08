package sesiunea9.TEMA_ANDREI_PAVEN;

// Ex. 4.

public class Pinidae extends Plant {

    public int numberOfSpecies = 50;

    // Pinidae constructor method matching super
    public Pinidae(int oxygenProduction) {
        super(oxygenProduction);
    }

    public String getGrowthEnvironment() {
        return "Mountain";

    }
}
