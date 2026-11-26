package org.ghizlane.model;

import org.ghizlane.observer.Observer;
import org.ghizlane.observer.Subject;
import org.ghizlane.strategy.*;
import org.ghizlane.annotations.*;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

import java.util.*;

@Component
@Scope("prototype")
public class Agent implements Subject, Observer {

    private final String name;

    private final List<Transaction> transactions =
            new ArrayList<>();

    private final List<Observer> observers =
            new ArrayList<>();

    private final List<Transaction> history =
            new ArrayList<>();

    private NotificationStrategy strategy =
            new DefaultStrategy();

    private double balance = 0;

    private long version = 0;

    public Agent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getVersion() {
        return version;
    }

    public List<Transaction> getHistory() {
        return List.copyOf(history);
    }

    public List<Transaction> getTransactions() {
        return List.copyOf(transactions);
    }

    public void addToHistory(Transaction transaction) {
        history.add(transaction);
    }

    public void setStrategy(NotificationStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy);
    }

    // Observer : souscription

    @Override
    public void subscribe(Observer observer) {

        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Event event) {

        for (Observer observer : List.copyOf(observers)) {
            observer.update(event);
        }
    }

    // Observer : réception d'une notification

    @Override
    public void update(Event event) {
        strategy.process(this, event);
    }

    // Ajouter une transaction

    @Log
    @SecuredBy(roles = {"ADMIN", "AGENT"})
    public void addTransaction(Transaction transaction) {

        transactions.add(
                Objects.requireNonNull(transaction)
        );

        version++;

        Event event = new Event(name, transaction);

        notifyObservers(event);
    }

    // Afficher les transactions

    @Log
    public void display() {

        System.out.println("Agent : " + name);

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    // Retourner la transaction maximale

    @Log
    @Cachable
    public Transaction maxTransaction() {

        System.out.println("Recherche du montant maximal...");

        return transactions.stream()
                .max(Comparator.comparingDouble(
                        Transaction::getAmount))
                .orElse(null);
    }
}