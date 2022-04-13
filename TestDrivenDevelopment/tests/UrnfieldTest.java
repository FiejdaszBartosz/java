import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pl.retsuz.conversions.urnfield.Urnfield;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UrnfieldTest {


    public static Collection<Object[]> urnfieldNumbersProvider()
    {
        //watch out for string escaping
        return Arrays.asList(new Object[][] {
                { "/", 1 }, // print /
                { "///", 3 }, // print ///
                { "/\\" , 6}, // print /\
                { "//\\\\", 12}, // print //\\
                { "//\\\\\\\\", 22}, // print //\\\\
                { "////\\\\\\\\\\", 29 } // print ////\\\\\
        });
    }

    @ParameterizedTest
    @MethodSource("urnfieldNumbersProvider")
    public void parTest(String urnfieldNumber, int arabicNumber){
        Urnfield urnfield = new Urnfield();
        assertEquals(urnfieldNumber, urnfield.fromArabic(arabicNumber));
        assertEquals(arabicNumber, urnfield.toArabic(urnfieldNumber));
    }

    public void testRangeOfNumbers()
    {
        assertThrows(IllegalArgumentException.class,new Executable(){
            @Override
            public void execute() throws Throwable {
                Urnfield urnfield = new Urnfield();
                assertEquals("//\\\\", urnfield.fromArabic(31));
            }
        });
    }
}
