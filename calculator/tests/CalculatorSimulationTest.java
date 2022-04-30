import calculate.Calculate;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import shuntingYard.ShuntingYard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorSimulationTest {
    @Test
    public void testItCalculate()
    {
        assertEquals(8.0, Calculate.calculateExpression(ShuntingYard.toPostfixNotation("2-(3-5)x3")));
        assertEquals(-133.0, Calculate.calculateExpression(ShuntingYard.toPostfixNotation("5x((3-7)x2-3x(5+1))-3")));
    }
}
