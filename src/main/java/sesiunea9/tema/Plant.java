package sesiunea9.tema;

public abstract class Plant {
    int oxygenProduction;

    public int getOxygenProduction() {

        return oxygenProduction;
    }

    public Plant setOxygenProduction(int oxygenProduction) {
        this.oxygenProduction = oxygenProduction;
        return this;
    }
}
