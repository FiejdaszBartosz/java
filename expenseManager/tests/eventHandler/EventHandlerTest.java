package eventHandler;

import event.Event;
import event.IEvent;
import exceptions.AmountTypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventHandlerTest {
    @Test
    void testCalculateTwoIncome() {
        EventHandler eventHandler = new EventHandler();

        try {
            Event firstEvent = new Event(
                    IEvent.EventType.INCOME,
                    200,
                    "test",
                    "salary",
                    "2022-05-31");
            Event secondEvent = new Event(
                    IEvent.EventType.INCOME,
                    100,
                    "test",
                    "salary",
                    "2022-06-31");

            eventHandler.addEvent(firstEvent);
            eventHandler.addEvent(secondEvent);

            assertEquals(300, eventHandler.getAccountBalance());
        } catch (AmountTypeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testCalculateTwoExpense() {
        EventHandler eventHandler = new EventHandler();

        try {
            Event firstEvent = new Event(
                    IEvent.EventType.EXPENSE,
                    -200,
                    "test",
                    "salary",
                    "2022-05-31");
            Event secondEvent = new Event(
                    IEvent.EventType.EXPENSE,
                    -100,
                    "test",
                    "salary",
                    "2022-06-31");

            eventHandler.addEvent(firstEvent);
            eventHandler.addEvent(secondEvent);

            assertEquals(-300, eventHandler.getAccountBalance());
        } catch (AmountTypeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testCalculateTwoMixEvents() {
        EventHandler eventHandler = new EventHandler();

        try {
            Event firstEvent = new Event(
                    IEvent.EventType.INCOME,
                    200,
                    "test",
                    "salary",
                    "2022-05-31");
            Event secondEvent = new Event(
                    IEvent.EventType.EXPENSE,
                    -100,
                    "test",
                    "salary",
                    "2022-06-31");

            eventHandler.addEvent(firstEvent);
            eventHandler.addEvent(secondEvent);

            assertEquals(100, eventHandler.getAccountBalance());
        } catch (AmountTypeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testSaveAndLoadFromFile() {
        EventHandler beforeSave = new EventHandler();
        EventHandler afterLoad = new EventHandler();

        try {
            Event firstEvent = new Event(
                    IEvent.EventType.INCOME,
                    200,
                    "test",
                    "salary",
                    "2022-05-31");
            Event secondEvent = new Event(
                    IEvent.EventType.EXPENSE,
                    -100,
                    "test",
                    "salary",
                    "2022-06-31");

            beforeSave.addEvent(firstEvent);
            beforeSave.addEvent(secondEvent);

            beforeSave.saveEventsToFile("SaveAndLoadFromFileTest.ser");
            afterLoad.loadEventsFromFile("SaveAndLoadFromFileTest.ser");

            assertEquals(beforeSave.getAccountBalance(), afterLoad.getAccountBalance());
        } catch (AmountTypeException e) {
            fail(e.getMessage());
        }
    }
}