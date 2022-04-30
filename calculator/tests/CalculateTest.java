import calculate.Calculate;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import shuntingYard.ShuntingYard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculateTest {
    @Test
    public void testItCalculateExpression()
    {
        assertEquals(8.0, Calculate.calculateExpression("2 3 5 - 3 x -"));
        assertEquals(-133.0, Calculate.calculateExpression("5 3 7 - 2 x 3 5 1 + x - x 3 -"));
    }
}
