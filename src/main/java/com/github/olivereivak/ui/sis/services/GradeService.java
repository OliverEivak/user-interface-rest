package com.github.olivereivak.ui.sis.services;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.olivereivak.ui.sis.dao.GradeDAO;
import com.github.olivereivak.ui.sis.entity.Grade;

@Singleton
public class GradeService {

    @Inject
    private GradeDAO gradeDAO;

    public Grade getByID(Long id) {
        return gradeDAO.findByID(id);
    }
}
