package sesiunea9;

public abstract class Animal {

    public String name;
    protected String animalType;
    private int age;
    private int ageAtConsult;

    public abstract void feed();

    public int getAgeAtConsult() {
        return ageAtConsult;
    }

    public String getAnimalType() {
        return animalType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAge(int age, int yearFromNow) {
        this.age = age;
        this.ageAtConsult = age + yearFromNow;
    }

    public void makeSound() {
        System.out.println("shhhh.....");
    }
}
