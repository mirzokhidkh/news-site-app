package uz.mk.newssiteapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {
    @Bean
    SpringSecurityAuditingAwareImpl auditorAware(){
        return new SpringSecurityAuditingAwareImpl();
    }
}
