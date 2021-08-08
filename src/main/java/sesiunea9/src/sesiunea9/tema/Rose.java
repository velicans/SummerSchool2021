package sesiunea9.tema;

public class Rose extends Plant implements Eatable
{
    Rose(int oxygenProduction)
    {
        super(oxygenProduction);
    }

    @Override
    public void isEatable(Boolean isEatable)
    {
        this.eatable = isEatable;
    }
}
