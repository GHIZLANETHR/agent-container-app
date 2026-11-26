package org.ghizlane;

import org.ghizlane.factory.StrategyFactory;
import org.ghizlane.strategy.NotificationStrategy;

public class TestFactory {

    public static void main(String[] args) {

        NotificationStrategy s1 =
                StrategyFactory.create("SCORING");

        NotificationStrategy s2 =
                StrategyFactory.create("HISTORY");

        System.out.println(s1.getClass().getSimpleName());
        System.out.println(s2.getClass().getSimpleName());
    }
}