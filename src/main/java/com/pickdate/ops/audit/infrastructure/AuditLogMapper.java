package com.pickdate.ops.audit.infrastructure;

import com.pickdate.ops.audit.domain.AuditLogEventEntity;

import static com.pickdate.bootstrap.domain.Value.valueOrNull;


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
