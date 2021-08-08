package sesiunea9.tema;

public class Cedar extends Pinidae implements Eatable
{
    Cedar(int oxygenProduction) {
        super(oxygenProduction);
        this.numberOfSpecies = 10;
    }

    public String[] getGrowthEnvironment()
    {
        return new String[]{"mountain", "Mediterranean"};
    }

    @Override
    public void isEatable(Boolean isEatable)
    {
        this.eatable = isEatable;
    }
}
