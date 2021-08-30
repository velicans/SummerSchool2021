package sesiunea9.Temasesiunea9;

class Cedar extends Pinidae implements Eatable {
    public int numberOfSpecies = 10;

    public Cedar(int oxygenProduction) {
        super (oxygenProduction);
    }

    @Override
    String getGrowthEnvironment () {
        return "mountain and Mediterranean";
    }
}