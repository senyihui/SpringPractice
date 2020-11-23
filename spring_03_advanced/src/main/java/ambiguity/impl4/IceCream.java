package ambiguity.impl4;

import ambiguity.Dessert;
import org.springframework.stereotype.Component;

@Component
@Cold
public class IceCream implements Dessert {
    public void sweet() {
        System.out.println("ice cream");
    }
}
