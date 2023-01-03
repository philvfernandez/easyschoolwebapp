package com.easybytes.easyschool.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {

        //returns user context of the authenticated user
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
