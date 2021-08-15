package Sesiunea10;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Tag("sesiunea10")
public class TestClass {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("BeforeAll");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("AfterAll");
    }

    static List<Arguments> numbersProvider() {
        List<Arguments> list = new ArrayList<>();
        list.add(Arguments.of(2, 3));
        list.add(Arguments.of(4, 5));
        list.add(Arguments.of(6, 7));

        return list;
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("BeforeEach");
    }

    @ParameterizedTest
    @MethodSource("numbersProvider")
    @DisplayName("Suma a doua numere.")
    @Tag("add")
    public void testCalcAdd(int a, int b) {
        BasicCalculator basicCalculator = new BasicCalculator();
        int result = basicCalculator.sum(a, b);
        Assertions.assertEquals(9, result, "Rezultatul nu este cel asteptat");
        System.out.println("Rezultat: " + result);
    }

    @Test
    public void testCalcMultiply() {
        BasicCalculator basicCalculator = new BasicCalculator();
        int result = basicCalculator.multiply(3, 4);
        //Assertions.assertEquals(12, result, "Rezultatul nu este cel asteptat");
        /*Assertions.assertAll(
                () -> Assertions.assertEquals(11, result),
                () -> Assertions.assertNotEquals(4, result),
                () -> Assertions.assertNotNull(result)
        );*/
        Assertions.assertEquals(11, result);
        Assertions.assertNotEquals(4, result);
        Assertions.assertNotNull(result);
        System.out.println("Rezultat: " + result);
    }

    @AfterEach
    public void afterEach() {
        System.out.println("AfterEach");
    }

}
