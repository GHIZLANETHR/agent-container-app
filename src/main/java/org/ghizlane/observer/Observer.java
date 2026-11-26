package org.ghizlane.observer;

import org.ghizlane.model.Event;

public interface Observer {

    void update(Event event);
}