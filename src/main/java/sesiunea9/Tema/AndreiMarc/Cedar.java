package sesiunea9.Tema.AndreiMarc;

public class Cedar extends Pinidae implements IEatable {
    public int numberOfSpecies = 10;

    public Cedar(int oxygenProduction) {
        super(oxygenProduction);

    }

    public String getGrowthEnvironment() {
        return "mountain and Mediterranean";

    }

    @Override
    public boolean isEatable() {
        return false;
    }
}
