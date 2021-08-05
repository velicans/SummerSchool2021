package Soare_Anda_sesiunea9tema;

//A
public abstract class Plant {
    int oxygenProduction;

    public Plant ( int oxygenProduction )
    {
        this.oxygenProduction = oxygenProduction;
    }

    public Plant ( )
    {

    }
}

//B

interface Fructiferous {
    String color = "    ";


    String getTimeToHarvest ( );

}

//C

abstract class Orange extends Plant implements Fructiferous, Eatable {

    String timeToHarvest;
    int age;
    int height;

    public Orange ( int age, int height, int oxygenProduciton ,String timeToHarvest)
    {
        super (oxygenProduciton);

        this.age = age;
        this.height = height;
        this.timeToHarvest = timeToHarvest;

    }

    @Override
    public String getTimeToHarvest ( )
    {
        return timeToHarvest;
    }
}

//D

class Pinidae extends Plant {
    public Pinidae ( int oxygenProduction )
    {
        super (oxygenProduction);
    }

    public int numberOfSpecies;

    {
        numberOfSpecies = 50;
    }

    public Pinidae ( )
    {

    }

    String getGrowthEnvironment ( ) {
        String mountain = "mountain";
        return mountain;
    }

}

//E

class Cedar extends Pinidae implements Eatable
{
    boolean isEatable; //isEatable

    public Cedar ( int oxygenProduction, boolean isEatable )
    {
        super ();
        this.isEatable = isEatable;
        this.numberOfSpecies = 10;
    }

    public String getGrowthEnvironment ( ) {
        String s = "mountain and Mediterranean";
        return s;
    }

    @Override
    public boolean isEatable ( ) {
        return isEatable;
    }

}


//F

interface Eatable {
    boolean isEatable ( );

}

class Rose extends Plant implements Eatable {
    public Rose ( int oxygenProduction ) {
        super (oxygenProduction);
    }

    public Rose ( ) {

    }

    @Override
    public boolean isEatable ( ) {
        return false;
    }

}


