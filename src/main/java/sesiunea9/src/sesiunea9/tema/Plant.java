package sesiunea9.tema;

public abstract class Plant {
    protected Boolean eatable;
    private int oxygenProduction;
    protected String timeToHarvest;

    public int getOxygenProduction()
    {
        return oxygenProduction;
    }

    public void setOxygenProduction(int oxygenProduction)
    {
        this.oxygenProduction = oxygenProduction;
    }

    Plant(int oxygenProduction)
    {
        this.oxygenProduction = oxygenProduction;
    }

    public void setTimeToHarvest(String timeToHarvest)
    {
        this.timeToHarvest = timeToHarvest;
    }
}
