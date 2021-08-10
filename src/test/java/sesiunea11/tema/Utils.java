package sesiunea11.tema;

import java.util.Random;

public final class Utils {

    private Utils() {
    }

    public static String generateRandom() {
        int leftLimit = 97;
        int rightLimit = 122;
        int stringLength = 8;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(stringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
