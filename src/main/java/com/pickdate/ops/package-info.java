/**
 * Ops (Operations / Observability) module.
 *
 * <p>
 * This module contains operational capabilities intended for administrators and support staff:
 * capturing, storing and exposing application error records ("incidents") for diagnostics.
 * </p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *   <li>Persisting error records (timestamp, request/correlation id, context, stack trace redacted if needed).</li>
 *   <li>Providing read-only API for browsing and inspecting recorded errors.</li>
 * </ul>
 *
 * <h2>Non-responsibilities</h2>
 * <ul>
 *   <li>No business/domain logic of other bounded contexts (poll, iam, etc.).</li>
 *   <li>No authentication/authorization logic – access control is delegated to IAM/security configuration.</li>
 * </ul>
 *
 * <h2>Exposed interface</h2>
 * <p>
 * Other modules should interact with Ops only via its exposed API (named interfaces / use cases),
 * e.g. reporting an error occurrence or querying error summaries. The module is CLOSED to prevent
 * accidental coupling to internal persistence/web details.
 * </p>
 */
@Nonnull
@ApplicationModule(type = CLOSED)
package com.pickdate.ops;

import jakarta.annotation.Nonnull;
import org.springframework.modulith.ApplicationModule;

import static org.springframework.modulith.ApplicationModule.Type.CLOSED;
