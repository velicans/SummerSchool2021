package sesiunea9.Tema;

public class Orange extends Plant implements Fructiferous, Eatable{


    public Orange(int oxygenProduction) {
        super(oxygenProduction);
    }

    @Override
    public String getTimeToHarvest() {
        return harvestTime;
    }

    public String getHarvestTime() {
        return harvestTime;
    }

    public void setHarvestTime(String harvestTime) {
        this.harvestTime = harvestTime;
    }

    private String harvestTime;
    private int age;
    private int height;

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
    public boolean isEatable() {
        return true;
    }
}
