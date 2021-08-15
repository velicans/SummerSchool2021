package tema;

class Cedar extends Pinidae implements Eatable{
    boolean isEatable;


    public String getGrowthEnvironment() {

        return "mountain and Mediterranean";

    }


    @Override
    public boolean isEatable() {
        return isEatable;

    }

    public Cedar(int oxygenProduction, boolean isEatable) {
        super(oxygenProduction);
        this.isEatable = isEatable;
        this.numberOfSpecies = 10;
    }

}