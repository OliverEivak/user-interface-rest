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

import com.github.olivereivak.ui.sis.entity.Link;

@Path("/links")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ILinkResource {

    @GET
    @RolesAllowed({"STUDENT", "TEACHER"})
    Link getByUserAndGrade(@QueryParam("username") String username, @QueryParam("grade") Long gradeID);

    @POST
    @RolesAllowed({"STUDENT"})
    Link createOrUpdate(Link link);

    @DELETE
    @Path("/{linkID}")
    @RolesAllowed({"STUDENT"})
    void delete(@PathParam("linkID") Long linkID);

}
