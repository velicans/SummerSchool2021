package sesiunea9.tema;

public class Orange extends Plant implements Fructiferous, Eatable {

    private int age;
    private int height;
    private boolean eatable;

    public Orange(int age, int height, boolean eatable) {
        this.age = age;
        this.height = height;
        this.eatable = eatable;
    }

    public int getAge() {
        return age;
    }

    public Orange setAge(int age) {
        this.age = age;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public Orange setHeight(int height) {
        this.height = height;
        return this;
    }

    @Override
    public void getTimeToHarvest() {
        System.out.println("The oranges are harvested in august");

    }

    @Override
    public void isEatable() {
        if (eatable) {
            System.out.println("Is eatable!");
        } else {
            System.out.println("Is not eatable!");
        }
    }


}

