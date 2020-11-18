package soundsystem.mixedconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;

@Configuration
@Import({CDPlayerConfig.class})
@ImportResource("classpath:META-INF/mixedconfig.xml")
public class SoundSystemConfig {
}
