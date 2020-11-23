package ambiguity.impl4;

import ambiguity.Dessert;
import org.springframework.stereotype.Component;

@Component
public class Cookie implements Dessert {
    public void sweet() {
        System.out.println("cookie");
    }
}
