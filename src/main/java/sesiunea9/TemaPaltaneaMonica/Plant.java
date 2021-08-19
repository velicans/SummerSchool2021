package sesiunea9.TemaPaltaneaMonica;

//A Plant abstract class with the field: oxygenProduction (mandatory for an object)

public abstract class Plant {

    private int oxygenProduction;

    public void oxygenProduction(int oxygenProduction) {
        this.oxygenProduction = oxygenProduction;
    }

    public Plant(int oxygenProduction)
    {
        this.oxygenProduction = oxygenProduction;
    }
}


