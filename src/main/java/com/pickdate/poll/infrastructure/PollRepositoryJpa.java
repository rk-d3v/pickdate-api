package com.pickdate.poll.infrastructure;

import com.pickdate.poll.domain.Poll;
import com.pickdate.poll.domain.PollRepository;
import com.pickdate.shared.domain.Identifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
interface PollRepositoryJpa extends PollRepository, JpaRepository<Poll, Identifier> {
}
