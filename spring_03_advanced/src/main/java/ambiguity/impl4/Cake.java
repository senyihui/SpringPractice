package ambiguity.impl4;

import ambiguity.Dessert;
import org.springframework.stereotype.Component;

@Component
public class Cake implements Dessert {
    public void sweet() {
        System.out.println("cake");
    }
}
