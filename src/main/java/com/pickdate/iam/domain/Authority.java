package com.pickdate.iam.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Instant;

import static lombok.AccessLevel.PROTECTED;


@Entity
@Table(name = "authorities")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Authority {

    public static Authority USER = new Authority("USER");
    public static Authority ADMIN = new Authority("ADMIN");

    @Id
    private String authority;

    @CreatedDate
    private Instant createdAt = Instant.now();

    @LastModifiedDate
    private Instant updatedAt;

    Authority(String authority) {
        this.authority = authority;
    }

    public static Authority of(String authority) {
        return new Authority(authority);
    }

    public String value() {
        return authority;
    }

    public GrantedAuthority toGrantedAuthority() {
        return new SimpleGrantedAuthority(authority);
    }
}
