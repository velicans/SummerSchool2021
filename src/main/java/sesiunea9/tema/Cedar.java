package sesiunea9.tema;


public class Cedar extends Pinidae implements Eatable{

    private final Pinidae pinidae = new Pinidae();
    public int numberOfSpecies = 10;

    public String getGrowthEnvironment(){
        return "Mediterranean";
    }

    @Override
    public boolean isEatable() {
        return false;
    }

    @Override
    public String toString() {
        return "Cedar{" + "\n" +
                "   numberOfSpecies=" + numberOfSpecies + ",\n" +
                "   environment=" + getGrowthEnvironment() + "\n" +
                '}';
    }
}
