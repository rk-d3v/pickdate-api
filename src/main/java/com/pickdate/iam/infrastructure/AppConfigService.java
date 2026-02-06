package com.pickdate.iam.infrastructure;

import com.pickdate.bootstrap.domain.Property;
import com.pickdate.bootstrap.encryption.Encryptor;
import com.pickdate.bootstrap.exception.FailedDependency;
import com.pickdate.iam.domain.AppConfigEntity;
import com.pickdate.iam.domain.DomainUrl;
import com.pickdate.iam.domain.KeyEntity;
import com.pickdate.iam.domain.KeyProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
class AppConfigService {

    private static final String KEY_NAME = "pickdate:encryptor";

    private final AppConfigRepository appConfigRepository;
    private final Encryptor encryptor;
    private final KeyProperties keyProperties;

    @Transactional
    public void setupDomain(DomainUrl domainUrl) {
        AppConfigEntity config = appConfigRepository.findAppConfig()
                .orElseGet(AppConfigEntity::new);

        config.setDomainUrl(domainUrl);
        appConfigRepository.save(config);
    }

    @Transactional
    public AESKeySettings setupEncryption() {
        assertMasterKey();
        AppConfigEntity appConfig = appConfigRepository.findAppConfig()
                .orElseGet(AppConfigEntity::new);

        KeyEntity key = KeyEntity.initKey(KEY_NAME);
        appConfig.setKey(key);
        appConfigRepository.save(appConfig);

        var settings = new AESKeySettings(
                key.info(),
                key.getSalt(),
                keyProperties.getMasterKey()
        );

        initializeEncryptor(settings);
        return settings;
    }

    @Transactional
    public void completeSetup() {
        AppConfigEntity appConfig = appConfigRepository.findAppConfig()
                .orElseThrow();

        if (appConfig.isSetupCompleted()) {
            return;
        }

        appConfig.completeSetup();
        appConfigRepository.save(appConfig);
    }

    @Transactional(readOnly = true)
    public boolean setupCompleted() {
        return appConfigRepository.findAppConfig()
                .map(AppConfigEntity::isSetupCompleted)
                .orElse(false);
    }

    private void assertMasterKey() {
        if (!keyProperties.isMasterKeySet()) {
            throw new FailedDependency(Property.of("master key", ""), "Master key is not set");
        }
    }

    private void initializeEncryptor(AESKeySettings settings) {
        String aesKey = AESKeyGenerator.generateAesKeyFromSettings(settings);
        Encryptor encryptor = AESEncryptor.create(aesKey);
        if (this.encryptor instanceof EncryptorDelegate encryptorDelegate) {
            encryptorDelegate.setDelegate(encryptor);
        }
    }
}
