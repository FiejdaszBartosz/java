import calculate.Calculate;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import shuntingYard.ShuntingYard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShuntingYardTest {
    @Test
    public void testItConvertsToPostfix()
    {
        assertEquals("2 3 5 - 3 x -", ShuntingYard.toPostfixNotation("2-(3-5)x3"));
        assertEquals("5 3 7 - 2 x 3 5 1 + x - x 3 -", ShuntingYard.toPostfixNotation("5x((3-7)x2-3x(5+1))-3"));
    }
}
