package tema;

class Pinidae extends Plant {


    public int numberOfSpecies = 50;

    public String getGrowthEnvironment() {

        return "mountain";

    }

    public Pinidae(int oxygenProduction) {
        super(oxygenProduction);
    }

}
