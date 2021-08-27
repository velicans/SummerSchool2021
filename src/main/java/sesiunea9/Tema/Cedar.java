package Tema;

public class Cedar extends Pinidae implements Eatable {
    public static int numberOfSpecies = 10;
    private int oxygenProduction;
    boolean canBeEaten;

    // consturctors ===============================================
    public Cedar(){
        this.oxygenProduction = 0;
        this.canBeEaten = false;
    }

    public Cedar(int oxygenProduction, boolean canBeEaten){
        this.oxygenProduction = oxygenProduction;
        this.canBeEaten = canBeEaten;
    }

    // overriden methods ============================================
    public String getGrowthEnvironment(){
        return "mountain and Mediterranean";
    }

    @Override
    public void isEatable() {
        if (canBeEaten){
            System.out.println("Is eatable.");
        }
        else
        {
            System.out.println("It is not eatable.");
        }
    }

    // getters and setters =======================================
    public int getOxygenProduction() {
        return oxygenProduction;
    }

    public void setOxygenProduction(int oxygenProduction) {
        this.oxygenProduction = oxygenProduction;
    }

    public static int getNumberOfSpecies() {
        return numberOfSpecies;
    }

    public static void setNumberOfSpecies(int numberOfSpecies) {
        Cedar.numberOfSpecies = numberOfSpecies;
    }

    public void setCanBeEaten(boolean canBeEaten) {
        this.canBeEaten = canBeEaten;
    }
}
