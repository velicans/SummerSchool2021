package Sesiunea10;

public class BasicCalculator {

    private int a;
    private int b;

    public BasicCalculator(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public BasicCalculator() {

    }

    public int sum(int a, int b) {
        return a + b;
    }

    public int dif(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

}
