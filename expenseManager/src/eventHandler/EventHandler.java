package eventHandler;

import event.Event;

import java.util.ArrayList;

/**
 * Handler for all events
 */
public class EventHandler implements IEventHandler {
    private ArrayList<Event> mEvents;
    private double mAccountBalance;

    /**
     * Creates empty client handler
     */
    public EventHandler() {
        this.mEvents = new ArrayList<>();
        this.mAccountBalance = 0;
    }


    /**
     * @return array with events
     */
    @Override
    public ArrayList<Event> getEventsArray() {
        return this.mEvents;
    }

    /**
     * @return current account balance
     */
    @Override
    public double getAccountBalance() {
        return this.mAccountBalance;
    }

    /**
     * @param events array with events
     */
    @Override
    public void setEventsArray(ArrayList<Event> events) {
        this.mEvents = events;
        calculateBalance();
    }

    /**
     * Calculate current account balance
     */
    private void calculateBalance() {
        double temp = 0;

        for(Event i : mEvents)
            temp += i.getAmount();

        this.mAccountBalance = temp;
    }

    /**
     * Adds event to events array
     * @param event event to add
     */
    public void addEvent(Event event) {
        this.mEvents.add(event);
        calculateBalance();
    }
}
