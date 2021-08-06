package sesiunea9.Tema_TereuMarius;

public class Orange extends Plant implements Fructiferous, Eatable{

    public Orange(int oxigenProduction,int age, double height, String timeToHarvest, boolean isEatable) {
        super(oxigenProduction);
        this.age=age;
        this.height=height;
        this.timeToHarvest=timeToHarvest;
        this.isEatable=isEatable;
    }
    private int age;
    private double height;
    private String timeToHarvest;
    private boolean isEatable;

    @Override
    public boolean isEatable() {
        return isEatable;
    }

    @Override
    public String getTimeToHarvest() {
        return timeToHarvest;
    }
}
