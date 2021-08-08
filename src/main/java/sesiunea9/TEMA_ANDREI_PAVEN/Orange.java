package sesiunea9.TEMA_ANDREI_PAVEN;

// Ex. 3.

public class Orange extends Plant implements Fructiferous, Eatable {

    public int age;
    public int height;

    // Orange tree constructor method matching super
    public Orange (int age, int height, int oxygenProduction){
        super(oxygenProduction);
        this.age = age;
        this.height = height;
    }

    public String getTimeToHarvest() {
        return "August";
    }

    public boolean isEatable() {
        return true;
    }
}