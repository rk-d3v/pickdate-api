package com.pickdate.ops.infrastructure;

import com.pickdate.ops.domain.ProblemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
interface ProblemRepository extends JpaRepository<ProblemEntity, String> {
}
