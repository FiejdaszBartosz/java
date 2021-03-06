package eventHandler;

import event.Event;

import java.util.ArrayList;

public interface IEventHandler {
    ArrayList<Event> getEventsArray();
    double getAccountBalance();
    void setEventsArray(ArrayList<Event> events);
    void addEvent(Event event);
    void saveEventsToFile(String fileName);
    void saveEventsToFile();
    void loadEventsFromFile(String fileName);
    void loadEventsFromFile();
}
