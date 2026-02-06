package com.pickdate.iam.infrastructure

import spock.lang.Specification

class AESKeyGeneratorSpec extends Specification {

    def "should generate aes key"() {
        when:
        def info = "test:key:v1"
        def salt = AESKeyGenerator.generateSalt()
        def master = Base64.encoder.encodeToString("master-key".bytes)
        def settings = new AESKeySettings(info, salt, master)
        def key = AESKeyGenerator.generateAesKeyFromSettings(settings)

        then:
        def instance = AESEncryptor.create(key)
        def encrypted = instance.encrypt("test")
        def decrypted = instance.decrypt(encrypted)

        and:
        decrypted == "test"
    }
}
