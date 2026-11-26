package org.ghizlane;

import org.ghizlane.model.*;
import org.ghizlane.strategy.*;
import org.ghizlane.container.Container;
import org.ghizlane.display.*;
import org.ghizlane.security.SecurityContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Application {

    public static void main(String[] args) {

        try (ConfigurableApplicationContext context =
                     SpringApplication.run(
                             Application.class, args)) {

            // Création des agents via Spring

            Agent a1 = context.getBean(
                    Agent.class, "Agent A"
            );

            Agent a2 = context.getBean(
                    Agent.class, "Agent B"
            );

            Agent a3 = context.getBean(
                    Agent.class, "Agent C"
            );

            // Observer

            a1.subscribe(a2);
            a1.subscribe(a3);

            // Strategy

            a2.setStrategy(new ScoringStrategy());
            a3.setStrategy(new HistoryStrategy());

            // Builder

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

            Transaction t3 = Transaction.builder()
                    .id("T3")
                    .amount(2000)
                    .type(TransactionType.VENTE)
                    .build();

            // =============================
            // TEST 1 : SECURITY
            // =============================

            System.out.println("\n=== TEST SECURITY ===");

            try {
                a1.addTransaction(t1);
            } catch (SecurityException e) {
                System.out.println(e.getMessage());
            }

            SecurityContext.login("user", "1234");

            try {
                a1.addTransaction(t1);
            } catch (SecurityException e) {
                System.out.println(e.getMessage());
            }

            SecurityContext.login("admin", "1234");

            // =============================
            // TEST 2 : LOGGING
            // OBSERVER ET STRATEGY
            // =============================

            System.out.println("\n=== TEST LOGGING ===");

            a1.addTransaction(t1);
            a1.addTransaction(t2);

            a1.display();

            // =============================
            // TEST 3 : CACHE
            // =============================

            System.out.println("\n=== TEST CACHE ===");

            System.out.println("Premier appel :");

            System.out.println(a1.maxTransaction());

            System.out.println("Deuxième appel :");

            System.out.println(a1.maxTransaction());

            System.out.println("Ajout de T3 :");

            a1.addTransaction(t3);

            System.out.println("Troisième appel :");

            System.out.println(a1.maxTransaction());

            // =============================
            // TEST 4 : SINGLETON
            // =============================

            System.out.println("\n=== TEST SINGLETON ===");

            Container c1 = Container.getInstance();
            Container c2 = Container.getInstance();

            System.out.println(
                    "Même instance : " + (c1 == c2)
            );

            c1.addAgent(a1);
            c1.addAgent(a2);
            c1.addAgent(a3);

            // =============================
            // TEST 5 : ADAPTER
            // =============================

            System.out.println("\n=== TEST ADAPTER ===");

            c1.setDisplayDevice(new HDMIProjector());

            c1.display();

            c1.setDisplayDevice(
                    new VGAAdapter(new VGAProjector())
            );

            c1.display();

            // Résultats des stratégies

            System.out.println(
                    "Solde Agent B : " + a2.getBalance()
            );

            System.out.println(
                    "Historique Agent C : "
                            + a3.getHistory().size()
            );

            SecurityContext.logout();
        }
    }
}