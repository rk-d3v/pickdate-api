package com.pickdate.iam.infrastructure;

import com.pickdate.bootstrap.encryption.Encryptor;
import com.pickdate.bootstrap.functional.Try;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;


class AESEncryptor implements Encryptor {

    private static final String ALGORITHM = "AES/GCM/NoPadding";

    private static final int GCM_TAG_LENGTH_BITS = 128;
    private static final int GCM_NONCE_LENGTH_BYTES = 12;

    private final Key key;
    private final ThreadLocal<Cipher> cipher;
    private final SecureRandom secureRandom;

    private AESEncryptor(Key key) {
        this.key = key;
        this.secureRandom = new SecureRandom();
        this.cipher = ThreadLocal.withInitial(() -> Try.of(() -> Cipher.getInstance(ALGORITHM)));
    }

    public static Encryptor create(Key key) {
        return new AESEncryptor(key);
    }

    public static Encryptor create(String key) {
        byte[] bytes = Base64.getDecoder().decode(key);
        var secretKey = new SecretKeySpec(bytes, "AES");
        return new AESEncryptor(secretKey);
    }

    @Override
    public String encrypt(String value) {
        if (value == null) return null;

        byte[] nonce = new byte[GCM_NONCE_LENGTH_BYTES];
        secureRandom.nextBytes(nonce);

        Cipher cipher = this.cipher.get();
        Try.run(() -> cipher.init(ENCRYPT_MODE, key, new GCMParameterSpec(GCM_TAG_LENGTH_BITS, nonce)));

        byte[] ciphertext = Try.of(() -> cipher.doFinal(value.getBytes(UTF_8)));

        byte[] combined = new byte[nonce.length + ciphertext.length];
        System.arraycopy(nonce, 0, combined, 0, nonce.length);
        System.arraycopy(ciphertext, 0, combined, nonce.length, ciphertext.length);

        return Base64.getEncoder().encodeToString(combined);
    }

    @Override
    public String decrypt(String value) {
        if (value == null) return null;

        byte[] combined = Base64.getDecoder().decode(value);
        if (combined.length < GCM_NONCE_LENGTH_BYTES + 1) {
            throw new IllegalArgumentException("Ciphertext too short");
        }

        byte[] nonce = new byte[GCM_NONCE_LENGTH_BYTES];
        byte[] ciphertext = new byte[combined.length - GCM_NONCE_LENGTH_BYTES];

        System.arraycopy(combined, 0, nonce, 0, GCM_NONCE_LENGTH_BYTES);
        System.arraycopy(combined, GCM_NONCE_LENGTH_BYTES, ciphertext, 0, ciphertext.length);

        Cipher cipher = this.cipher.get();
        Try.run(() -> cipher.init(DECRYPT_MODE, key, new GCMParameterSpec(GCM_TAG_LENGTH_BITS, nonce)));

        byte[] plaintext = Try.of(() -> cipher.doFinal(ciphertext));
        return new String(plaintext, UTF_8);
    }
}
