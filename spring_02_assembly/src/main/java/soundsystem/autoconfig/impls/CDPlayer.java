package soundsystem.autoconfig.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import soundsystem.autoconfig.interfaces.CompactDisc;
import soundsystem.autoconfig.interfaces.MediaPlayer;

@Component
public class CDPlayer implements MediaPlayer {

    private CompactDisc cd;

    public CDPlayer(@Qualifier("beatles") CompactDisc compactDisc) {
        this.cd = compactDisc;
    }

    public void setCd(@Qualifier("beatles") CompactDisc cd) {
        this.cd = cd;
    }

    public void play() {
        cd.play();
    }
}
