package com.pickdate.test.fixture

import com.pickdate.observability.audit.domain.Action
import com.pickdate.observability.audit.domain.AuditLogEventEntity
import com.pickdate.shared.domain.Identifier

import java.time.Instant

class AuditFixture {

    static AuditLogEventEntity audit(String action, String userId, String createdAt) {
        return new AuditLogEventEntity(
                Identifier.generate(),
                Action.of(action),
                userId,
                null,
                null,
                null,
                Instant.parse(createdAt)
        )
    }
}

