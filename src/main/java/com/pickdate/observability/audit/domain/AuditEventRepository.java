package com.pickdate.observability.audit.domain;

import com.pickdate.observability.audit.application.AuditLogFilter;
import com.pickdate.shared.domain.Identifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface AuditEventRepository {

    Page<AuditLogEventEntity> findAll(Pageable pageable);

    AuditLogEventEntity save(AuditLogEventEntity eventEntity);

    void deleteById(Identifier identifier);

    void deleteAll();

    Optional<AuditLogEventEntity> findById(Identifier identifier);

    Page<AuditLogEventEntity> findAll(AuditLogFilter auditLogFilter, Pageable pageable);
}
