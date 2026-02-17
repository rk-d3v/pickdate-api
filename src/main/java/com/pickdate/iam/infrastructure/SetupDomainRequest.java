package com.pickdate.iam.infrastructure;

import com.pickdate.iam.domain.DomainUrl;

record SetupDomainRequest(
        String domain
) {

    public DomainUrl getDomainUrl() {
        return new DomainUrl(domain);
    }
}
