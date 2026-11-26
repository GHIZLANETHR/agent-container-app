package org.ghizlane;

import org.ghizlane.container.Container;
import org.ghizlane.model.Agent;

public class TestSingleton {

    public static void main(String[] args) {

        Container c1 = Container.getInstance();
        Container c2 = Container.getInstance();

        System.out.println(
                "Même instance : " + (c1 == c2)
        );

        c1.addAgent(new Agent("Agent A"));
        c1.addAgent(new Agent("Agent B"));

        System.out.println(
                "Nombre d'agents : " + c2.getAgents().size()
        );

        System.out.println(
                "Recherche : "
                        + c2.findAgent("Agent A").getName()
        );

        c2.removeAgent("Agent B");

        System.out.println(
                "Après suppression : "
                        + c1.getAgents().size()
        );
    }
}