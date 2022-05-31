package event;

import exceptions.AmountTypeException;

/**
 * This class is used to create and expense or income.
 */
public class Event implements IEvent {
    private EventType mType;
    private double mAmount;
    private String mDescription;
    private String mCategory;
    private String mDate;

    /**
     * Creates event
     * @param type        event type
     * @param amount      amount of income or expense
     * @param description description of the event
     * @param category    category of the event
     * @param date        date of the event
     * @throws AmountTypeException if the amount doesn't match the type
     */
    public Event(EventType type, double amount, String description, String category, String date) throws AmountTypeException {
        this.mType = type;
        setAmount(amount);
        this.mDescription = description;
        this.mCategory = category;
        this.mDate = date;
    }


    /**
     * @return eventType
     */
    @Override
    public EventType getEventType() {
        return this.mType;
    }

    /**
     * @return amount
     */
    @Override
    public double getAmount() {
        return this.mAmount;
    }

    /**
     * @return description
     */
    @Override
    public String getDescription() {
        return this.mDescription;
    }

    /**
     * @return category
     */
    @Override
    public String getCategory() {
        return this.mCategory;
    }

    /**
     * @return date
     */
    @Override
    public String getDate() {
        return this.mDate;
    }

    /**
     * @param eventType is it income or expense
     */
    @Override
    public void setEventType(EventType eventType) {
        this.mType = eventType;
    }

    /**
     * @param amount amount of event
     * @throws AmountTypeException if the amount doesn't match the type
     */
    @Override
    public void setAmount(double amount) throws AmountTypeException {
        switch (this.mType) {
            case INCOME:
                if (amount > 0)
                    this.mAmount = amount;
                else
                    throw new AmountTypeException();
                break;
            case EXPENSE:
                if (amount < 0)
                    this.mAmount = amount;
                else
                    throw new AmountTypeException();
                break;
            default:
                this.mAmount = amount;
        }
    }

    /**
     * @param description description of event
     */
    @Override
    public void setDescription(String description) {
        this.mDescription = description;
    }

    /**
     * @param category category of the event
     */
    @Override
    public void setCategory(String category) {
        this.mCategory = category;
    }

    /**
     * @param date date of the event
     */
    @Override
    public void setDate(String date) {
        this.mDate = date;
    }

}
