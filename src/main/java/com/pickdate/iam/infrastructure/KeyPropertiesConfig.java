package com.pickdate.iam.infrastructure;

import com.pickdate.iam.domain.KeyProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties("pickdate.keys")
@NoArgsConstructor
class KeyPropertiesConfig {

    private String rememberMeKey;

    @Bean
    public KeyProperties keyProperties() {
        return new KeyProperties(rememberMeKey);
    }
}
