import aspect.TrackCounter;
import config.TrackCounterConfig;
import interfaces.CompactDisc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TrackCounterConfig.class)
public class TrackCounterTest {
    @Autowired
    private CompactDisc compactDisc;

    @Autowired
    private TrackCounter trackCounter;
    
    @Test
    public void test() {
        compactDisc.playTrack(1);
        compactDisc.playTrack(3);
        compactDisc.playTrack(3);
        compactDisc.playTrack(3);
        compactDisc.playTrack(4);
        compactDisc.playTrack(4);

        assertEquals(1, trackCounter.getPlayCount(1));
        assertEquals(0, trackCounter.getPlayCount(2));
        assertEquals(3, trackCounter.getPlayCount(3));
        assertEquals(2, trackCounter.getPlayCount(4));
    }
}
