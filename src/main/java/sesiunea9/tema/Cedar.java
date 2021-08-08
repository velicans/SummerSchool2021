package sesiunea9.tema;

public class Cedar extends Piniade implements Eatable {

    private final int numberOfSpecies = 10;
    private int oxygenProduction;
    private boolean eatable;


    public Cedar(int oxygenProduction, boolean eatable) {
        this.oxygenProduction = oxygenProduction;
        this.eatable = eatable;
    }

    @Override
    public int getNumberOfSpecies() {
        System.out.println("There are  " + numberOfSpecies + " cedar species ");
        return numberOfSpecies;
    }

    @Override
    public String getGrowthEnvironment() {
        return "Mountain";
    }

    @Override
    public void isEatable() {
        if (eatable) {
            System.out.println("Is eatable!");
        } else {
            System.out.println("Is not eatable!");
        }
    }
}
