package sesiunea9.Tema_TereuMarius;

public class Rose extends Plant implements Eatable{

    private boolean isEatable;
    private String roseType;

    public String getRoseType() {
        return roseType;
    }

    public Rose setRoseType(String roseType) {
        this.roseType = roseType;
        return this;
    }

    public Rose(int oxigenProduction, boolean isEatable, String roseType) {

        super(oxigenProduction);
        this.isEatable=isEatable;
        this.roseType=roseType;

    }

    @Override
    public boolean isEatable() {
        return isEatable;
    }
}
