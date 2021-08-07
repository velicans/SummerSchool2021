package sesiunea8;

public class MainExample {


    public static void main(String[] args) {
        printName("sorin");
        printName("alex");
        printName(1);
    }

    public static void printName(int i){
        System.out.println("nu este un nume");
    }

    public static void printName(String name) {
        System.out.println("numele lui este " + name);
    }
}