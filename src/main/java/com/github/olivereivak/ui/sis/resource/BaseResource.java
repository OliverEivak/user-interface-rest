package com.github.olivereivak.ui.sis.resource;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import com.github.olivereivak.ui.sis.entity.Authentication;
import com.github.olivereivak.ui.sis.entity.User;
import com.github.olivereivak.ui.sis.resource.filter.ApplicationPrincipal;

import lombok.extern.slf4j.Slf4j;

/**
 * Extend this to have resources automatically bound in ApplicationModule.
 */

@Slf4j
public class BaseResource {

    @Context
    private SecurityContext securityContext;

    protected Authentication getAuthentication() {
        ApplicationPrincipal applicationPrincipal = (ApplicationPrincipal) securityContext.getUserPrincipal();
        Authentication authentication = null;

        if (applicationPrincipal != null) {
            authentication = applicationPrincipal.getAuthentication();
        }

        return authentication;
    }

    protected User getUser() {
        User user = null;

        Authentication authentication = getAuthentication();
        if (authentication != null) {
            user = authentication.getUser();
        }

        return user;
    }

}
