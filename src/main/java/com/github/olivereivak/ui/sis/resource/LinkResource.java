package com.github.olivereivak.ui.sis.resource;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.github.olivereivak.ui.sis.entity.Grade;
import com.github.olivereivak.ui.sis.entity.Link;
import com.github.olivereivak.ui.sis.entity.User;
import com.github.olivereivak.ui.sis.services.GradeService;
import com.github.olivereivak.ui.sis.services.LinkService;
import com.github.olivereivak.ui.sis.services.UserService;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Path("/links")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LinkResource extends BaseResource implements ILinkResource {

    @Inject
    private LinkService linkService;

    @Inject
    private UserService userService;

    @Inject
    private GradeService gradeService;

    @GET
    @RolesAllowed({"STUDENT", "TEACHER"})
    @Transactional
    public Link getByUserAndGrade(@QueryParam("username") String username, @QueryParam("grade") Long gradeID) {
        User loggedInUser = getUser();
        User user = userService.getByUsername(username);
        Grade grade = gradeService.getByID(gradeID);

        return linkService.getByUserAndGrade(user, grade, loggedInUser);
    }

    @POST
    @RolesAllowed({"STUDENT"})
    @Transactional
    public Link createOrUpdate(Link link) {
        if (link.getId() == null) {
            return linkService.create(link, getUser());
        } else {
            return linkService.update(link, getUser());
        }
    }

    @DELETE
    @Path("/{linkID}")
    @RolesAllowed({"STUDENT"})
    @Transactional
    public void delete(@PathParam("linkID") Long linkID) {
        Link link = linkService.getByID(linkID, getUser());
        linkService.delete(link, getUser());
    }
}
