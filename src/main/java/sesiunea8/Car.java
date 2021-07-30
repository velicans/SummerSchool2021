package sesiunea8;

public class Car {
    private String model;
    private String color;
    private int nrOfDoors;
    private String motor;

    public Car(String model, String color, int nrOfDoors, String motor) {
        this.model = model;
        this.color = color;
        this.nrOfDoors = nrOfDoors;
        this.motor = motor;
    }

    public String getModel() {
        return model;
    }

    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Car setColor(String color) {
        this.color = color;
        return this;
    }

    public int getNrOfDoors() {
        return nrOfDoors;
    }

    public Car setNrOfDoors(int nrOfDoors) {
        this.nrOfDoors = nrOfDoors;
        return this;
    }

    public String getMotor() {
        return motor;
    }

    public Car setMotor(String motor) {
        this.motor = motor;
        return this;
    }

    public void run(int maxSpeed){
        System.out.println("The max speed is :"+ maxSpeed);
    }
}
