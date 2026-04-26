package com.pickdate.observability.audit.application;

import com.pickdate.observability.audit.domain.AuditEvent;
import com.pickdate.observability.audit.domain.AuditLogEventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuditEventUseCase {

    AuditLogEventEntity findById(String id);

    Page<AuditLogEventEntity> findAll(AuditLogFilter auditLogFilter, Pageable pageable);

    Page<AuditLogEventEntity> findAll(Pageable pageable);

    void save(AuditEvent auditEvent);

    void delete(String id);

    void deleteAll();
}
