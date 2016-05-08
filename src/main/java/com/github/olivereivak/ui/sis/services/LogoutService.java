package com.github.olivereivak.ui.sis.services;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.olivereivak.ui.sis.dao.AuthenticationDAO;
import com.github.olivereivak.ui.sis.entity.Authentication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class LogoutService  {

    @Inject
    private AuthenticationDAO authenticationDAO;

    public void logout(Authentication authentication) {
        log.info(String.format("User %s is logging out", authentication.getUser().getUsername()));
        authenticationDAO.remove(authentication);
    }

}
