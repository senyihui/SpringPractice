package aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class TrackCounter {

    private Map<Integer, Integer> trackCounts = new HashMap<Integer, Integer>();

    //定义切点，通知playTrack方法
    @Pointcut("execution(* interfaces.CompactDisc.playTrack(int)) && args(trackNum)")
    public void trackPlayed(int trackNum) {
    }

    //在播放前，为该磁道计数
    @Before("trackPlayed(trackNum)")
    public void countTrack(int trackNum) {
        int currentCount = getPlayCount(trackNum);
        trackCounts.put(trackNum, currentCount + 1);
    }

    //获取某磁道的播放次数
    public int getPlayCount(int trackNum) {
        return trackCounts.containsKey(trackNum) ? trackCounts.get(trackNum) : 0;
    }

}
