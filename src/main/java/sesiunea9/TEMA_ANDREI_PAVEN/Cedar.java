package sesiunea9.TEMA_ANDREI_PAVEN;

// Ex. 5.

public class Cedar extends Pinidae implements Eatable {

    public int numberOfSpecies = 10;

    // Cedar constructor method matching super
    public Cedar(int oxygenProduction) {
        super(oxygenProduction);
    }

    public String getGrowthEnvironment() {
        return "mountain and Mediterranean";
    }

    public boolean isEatable() {
        return false;
    }
}