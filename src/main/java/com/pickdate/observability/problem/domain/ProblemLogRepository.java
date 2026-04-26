package com.pickdate.observability.problem.domain;

import com.pickdate.observability.problem.application.ProblemLogFilter;
import com.pickdate.shared.domain.Identifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProblemLogRepository {

    ProblemLog save(ProblemLog entity);

    Page<ProblemLog> findAll(Pageable pageable);

    Page<ProblemLog> findAll(ProblemLogFilter problemLogFilter, Pageable pageable);

    void deleteById(Identifier problemId);

    void deleteAll();

    Optional<ProblemLog> findById(Identifier identifier);
}
