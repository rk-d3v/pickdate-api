package com.pickdate.iam.domain;

import com.pickdate.iam.infrastructure.AESKeyGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "keys")
@NoArgsConstructor
public class KeyEntity {
    @Id
    private String id;
    private String salt;
    private int version;

    public KeyEntity(String id, String salt, int version) {
        this.id = id;
        this.salt = salt;
        this.version = version;
    }

    public static KeyEntity initKey(String id) {
        return new KeyEntity(id, AESKeyGenerator.generateSalt(), 0);
    }

    public String info() {
        return id + ":" + version;
    }
}
