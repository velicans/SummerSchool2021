package sesiunea9.Tema.AndreiMarc;

public class Orange extends Plant implements IFructiferous, IEatable {
    private int age;
    private int height;

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


