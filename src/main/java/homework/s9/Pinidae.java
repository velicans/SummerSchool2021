package homework.s9;

public class Pinidae extends Plant {
    public int numberOfSpecies;

    public Pinidae(int oxygenProduction) {
        super(oxygenProduction);
        this.numberOfSpecies = 50;
    }

    public int getNumberOfSpecies() {
        return numberOfSpecies;
    }


    public String getGrowthEnvironment() {
        return "mountain";


    }
}
