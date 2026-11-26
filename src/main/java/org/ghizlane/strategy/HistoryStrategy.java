package org.ghizlane.strategy;

import org.ghizlane.model.*;

public class HistoryStrategy
        implements NotificationStrategy {

    @Override
    public void process(Agent agent, Event event) {

        agent.addToHistory(event.transaction());

        System.out.println(
                agent.getName()
                        + " - Transaction ajoutée à l'historique : "
                        + event.transaction().getId()
        );
    }
}