package org.ghizlane.strategy;

import org.ghizlane.model.Agent;
import org.ghizlane.model.Event;

public interface NotificationStrategy {

    void process(Agent agent, Event event);
}