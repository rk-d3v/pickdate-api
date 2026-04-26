package com.pickdate.observability.audit.infrastructure;

import com.pickdate.shared.web.RequestDetails;
import org.springframework.security.core.Authentication;


final class IpExtractor {

    private IpExtractor() {
    }

    static String extractIp(Authentication authentication) {
        if (authentication == null) return null;
        Object details = authentication.getDetails();
        return details instanceof RequestDetails requestDetails
                ? requestDetails.clientIp()
                : null;
    }

    static String extractUserAgent(Authentication authentication) {
        if (authentication == null) return null;
        Object details = authentication.getDetails();
        return details instanceof RequestDetails requestDetails
                ? requestDetails.userAgent()
                : null;
    }
}
