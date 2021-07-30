package sesiunea8;

public class MainExample {


    public static void main(String[] args) {
        //printName("sorin");
        // printName("alex");
        // printName(1);

        Car firstCar = new Car("VW", "Black", 5, "Diesel");
        System.out.println("My first car is a " + firstCar.getModel() +
                " and have the color " + firstCar.getColor() +
                " in " + firstCar.getNrOfDoors() + " doors " +
                " with a " + firstCar.getMotor() + " motor ");


        firstCar.run(200);

        firstCar.setColor("Blue");
        firstCar.setModel("BMW");
        firstCar.setNrOfDoors(3);

        System.out.println("My second car is a " + firstCar.getModel() +
                " and have the color " + firstCar.getColor() +
                " in " + firstCar.getNrOfDoors() + " doors " +
                " with a " + firstCar.getMotor() + " motor ");
    }

    public static void printName(int i) {
        System.out.println("Nu este un nume");
    }

    public static void printName(String name) {
        System.out.println("Numele lui este " + name);
    }
}