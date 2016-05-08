package com.github.olivereivak.ui.sis.resource.filter;

import java.security.Principal;

import com.github.olivereivak.ui.sis.entity.Authentication;
import com.github.olivereivak.ui.sis.entity.Role;

public class ApplicationPrincipal implements Principal {

    private Authentication authentication;

    public ApplicationPrincipal (Authentication authentication) {
        this.authentication = authentication;
    }

    @Override
    public String getName() {
        return String.format("%s:%s", authentication.getToken(), authentication.getUser().getUsername());
    }

    public Role getRole() {
        return authentication.getUser().getRole();
    }

    public Authentication getAuthentication() {
        return authentication;
    }
}
