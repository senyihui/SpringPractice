package soundsystem.autoconfig.impls;

import org.springframework.stereotype.Component;
import soundsystem.autoconfig.interfaces.CompactDisc;

@Component("beatles")
public class TheBeatles implements CompactDisc {
    private String title = "Yesterday";
    private String artist = "The Beatles";

    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
