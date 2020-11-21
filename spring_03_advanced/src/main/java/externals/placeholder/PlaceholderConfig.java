
package externals.placeholder;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Description: 使用占位符表达式的配置
 */
@Configuration
@ComponentScan	//扫描BlankDiscPlaceholder
@PropertySource("classpath:externals/app.properties")
public class PlaceholderConfig {
	
//	@Value("${disc.title}")
//	private String title;
//
//	@Value("${disc.artist}")
//	private String artist;
//
//	@Bean
//	public BlankDisc blankDisc() {
//		return new BlankDisc(title, artist);
//	}
	
}
