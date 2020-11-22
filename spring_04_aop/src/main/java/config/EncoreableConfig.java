
package config;

import aspect.EncoreableIntroducer;
import impls.SinglePerformance;
import interfaces.Performance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@EnableAspectJAutoProxy
public class EncoreableConfig {

	@Bean
	public Performance performance() {
		return new SinglePerformance();
	}
	
	@Bean
	public EncoreableIntroducer encoreableIntroducer() {
		return new EncoreableIntroducer();
	}
}
