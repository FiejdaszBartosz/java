package event;

public interface IEvent {
    enum EventType {
        INCOME,
        EXPENSE
    }

    EventType getEventType();
    double getAmount();
    String getDescription();
    String getCategory();
    String getDate();
    void setEventType(EventType eventType);
    void setAmount(double amount);
    void setDescription(String description);
    void setCategory(String category);
    void setDate(String date);
}
