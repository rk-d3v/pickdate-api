package com.pickdate.iam.infrastructure;

import com.pickdate.iam.domain.KeyProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Data
@Configuration
@ConfigurationProperties("pickdate.keys")
@NoArgsConstructor
class KeyPropertiesConfig {

    private String rememberMeKey;

    @Bean
    public KeyProperties keyProperties() {
        log.trace("Creating KeyProperties with rememberMeKey: {}", rememberMeKey);
        return new KeyProperties(rememberMeKey);
    }
}
