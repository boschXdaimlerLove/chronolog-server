package com.bxdlove.worktimeserver.api.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.credential   .UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.EnumSet;
import java.util.Set;

@ApplicationScoped
public class ApplicationIdentityStore implements IdentityStore {

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return EnumSet.of(ValidationType.VALIDATE);
    }

    public CredentialValidationResult validate(UsernamePasswordCredential credential) {
        if (CredentialUtils.validate(credential.getCaller(), credential.getPasswordAsString())) {
            return new CredentialValidationResult(credential.getCaller(), Set.of("users"));
        }
        return CredentialValidationResult.NOT_VALIDATED_RESULT;
    }
}
