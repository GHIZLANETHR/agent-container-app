package org.ghizlane.factory;

import org.ghizlane.strategy.*;

public class StrategyFactory {

    public static NotificationStrategy create(String type) {

        return switch (type.toUpperCase()) {

            case "SCORING" -> new ScoringStrategy();

            case "HISTORY" -> new HistoryStrategy();

            case "DEFAULT" -> new DefaultStrategy();

            default -> throw new IllegalArgumentException(
                    "Stratégie inconnue : " + type
            );
        };
    }
}