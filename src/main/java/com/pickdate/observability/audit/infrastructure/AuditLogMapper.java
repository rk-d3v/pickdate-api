package com.pickdate.observability.audit.infrastructure;

import com.pickdate.observability.audit.domain.AuditLogEventEntity;

import static com.pickdate.shared.domain.Value.valueOrNull;


final class AuditLogMapper {

    private AuditLogMapper() {
    }

    static AuditLogResponse toResponse(AuditLogEventEntity auditLog) {
        return new AuditLogResponse(
                valueOrNull(auditLog.getId()),
                valueOrNull(auditLog.getAction()),
                auditLog.getUserId(),
                auditLog.getRemoteAddress(),
                auditLog.getUserAgent(),
                auditLog.getPayload(),
                auditLog.getCreatedAt()
        );
    }
}
