package sesiunea9.tema;


public class Piniade extends Plant {

    public int numberOfSpecies = 50;

    public Piniade() {
    }

    public Piniade(int numberOfSpecies) {

        this.numberOfSpecies = numberOfSpecies;
    }

    public int getNumberOfSpecies() {
        System.out.println("There are : " + numberOfSpecies + " species ");
        return numberOfSpecies;
    }

    public Piniade setNumberOfSpecies(int numberOfSpecies) {
        this.numberOfSpecies = numberOfSpecies;
        return this;
    }

    public String getGrowthEnvironment() {
        return "Mountain";
    }
}
