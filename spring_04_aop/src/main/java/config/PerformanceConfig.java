package config;

import aspect.Audience;
import impls.SinglePerformance;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = { SinglePerformance.class, Audience.class})
public class PerformanceConfig {
    // Java Config方式：哪个切面在前面，就先执行哪个，同时也先结束
//    @Bean
//    public Audience audience() {
//        return new Audience();
//    }
//
//    @Bean
//    public AroundAudience aroundAudience() {
//        return new AroundAudience();
//    }
}
