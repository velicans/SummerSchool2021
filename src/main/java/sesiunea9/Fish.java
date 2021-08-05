package sesiunea9;

public class Fish extends Animal {
    private String color;

    public Fish() {
        animalType = "peste";
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void feed() {
        System.out.println("dam de mancare la peste.");
    }

    public void makeSound() {
        System.out.println("fhhh fhhh.....");
    }

    @Override
    public String toString() {
        return "Fish " +
                "name= " + name + '\'' +
                '}';
    }
}
