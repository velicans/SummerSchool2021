package tema;

class Orange extends Plant implements Fructiferous, Eatable {

    int age;
    int height;
    String timeToHarvest;

    public Orange(int age, int height, String timeToHarvest, int oxygenProduction) {
        super(oxygenProduction);
        this.age = age;
        this.height = height;
        this.timeToHarvest = timeToHarvest;

    }

    @Override
    public boolean isEatable() {
        return false;
    }

    @Override
    public String getTimeToHarvest() {
        return timeToHarvest;
    }

}
