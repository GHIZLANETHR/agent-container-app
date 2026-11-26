package org.ghizlane.strategy;

import org.ghizlane.model.*;

public class ScoringStrategy
        implements NotificationStrategy {

    @Override
    public void process(Agent agent, Event event) {

        Transaction t = event.transaction();

        if (t.getType() == TransactionType.VENTE) {

            agent.setBalance(
                    agent.getBalance() + t.getAmount()
            );

        } else {

            agent.setBalance(
                    agent.getBalance() - t.getAmount()
            );
        }

        System.out.println(
                agent.getName() + " - Solde : "
                        + agent.getBalance()
        );
    }
}