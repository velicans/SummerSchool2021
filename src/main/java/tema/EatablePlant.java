package tema;

class EatablePlant extends Plant implements Eatable {

    boolean eatable;

    public EatablePlant(boolean eatable, int oxygenProduction) {
        super(oxygenProduction);
        this.eatable = eatable;

    }

    @Override
    public boolean isEatable() {
        return true;

    }

}
