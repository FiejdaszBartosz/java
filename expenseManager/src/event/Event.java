package event;

/**
 * This class is used to create and expense or income.
 */
public class Event implements IEvent{
    private EventType mType;
    private double mAmount;
    private String mDescription;
    private String mCategory;
    private String mDate;

    /**
     * Creates event
     * @param type event type
     * @param amount amount of income or expense
     * @param description description of the event
     * @param category category of the event
     * @param date date of the event
     */
    public Event(EventType type, double amount, String description, String category, String date) {
        this.mType = type;
        this.mAmount = amount;
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


}
