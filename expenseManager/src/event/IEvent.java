package event;

public interface IEvent {
    enum EventType {
        income,
        expense
    }

    EventType getEventType();
    double getAmount();
    String getDescription();
    String getCategory();
    String getDate();
}
