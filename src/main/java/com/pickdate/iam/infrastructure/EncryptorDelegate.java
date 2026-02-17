package com.pickdate.iam.infrastructure;

import com.pickdate.bootstrap.encryption.Encryptor;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;


@Component
class EncryptorDelegate implements Encryptor {

    private final AtomicReference<Encryptor> delegate = new AtomicReference<>();

    public EncryptorDelegate() {
        this.delegate.set(Encryptor.noop());
    }

    public void setDelegate(Encryptor delegate) {
        this.delegate.set(delegate);
    }

    @Override
    public String encrypt(String value) {
        return delegate.get().encrypt(value);
    }

    @Override
    public String decrypt(String value) {
        return delegate.get().decrypt(value);
    }
}
