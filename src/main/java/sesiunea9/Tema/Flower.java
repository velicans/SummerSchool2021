package sesiunea9.Tema;

//I
public abstract class Flower extends Plant{

    public Flower(int oxygenProduction) {
        super(oxygenProduction);
    }

    private String color = "nedefinit";

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public abstract boolean hasThorns();

}
