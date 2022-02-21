package sesiunea9.Tema.AndreiMarc;

public class Rose extends Plant implements IEatable {
    private String color;
    private boolean isExotic;
    private String bloomingMonth;

    public Rose(int oxygenProduction, String color, boolean isExotic, String bloomingMonth) {
        super(oxygenProduction);
        this.color = color;
        this.isExotic = isExotic;
        this.bloomingMonth = bloomingMonth;
    }

    public boolean isEatable(){
        return false;
    }
}
