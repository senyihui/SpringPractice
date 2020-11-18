package soundsystem.javaconfig.impls;

import org.springframework.stereotype.Component;
import soundsystem.javaconfig.interfaces.CompactDisc;

public class Lover implements CompactDisc {
    private String title = "Lover";
    private String artist = "Taylor Swift";

    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
