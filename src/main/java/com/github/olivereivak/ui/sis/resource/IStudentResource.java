package com.github.olivereivak.ui.sis.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

    @POST
    @RolesAllowed({"TEACHER"})
    User update(User user);

    @POST
    @Path("/join")
    @RolesAllowed({"TEACHER"})
    List<User> join(List<User> users);

    @POST
    @Path("/split")
    @RolesAllowed({"TEACHER"})
    List<User> split(List<User> users);

}
