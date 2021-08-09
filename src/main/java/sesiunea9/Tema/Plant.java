package sesiunea9.Tema;

public abstract class Plant {

    public Plant(int oxygenProduction) {
        this.oxygenProduction = oxygenProduction;
    }

    public int oxygenProduction;

    public int getOxygenProduction() {
        return oxygenProduction;
    }

    public void setOxygenProduction(int oxygenProduction) {
        this.oxygenProduction = oxygenProduction;
    }
}
