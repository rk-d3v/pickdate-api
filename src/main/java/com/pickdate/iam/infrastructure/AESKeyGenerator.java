package com.pickdate.iam.infrastructure;

import com.pickdate.bootstrap.functional.Try;

import javax.crypto.KDF;
import javax.crypto.SecretKey;
import javax.crypto.spec.HKDFParameterSpec;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;


public class AESKeyGenerator {

    private static final String KEY_ALGORITHM = "AES";
    private static final int AES_256_KEY_BYTES = 32;

    public static String generateAesKeyFromSettings(AESKeySettings settings) {
        KDF hkdf = Try.of(() -> KDF.getInstance("HKDF-SHA256"));
        byte[] masterKeyBytes = Base64.getDecoder().decode(settings.master());
        byte[] saltBytes = Base64.getDecoder().decode(settings.salt());

        AlgorithmParameterSpec params = HKDFParameterSpec.ofExtract()
                .addIKM(masterKeyBytes)
                .addSalt(saltBytes)
                .thenExpand(settings.info().getBytes(UTF_8), AES_256_KEY_BYTES);

        return Try.of(() -> toString(hkdf.deriveKey(KEY_ALGORITHM, params)));
    }

    public static String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private static String toString(SecretKey key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
}
