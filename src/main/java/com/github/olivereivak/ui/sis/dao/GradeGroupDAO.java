package com.github.olivereivak.ui.sis.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import com.github.olivereivak.ui.sis.entity.GradeGroup;

public class GradeGroupDAO extends BaseDAO<GradeGroup> {

    public List<GradeGroup> findAll() {
        TypedQuery<GradeGroup> typedQuery = em().createQuery("SELECT g FROM GradeGroup g", GradeGroup.class);
        return typedQuery.getResultList();
    }

}
