package event;

import exceptions.AmountTypeException;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public interface IEvent extends Serializable {
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
    void setAmount(double amount) throws AmountTypeException;
    void setDescription(String description);
    void setCategory(String category);
    void setDate(String date);
}
