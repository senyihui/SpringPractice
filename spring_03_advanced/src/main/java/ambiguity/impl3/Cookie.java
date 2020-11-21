package ambiguity.impl3;

import ambiguity.Dessert;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Cookie implements Dessert {
    public void sweet() {
        System.out.println("cookie");
    }
}
