package synchMessage;

import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SynchMessage {
    private ArrayList<ObjectOutputStream> list;

    public SynchMessage() {
        list = new ArrayList<ObjectOutputStream>();
    }

    public synchronized ObjectOutputStream get(int i) {
        return list.get(i);
    }

    public synchronized void add(ObjectOutputStream object) {
        list.add(object);
    }

    public synchronized int size() {
        return list.size();
    }

    public synchronized void remove(ObjectOutputStream object) {
        list.remove(object);
    }
}
