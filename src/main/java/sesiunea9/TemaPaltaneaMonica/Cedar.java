package sesiunea9.TemaPaltaneaMonica;
//A Cedar class that extends Pinidae with an overridden field numberOfSpecies equal to 10 and a method getGrowthEnvironment that return mountain and Mediterranean
public class Cedar extends Pinidae implements Eatable{

    public Cedar(int oxygenProduction) {
        super(oxygenProduction);
        // there is no way to override a class variable.
        this.numberOfSpecies = 10;
    }

    @Override
    public String getGrowthEnvironment () {
        return "mountain and Mediterranean";
    }

    @Override
    public boolean isEatable() {
        return false;
    }
}
