package com.github.olivereivak.ui.sis.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.github.olivereivak.ui.sis.entity.StudentGrade;
import com.github.olivereivak.ui.sis.entity.User;
import com.github.olivereivak.ui.sis.services.StudentGradeService;
import com.github.olivereivak.ui.sis.services.UserService;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Path("/studentGrades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentGradeResource extends BaseResource implements IStudentGradeResource {

    @Inject
    private StudentGradeService studentGradeService;

    @Inject
    private UserService userService;

    @GET
    @RolesAllowed({"STUDENT", "TEACHER"})
    @Transactional
    public List<StudentGrade> getByUser(@QueryParam("user") String username) {
        User loggedInUser = getUser();
        User user = userService.getByUsername(username);
        if (user == null) {
            return studentGradeService.getAll(loggedInUser);
        } else {
            return studentGradeService.getByUser(user, loggedInUser);
        }
    }
}
