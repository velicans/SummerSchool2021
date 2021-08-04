package Tema;

public class Orange extends Plant implements Fructiferous, Eatable {
    protected int age, height;
    String timeToHarvest;
    boolean canBeEaten;
    int oxygenProduction;
    public String color;

    // default constructor ======================================
    public Orange(){
        this.age = 0;
        this.height = 0;
        this.timeToHarvest = "unkown";
        this.canBeEaten = true;
        this.oxygenProduction = 0;
        this.color = "Orange";
    }

    // specific/set constructor =======================================
    public Orange(int age, int height, String timeToHarvest, boolean canBeEaten, int oxygenProduction){
        this.age = age;
        this.height = height;
        this.timeToHarvest = timeToHarvest;
        this.canBeEaten = canBeEaten;
        this.oxygenProduction = oxygenProduction;
        this.color = "Orange";
    }

    // getters and setters ===============================
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // overriden methods ===============================
    @Override
    public void isEatable() {
        if (canBeEaten) {
            System.out.println("Is eatable.");
        }
        else
        {
            System.out.println("It is not eatable.");
        }
    }

    @Override
    public String getTimeToHarvest() {
        return timeToHarvest;
    }

    // other getters and setters =====================================
    public int getOxygenProduction() {
        return oxygenProduction;
    }

    public void setCanBeEaten(boolean canBeEaten) {
        this.canBeEaten = canBeEaten;
    }

    public void setOxygenProduction(int oxygenProduction) {
        this.oxygenProduction = oxygenProduction;
    }

    public void setTimeToHarvest(String timeToHarvest) {
        this.timeToHarvest = timeToHarvest;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
