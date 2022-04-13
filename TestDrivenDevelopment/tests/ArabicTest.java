import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import pl.retsuz.conversions.arabic.Arabic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArabicTest {
    @Test
    public void testItConvertsToArabic()
    {
        Arabic arabic = new Arabic();
        assertEquals(1, arabic.toArabic("1"));
    }

    @Test
    public void testItConvertsFromArabic()
    {
        Arabic arabic = new Arabic();
        assertEquals("1", arabic.fromArabic(1));
    }

    @Test
    public void testItThrowsExceptionOnInvalidArabicString()
    {
        assertThrows(IllegalArgumentException.class,new Executable(){
            @Override
            public void execute() throws Throwable {
                Arabic arabic = new Arabic();
                arabic.toArabic("test");
            }

        });

    }
}
