package com.pickdate.iam.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.EAGER;


@Data
@Entity
@Table(name = "config")
@NoArgsConstructor
public class AppConfigEntity {

    public static final String APP_CONFIG = "app_config";

    @Id
    private String id = APP_CONFIG;

    private DomainUrl domainUrl;

    @OneToOne(cascade = ALL, fetch = EAGER)
    @JoinColumn(name = "key", referencedColumnName = "id")
    private KeyEntity key;

    @Enumerated(STRING)
    @Column(nullable = false)
    private SetupStatus setupStatus = SetupStatus.PENDING;

    public void completeSetup() {
        this.setupStatus = SetupStatus.COMPLETED;
    }

    public boolean isSetupCompleted() {
        return this.setupStatus == SetupStatus.COMPLETED;
    }

    enum SetupStatus {
        PENDING,
        COMPLETED
    }
}
