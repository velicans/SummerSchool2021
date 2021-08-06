package sesiunea9_tema1;

import groovy.lang.GString;

public class Orange extends Plant implements Fructiferous,Eatable{
    int age;
    int height;
    public boolean isItEatable;

    @Override
    public String getTimeToHarvest() {
        return timeToHarvest;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public String IsEatable() {
        if (isItEatable == true) {
            return "is eatable";
        }
        else {
            return "is not eatable";
        }
    }

}
