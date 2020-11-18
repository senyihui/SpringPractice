package soundsystem.javaconfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import soundsystem.javaconfig.impls.CDPlayer;
import soundsystem.javaconfig.impls.Lover;
import soundsystem.javaconfig.impls.TheBeatles;
import soundsystem.javaconfig.interfaces.CompactDisc;

@Configuration
public class CDPlayerConfig {

    @Bean(name = "lover")
    public CompactDisc lover() {
        return new Lover();
    }

    @Bean(name = "beatles")
    public CompactDisc theBeatles() {
        return new TheBeatles();
    }

    @Bean
    public CDPlayer cdPlayer(@Qualifier("beatles") CompactDisc cd) {
        return new CDPlayer(cd);
    }
}
