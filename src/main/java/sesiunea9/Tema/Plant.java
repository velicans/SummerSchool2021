package sesiunea9.Tema;

//A
public abstract class Plant {

    private int oxygenProduction;

    public Plant(int oxygenProduction){

        this.oxygenProduction = oxygenProduction;

    }

    public int getOxygenProduction() {
        return oxygenProduction;
    }

    public void setOxygenProduction(int oxygenProduction) {
        this.oxygenProduction = oxygenProduction;
    }

}
