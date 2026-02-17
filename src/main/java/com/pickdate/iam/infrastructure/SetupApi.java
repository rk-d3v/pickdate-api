package com.pickdate.iam.infrastructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/iam/setup")
@AllArgsConstructor
@Tag(name = "Setup", description = "Bootstrap endpoints for initial application configuration (encryption, public domain/origin, and initial admin user). Available only until setup is completed.")
class SetupApi {

    private final AppConfigService appConfigService;
    private final UserService userService;

    @PostMapping("/encryption")
    @Operation(summary = "Initialize encryption for the application")
    ResponseEntity<AESKeyResponse> generateAesKey() {
        AESKeySettings aesKeySettings = appConfigService.setupEncryption();
        return ResponseEntity.ok(new AESKeyResponse(aesKeySettings.info()));
    }

    @PostMapping("/domain")
    @Operation(summary = "Set public domain/origin for the application")
    ResponseEntity<Void> setupDomain(@RequestBody SetupDomainRequest request) {
        appConfigService.setupDomain(request.getDomainUrl());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/user")
    @Operation(summary = "Create initial admin user")
    ResponseEntity<Void> setupUser(@RequestBody CreateUserRequest request) {
        userService.setupAdmin(request.user());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping
    @Operation(summary = "Complete setup (locks bootstrap endpoints)")
    ResponseEntity<Void> completeSetup() {
        appConfigService.completeSetup();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
