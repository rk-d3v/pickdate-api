package com.pickdate.poll.infrastructure;

import com.pickdate.bootstrap.domain.Email;
import com.pickdate.bootstrap.encryption.Encryptor;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Converter(autoApply = true)
@RequiredArgsConstructor
class EmailConverter implements AttributeConverter<Email, String> {

    private final Encryptor encryptor;

    @Override
    public String convertToDatabaseColumn(Email email) {
        return email == null ? null : encryptor.encrypt(email.value());
    }

    @Override
    public Email convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Email.of(encryptor.decrypt(dbData));
    }
}
