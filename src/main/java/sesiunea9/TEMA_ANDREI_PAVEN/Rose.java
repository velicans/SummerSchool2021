package sesiunea9.TEMA_ANDREI_PAVEN;

// Ex. 9.

public class Rose extends Plant implements Eatable {

    public String color;
    public String origin;

    public Rose(int oxygenProduction, String color, String origin) {
        super(oxygenProduction);
        this.color = color;
        this.origin = origin;
    }

    public String getGrowthEnvironment() {
        return "neutral soil";
    }

    public boolean isEatable() {
        return false;
    }
}
