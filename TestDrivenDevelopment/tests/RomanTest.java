import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pl.retsuz.conversions.arabic.Arabic;
import pl.retsuz.conversions.roman.Roman;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RomanTest {

    public static Collection<Object[]> romanNumbersProvider() {
        return Arrays.asList(new Object[][] {
                { "XXVI", 26 },
                { "LXXVI", 76 },
                { "DCLXVI", 666},
                { "I", 1},
                { "VI", 6},
                { "XII", 12 }
        });
    }
    @ParameterizedTest
    @MethodSource("romanNumbersProvider")
    public void parTest(String romanNumber, int arabicNumber){
        Roman roman = new Roman();
        assertEquals(romanNumber, roman.fromArabic(arabicNumber));
        assertEquals(arabicNumber, roman.toArabic(romanNumber));
    }


    public void testRangeOfNumbers()
    {
        assertThrows(IllegalArgumentException.class,new Executable(){
            @Override
            public void execute() throws Throwable {
                Roman roman = new Roman();
                assertEquals("MMMCCCXXXVIII", roman.fromArabic(3338));
            }

        });

    }
}
