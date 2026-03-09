package com.pickdate.ops.problem.application;

import com.pickdate.ops.problem.domain.ProblemLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface ProblemLogUseCase {

    void save(ProblemLog entity);

    Page<ProblemLog> getProblems(ProblemLogFilter problemLogFilter, Pageable pageable);

    void deleteProblem(String problemId);

    void deleteAll();

    Optional<ProblemLog> getProblemById(String problemId);
}
