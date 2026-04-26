package com.pickdate.observability.audit.application;

import com.pickdate.observability.audit.domain.AuditEvent;
import com.pickdate.observability.audit.domain.AuditLogEventEntity;


final class AuditEventFactory {

    private AuditEventFactory() {
    }

    static AuditLogEventEntity createFrom(AuditEvent auditEvent) {
        return new AuditLogEventEntity()
                .withUserId(auditEvent.userId())
                .withAction(auditEvent.action())
                .withRemoteAddress(auditEvent.remoteAddress())
                .withUserAgent(auditEvent.userAgent())
                .withPayload(auditEvent.payload().orNull());
    }
}
