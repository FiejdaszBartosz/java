package event;

import exceptions.AmountTypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    @Test
    public void testEventStructure() {
        try {
            Event event = new Event(
                    IEvent.EventType.INCOME,
                    200,
                    "electricity bill",
                    "bill",
                    "2022-31-05");

            assertEquals(IEvent.EventType.INCOME, event.getEventType());
            assertEquals(200, event.getAmount());
            assertEquals("electricity bill", event.getDescription());
            assertEquals("bill", event.getCategory());
            assertEquals("2022-31-05", event.getDate());

        } catch (AmountTypeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testEventException() {
        AmountTypeException thrown = assertThrows(
                AmountTypeException.class, () -> {
                    Event event = new Event(
                            IEvent.EventType.INCOME,
                            -200,
                            "electricity bill",
                            "bill",
                            "2022-31-05");
                }
        );

        assertTrue(thrown.getMessage().contains("Invalid amount"));
    }


}