package org.ghizlane;

import org.ghizlane.model.*;
import org.ghizlane.strategy.*;

public class TestAgent {

    public static void main(String[] args) {

        Agent a1 = new Agent("Agent A");
        Agent a2 = new Agent("Agent B");
        Agent a3 = new Agent("Agent C");

        // Souscription

        a1.subscribe(a2);
        a1.subscribe(a3);

        // Changement des stratégies

        a2.setStrategy(new ScoringStrategy());
        a3.setStrategy(new HistoryStrategy());

        Transaction t1 = Transaction.builder()
                .id("T1")
                .amount(1000)
                .type(TransactionType.VENTE)
                .build();

        Transaction t2 = Transaction.builder()
                .id("T2")
                .amount(300)
                .type(TransactionType.ACHAT)
                .build();

        a1.addTransaction(t1);
        a1.addTransaction(t2);

        System.out.println(
                "Solde final : " + a2.getBalance()
        );

        System.out.println(
                "Taille historique : " + a3.getHistory().size()
        );

        System.out.println(
                "Transaction maximale : "
                        + a1.maxTransaction().getId()
        );

        // Désabonnement

        a1.unsubscribe(a2);

        // Changement de stratégie pendant l'exécution

        a3.setStrategy(new DefaultStrategy());

        a1.addTransaction(t1);
    }
}