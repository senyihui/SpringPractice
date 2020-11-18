package soundsystem.xmlconfig.impls;

import soundsystem.xmlconfig.interfaces.CompactDisc;

public class Lover implements CompactDisc {
    private String title = "Lover";
    private String artist = "Taylor Swift";

    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
