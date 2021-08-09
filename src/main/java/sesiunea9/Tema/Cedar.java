package sesiunea9.Tema;

public class Cedar extends Pinidae implements Eatable {

    public int numberOfSpecies = 10;

    public Cedar(int oxygenProduction) {
        super(oxygenProduction);
    }

    @Override
    public String getGrowthEnvironment() {
        return super.getGrowthEnvironment() + " and Mediterranean ";
    }

    @Override
    public boolean isEatable() {
        return false;
    }
}
