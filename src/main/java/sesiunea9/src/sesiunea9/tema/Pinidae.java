package sesiunea9.tema;

public class Pinidae extends Plant
{
    Pinidae(int oxygenProduction)
    {
        super(oxygenProduction);
    }

    public int numberOfSpecies = 50;

    public String[] getGrowthEnvironment()
    {
        return new String[]{"mountain"};
    }
}
