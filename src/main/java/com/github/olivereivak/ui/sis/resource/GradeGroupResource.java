package com.github.olivereivak.ui.sis.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.olivereivak.ui.sis.entity.GradeGroup;
import com.github.olivereivak.ui.sis.services.GradeGroupService;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Path("/gradeGroups")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GradeGroupResource extends BaseResource implements IGradeGroupResource {

    @Inject
    private GradeGroupService gradeGroupService;

    @GET
    @RolesAllowed({"STUDENT", "TEACHER"})
    @Transactional
    public List<GradeGroup> getGradeGroups() {
        return gradeGroupService.getGradeGroups();
    }
}
