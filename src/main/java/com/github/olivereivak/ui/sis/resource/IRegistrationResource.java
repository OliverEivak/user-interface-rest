package com.github.olivereivak.ui.sis.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.olivereivak.ui.sis.entity.Authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Path("/register")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IRegistrationResource {

    @POST
    Authentication register(RegistrationForm registrationForm);

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    class RegistrationForm {
        private String username;
        private String password;
        private String firstName;
        private String lastName;
    }

}
