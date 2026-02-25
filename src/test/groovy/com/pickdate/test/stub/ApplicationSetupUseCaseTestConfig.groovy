package com.pickdate.test.stub

import com.pickdate.iam.domain.ApplicationSetupService
import com.pickdate.iam.domain.ApplicationSetupUseCase


class ApplicationSetupUseCaseTestConfig {

    static final def setupRepo = new ApplicationSetupRepositoryFake()
    static final def userRepo = new UserRepositoryFake()

    static ApplicationSetupUseCase applicationSetupUseCase() {
        new ApplicationSetupService(setupRepo, userRepo)
    }
}
