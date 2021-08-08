package sesiunea9.tema;

public class Rose extends Plant implements Eatable {

    public int numberOfSpecies = 10;
    private String color;
    boolean eatable;

    public Rose() {
    }

    public Rose(int numberOfSpecies, String color, boolean eatable) {
        this.numberOfSpecies = numberOfSpecies;
        this.color = color;
        this.eatable = eatable;
    }

    public int getNumberOfSpecies() {
        return numberOfSpecies;
    }

    public Rose setNumberOfSpecies(int numberOfSpecies) {
        this.numberOfSpecies = numberOfSpecies;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Rose setColor(String color) {
        this.color = color;
        return this;
    }

    public Rose setEatable(boolean eatable) {
        this.eatable = eatable;
        return this;
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
