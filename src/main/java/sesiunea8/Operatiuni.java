package sesiunea8;

import org.junit.Test;

public class Operatiuni {

    @Test
    public void test2() {

        int i = 5;
        printeazaNumar(i);

        printeazaNumar(++i);

        i += 3;
        printeazaNumar(i);

        if (i == 9) {
            System.out.println("Valoarea lui i este :" + i);
        }

        if (i != 8 && i != 10 && i != 9) {
            System.out.println("Valoarea lui i nu e nici 8, nici 10, este " + i);
        } else {
            System.out.println("Conditia nu a fost indeplinita.");
        }
        i++;

        printeazaNumar(i);
    }

    public void printeazaNumar(int i) {
        switch (i) {
            case 1: {
                System.out.println("unu");
                break;
            }
            case 6: {
                System.out.println("sase");
                break;
            }
            case 9: {
                System.out.println("noua");
                break;
            }
            default: {
                System.out.println("Nu stim sa scriem numarul!");

            }
        }
    }
}
