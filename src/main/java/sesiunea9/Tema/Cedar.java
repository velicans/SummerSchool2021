package sesiunea9.Tema;

public class Cedar extends Pinidae implements Eatable{


    public Cedar(int oxygenProduction) {
        super(oxygenProduction);
    }


    {
        numberOfSpecies = 10;
    }

    @Override
    public String getGrowthEnvironment() {
        return "Mountain and Mediterranean ";


    }

    @Override
    public boolean isEatable() {
        return false;
    }
}
