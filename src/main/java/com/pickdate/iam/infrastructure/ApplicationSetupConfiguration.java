package com.pickdate.iam.infrastructure;

import com.pickdate.iam.domain.ApplicationSetupRepository;
import com.pickdate.iam.domain.ApplicationSetupService;
import com.pickdate.iam.domain.ApplicationSetupUseCase;
import com.pickdate.iam.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
class ApplicationSetupConfiguration {

    @Bean
    public ApplicationSetupUseCase applicationSetupUseCase(
            ApplicationSetupRepository repository,
            UserRepository userRepository
    ) {
        return new ApplicationSetupService(
                repository,
                userRepository
        );
    }
}
