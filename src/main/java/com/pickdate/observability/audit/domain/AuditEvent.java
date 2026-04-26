package com.pickdate.observability.audit.domain;

import com.pickdate.shared.functional.Maybe;


public interface AuditEvent {

    String userId();

    Action action();

    String remoteAddress();

    String userAgent();

    default Maybe<AuditPayload> payload() { return Maybe.none(); }
}

