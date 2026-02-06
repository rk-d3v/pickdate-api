package com.pickdate.bootstrap.encryption;

public interface Encryptor {

    static Encryptor noop() {

        return new Encryptor() {

            @Override
            public String encrypt(String value) {
                return value;
            }

            @Override
            public String decrypt(String value) {
                return value;
            }
        };

    }

    String encrypt(String value);

    String decrypt(String value);
}
