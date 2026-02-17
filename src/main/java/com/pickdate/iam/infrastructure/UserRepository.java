package com.pickdate.iam.infrastructure;

import com.pickdate.bootstrap.domain.Email;
import com.pickdate.bootstrap.domain.Username;
import com.pickdate.iam.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


interface UserRepository extends JpaRepository<User, Username> {

    boolean existsByEmail(Email email);
}
