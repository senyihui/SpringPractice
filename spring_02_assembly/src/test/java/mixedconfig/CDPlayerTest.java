package mixedconfig;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import soundsystem.mixedconfig.CDPlayerConfig;
import soundsystem.mixedconfig.SoundSystemConfig;
import soundsystem.mixedconfig.interfaces.CompactDisc;
import soundsystem.mixedconfig.interfaces.MediaPlayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SoundSystemConfig.class)
public class CDPlayerTest {

    @Autowired
    private MediaPlayer player;

    @Test
    public void play() {
        player.play();
    }
}
