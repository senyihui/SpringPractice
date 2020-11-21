
package conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:conditional/magic.properties")
public class MagicConfig {

	/**
	 * <p>描述：条件化地创建bean</p>
	 */
	@Bean
	@Conditional(MagicExistsCondition.class)
	public MagicBean magicBean() {
		return new MagicBean();
	}
	
}
