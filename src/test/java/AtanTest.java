import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;



public class AtanTest {
    private static final double DELTA = 0.0000001;

    @ParameterizedTest(name = "Calculate atan with x = {0} and expected result = {1}")
    @CsvFileSource(resources = "/atanTest.csv")
    void dataTest(Double arg, Double expectedRes) {
    Assertions.assertEquals(expectedRes, AtanCalculator.calculateAtan(arg, DELTA), DELTA);
    }

    @Test
    void nullArgTest() {
        Assertions.assertEquals(Double.NaN, AtanCalculator.calculateAtan(Double.NaN, DELTA), DELTA);
        Assertions.assertEquals(AtanCalculator.calculateAtan(1, DELTA), AtanCalculator.calculateAtan(1, Double.NaN), DELTA);
    }

    @Test
    void lessThanMinErrTest() {
        Assertions.assertEquals(AtanCalculator.calculateAtan(1, DELTA), AtanCalculator.calculateAtan(1, 10e-8));
    }
}

