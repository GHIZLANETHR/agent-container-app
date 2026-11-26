package org.ghizlane;

import org.ghizlane.container.Container;
import org.ghizlane.display.*;
import org.ghizlane.model.Agent;

public class TestAdapter {

    public static void main(String[] args) {

        Container container = Container.getInstance();

        container.addAgent(new Agent("Agent A"));

        // Afficheur compatible HDMI

        container.setDisplayDevice(
                new HDMIProjector()
        );

        container.display();

        // Afficheur incompatible VGA
        // Utilisation de l'adaptateur

        VGA vga = new VGAProjector();

        HDMI adapter = new VGAAdapter(vga);

        container.setDisplayDevice(adapter);

        container.display();
    }
}