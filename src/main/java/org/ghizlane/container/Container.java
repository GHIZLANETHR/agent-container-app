package org.ghizlane.container;

import org.ghizlane.model.Agent;
import org.ghizlane.display.HDMI;

import java.util.*;

public class Container {

    private static final Container instance =
            new Container();

    private final HashMap<String, Agent> agents =
            new HashMap<>();

    private HDMI displayDevice;

    // Constructeur privé

    private Container() {
    }

    // Instance unique

    public static Container getInstance() {
        return instance;
    }

    public void addAgent(Agent agent) {

        agents.put(agent.getName(), agent);
    }

    public void removeAgent(String name) {

        agents.remove(name);
    }

    public Agent findAgent(String name) {

        return agents.get(name);
    }

    public Map<String, Agent> getAgents() {

        return Map.copyOf(agents);
    }

    public void setDisplayDevice(HDMI displayDevice) {

        this.displayDevice = displayDevice;
    }

    public void display() {

        if (displayDevice == null) {
            System.out.println("Aucun afficheur connecté");
            return;
        }

        StringBuilder data = new StringBuilder();

        for (Agent agent : agents.values()) {

            data.append(agent.getName())
                    .append(" : ")
                    .append(agent.getTransactions())
                    .append("\n");
        }

        displayDevice.display(data.toString());
    }
}