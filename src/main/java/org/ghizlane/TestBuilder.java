package org.ghizlane;

import org.ghizlane.model.*;

public class TestBuilder {

    public static void main(String[] args) {

        Transaction t1 = Transaction.builder()
                .id("T1")
                .amount(1000)
                .type(TransactionType.VENTE)
                .build();

        Transaction t2 = Transaction.builder()
                .id("T2")
                .amount(500)
                .type(TransactionType.ACHAT)
                .build();

        System.out.println(t1);
        System.out.println(t2);
    }
}