package Sesiunea14.tema;

import java.util.Random;

public class Utils {


    public String randomMustType(String[] list){
        Random random = new Random();

        return list[random.nextInt(list.length)];
    }

}
