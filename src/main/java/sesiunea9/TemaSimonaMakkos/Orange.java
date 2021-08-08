package sesiunea9.TemaSimonaMakkos;

public class Orange extends Plant implements Fructiferous,Eatable {

    public int age;
    public double height;

    public static String color = "Orange";
    public String timeToHarvest;

    public Orange(int age, double height, String tToHarvest, int oxigenProduction){
        this.age = age;
        this.height = height;
        this.timeToHarvest = tToHarvest;
        this.oxygenProduction = oxigenProduction;
    }

    public Orange(){
    }

    public String getTimeToHarvest(){
        return this.timeToHarvest;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    //orange is always eatable
    @Override
    public boolean isEatable() {
        return true;
    }
}
