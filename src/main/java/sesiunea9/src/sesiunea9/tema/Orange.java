package sesiunea9.tema;

import java.time.Duration;

public class Orange extends Plant implements Fructiferous, Eatable
{
    Orange(int oxygenProduction)
    {
        super(oxygenProduction);
    }

    @Override
    public String getTimeToHarvest()
    {
        return this.timeToHarvest;
    }

    private int age;

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    private int height;

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    @Override
    public void isEatable(Boolean isEatable)
    {
        this.eatable = isEatable;
    }
}
