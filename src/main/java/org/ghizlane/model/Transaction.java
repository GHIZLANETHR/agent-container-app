package org.ghizlane.model;

import java.time.LocalDateTime;

public class Transaction {

    private final String id;
    private final LocalDateTime date;
    private final double amount;
    private final TransactionType type;

    private Transaction(Builder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.amount = builder.amount;
        this.type = builder.type;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private LocalDateTime date = LocalDateTime.now();
        private double amount;
        private TransactionType type;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder date(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder type(TransactionType type) {
            this.type = type;
            return this;
        }

        public Transaction build() {

            if (id == null || date == null || type == null
                    || !Double.isFinite(amount) || amount < 0) {
                throw new IllegalArgumentException(
                        "Transaction invalide"
                );
            }

            return new Transaction(this);
        }
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", type=" + type +
                '}';
    }
}