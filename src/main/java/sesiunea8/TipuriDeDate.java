package sesiunea8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TipuriDeDate {

    int i = 5;
    Integer ii = 5;

    char a = 66;
    char b = 'B';

    String s = "Ana are mere";

    @Test
    public void test() {
        System.out.println(Integer.toString(i));
        System.out.println(ii.toString());

    }

    @Test
    public void test1() {
        System.out.println(a);
        System.out.println(b);
    }


}
