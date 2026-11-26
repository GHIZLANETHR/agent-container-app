package org.ghizlane.strategy;

import org.ghizlane.model.*;

public class DefaultStrategy
        implements NotificationStrategy {

    @Override
    public void process(Agent agent, Event event) {

        System.out.println(
                agent.getName()
                        + " a reçu une notification de "
                        + event.agentName()
        );
    }
}