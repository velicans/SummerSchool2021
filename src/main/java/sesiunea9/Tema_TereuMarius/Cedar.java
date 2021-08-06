package sesiunea9.Tema_TereuMarius;

public class Cedar extends Pinidae implements Eatable{

    private int numberOfSpecies=10;
    private boolean isEatable;

    public int getNumberOfSpecies() {
        return numberOfSpecies;
    }

    public String getGrowthEnvironment(){
        return "mountain and Mediterranean";
    }

    public Cedar(int oxigenProduction, boolean isEatable) {
        super(oxigenProduction);
        this.isEatable=isEatable;
    }

    @Override
    public boolean isEatable() {
        return isEatable;
    }
}
