package com.github.olivereivak.ui.sis.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.olivereivak.ui.sis.dao.GradeGroupDAO;
import com.github.olivereivak.ui.sis.entity.GradeGroup;

@Singleton
public class GradeGroupService {

    @Inject
    private GradeGroupDAO gradeGroupDAO;

    public List<GradeGroup> getGradeGroups() {
        return gradeGroupDAO.findAll();
    }
}
