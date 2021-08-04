package sesiunea9.Tema;

//D
public class Pinidae extends Plant{

    public Pinidae(int oxygenProduction) {
        super(oxygenProduction);
    }

    public int numberOfSpecies;

    {
        numberOfSpecies = 50;
    }


    public String getGrowthEnvironment(){
        return "Mountain";
    }

}
