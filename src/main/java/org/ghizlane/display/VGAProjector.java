package org.ghizlane.display;

public class VGAProjector implements VGA {

    @Override
    public void print(String data) {

        System.out.println("Affichage VGA :");
        System.out.println(data);
    }
}