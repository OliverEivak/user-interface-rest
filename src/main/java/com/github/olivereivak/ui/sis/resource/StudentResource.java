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
import com.github.olivereivak.ui.sis.services.UserService;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource extends BaseResource implements IStudentResource {

    @Inject
    private UserService userService;

    @GET
    @RolesAllowed({"TEACHER"})
    @Transactional
    public List<User> getStudents() {
        return userService.getStudents();
    }

    @POST
    @RolesAllowed({"TEACHER"})
    @Transactional
    public User update(User user) {
        return userService.update(user);
    }

    @POST
    @Path("/join")
    @RolesAllowed({"TEACHER"})
    @Transactional
    public List<User> join(List<User> users) {
        return userService.join(users);
    }

    @POST
    @Path("/split")
    @RolesAllowed({"TEACHER"})
    @Transactional
    public List<User> split(List<User> users) {
        return userService.split(users);
    }

}
