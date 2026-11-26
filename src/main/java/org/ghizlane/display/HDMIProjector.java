package org.ghizlane.display;

public class HDMIProjector implements HDMI {

    @Override
    public void display(String data) {

        System.out.println("Affichage HDMI :");
        System.out.println(data);
    }
}