package eventHandler;

import event.Event;

import java.io.*;
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

    /**
     * Saves events array to file
     */
    @Override
    public void saveEventsToFile() {
        try {
            FileOutputStream fOut = new FileOutputStream("/Users/bartoszfiejdasz/studia/semestr4/java/expenseManager/test.ser");
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);

            oOut.writeObject(mEvents);
            oOut.flush();
            oOut.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads events list from file
     */
    @Override
    public void loadEventsFromFile() {
        try {
            FileInputStream fIn = new FileInputStream("/Users/bartoszfiejdasz/studia/semestr4/java/expenseManager/test.ser");
            ObjectInputStream oIn = new ObjectInputStream(fIn);

            setEventsArray((ArrayList<Event>) oIn.readObject());

            oIn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
