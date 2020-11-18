package soundsystem.autoconfig.impls;

import org.springframework.stereotype.Component;
import soundsystem.autoconfig.interfaces.CompactDisc;

@Component("lover")
public class Lover implements CompactDisc {
    private String title = "Lover";
    private String artist = "Taylor Swift";

    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
