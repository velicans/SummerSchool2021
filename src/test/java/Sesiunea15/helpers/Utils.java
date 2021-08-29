package Sesiunea15.helpers;

public class Utils {

    public static void sleep(int unitsOf200ms) {

        try {
            Thread.sleep(unitsOf200ms * 200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
