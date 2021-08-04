package sesiunea9;

import java.util.ArrayList;
import java.util.HashMap;

public class Test {

    @org.junit.Test
    public void arrays() {
        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        Dog dog3 = new Dog();
    }

    @org.junit.Test
    public void arrays3() {
        int[][] numbers = {
                {1, 2, 3, 4},
                {5, 6, 7}};

        System.out.println(numbers[1][1]);
        System.out.println(numbers.length);
    }

    @org.junit.Test
    public void arrayList1() {
        ArrayList<String> cars = new ArrayList<String>();
        cars.add("audi");
        cars.add("bentley");
        cars.add("citroen");
        System.out.println(cars.size());
        System.out.println(cars.get(0));


        cars.remove("audi");
        System.out.println(cars.size());
        System.out.println(cars.get(0));


    }

    @org.junit.Test
    public void hashmap() {
        HashMap<String, String> cars = new HashMap<String, String>();

        cars.put("bogdan", "bentley");
        cars.put("cosmin", "citroen");
        cars.put("alex", "audi");

        System.out.println(cars.get("alex"));

    }

    @org.junit.Test
    public void arrays2() {

        String cars[] = {"audi", "bentley", "citroen"};
        System.out.println(cars.length);

        System.out.println(cars[0]);

        if (cars[0].equals(cars[1])) {
            System.out.println("sunt aceasi marca.");
        }

    }


    @org.junit.Test
    public void test1() {

        Dog dog = new Dog();
        dog.setColor("black");
        // dog.setAge(1);
        dog.setName("grivei");
        dog.setNumarDePicioare(4);
        dog.setAge(2, 2);

        System.out.println(dog.getAge());
        System.out.println(dog.getAgeAtConsult());


    }

    @org.junit.Test
    public void test() {
        Dog dog = new Dog();
        dog.setColor("black");
        dog.setAge(1);
        dog.setName("grivei");
        dog.setNumarDePicioare(4);

        System.out.println("caine: " + dog.getAnimalType());
        dog.makeSound();

        Fish fish = new Fish();
        fish.setColor("red");
        fish.setAge(2);
        fish.name = "Nemo";
        System.out.println("peste: " + fish.getAnimalType());
        fish.makeSound();


    }
}
