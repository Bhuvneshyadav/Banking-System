package com.bhuvnesh.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Implement logic to retrieve the current auditor
        return Optional.of("ACCOUNTS_MS"); // Example hardcoded user
    }
}