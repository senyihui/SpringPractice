package ambiguity.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "ambiguity.impl1")
public class ImplConfig1 {
}
