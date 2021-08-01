package homework;

import homework.s8.Calculator;

public class MainCalculator {
    public static void main(String[] args) {
        //System.out.println("Hello Word");

        Calculator calculator = new Calculator();

        int sum = calculator.addNumbers(5, 3);
        System.out.println("Suma este " + sum);

        double sum1 = calculator.addNumbers(5.5, 2.1);
        System.out.println("Suma este " + sum1);

        int diff = calculator.subtractNumbers(5, 3);
        System.out.println("Diferenta este " + diff);

        int prod = calculator.multiplyNumbers(5, 3);
        System.out.println("Produsul este " + prod);

        double div = calculator.divideNumbers(3.0, 5.0);
        System.out.println("Rezultatul este " + div);

        int mod = calculator.modulo(7, 3);
        System.out.println("Restul impartirii este " + mod);
    }
}
