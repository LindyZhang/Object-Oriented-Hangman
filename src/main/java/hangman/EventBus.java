package hangman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {
    private static EventBus instance;
    private Map<EventType, List<IObserver>> observers;
    private EventBus() {

        observers = new HashMap<>();
        for (EventType type : EventType.values()) {
            observers.put(type, new ArrayList<>());
        }
    }
    public static EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }
    public void attach(IObserver observer, EventType eventType) {
        if (eventType == EventType.ALL) {
            for (EventType type : EventType.values()) {
                if (type != EventType.ALL) {  //
                    observers.get(type).add(observer);
                }
            }
        } else {
            observers.get(eventType).add(observer);
        }
    }
    public void postMessage(EventType eventType) {
        for (IObserver observer : observers.get(eventType)) {
            observer.update(eventType);
        }
    }
    public void removeObservers() {

        for (EventType type : EventType.values()) {
            observers.get(type).clear();
        }
    }
}
