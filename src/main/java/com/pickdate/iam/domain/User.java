package com.pickdate.iam.domain;

import com.pickdate.bootstrap.domain.Email;
import com.pickdate.bootstrap.domain.Password;
import com.pickdate.bootstrap.domain.Username;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.CascadeType.*;
import static java.util.stream.Collectors.toSet;
import static lombok.AccessLevel.PROTECTED;


@With
@Getter
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class User {

    @ManyToMany(cascade = {DETACH, MERGE, PERSIST, REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authorities",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "authority")
    )
    private final Set<Authority> authorities = new HashSet<>();

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "username"))
    private Username username;

    @Column(name = "password")
    private Password password;

    @Column(name = "email")
    private Email email;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    public User(Username username, Password password, Email email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User addAuthority(Authority authority) {
        authorities.add(authority);
        return this;
    }

    List<String> getAuthoritiesAsString() {
        return authorities.stream()
                .map(Authority::value)
                .toList();
    }

    public Set<GrantedAuthority> getAuthorities() {
        return authorities.stream()
                .map(Authority::toGrantedAuthority)
                .collect(toSet());
    }

    public UserDetails toUserDetails() {
        return new UserAuth()
                .withUsername(username.value())
                .withPassword(password.value())
                .withAuthorities(getAuthorities());
    }

    @With
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class UserAuth implements UserDetails, CredentialsContainer {

        private String username;
        private String password;
        private String email;
        private Set<GrantedAuthority> authorities;

        @Override
        public void eraseCredentials() {
            this.password = "";
        }
    }
}
