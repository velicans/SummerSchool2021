package sesiunea9.Temasesiunea9;

 class Orange extends Plant implements Fructiferous, Eatable {
         int age;
         int height;
         String color = "Orange";

public Orange(int age, int height, int oxygenProduction) {
        super (oxygenProduction);

        this.age = age;
        this.height = height;
        }

public boolean isEatable() {
        return true;
        }

public String getTimeToHarvest() {
        return "August";
        }}
