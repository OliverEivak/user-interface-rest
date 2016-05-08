package com.github.olivereivak.ui.sis.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.mindrot.jbcrypt.BCrypt;

import com.github.olivereivak.ui.sis.entity.Authentication;
import com.github.olivereivak.ui.sis.entity.User;
import com.github.olivereivak.ui.sis.services.LoginService;
import com.github.olivereivak.ui.sis.services.UserService;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/register")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistrationResource extends BaseResource implements IRegistrationResource {

    @Inject
    private UserService userService;

    @Inject
    private LoginService loginService;

    @POST
    @Transactional
    public Authentication register(RegistrationForm registrationForm) {
        User user = new User();
        user.setUsername(registrationForm.getUsername());
        user.setPassword(BCrypt.hashpw(registrationForm.getPassword(), BCrypt.gensalt()).getBytes());
        user.setFirstName(registrationForm.getFirstName());
        user.setLastName(registrationForm.getLastName());

        user = userService.create(user);
        log.info(String.format("User %s has registered", user.getUsername()));

        return loginService.login(registrationForm.getUsername(), registrationForm.getPassword());
    }

}
