package soundsystem.javaconfig.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import soundsystem.javaconfig.interfaces.CompactDisc;
import soundsystem.javaconfig.interfaces.MediaPlayer;

public class CDPlayer implements MediaPlayer {

    private CompactDisc cd;

    public CDPlayer(CompactDisc compactDisc) {
        this.cd = compactDisc;
    }

    public void setCd(CompactDisc cd) {
        this.cd = cd;
    }

    public void play() {
        cd.play();
    }
}
