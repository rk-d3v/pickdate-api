package com.pickdate.iam.domain;


public interface ApplicationSetupUseCase {

    void setupDomain(DomainUrl domainUrl);

    boolean setupCompleted();

    void completeSetup();

    void setupAdmin(User user);
}
