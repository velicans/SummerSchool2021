package sesiunea9.Tema;

public class Pinidae extends Plant {

    public int numberOfSpecies = 50;

    public Pinidae(int oxygenProduction) {
        super(oxygenProduction);
    }

    public String getGrowthEnvironment() {
        return "Mountain";
    }
}
