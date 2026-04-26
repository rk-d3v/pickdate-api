package com.pickdate.bootstrap.infrastructure.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.List.of;


@Slf4j
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Tag(name = "API v1", description = "API v1 discovery and endpoint index")
class V1Api {

    @GetMapping
    @Operation(
            summary = "API v1 index",
            description = "Returns a high-level description of available API v1 resources and endpoints."
    )
    ResponseEntity<ApiIndexResponse> getApiIndex() {
        log.debug("retrieving API v1 index");

        var response = new ApiIndexResponse(
                "v1",
                "Pickdate API",
                "Public REST API for managing polls, votes, users, audit events and problem reports.",
                of(
                        new ApiResourceResponse(
                                "Polls",
                                "Manage polls, options, participants and locations.",
                                "/api/v1/polls",
                                of(
                                        new ApiEndpointResponse("POST", "/api/v1/polls", "Create new poll"),
                                        new ApiEndpointResponse("GET", "/api/v1/polls/{pollId}", "Get poll by id"),
                                        new ApiEndpointResponse("DELETE", "/api/v1/polls/{pollId}", "Delete poll"),
                                        new ApiEndpointResponse("POST", "/api/v1/polls/{pollId}/options", "Add option to poll"),
                                        new ApiEndpointResponse("POST", "/api/v1/polls/{pollId}/participants", "Register participant"),
                                        new ApiEndpointResponse("POST", "/api/v1/polls/{pollId}/location", "Add location to poll")
                                )
                        ),
                        new ApiResourceResponse(
                                "Votes",
                                "Cast and list votes for a poll.",
                                "/api/v1/polls/{pollId}/votes",
                                of(
                                        new ApiEndpointResponse("GET", "/api/v1/polls/{pollId}/votes", "List votes"),
                                        new ApiEndpointResponse("POST", "/api/v1/polls/{pollId}/votes", "Cast a vote")
                                )
                        ),
                        new ApiResourceResponse(
                                "Users",
                                "Manage user accounts.",
                                "/api/v1/iam/users",
                                of(
                                        new ApiEndpointResponse("GET", "/api/v1/iam/users", "List users"),
                                        new ApiEndpointResponse("GET", "/api/v1/iam/users/{id}", "Get user by id"),
                                        new ApiEndpointResponse("POST", "/api/v1/iam/users", "Create user")
                                )
                        ),
                        new ApiResourceResponse(
                                "Audit",
                                "Browse and manage audit events.",
                                "/api/v1/observability/audit",
                                of(
                                        new ApiEndpointResponse("GET", "/api/v1/observability/audit", "List audit events"),
                                        new ApiEndpointResponse("GET", "/api/v1/observability/audit/{id}", "Get audit event by id"),
                                        new ApiEndpointResponse("DELETE", "/api/v1/observability/audit", "Delete all audit events"),
                                        new ApiEndpointResponse("DELETE", "/api/v1/observability/audit/{id}", "Delete audit event by id")
                                )
                        ),
                        new ApiResourceResponse(
                                "Problems",
                                "Browse and manage application problem/error reports.",
                                "/api/v1/observability/problems",
                                of(
                                        new ApiEndpointResponse("GET", "/api/v1/observability/problems", "List problems"),
                                        new ApiEndpointResponse("GET", "/api/v1/observability/problems/{problemId}", "Get problem by id"),
                                        new ApiEndpointResponse("DELETE", "/api/v1/observability/problems", "Delete all problems"),
                                        new ApiEndpointResponse("DELETE", "/api/v1/observability/problems/{problemId}", "Delete problem by id")
                                )
                        )
                )
        );

        return ResponseEntity.ok(response);
    }

    record ApiIndexResponse(
            String version,
            String name,
            String description,
            List<ApiResourceResponse> resources
    ) {
    }

    record ApiResourceResponse(
            String name,
            String description,
            String basePath,
            List<ApiEndpointResponse> endpoints
    ) {
    }

    record ApiEndpointResponse(
            String method,
            String path,
            String description
    ) {
    }
}
