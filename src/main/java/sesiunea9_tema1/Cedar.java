package sesiunea9_tema1;

public class Cedar extends Pinidae implements Eatable {
    public boolean isItEatable;
    int numberOfSpecies = 10;

    public String getGrowthEnvironment() {
        return "mountain and Mediterranean";
    }

    public String IsEatable() {
        if (isItEatable == true) {
            return "is eatable";
        }
        else{
            return "is not eatable";
        }
    }
}
