package sesiunea9.TemaPaltaneaMonica;

public class Rose extends Plant implements Eatable, Flower{


    public Rose(int oxygenProduction) {
        super(oxygenProduction);
    }

    @Override
    public boolean isEatable() {
        return false;
    }

    public String color = "red";
}
