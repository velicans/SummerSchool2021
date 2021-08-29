package sesiunea9;

import javax.sound.midi.Soundbank;

//a) A Plant abstarct class with the field: oxygenProduction (mandatory for an object)
public abstract class Plant {
    int oxygenProduction;

    public Plant(int oxygenProduction) {
        this.oxygenProduction = oxygenProduction;
    }
}

//b) A Fructiferous interface with the field:color and a method getTimeToHarvest
interface Fructiferous {
    String color = "";

    String getTimeToHarvest();
}

//f) An Eatable interface that will be extended by both Orange and Cedar class
//with a method isEatable
interface Eatable {
    boolean isEatable();
}

//c) An Orange class that extends Plant and Fructiferous with the fields:age, height
class Orange extends Plant implements Fructiferous, Eatable {
    String getTimeToHarvest;
    int age;
    int height;

    public Orange(int age, int height, int oxygenProduction, String getTimeToHarvest, boolean isEatable)
        super(oxygenProduction);
    this.age=age;
    this.height =height;
    this.getTimeToHarvest =getTimeToHarvest;
}
@Override
public String getGetTimeToHarvest(){
    return getTimeToHarvest;
}
@Override
public boolean isEatablle() {
    return isEatable();
}

//d) A Pinidae class that extends Plant with a public field numberOfSpecies equal to 50,
//with the method getGrowthEnvironment that returns mountain
class Pinidae extends Plant {
    public Pinidae (int oxygenProduction){
    super(oxygenProduction);
}
public int numberOfSpecies;
    public int getNumberOfSpecies(){
    return numberOfSpecies =50;}
    String getGrowthEnvironment(){
        String mountain = "mountain";
    return mountain;
    }
}

//e) A cedar class that extends Pinidae with an overridden field
//numberOfSpecies equal to 10 and a method getGrowthEnvironment
//that return mountain and Mediterranean
class Cedar extends Pinidae implements Eatable{
    boolean isEatable;
    @Override
    public int getNumberOfSpecies (){
        return numberOfSpecies=10;
    }
    @Override
    public String getGrowthEnvironment(){
        return "mountain and Mediterranean";
    }
    @Override
    public boolean isEatable(){
        return false;
    }
    public Cedar (int oxygenProduction, boolean isEatable){
        super (oxygenProduction);
    }
}

//g) Create an object: an Orange tree,aged 10, of heght 5, that is eatable,
//the time to harvest is August and an oxygen production of 50
class OrangeTree {
    public static void main (String[] args){
        Orange OrangeTree = new Orange (10,5,50,"August", true)
    }
}

//h) Create an object: A Cedar tree having a reference type of Pinidae,
//with an oxygen production of 200, not eatable.
//Print the field name of species and the result of the getGrowthEnvironment method.
class cedarTree{
    public static void main (String[] args){
        Pinidae cedarTree = new Cedar (200, false);
        System.out.println (cedarTree.numberOfSpecies);
        System.out.println (cedarTree.getGrowthEnvironment());
    }
}

//i) Implement a way to create a Rose object
class Rose {
    public static void main (String[] args){
        OrangeTree Rose =new OrangeTree ();
    }
}
