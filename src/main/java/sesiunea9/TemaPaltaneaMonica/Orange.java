package sesiunea9.TemaPaltaneaMonica;
// An Orange class that extends Plant and Fructiferous  with the fields: age, height

public class Orange extends Plant implements Fructiferous, Eatable{
    private int age;
    private int height;

    public Orange(int oxygenProduction) {
        super(oxygenProduction);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String getTimeToHarvest() {
        return "August";
    }

    @Override
    public boolean isEatable() {
        return true;
    }
}
