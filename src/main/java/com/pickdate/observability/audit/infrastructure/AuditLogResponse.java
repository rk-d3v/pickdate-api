package com.pickdate.observability.audit.infrastructure;

import com.pickdate.observability.audit.domain.AuditPayload;

import java.time.Instant;

record AuditLogResponse(
        String id,
        String action,
        String user,
        String remoteAddress,
        String userAgent,
        AuditPayload payload,
        Instant createdAt
) {
}
