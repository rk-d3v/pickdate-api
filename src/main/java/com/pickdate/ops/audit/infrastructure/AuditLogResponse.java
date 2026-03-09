package com.pickdate.ops.audit.infrastructure;

import com.pickdate.ops.audit.domain.AuditPayload;

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
