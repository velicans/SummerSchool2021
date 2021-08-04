package sesiunea9;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Dog extends Animal {

    private String color;
    private int numarDePicioare;

    public Dog() {
        animalType = "caine";
    }

    public int getNumarDePicioare() {
        return numarDePicioare;
    }

    public void setNumarDePicioare(int numarDePicioare) {
        this.numarDePicioare = numarDePicioare;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void feed() {
        System.out.println("Dam de mancare la caine.");
    }

    public void makeSound() {
        System.out.println("ham ham.....");
    }

    @Override
    public String toString() {
        return "Acest canine este " + name + "!";
    }

}
