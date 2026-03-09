package com.pickdate.test.fixture

import com.pickdate.bootstrap.domain.Identifier
import com.pickdate.ops.audit.domain.Action
import com.pickdate.ops.audit.domain.AuditLogEventEntity

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

