package eventHandler;

import event.Event;
import exceptions.EventsArrayIsEmptyException;

import java.io.*;
import java.util.ArrayList;

/**
 * Handler for all events
 */
public class EventHandler implements IEventHandler {
    private ArrayList<Event> mEvents;
    private double mAccountBalance;
    private final String mFileName = "database.ser";

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
     * @param fileName file name to save
     */
    @Override
    public void saveEventsToFile(String fileName) {
        try {
            FileOutputStream fOut = new FileOutputStream(fileName);
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);

            if (!mEvents.isEmpty())
                oOut.writeObject(mEvents);
            else
                throw new EventsArrayIsEmptyException();

            oOut.flush();
            oOut.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves events array to file. Default file name.
     */
    @Override
    public void saveEventsToFile() {
        try {
            FileOutputStream fOut = new FileOutputStream(this.mFileName);
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);

            if (!mEvents.isEmpty())
                oOut.writeObject(mEvents);
            else
                throw new EventsArrayIsEmptyException();

            oOut.flush();
            oOut.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads events list from file
     * @param fileName file name to load
     */
    @Override
    @SuppressWarnings("unchecked")
    public void loadEventsFromFile(String fileName) {
        try {
            FileInputStream fIn = new FileInputStream(fileName);
            ObjectInputStream oIn = new ObjectInputStream(fIn);

            setEventsArray((ArrayList<Event>) oIn.readObject());

            oIn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads events list from file. Default file name.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void loadEventsFromFile() {
        try {
            FileInputStream fIn = new FileInputStream(this.mFileName);
            ObjectInputStream oIn = new ObjectInputStream(fIn);

            setEventsArray((ArrayList<Event>) oIn.readObject());

            oIn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
