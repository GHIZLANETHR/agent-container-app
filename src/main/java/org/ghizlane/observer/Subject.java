package org.ghizlane.observer;

import org.ghizlane.model.Event;

public interface Subject {

    void subscribe(Observer observer);

    void unsubscribe(Observer observer);

    void notifyObservers(Event event);
}