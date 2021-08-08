package sesiunea9.TemaSimonaMakkos;

public class Cedar extends Pinidae implements Eatable {

    @Override
    public int getNumberOfSpecies() {
        numberOfSpecies = 10;
        return numberOfSpecies;
    }

    @Override
    public String getGrowthEnvironment() {
        return "Growth envinroment of Cedar: mountain and Mediterranean";
    }

    @Override
    public boolean isEatable() {
        return false;
    }
}
