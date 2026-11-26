package org.ghizlane.display;

public class VGAAdapter implements HDMI {

    private final VGA vga;

    public VGAAdapter(VGA vga) {
        this.vga = vga;
    }

    @Override
    public void display(String data) {

        vga.print(data);
    }
}