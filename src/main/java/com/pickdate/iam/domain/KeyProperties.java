package com.pickdate.iam.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties("pickdate.keys")
@NoArgsConstructor
public class KeyProperties {

    private String masterKey;
    private String rememberMeKey;

    public boolean isMasterKeySet() {
        return masterKey != null
                && !masterKey.isBlank()
                && !masterKey.equals("none");
    }
}
