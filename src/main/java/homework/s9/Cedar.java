package homework.s9;

public class Cedar extends Pinidae implements Eatable {


    public Cedar(int oxygenProduction) {
        super(oxygenProduction);
        this.numberOfSpecies = 10;
    }

    @Override
    public String getGrowthEnvironment() {
        return "mountain and mediteranean";
    }

    @Override
    public boolean isEatable() {
        return false;
    }
}
