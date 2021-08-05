package homework.s9;


public class Rose extends Plant {
    private String color;

    public Rose(int oxygenProduction, String color) {
        super(oxygenProduction);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Rose{" +
                "oxygenProduction=" + oxygenProduction +
                ", color='" + color + '\'' +
                '}';
    }
}
