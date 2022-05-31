package event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testEventStructure() {
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
    }
}