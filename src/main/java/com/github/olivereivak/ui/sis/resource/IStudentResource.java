package com.github.olivereivak.ui.sis.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.olivereivak.ui.sis.entity.User;

@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IStudentResource {

    @GET
    @RolesAllowed({"TEACHER"})
    List<User> getStudents();

}
