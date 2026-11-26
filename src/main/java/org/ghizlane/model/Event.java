package org.ghizlane.model;

public record Event(
        String agentName,
        Transaction transaction
) {}