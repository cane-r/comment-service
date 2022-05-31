package com.csiris.commentservice.configuration;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfig {

	@Bean
	public AuditorAwareImpl auditorAware() {
		return new AuditorAwareImpl();
	}

	class AuditorAwareImpl implements AuditorAware<String> {
		@Override
		public Optional<String> getCurrentAuditor() {
			return Optional.of("anonymous");
		}
	}

}
