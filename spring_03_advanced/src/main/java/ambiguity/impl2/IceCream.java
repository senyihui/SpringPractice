package ambiguity.impl2;

import ambiguity.Dessert;
import org.springframework.stereotype.Component;

@Component
public class IceCream implements Dessert {
    public void sweet() {
        System.out.println("ice cream");
    }
}
