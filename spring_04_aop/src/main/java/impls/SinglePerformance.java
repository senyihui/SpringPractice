package impls;

import interfaces.Performance;
import org.springframework.stereotype.Component;

@Component
public class SinglePerformance implements Performance {
    public void perform() {
        System.out.println("Performing......");
    }
}
