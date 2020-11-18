package soundsystem.mixedconfig.impls;

import soundsystem.mixedconfig.interfaces.CompactDisc;
import soundsystem.mixedconfig.interfaces.MediaPlayer;

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
