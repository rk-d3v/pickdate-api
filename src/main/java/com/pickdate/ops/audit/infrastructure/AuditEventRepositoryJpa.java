package com.pickdate.ops.audit.infrastructure;

import com.pickdate.bootstrap.domain.Identifier;
import com.pickdate.ops.audit.application.AuditLogFilter;
import com.pickdate.ops.audit.domain.Action;
import com.pickdate.ops.audit.domain.AuditEventRepository;
import com.pickdate.ops.audit.domain.AuditLogEventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import static com.pickdate.ops.audit.infrastructure.AuditEventSpecifications.*;


@Repository
interface AuditEventRepositoryJpa extends AuditEventRepository,
        JpaRepository<AuditLogEventEntity, Identifier>,
        JpaSpecificationExecutor<AuditLogEventEntity> {

    @Override
    default Page<AuditLogEventEntity> findAll(AuditLogFilter filter, Pageable pageable) {
        if (filter == null || filter.isEmpty()) return findAll(pageable);
        var action = Action.ofNullable(filter.action());
        Specification<AuditLogEventEntity> specification = Specification
                .where(actionEquals(action))
                .and(userIdEquals(filter.user()))
                .and(createdAtFrom(filter.from()))
                .and(createdAtTo(filter.to()));

        return findAll(specification, pageable);
    }
}
