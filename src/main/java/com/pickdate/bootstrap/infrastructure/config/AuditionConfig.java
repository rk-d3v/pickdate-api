package com.pickdate.bootstrap.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.Instant;
import java.util.Optional;


@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
class AuditionConfig {

    @Bean(name = "auditingDateTimeProvider")
    DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(Instant.now());
    }
}
