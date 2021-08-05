package homework.s9;


public class Orange extends Plant implements Fructiferous, Eatable {
    public int age;
    public int height;


    public Orange(int oxygenProduction, int age, int height) {
        super(oxygenProduction);
        this.age = age;
        this.height = height;
    }

    @Override
    public boolean isEatable() {
        return true;
    }

    @Override
    public String toString() {
        return "This Orange has " +
                "the age of " + age +
                ", height of " + height +
                " and oxygenProduction " + oxygenProduction;

    }

    @Override
    public String getTimeToHarvest() {
        return "August";


    }
}
