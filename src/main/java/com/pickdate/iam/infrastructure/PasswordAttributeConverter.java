package com.pickdate.iam.infrastructure;

import com.pickdate.bootstrap.domain.Password;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@Converter(autoApply = true)
class PasswordAttributeConverter implements AttributeConverter<Password, String> {

    private final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public String convertToDatabaseColumn(Password password) {
        if (password == null) return null;
        if (password.value() == null || password.value().isBlank()) return null;
        if (password.isHashed()) return password.value();

        return encoder.encode(password.value());
    }

    @Override
    public Password convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) return null;
        return Password.hashed(dbData);
    }
}
